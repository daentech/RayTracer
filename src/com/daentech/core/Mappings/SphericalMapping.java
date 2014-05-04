package com.daentech.core.Mappings;

import com.daentech.core.Constants;
import com.daentech.core.Vector3D;

/**
 * Created by dangilbert on 04/05/2014.
 */
public class SphericalMapping extends Mapping {

    @Override
    public Vector3D getTexelCoordinates(Vector3D local_hit_point, int hres, int vres) {

        Vector3D coords = new Vector3D();

        double theta = Math.acos(local_hit_point._y * Constants.PI_ON_180);
        double phi = Math.atan2(local_hit_point._x, local_hit_point._z);
        if (phi < 0.0) {
            phi += Constants.TWO_PI;
        }

        double u = phi * Constants.invTWO_PI;
        double v = 1 - theta * Constants.invPI;

        coords._x = (int)((hres - 1) * u);
        coords._y = (int)((vres - 1) * v);

        return coords;
    }
}
