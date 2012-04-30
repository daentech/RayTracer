package com.daentech.core.Lights;

import com.daentech.core.Colour;
import com.daentech.core.Ray;
import com.daentech.core.ShadeRec;
import com.daentech.core.Vector3D;
import com.daentech.core.ray_hit;

public class Point extends Light {

	public Colour _colour = new Colour(255, 255, 255);
	
	@Override
	public Vector3D get_direction(ShadeRec sr){
		Vector3D v = _origin.sub(sr.hit_point);
		v.normalise();
		return v;
	}
	
	@Override
	public Colour L(ShadeRec sr){
		return _colour.mul(_ls);
	}

	@Override
	public boolean in_shadow(Ray shadow_ray, ShadeRec sr) {
		ray_hit rh = new ray_hit();
		int num_objects = sr.w.objects.size();
		double d = _origin.distance(shadow_ray.o);
		
		for (int j = 0; j < num_objects; j++){
			if(sr.w.objects.get(j).shadow_hit(shadow_ray, rh) && rh.t < d){
				return true;
			}
		}
		return false;
	}
	
}
