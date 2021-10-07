package oop;

public class Pixel {

    private final int x;
    private final int y;
    private double quotient;
    private boolean paint;

    public Pixel(int x, int y, boolean paint, double quotient) {
        this.x = x;
        this.y = y;
        this.paint = paint;
        this.quotient = quotient;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getQuotient() {
        return quotient;
    }

    public boolean toPaint() {
        return paint;
    }
}
