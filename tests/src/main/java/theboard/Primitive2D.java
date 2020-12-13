package theboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author truebondar
 */

//Fully checked Kappa
public class Primitive2D {

    public final static int MAX_POINTS = Integer.MAX_VALUE;

    private Color color;
    private int thikness;
    private List<Point> points = new ArrayList<Point>();

    public Primitive2D(Color color, int thickness) {
            this.color = color;
            this.thikness = thickness;

    }

    public Color getColor() {
        return color;
    }

    public int getThikness() {
        return thikness;
    }

    public List<Point> getPoints() {
        return points;
    }

    public Point getPoint(int i) {
        return (Point)points.get(i);
    }

    public void addPoint(Point p) {
        if (points.size() > MAX_POINTS)
            points.remove(0);
        points.add(p);
    }

    public int getPointsCount() {
        return points.size();
    }
}
