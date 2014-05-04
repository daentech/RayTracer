package com.daentech.core.Materials;

import com.daentech.core.BRDFs.SV_Lambertian;
import com.daentech.core.Colour;
import com.daentech.core.Lights.Light;
import com.daentech.core.Ray;
import com.daentech.core.ShadeRec;
import com.daentech.core.Texture.Texture;
import com.daentech.core.Vector3D;

/**
 * Created by dangilbert on 04/05/2014.
 */
public class SV_Diffuse extends Material {

    private SV_Lambertian ambient_brdf = new SV_Lambertian();
    private SV_Lambertian diffuse_brdf = new SV_Lambertian();

    public void set_ka(double k){
        ambient_brdf.kd = k;
    }

    public void set_kd(double k){
        diffuse_brdf.kd = k;
    }

    public void set_cd(Texture t) {
        ambient_brdf.cd = t;
        diffuse_brdf.cd = t;
    }

    @Override
    public Colour shade(ShadeRec sr) {
        Vector3D wo = sr.ray.d.neg();
        Colour L = ambient_brdf.rho(sr, wo).mul(sr.w.ambient_light.L(sr));
        int num_lights = sr.w.lights.size();

        for (int j = 0; j < num_lights; j++) {
            Vector3D wi = sr.w.lights.get(j).get_direction(sr);
            wi.normalise();
            double ndotwi = sr.normal.dot(wi);
            double ndotwo = sr.normal.dot(wo);

            if (ndotwi > 0.0 && ndotwo > 0.0) {
                boolean in_shadow = false;

                if (sr.w.lights.get(j).casts_shadow) {
                    Ray shadow_ray = new Ray(sr.hit_point, wi);
                    in_shadow = sr.w.lights.get(j).in_shadow(shadow_ray, sr);
                }

                if (!in_shadow) {
                    Colour c = diffuse_brdf.f(sr, wi, wo);
                    Colour l = sr.w.lights.get(j).L(sr);
                    l = l.mul(ndotwi);
                    L.add(c.mul(l));
                }
            }
        }
        L.normalise();
        return L;
    }
}
