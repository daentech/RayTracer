package com.daentech.core.Materials;

import com.daentech.core.Colour;
import com.daentech.core.ShadeRec;
import com.daentech.core.Vector3D;
import com.daentech.core.BRDFs.Lambertian;

public class Diffuse extends Material {
	
	public Lambertian ambient_brdf = new Lambertian();
	public Lambertian diffuse_brdf = new Lambertian();
	
	public void set_ka(double k){
		ambient_brdf.kd = k;
	}
	
	public void set_kd(double k){
		diffuse_brdf.kd = k;
	}
	
	public void set_cd(Colour c){
		ambient_brdf.cd = c;
		diffuse_brdf.cd = c;
	}
	
	@Override
	public Colour shade(ShadeRec sr){
		sr.ray.d.normalise();
		Vector3D wo = sr.ray.d.neg();
		Colour tmp1 = ambient_brdf.rho(sr, wo);
		Colour tmp2 = sr.w.ambient_light.L(sr);
		Colour L = tmp1.mul(tmp2);
		
		int num_lights = sr.w.lights.size();
		
		for (int j = 0; j < num_lights; j++){
			Vector3D wi = sr.w.lights.get(j).get_direction(sr);
			wi.normalise();
			double ndotwi = sr.normal.dot(wi);
			
			if (ndotwi > 0.0){
				Colour c = diffuse_brdf.f(sr, wi, wo);
				Colour l = sr.w.lights.get(j).L(sr);
				l = l.mul(ndotwi);
				L.add(c.mul(l));
			}
		}
		L.normalise();
		return L;
	}

}
