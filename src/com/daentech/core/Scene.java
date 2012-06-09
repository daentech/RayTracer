package com.daentech.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.daentech.core.Lights.Ambient;
import com.daentech.core.Lights.Light;
import com.daentech.core.Objects.GeoObject;
import com.daentech.core.Tracers.Tracer;

public class Scene {
	
	public Colour background_colour;
	public Colour[] image;
	public Tracer	tracer_ptr;
	public Camera	camera;
	public Light	ambient_light;
	
	public ArrayList<GeoObject> objects = new ArrayList<GeoObject>();
	public ArrayList<Light> lights = new ArrayList<Light>();
	
	public Scene(){
		ambient_light = new Ambient();
		
	}
	
	public void build(){
		background_colour = new Colour();
		
	}
	
	public void render_camera(int i){
		Colour L;
		Ray ray = new Ray();
		
		int depth = 0;
		
		Vector3D pp = new Vector3D();
		
		create_image();
		
		ray.o = camera._origin;
		
		for (int r = 0; r < camera.vres; r++){
			for (int c = 0; c < camera.hres; c++){
				pp._x = camera.s * (c - 0.5 * (camera.hres - 1));
				pp._y = camera.s * (r - 0.5 * (camera.vres - 1));
				ray.d = camera.ray_direction(pp);
				L = tracer_ptr.trace_ray(ray, depth);
				display_pixel(r, c, L);
			}
		}
		
		write_image(i);
	}
	
	public void write_image(int num){
		try {
			FileOutputStream fout = new FileOutputStream(new File("images/" + String.format("%05d", num) + ".ppm"));
			OutputStreamWriter osw = new OutputStreamWriter(fout);
			osw.write("P3\n");
			osw.write(camera.hres + " " + camera.vres + "\n");
			osw.write("255\n");
			
			// Write image data in reverse;
			for (int i = camera.vres - 1; i >= 0; i--){
				for (int j = camera.hres - 1; j >= 0; j--){
					Colour c = image[i * camera.hres + j];
					osw.write(Math.min((int)c._r, 255) + " " + Math.min((int)c._g, 255) + " " + Math.min((int)c._b, 255));
					if (j == 0)
						osw.write("\n");
					else
						osw.write(" ");
				}
			}
			
			
			osw.flush();
			osw.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void create_image(){
		image = new Colour[camera.hres * camera.vres];
	}
	
	public void display_pixel(int row, int col, Colour pixel){
		image[row * camera.hres + col] = pixel;
	}
	
	public void add_object(GeoObject o){
		objects.add(o);
	}
	
	public void add_light(Light l){
		lights.add(l);
	}
	
	public ShadeRec hit_bare_bones_objects(Ray r){
		ShadeRec sr = new ShadeRec(this);
		ray_hit rh = new ray_hit();
		Vector3D normal = new Vector3D();
		Vector3D local_hit_point = new Vector3D();
		double tmin = Constants.kHugeValue;
		int num_objects = objects.size();
		
		for (int j = 0; j < num_objects; j++){
			if(objects.get(j).hit(r, rh, sr) && rh.t < tmin){
				sr.hit_an_object = true;
				tmin = rh.t;
				sr.material = objects.get(j).get_material();
				sr.hit_point = r.o.add(r.d.mul(rh.t));
				normal = sr.normal;
				local_hit_point = sr.local_hit_point;				
			}
		}
		
		if (sr.hit_an_object){
			sr.t = tmin;
			sr.normal = normal;
			sr.local_hit_point = local_hit_point;
		}
		return sr;
	}

}
