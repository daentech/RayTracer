package com.daentech.core.Lights;

import com.daentech.core.Colour;
import com.daentech.core.ShadeRec;
import com.daentech.core.Vector3D;

public class Ambient extends Light{
	
	public Colour colour;
	
	public Ambient(){
		_ls = 1.0;
		colour = new Colour(255, 255, 255);
	}
	
	@Override
	public Vector3D get_direction(ShadeRec sr){
		return new Vector3D();
	}
	
	@Override
	public Colour L(ShadeRec sr){
		return colour.mul(_ls);
	}

}
