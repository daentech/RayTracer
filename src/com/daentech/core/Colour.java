package com.daentech.core;

public class Colour {

	double _r, _g, _b;

	public Colour() {
		_r = 0;
		_g = 0;
		_b = 0;
	}

	public Colour(double r, double g, double b) {
		_r = r;
		_g = g;
		_b = b;
	}

	public Colour(double d) {
		_r = d;
		_g = d;
		_b = d;
	}

	public void add(Colour tr) {
		this._r += tr._r;
		this._g += tr._g;
		this._b += tr._b;
	}

	public Colour mul(double kd) {
		return new Colour(this._r * kd, this._g * kd,
				this._b * kd);
	}

	public Colour mul(Colour l) {
		double r = this._r * l._r;
		double g = this._g * l._g;
		double b = this._b * l._b;

		return new Colour(r, g, b);
	}

	public void normalise() {
		double val;
		if (_r > _g) {
			if (_r > _b) {
				// r is the biggest
				val = _r;
			} else {
				// b is the biggest
				val = _b;
			}
		} else {
			if (_g > _b) {
				// g is the biggest
				val = _g;
			} else {
				// b is the biggest
				val = _b;
			}
		}
		if (val <= 255){
			return;
		}
		if (val == 0) {
			_r = 0;
			_g = 0;
			_b = 0;
		} else {
			_r = _r / val * 255;
			_g = _g / val * 255;
			_b = _b / val * 255;
		}
	}

	public Colour div(double kd) {
		return new Colour(this._r / kd, this._g / kd,
				this._b / kd);
	}

}
