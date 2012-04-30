package com.daentech.core.Objects;

import com.daentech.core.Constants;
import com.daentech.core.Ray;
import com.daentech.core.ShadeRec;
import com.daentech.core.Vector3D;
import com.daentech.core.ray_hit;

public class Plane extends GeoObject {
	
	public Vector3D _point;
	public Vector3D _normal;
	
	@Override
	public boolean hit(Ray r, ray_hit rh, ShadeRec sr){
		_normal.normalise();
		double t = (_point.sub(r.o)).dot(_normal) / ((r.d.dot(_normal)));
		
		if (t > Constants.kEpsilon){
			rh.t = t;
			sr.normal = _normal;
			sr.local_hit_point = r.o.add(r.d.mul(t));
			return true;
		} else
			return false;
	}
	
	@Override
	public boolean shadow_hit(Ray r, ray_hit rh){
		_normal.normalise();
		r.d.normalise();
			double t = (_point.sub(r.o)).dot(_normal) / ((r.d.dot(_normal)));
		
		if (t > Constants.kEpsilon){
			rh.t = t;
			return true;
		} else
			return false;
	}
	

}
