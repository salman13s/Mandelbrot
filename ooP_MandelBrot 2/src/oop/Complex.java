package oop;

public class Complex {

    private double re;
    private double im;


    public Complex() {

    }

    /**
     * der konsturktor erzeugt komplexe Zahlen mit den double werten
     * @param re
     * @param im
     */
    public Complex(double re, double im) {

        this.re = re;
        this.im = im;
    }


    /**
     * brechnet den komplexen Betrag
     * @return double
     */
    public double complexAbs() {
        return (Math.pow(re, 2) + Math.pow(im, 2));
    }

    /**
     * brechnet die Summe von 2 komplexen Zahlen
     * @param c2
     * @return Complex
     */
    public Complex complexSum(Complex c2) {

        Complex sum = new Complex(0, 0);

        sum.setRe((this.getRe() + c2.getRe()));
        sum.setIm(this.getIm() + c2.getIm());

        return sum;
    }

    /**
     * quadriert die komplexe Zahl
     * @param c
     * @return Complex
     */
    public Complex complexPow(Complex c) {

        Complex res = new Complex(0, 0);

        res.setRe((c.getRe() * c.getRe()) - (c.getIm() * c.getIm()));
        res.setIm(2 * c.getRe() * c.getIm());

        return res;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    public void setRe(double re) {
        this.re = re;
    }

    public void setIm(double im) {
        this.im = im;
    }


    public String toString() {

        return re + "+" + im + "i";
    }
}
