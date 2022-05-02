package com.company;

enum Location {
    LEFT,
    RIGHT,
    ONLINE
}
public class Point {
    private int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    double getDistanceFromLine(Point b, Point p) {
        int ax, ay, bx, by, px, py;
        ax = this.x;
        ay = this.y;
        bx = b.getX();
        by = b.getY();
        px = p.getX();
        py = p.getY();

        double distance = (bx - ax) * (ay - py) - (by - ay) * (ax - px);
        if (distance < 0)
            distance = -distance;

        return distance;
    }

    Location sideOfPoint(Point b, Point p) {
        int ax, ay, bx, by, px, py;
        ax = this.x;
        ay = this.y;
        bx = b.getX();
        by = b.getY();
        px = p.getX();
        py = p.getY();

        double result = (bx - ax) * (py - ay) - (by - ay) * (px - ax);

        if (result > 0) {
            return Location.RIGHT;
        }

        if (result < 0) {
            return Location.LEFT;
        }

        return Location.ONLINE;
    }

    boolean isInTriangle(Point b, Point c, Point p) {
        int ax, ay, bx, by, cx, cy, px, py;
        ax = this.x;
        ay = this.y;
        bx = b.getX();
        by = b.getY();
        cx = c.getX();
        cy = c.getY();
        px = p.getX();
        py = p.getY();

        int eq1, eq2, eq3;

        eq1 = (px - ax) * (by - ay) - (py - ay) * (bx - ax);
        eq2 = (px - bx) * (cy - by) - (py - by) * (cx - bx);
        eq3 = (px - cx) * (ay - cy) - (py - cy) * (ax - cy);

        boolean allPositive = (eq1 > 0 && eq2 > 0 && eq3 > 0);
        boolean allNegative = (eq1 < 0 && eq2 < 0 && eq3 < 0);

        if (allNegative || allPositive) {
            return true;
        }

        return false;
    }

    static Point createPoint(String input) {
        int x, y;

        String[] points = input.split(" ");
        x = Integer.parseInt(points[0]);
        y = Integer.parseInt(points[1]);

        return new Point(x, y);
    }
}
