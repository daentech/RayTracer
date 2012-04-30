package com.daentech.core.Objects;

import com.daentech.core.Ray;
import com.daentech.core.ShadeRec;
import com.daentech.core.ray_hit;
import com.daentech.core.Materials.Material;

public class GeoObject {
	
	public Material material;

	public boolean hit(Ray r, ray_hit rh, ShadeRec sr) {
		return false;
	}

	public Material get_material() {
		return this.material;
	}

	public boolean shadow_hit(Ray r, ray_hit rh) {
		return false;
	}

}
