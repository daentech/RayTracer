package com.daentech.core.Lights;

import com.daentech.core.Colour;
import com.daentech.core.Ray;
import com.daentech.core.ShadeRec;
import com.daentech.core.Vector3D;

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
		return false;
	}
	
}
