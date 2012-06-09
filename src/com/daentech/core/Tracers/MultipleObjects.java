package com.daentech.core.Tracers;

import com.daentech.core.Colour;
import com.daentech.core.Ray;
import com.daentech.core.Scene;
import com.daentech.core.ShadeRec;

public class MultipleObjects extends Tracer {
	
	public MultipleObjects(Scene s) {
		super(s);
	}
	
	@Override
	public Colour trace_ray(Ray ray, int depth){
		if (depth > _s.camera.max_depth)
			return new Colour();
		ShadeRec sr = _s.hit_bare_bones_objects(ray);
		
		if (sr.hit_an_object){
			sr.depth = depth;
			sr.ray = ray;
			return sr.material.shade(sr);
		} else
			return _s.background_colour;
	}

}
