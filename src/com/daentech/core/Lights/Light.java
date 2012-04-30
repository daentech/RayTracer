package com.daentech.core.Lights;

import com.daentech.core.Colour;
import com.daentech.core.Ray;
import com.daentech.core.ShadeRec;
import com.daentech.core.Vector3D;

public class Light {
	
	public Vector3D _origin;
	public Vector3D _direction;
	public boolean casts_shadow = true;
	
	public double _ls;

	public Vector3D get_direction(ShadeRec sr){
		return new Vector3D();
	}
	
	public Colour L(ShadeRec sr){
		return new Colour();
	}

	public boolean in_shadow(Ray shadow_ray, ShadeRec sr) {
		return false;
	}
	
	
}
