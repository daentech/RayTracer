package com.daentech.core;

import com.daentech.core.Lights.Directional;
import com.daentech.core.Lights.Point;
import com.daentech.core.Materials.Diffuse;
import com.daentech.core.Materials.Phong;
import com.daentech.core.Objects.Plane;
import com.daentech.core.Objects.Sphere;
import com.daentech.core.Tracers.MultipleObjects;

public class raytracer {
	
	public static void main(String [] args){
		System.out.println("Here");
		
		Scene s = new Scene();
		create_objects(s);
		create_camera(s);
		create_lights(s);

		s.build();
		s.tracer_ptr = new MultipleObjects(s);
		
		s.render_camera();
		
	}
	
	public static void create_objects(Scene s){
		Sphere s1 = new Sphere();
		s1._origin = new Vector3D(10, -5, 0);
		s1._radius = 27;
		Diffuse m = new Diffuse();
		m.set_cd(new Colour(255, 0, 0));
		m.set_ka(0.25);
		m.set_kd(0.65);
		s1.material = m;

		Sphere s2 = new Sphere();
		s2._origin = new Vector3D(0, 20, 30);
		s2._radius = 27;
		Phong p = new Phong();
		p.set_cd(new Colour(255, 255, 0));
		p.set_ka(0.25);
		p.set_kd(0.65);
		p.set_ks(0.2);
		p.set_exp(4);
		s2.material = p;
		
		Plane p1 = new Plane();
		p1._normal = new Vector3D(0, 1, 0);
		p1._point = new Vector3D(0, -20, 0);
		m = new Diffuse();
		m.set_cd(new Colour(0, 255, 255));
		m.set_ka(0.25);
		m.set_kd(0.65);
		p1.material = m;
		// add objects
		s.add_object(s1);
		s.add_object(s2);
		s.add_object(p1);
	}
	
	public static void create_lights(Scene s){
		Point p = new Point();
		p._origin = new Vector3D(10, 50, 150);
		p._direction = new Vector3D();
		p._colour = new Colour(255, 255, 255);
		p._ls = 1.0;
		s.add_light(p);
		
		Directional d = new Directional();
		d._colour = new Colour(255);
		d._direction = new Vector3D(0, 0, 1);
		d._ls = 1.0;
		//s.add_light(d);
	}
	
	public static void create_camera(Scene s){
		s.camera = new Camera();
		s.camera._origin = new Vector3D(-200, 30, 400);
		s.camera._lookat = new Vector3D(0, 0, 0);
		s.camera._up = new Vector3D(0, 1, 0);
		s.camera.hres = 400;
		s.camera.vres = 400;
		s.camera._d = 850;
		
		s.camera.compute_uvw();
	}

}
