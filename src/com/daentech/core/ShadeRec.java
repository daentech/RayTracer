package com.daentech.core;

import com.daentech.core.Materials.Material;

public class ShadeRec {

	public Scene w;
	public boolean hit_an_object;
	public Material material;
	public Vector3D normal;
	public Vector3D local_hit_point;
	public double t;
	public Vector3D hit_point;
	public Ray ray;
	public int depth;
	public Vector3D wi;
	
	
	public ShadeRec(Scene scene) {
		w = scene;
	}

}
