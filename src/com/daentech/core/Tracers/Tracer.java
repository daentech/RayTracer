package com.daentech.core.Tracers;

import com.daentech.core.Colour;
import com.daentech.core.Ray;
import com.daentech.core.Scene;

public class Tracer {
	
	Scene _s;
	
	public Tracer(Scene s){
		_s = s;
	}

	public Colour trace_ray(Ray ray, int depth) {
		return new Colour();
	}

}
