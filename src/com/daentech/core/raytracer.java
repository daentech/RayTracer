package com.daentech.core;

import com.daentech.core.Lights.Directional;
import com.daentech.core.Lights.Point;
import com.daentech.core.Mappings.SphericalMapping;
import com.daentech.core.Materials.*;
import com.daentech.core.Objects.Circle;
import com.daentech.core.Objects.Plane;
import com.daentech.core.Objects.Sphere;
import com.daentech.core.Objects.Triangle;
import com.daentech.core.Texture.ImageTexture;
import com.daentech.core.Tracers.MultipleObjects;
import com.daentech.core.Utils.Image;

public class raytracer {

	public static void main(String[] args) {

		Scene s = new Scene();
		create_objects(s);

		create_lights(s);
		int frames = 1;
		for (int i = 0; i < frames; i++) {
			create_camera(s, i);
			s.build();
			s.tracer_ptr = new MultipleObjects(s);

			s.render_camera(i);
			System.out.println("Done rendering frame " + (i + 1) + " of " + frames);
		}
		System.out.println("Done!");
	}

	public static void create_objects(Scene s) {
		Sphere s1 = new Sphere();
		s1._origin = new Vector3D(10, -5, 0);
		s1._radius = 27;
		Diffuse m = new Diffuse();
		m.set_cd(new Colour(255, 0, 0));
		m.set_ka(0.25);
		m.set_kd(0.65);
		s1.material = m;

		PerfectReflective r2 = new PerfectReflective();
		r2.set_cd(new Colour(192, 34, 10));
		r2.set_ka(0.25);
		r2.set_kd(0.5);
		r2.set_ks(0.15);
		r2.set_exp(100);
		r2.set_kr(0.55);
		r2.set_cr(new Colour(255));

        SV_Diffuse d1 = new SV_Diffuse();
        ImageTexture tex1 = new ImageTexture();
        tex1.mapping = new SphericalMapping();
        tex1.setImage(Image.fromPPM6("textures/EarthHighRes.ppm"));

        d1.set_ka(0.25);
        d1.set_kd(0.75);
        d1.set_cd(tex1);

        SV_PerfectReflective d2 = new SV_PerfectReflective();
        ImageTexture tex2 = new ImageTexture();
        tex2.mapping = new SphericalMapping();
        tex2.setImage(Image.fromPPM6("textures/EarthHighRes.ppm"));

        d2.set_ka(0.25);
        d2.set_kd(0.75);
        d2.set_cd(tex2);
        d2.set_ks(0.15);
        d2.set_exp(100);
        d2.set_kr(0.55);
        d2.set_cr(new Colour(255));

		Sphere s2 = new Sphere();
		s2._origin = new Vector3D(0, 20, 30);
		s2._radius = 30;
		s2.material = d1;
		Sphere s4 = new Sphere();
		s4._origin = new Vector3D(0, 20, 90);
		s4._radius = 30;
		s4.material = r2;
		Sphere s5 = new Sphere();
		s5._origin = new Vector3D(-60, 20, 90);
		s5._radius = 30;
		s5.material = r2;
		Sphere s6 = new Sphere();
		s6._origin = new Vector3D(-60, 20, 150);
		s6._radius = 30;
		s6.material = r2;
		Sphere s7 = new Sphere();
		s7._origin = new Vector3D(0, 20, 150);
		s7._radius = 30;
		s7.material = d2;
		Sphere s8 = new Sphere();
		s8._origin = new Vector3D(-60, 20, -30);
		s8._radius = 30;
		s8.material = r2;
		Sphere s9 = new Sphere();
		s9._origin = new Vector3D(0, 20, -30);
		s9._radius = 30;
		s9.material = r2;

		Plane p1 = new Plane();
		p1._normal = new Vector3D(0, 1, 0);
		p1._point = new Vector3D(0, -20, 0);
		PerfectReflective r3 = new PerfectReflective();
		r3.set_cd(new Colour(10, 34, 192));
		r3.set_ka(0.25);
		r3.set_kd(0.5);
		r3.set_ks(0.15);
		r3.set_exp(100);
		r3.set_kr(0.55);
		r3.set_cr(new Colour(200));
		p1.material = r3;

		Plane pl2 = new Plane();
		pl2._normal = new Vector3D(2, 0, 1);
		pl2._point = new Vector3D(-30, 0, 0);
		PerfectReflective r4 = new PerfectReflective();
		r4.set_cd(new Colour(200, 134, 0));
		r4.set_ka(0.25);
		r4.set_kd(0.5);
		r4.set_ks(0.15);
		r4.set_exp(100);
		r4.set_kr(0.55);
		r4.set_cr(new Colour(200));
		pl2.material = r4;

		Plane pl3 = new Plane();
		pl3._normal = new Vector3D(-2, 0, 1);
		pl3._point = new Vector3D(-30, 0, 0);
		PerfectReflective r5 = new PerfectReflective();
		r5.set_cd(new Colour(0, 134, 110));
		r5.set_ka(0.25);
		r5.set_kd(0.5);
		r5.set_ks(0.15);
		r5.set_exp(100);
		r5.set_kr(0.55);
		r5.set_cr(new Colour(200));
		pl3.material = r5;

		Triangle t1 = new Triangle();
		t1._a = new Vector3D(-100, 0, 0);
		t1._b = new Vector3D(100, 0, 0);
		t1._c = new Vector3D(0, 50, -30);
		t1.set_normal();
		Phong p2 = new Phong();
		p2.set_cd(new Colour(255, 0, 255));
		p2.set_ka(0.25);
		p2.set_kd(0.65);
		p2.set_ks(0.1);
		p2.set_exp(4);
		t1.material = p2;

		Sphere s3 = new Sphere();
		s3._origin = new Vector3D(-60, 20, 30);
		s3._radius = 30;
		s3.material = r2;

		Circle c1 = new Circle();
		c1._normal = new Vector3D(0, 1, 0.3);
		c1._point = new Vector3D(0, 20, -30);
		c1._radius = 40;
		Phong p3 = new Phong();
		p3.set_cd(new Colour(0, 255, 0));
		p3.set_ka(0.25);
		p3.set_kd(0.65);
		p3.set_ks(0.1);
		p3.set_exp(4);
		c1.material = p3;
		// add objects
		s.add_object(s1);
		s.add_object(s2);
		s.add_object(p1);
		s.add_object(pl2);
		s.add_object(pl3);
		s.add_object(t1);
		s.add_object(s3);
		s.add_object(s4);
		s.add_object(s5);
		s.add_object(s6);
		s.add_object(s7);
		s.add_object(s8);
		s.add_object(s9);
		// s.add_object(c1);
	}

	public static void create_lights(Scene s) {
		Point p = new Point();
		p._origin = new Vector3D(10, 100, 150);
		p._direction = new Vector3D();
		p._colour = new Colour(255, 255, 255);
		p._ls = 1.0;
		s.add_light(p);

		Directional d = new Directional();
		d._colour = new Colour(255);
		d._direction = new Vector3D(0, 1, 1);
		d._ls = 0.5;
		s.add_light(d);
	}

	public static void create_camera(Scene s, int i) {
		s.camera = new Camera();
		s.camera._origin = new Vector3D(i - 100, 200, 300);
		s.camera._lookat = new Vector3D(0, 0, 90);
		s.camera._up = new Vector3D(0, 1, 0);
		s.camera.hres = 640;
		s.camera.vres = 480;
		s.camera._d = 850;
		s.camera.max_depth = 10;
		s.camera.s = 1;

		s.camera.compute_uvw();
	}

}
