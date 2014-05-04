package com.daentech.core.Utils;

import com.daentech.core.Colour;
import java.io.IOException;

/**
 * Created by dangilbert on 04/05/2014.
 */
public class Image {

    public Colour[] pixels;

    public int width;
    public int height;

    public Image(int width, int height) {
        pixels = new Colour[width * height];
        this.width = width;
        this.height = height;
    }

    public void setPixel(int pos, Colour pixel) {
        pixels[pos] = pixel;
    }

    public Colour getColour(int col, int row) {
        return pixels[row * width + col];
    }

    public static Image fromPPM3(String filename) {
        Image i = new Image(0, 0);
        try {
            i.pixels = PPM.readImage(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static Image fromPPM6(String filename) {
        Image i = new Image(0, 0);
        PPM6 ppm;
        try {
            ppm = new PPM6(filename);
            i.width = ppm.getWidth();
            i.height = ppm.getHeight();
            i.pixels = ppm.getPixels();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }
}
