package oop;

import java.util.HashSet;

public class Model {


    /**
     * der algorithmus,der die mandelBrotMenge berechent und malt sie
     * @param min
     * @param max
     * @param width
     * @param height
     * @param maxIterations
     * @return pixel hashset
     */
    public HashSet<Pixel> paintAndCalculateMandelBrot(Complex min, Complex max, double width, double height, int maxIterations) { //zu double statt int gemacht

        HashSet<Pixel> pixels = new HashSet<>();

        double minRe = min.getRe();
        double maxRe = max.getRe();
        double minIm = min.getIm();
        double maxIm = max.getIm();


        double xCo = (maxRe - minRe) / (width - 1);
        double yCo = (maxIm - minIm) / (height - 1);

        Complex c = new Complex();
        Complex z;

        for (int i = 0; i < width; i++) {

            c.setRe(minRe + i * xCo);

            for (int j = 0; j < height; j++) {

                c.setIm(maxIm - j * yCo);
                z = new Complex(c.getRe(), c.getIm());
                int numOfIterations = 0;

                while ((numOfIterations < maxIterations) && (z.complexAbs() < 4)){
                    z = (z.complexPow(z)).complexSum(c);//c[i+1]=c[i]^2+c
                    numOfIterations++;
                }
                double quotient = (double)numOfIterations/(double)maxIterations;
                pixels.add(new Pixel(i, j, true, quotient));
            }
        }
        return pixels;
    }
}