package com.daentech.core.BRDFs;

import com.daentech.core.Colour;
import com.daentech.core.ShadeRec;
import com.daentech.core.Vector3D;

public class BRDF {
	
	public Colour f(ShadeRec sr, Vector3D wi, Vector3D wo){
		return new Colour();
	}
	
	public Colour rho(ShadeRec sr, Vector3D wo){
		return new Colour();
	}

	public Colour sample_f(ShadeRec sr, Vector3D wo, Vector3D wi) {
		return new Colour();
	}

}
