package com.daentech.core.Lights;

import com.daentech.core.Colour;
import com.daentech.core.Ray;
import com.daentech.core.ShadeRec;
import com.daentech.core.Vector3D;
import com.daentech.core.ray_hit;

public class Directional extends Light {

	public Colour _colour = new Colour(255, 255, 255);
	public Vector3D _direction = new Vector3D();
	
	
	public void set_direction(Vector3D v){
		v.normalise();
		_direction = v;
	}
	
	@Override
	public Vector3D get_direction(ShadeRec sr){
		
		return _direction;
	}
	
	@Override
	public Colour L(ShadeRec sr){
		return _colour.mul(_ls);
	}
	
	@Override
	public boolean in_shadow(Ray shadow_ray, ShadeRec sr) {
		ray_hit rh = new ray_hit();
		int num_objects = sr.w.objects.size();
		double d = Double.MAX_VALUE;
		
		for (int j = 0; j < num_objects; j++){
			if(sr.w.objects.get(j).shadow_hit(shadow_ray, rh) && rh.t < d){
				return true;
			}
		}
		return false;
	}
	
}
