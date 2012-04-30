package com.daentech.core.Materials;

import com.daentech.core.Colour;
import com.daentech.core.Ray;
import com.daentech.core.ShadeRec;
import com.daentech.core.Vector3D;
import com.daentech.core.BRDFs.Lambertian;
import com.daentech.core.BRDFs.Specular;

public class Phong extends Material {

	public Lambertian ambient_brdf = new Lambertian();
	public Lambertian diffuse_brdf = new Lambertian();
	public Specular specular_brdf = new Specular();

	@Override
	public Colour shade(ShadeRec sr) {
		sr.ray.d.normalise();
		Vector3D wo = sr.ray.d.neg();
		Colour tmp1 = ambient_brdf.rho(sr, wo);
		Colour tmp2 = sr.w.ambient_light.L(sr);
		Colour L = tmp1.mul(tmp2);

		int num_lights = sr.w.lights.size();

		for (int j = 0; j < num_lights; j++) {
			Vector3D wi = sr.w.lights.get(j).get_direction(sr);
			wi.normalise();
			double ndotwi = sr.normal.dot(wi);

			if (ndotwi > 0.0) {
				boolean in_shadow = false;

				if (sr.w.lights.get(j).casts_shadow) {
					Ray shadow_ray = new Ray(sr.hit_point, wi);
					in_shadow = sr.w.lights.get(j).in_shadow(shadow_ray, sr);
				}

				if (!in_shadow) {
					Colour c = diffuse_brdf.f(sr, wi, wo);
					Colour s = specular_brdf.f(sr, wi, wo);
					c.add(s);
					Colour l = sr.w.lights.get(j).L(sr);
					l = l.mul(ndotwi);

					L.add(c.mul(l));
				}
			}
		}
		L.normalise();
		return L;
	}

	public void set_cd(Colour colour) {
		ambient_brdf.cd = colour;
		diffuse_brdf.cd = colour;
		specular_brdf.cs = colour;

	}

	public void set_ka(double d) {
		ambient_brdf.kd = d;
	}

	public void set_kd(double d) {
		diffuse_brdf.kd = d;
	}

	public void set_ks(double d) {
		specular_brdf.ks = d;
	}

	public void set_exp(double e) {
		specular_brdf.exp = e;
	}

}
