package com.daentech.core.BRDFs;

import com.daentech.core.Colour;
import com.daentech.core.ShadeRec;
import com.daentech.core.Texture.Texture;
import com.daentech.core.Vector3D;

public class SV_PerfectSpecular extends BRDF {

	public Colour cr;
	public double kr;
	
	@Override
	public Colour sample_f(ShadeRec sr, Vector3D wo, Vector3D wi){
		double ndotwo = sr.normal.dot(wo);
		sr.wi = sr.normal.mul(ndotwo * 2.0).sub(wo); // calculate the exit angle
		
		return cr.mul(kr / 255).div(sr.normal.dot(sr.wi));
	}
	
}
