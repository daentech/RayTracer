package com.daentech.core;

public class Camera {

	public int hres = 200;
	public int vres = 200;
	public Vector3D _origin;
	public Vector3D _lookat;
	public Vector3D _up;
	public double _d;
	double exposure;
	public int max_depth;
	public int s = 1;
	
	Vector3D u, v, w;
	
	public void compute_uvw(){
		w = _origin.sub(_lookat);
		w.normalise();
		u = _up.cross(w);
		u.normalise();
		v = w.cross(u);
	}
	
	public Vector3D ray_direction(Vector3D p) {
		Vector3D tmp1 = u.mul(p._x);
		Vector3D tmp2 = v.mul(p._y);
		Vector3D tmp3 = w.mul(_d);
		Vector3D dir = tmp1.add(tmp2.sub(tmp3));
		dir.normalise();
		return dir;
	}

}
