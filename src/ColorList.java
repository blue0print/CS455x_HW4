// Name: Ismayil Adigozalzada
// USC NetID: 3786609877
// CSCI 455 HW#4
// Summer 2021

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class ColorList {
    // 'colList' is the color list we are going to fill and keep. Because it is
    // public, if someone wants a color list (of 256 random colors), they just do:
    // ColorList colors = new ColorList(); // 'colors' will now contain 256 random colors!
    // To access a specific color (eg. the color at index 12), the user just does:
    // Color aColor = colors.colList.get(12); // .get() is a method of ArrayList
    // Search for 'Java ArrayList class' to get more info on using the templated ArrayList class.
    public ArrayList <java.awt.Color> colList = new ArrayList <java.awt.Color> ();

    ColorList() {
        Random rng = new Random(); // rand num generator

        /*
          loop 256 times:
            create r,g,b as random values between 0..255
            create a color using Color c = new ...
            add the color 'c' to the list
          next
       */
        for (int i=0; i<256; i++) {
            int randomR = rng.nextInt(256);
            int randomG = rng.nextInt(256);
            int randomB = rng.nextInt(256);
            //int rndColorNum = rng.nextInt(256);
            Color rndColor = new Color(randomR,randomG,randomB);
            colList.add(rndColor);
        }

    }// constructor
}// ColorList
