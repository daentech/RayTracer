package com.daentech.core.Utils;

import com.daentech.core.Colour;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by dangilbert on 04/05/2014.
 */
public class PPM {
    /*
   PPM contains methods that

    readImage - read a ppm image file into an array of Colour

   Author: Michael Eckmann
   Skidmore College
   Spring 2012

*/
    public static Colour[] readImage(String fName) throws IOException {
        File theFile = new File(fName);
        Scanner scan = new Scanner(theFile);
        int i = 0, j = 0;
        int tokenCounter = 0;
        int cols = 0;
        int rows = 0;
        StringTokenizer tokens = null;

        outloop:
        while (scan.hasNextLine()) {
            String aLine = scan.nextLine();
            if (aLine.charAt(0) == '#')
                continue;

            // process the line:
            tokens = new StringTokenizer(aLine, " ");
            while (tokens.hasMoreTokens()) {
                tokenCounter++;
                String aToken = tokens.nextToken();
                if (tokenCounter == 1) {
                    // expect that aToken is "P3"
                    if (!aToken.equals("P3")) System.out.println("Expected P3 magic number, got " + aToken);
                }
                if (tokenCounter == 2) {
                    // expect that aToken is the number of columns
                    cols = Integer.parseInt(aToken);
                }
                if (tokenCounter == 3) {
                    // expect that aToken is the number of rows
                    rows = Integer.parseInt(aToken);
                }
                if (tokenCounter == 4) {
                    // expect that aToken is the maximum value
                    // ignore
                    break outloop;
                }

            }

        }

        System.out.println("Loading image: " + theFile + " with size: (" + cols + "," + rows + ")");
        Colour outImage[] = new Colour[rows * cols];

        // still worry if more tokens in previously read line

        int channelCount = 0;
        int redVal = 0, blueVal = 0, greenVal = 0;
        while (scan.hasNextLine()) {
            String aLine = scan.nextLine();
            if (aLine.charAt(0) == '#')
                continue;

            // process the line:
            tokens = new StringTokenizer(aLine, " ");
            while (tokens.hasMoreTokens()) {
                String aToken = tokens.nextToken();
                if (channelCount == 0) {
                    redVal = Integer.parseInt(aToken);
                    channelCount++;
                } else if (channelCount == 1) {
                    // expect that aToken is the number of columns
                    greenVal = Integer.parseInt(aToken);
                    channelCount++;
                } else if (channelCount == 2) {
                    // expect that aToken is the number of rows
                    blueVal = Integer.parseInt(aToken);
                    outImage[i * cols + j] = new Colour(redVal, greenVal, blueVal);
                    j++;
                    if (j >= cols) {
                        i++;
                        j = 0;
                    }
                    channelCount = 0;

                }
            }

        }

        return outImage;
    }


}
