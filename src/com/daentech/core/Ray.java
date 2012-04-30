package com.daentech.core;

public class Ray {
	
	public Ray(Vector3D hit_point, Vector3D wi) {
		this.o = hit_point;
		this.d = wi;
	}
	public Ray() {
		// TODO Auto-generated constructor stub
	}
	public Vector3D o;
	public Vector3D d;

}
