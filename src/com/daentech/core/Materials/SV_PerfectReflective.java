package com.daentech.core.Materials;

import com.daentech.core.BRDFs.SV_PerfectSpecular;
import com.daentech.core.Colour;
import com.daentech.core.Ray;
import com.daentech.core.ShadeRec;
import com.daentech.core.Texture.Texture;
import com.daentech.core.Vector3D;

public class SV_PerfectReflective extends SV_Phong {

	SV_PerfectSpecular reflective = new SV_PerfectSpecular();
	
	@Override
	public Colour shade(ShadeRec sr){
		Colour L = super.shade(sr);
		
		Vector3D wo = sr.ray.d.neg();
		Vector3D wi = new Vector3D();
		
		Colour fr = reflective.sample_f(sr, wo, wi);
		Ray reflected_ray = new Ray(sr.hit_point, sr.wi);
		
		L.add(fr.mul(sr.w.tracer_ptr.trace_ray(reflected_ray, sr.depth + 1)).mul(sr.normal.dot(sr.wi)));
		return L;
	}

	public void set_kr(double d) {
		reflective.kr = d;
	}
	
	public void set_cr(Colour c){
		reflective.cr = c;
	}
	
}
