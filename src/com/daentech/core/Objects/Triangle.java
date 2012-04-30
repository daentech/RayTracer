package com.daentech.core.Objects;

import com.daentech.core.Constants;
import com.daentech.core.Ray;
import com.daentech.core.ShadeRec;
import com.daentech.core.Vector3D;
import com.daentech.core.ray_hit;

public class Triangle extends GeoObject {

	public Vector3D _a, _b, _c;
	Vector3D normal;
	
	public Triangle(){
		_a = new Vector3D(0, 0, 0);
		_b = new Vector3D(0, 0, 1);
		_c = new Vector3D(1, 0, 0);
		normal = new Vector3D(0, 1, 0);
	}
	
	public Triangle(Vector3D a, Vector3D b, Vector3D c){
		_a = a;
		_b = b;
		_c = c;
		
		set_normal();
	}
	
	public void set_normal(){
		normal = _b.sub(_a).cross(_c.sub(_a));
		normal.normalise();
	}
	
	@Override
	public boolean hit(Ray r, ray_hit rh, ShadeRec sr) {
		double a = _a._x - _b._x, b = _a._x - _c._x, c = r.d._x, d = _a._x - r.o._x;
		double e = _a._y - _b._y, f = _a._y - _c._y, g = r.d._y, h = _a._y - r.o._y;
		double i = _a._z - _b._z, j = _a._z - _c._z, k = r.d._z, l = _a._z - r.o._z;
		
		double m = f * k - g * j, n = h * k - g * l, p = f * l - h * j;
		double q = g * i - e * k, s = e * j - f * i;
		
		double invdenom = 1.0 / (a * m  + b * q + c * s);
		
		double e1 = d * m - b * n - c * p;
		double beta = e1 * invdenom;
		
		if(beta < 0.0)
			return false;
		
		double t = e * l - h * i;
		double e2 = a * n + d * q + c * t;
		double gamma = e2 * invdenom;
		
		if (gamma < 0.0)
			return false;
		
		if (beta + gamma > 1.0)
			return false;
		
		double e3 = a * p - b * t + d * s;
		double u = e3 * invdenom;
		
		if (u < Constants.kEpsilon)
			return false;
		
		rh.t = u;
		sr.normal = normal;
		sr.local_hit_point = r.o.add(r.d.mul(u));
		
		return true;
	}
	
	@Override
	public boolean shadow_hit(Ray r, ray_hit rh){
		double a = _a._x - _b._x, b = _a._x - _c._x, c = r.d._x, d = _a._x - r.o._x;
		double e = _a._y - _b._y, f = _a._y - _c._y, g = r.d._y, h = _a._y - r.o._y;
		double i = _a._z - _b._z, j = _a._z - _c._z, k = r.d._z, l = _a._z - r.o._z;
		
		double m = f * k - g * j, n = h * k - g * l, p = f * l - h * j;
		double q = g * i - e * k, s = e * j - f * i;
		
		double invdenom = 1.0 / (a * m  + b * q + c * s);
		
		double e1 = d * m - b * n - c * p;
		double beta = e1 * invdenom;
		
		if(beta < 0.0)
			return false;
		
		double t = e * l - h * i;
		double e2 = a * n + d * q + c * t;
		double gamma = e2 * invdenom;
		
		if (gamma < 0.0)
			return false;
		
		if (beta + gamma > 1.0)
			return false;
		
		double e3 = a * p - b * t + d * s;
		double u = e3 * invdenom;
		
		if (t < Constants.kEpsilon)
			return false;
		
		rh.t = t;
		
		return true;
	}
	
}
