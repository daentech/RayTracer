package com.daentech.core.Texture;

import com.daentech.core.Colour;
import com.daentech.core.Mappings.Mapping;
import com.daentech.core.ShadeRec;
import com.daentech.core.Utils.Image;
import com.daentech.core.Vector3D;

/**
 * Created by dangilbert on 04/05/2014.
 */
public class ImageTexture extends Texture {

    public int hres = 640, vres = 480;
    public Image image;
    public Mapping mapping;

    @Override
    public Colour getColour(ShadeRec sr) {

        Vector3D coords = new Vector3D();

        if (mapping != null) {
            coords = mapping.getTexelCoordinates(sr.local_hit_point, hres, vres);
        } else {
            coords._y = (int)(sr.v * (vres -1));
            coords._x = (int)(sr.u * (hres -1));
        }

        return image.getColour((int)coords._x, (int)coords._y);
    }

    public void setImage(Image image) {
        this.image = image;
        hres = image.width;
        vres = image.height;
    }
}
