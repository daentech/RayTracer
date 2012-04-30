package com.daentech.core.Objects;

import com.daentech.core.Constants;
import com.daentech.core.Ray;
import com.daentech.core.ShadeRec;
import com.daentech.core.Vector3D;
import com.daentech.core.ray_hit;

public class Circle extends Plane{
	
	public double _radius;
	
	@Override
	public boolean hit(Ray r, ray_hit rh, ShadeRec sr) {
		double t;
		Vector3D temp = r.o.sub(_point);
		double a = r.d.dot(r.d);
		double b = temp.mul(2.0).dot(r.d);
		double c = temp.dot(temp) - _radius * _radius;
		
		double disc = b * b - 4.0 * a * c;
		
		if (disc < 0.0)
			return false;
		else {
			double e = Math.sqrt(disc);
			double denom = 2.0 * a;
			t = (-b - e) / denom;
			
			if (t > Constants.kEpsilon){
				rh.t = t;
				sr.normal = _normal;
				sr.local_hit_point = r.o.add(r.d.mul(t));
				return true;
			}
			t = (-b + e) / denom;
			
			if (t > Constants.kEpsilon){
				rh.t = t;
				sr.normal = _normal;
				sr.local_hit_point = r.o.add(r.d.mul(t));
				return true;
			}
			return false;
		}
	}
	
	@Override
	public boolean shadow_hit(Ray r, ray_hit rh) {
		double t;
		Vector3D temp = r.o.sub(_point);
		double a = r.d.dot(r.d);
		double b = temp.mul(2.0).dot(r.d);
		double c = temp.dot(temp) - _radius * _radius;
		
		double disc = b * b - 4.0 * a * c;
		
		if (disc < 0.0)
			return false;
		else {
			double e = Math.sqrt(disc);
			double denom = 2.0 * a;
			t = (-b - e) / denom;
			
			if (t > Constants.kEpsilon){
				rh.t = t;
				return true;
			}
			t = (-b + e) / denom;
			
			if (t > Constants.kEpsilon){
				rh.t = t;
				return true;
			}
			return false;
		}
	}
	

}
