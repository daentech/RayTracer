package com.daentech.core.Texture;

import com.daentech.core.Colour;
import com.daentech.core.ShadeRec;

/**
 * Created by dangilbert on 04/05/2014.
 */
public class ConstantColour extends Texture {

    private Colour colour;

    public void setColour(Colour c) {
        colour = c;
    }

    @Override
    public Colour getColour(ShadeRec sr) {
        return colour;
    }
}
