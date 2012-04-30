package com.daentech.core;

public class Vector3D {
	
	public double _x;
	public double _y;
	public double _z;

	public Vector3D(){
		_x = 0;
		_y = 0;
		_z = 0;
	}
	
	public Vector3D(double x, double y, double z) {
		_x = x;
		_y = y;
		_z = z;
	}

	public Vector3D mul(double t) {
		Vector3D v = new Vector3D();
		v._x = this._x * t;
		v._y = this._y * t;
		v._z = this._z * t;
		
		return v;
	}

	public Vector3D add(Vector3D o) {
		Vector3D v = new Vector3D();
		v._x = this._x + o._x;
		v._y = this._y + o._y;
		v._z = this._z + o._z;
		
		return v;
	}

	public Vector3D sub(Vector3D o) {
			Vector3D v = new Vector3D();
			v._x = this._x - o._x;
			v._y = this._y - o._y;
			v._z = this._z - o._z;
			
			return v;
	}

	public void normalise() {
		double magnitude = Math.sqrt(Math.pow(this._x, 2) + Math.pow(this._y, 2) + Math.pow(this._z, 2));
		this._x /= magnitude;
		this._y /= magnitude;
		this._z /= magnitude;
		
	}

	public Vector3D cross(Vector3D w) {
		double x = this._y * w._z - this._z * w._y;
		double y = this._z * w._x - this._x * w._z;
		double z = this._x * w._y - this._y * w._x;
		return new Vector3D(x, y, z);
	}

	public double dot(Vector3D d) {
		return this._x * d._x + this._y * d._y + this._z * d._z;
	}

	public Vector3D div(double t) {
		Vector3D v = new Vector3D();
		v._x = this._x / t;
		v._y = this._y / t;
		v._z = this._z / t;
		
		return v;
	}

	public Vector3D neg() {
		double x = -this._x;
		double y = -this._y;
		double z = -this._z;
		return new Vector3D(x, y, z);
	}

	public double distance(Vector3D o) {
		Vector3D v = new Vector3D();
		v._x = o._x - this._x;
		v._y = o._y - this._y;
		v._z = o._z - this._z;

		return Math.sqrt(Math.pow(v._x, 2) + Math.pow(v._y, 2) + Math.pow(v._z, 2));
	}

}
