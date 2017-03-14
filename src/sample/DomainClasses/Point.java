package sample.DomainClasses;

import java.util.Random;

/**
 * Created by jasve_29 on 19-Feb-17.
 */
@SuppressWarnings("DefaultFileTemplate")
public class Point {
    public double x;
    public double y;

    @SuppressWarnings("WeakerAccess")
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static Point getRandom() {
        Random rand = new Random();
        return new Point(rand.nextInt(40)-20, rand.nextInt(40)-20);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
