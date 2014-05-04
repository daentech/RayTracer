package com.daentech.core.BRDFs;

import com.daentech.core.Colour;
import com.daentech.core.Constants;
import com.daentech.core.ShadeRec;
import com.daentech.core.Texture.Texture;
import com.daentech.core.Vector3D;

/**
 * Created by dangilbert on 04/05/2014.
 */
public class SV_Lambertian extends BRDF {

    public double kd;
    public Texture cd;

    @Override
    public Colour rho(ShadeRec sr, Vector3D wo) {
        return (cd.getColour(sr).mul(kd/255));
    }

    @Override
    public Colour f(ShadeRec sr, Vector3D wo, Vector3D wi) {
        return (cd.getColour(sr).mul(kd/255));
    }

}
