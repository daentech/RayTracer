package com.daentech.core.BRDFs;

import com.daentech.core.Colour;
import com.daentech.core.ShadeRec;
import com.daentech.core.Vector3D;

public class Lambertian extends BRDF {

	public double kd;
	public Colour cd;
	
	@Override
	public Colour f(ShadeRec sr, Vector3D wi, Vector3D wo){
		return cd.mul(kd/255);
	}
	
	@Override
	public Colour rho(ShadeRec sr, Vector3D wi){
		return cd.mul(kd/255);
	}
	
}
