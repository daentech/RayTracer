package com.daentech.core.BRDFs;

import com.daentech.core.Colour;
import com.daentech.core.ShadeRec;
import com.daentech.core.Texture.Texture;
import com.daentech.core.Vector3D;

public class SV_Specular extends BRDF {
	
	
	public Texture cs;
	public double ks;
	public double exp;
	
	@Override
	public Colour f(ShadeRec sr, Vector3D wi, Vector3D wo){
		Colour L = new Colour();
		double ndotwi = sr.normal.dot(wi);
		Vector3D r = (wi.neg().add(sr.normal.mul(2.0 * ndotwi)));
		double rdotwo = r.dot(wo);
		
		if (rdotwo > 0.0){
			L = new Colour(ks * Math.pow(rdotwo,  exp));
		}
		return L;
	}
	
	public Colour rho(ShadeRec sr, Vector3D wo){
		return new Colour();
	}

}
