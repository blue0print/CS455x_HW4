// Name: Ismayil Adigozalzada
// USC NetID: 3786609877
// CSCI 455 HW#4
// Summer 2021

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Mandelbrot {

    String filename;
    int xRes, yRes;
    double cReal, cImag, cxMin, cxMax, cyMin, cyMax;
    int[][] xtable;

    public Mandelbrot(int xRes, int yRes, double cxMin, double cxMax, double cyMin, double cyMax) {
        this.xRes = xRes;
        this.yRes = yRes;
        this.cxMin = cxMin;
        this.cxMax = cxMax;
        this.cyMin = cyMin;
        this.cyMax = cyMax;
        double cReal = 0, cImag = 0;
        filename = "mandelbrot_fractal.ppm";

        ColorList colors = new ColorList();

        // each element of the above array points to a 'row' of xRes elements
        xtable = new int[yRes][xRes];
        for(int y=0; y<yRes; y++){
            for(int x=0; x<xRes; x++){
                double yD = y;
                double xD = x;
                cReal = cxMin + xD / (xRes - 1.0) * (cxMax - cxMin);
                cImag = cyMin + yD / (yRes - 1.0) * (cyMax - cyMin);
                ComplexNumber c = new ComplexNumber(cReal,cImag);
                ComplexNumber z = new ComplexNumber(0,0);

                int maxIter=250, iter=0, val=0;

                while (iter < maxIter && z.abs(z) < 2) {
                    z = c.add(z.squared(z));
                    //z.prt(z); // *TEST*
                    if (z.abs(z) >= 2) {
                        val = iter;
                    } else {
                        val = 0;
                    }
                    iter++;
                }
                //System.out.println(val+"\n"); // *TEST*
                xtable[y][x]=val; //
            }
        }
    } // Mandelbrot constructor

    public void setPixel(Graphics g, int y, int x, int red, int grn, int blu) {
        Color c = new Color(red,grn,blu);
        g.setColor(c);
        g.drawLine(x,y,x,y);
    }// setPixel()

    public void displayFractal(Graphics g, ColorList colors) {
        Color c = null;
        for(int y=0; y<yRes; y++) {
            for (int x = 0; x < xRes; x++) {
                c = colors.colList.get(xtable[y][x]);
                setPixel(g,y,x,c.getRed(),c.getGreen(),c.getBlue());
            } // next x
        } // nxt y
    }// displayFractal()

    public void saveFractal(String filename, ColorList colors) throws FileNotFoundException {
        File f = new File(filename);
        FileOutputStream fout = new FileOutputStream(f);
        PrintStream out = new PrintStream(fout);

        // header
        out.println("P3\r\n" + xRes + " " + yRes + "\r\n255\r\n"); // P2 xres yres maxval

        Color c = null;
        for(int y=0; y<yRes; y++) {
            for (int x = 0; x < xRes; x++) {
                c = colors.colList.get(xtable[y][x]);
                out.println(c.getRed() + " " + c.getGreen() + " " + c.getBlue());
            } // next x
        } // nxt y
        out.close();
    }// saveFractal()

    public static void main(String[] args) throws FileNotFoundException {

        final double CXMIN = -2.0, CXMAX = 1.0, CYMIN = -1.0,  CYMAX = 1.0;

        JFrame frame = new JFrame();

        frame.setSize(650,490);
        frame.setTitle("Mandelbrot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ColorList colors = new ColorList(); // generate colors, to use for populating strips below

        // create 256 colored panels, add them to the frame
        JPanel p;
        Color c;

        c = Color.WHITE; // *TEST*
        //System.out.println(c.toString()); // *TEST*

        p = new JPanel();
        p.setBackground(c); // color our panel
        p.setPreferredSize(new Dimension(650,490)); // set the dimensions of our panel
        frame.add(p); // pop the panel into the frame

        BufferedImage pb1 = new BufferedImage(650,490,BufferedImage.TYPE_INT_RGB);

        JLabel pi1 = new JLabel(new ImageIcon(pb1));
        pi1.setPreferredSize(new Dimension(650,490));
        pi1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        p.add(pi1);

        Graphics g1 = pb1.createGraphics();

        Mandelbrot mand = new Mandelbrot(640,480,CXMIN,CXMAX,CYMIN,CYMAX);
        mand.displayFractal(g1,colors);
        mand.saveFractal(mand.filename,colors);

        // all done
        frame.setVisible(true); // show to the world :)
        frame.setResizable(false);
        frame.pack(); // arrange all the panels
        frame.setVisible(true);

    }
}
