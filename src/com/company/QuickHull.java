package com.company;

import java.util.ArrayList;
import java.util.List;

public class QuickHull {
    private ArrayList<Point> hull;

    QuickHull() {
        this.hull = new ArrayList<Point>();
    }

    ArrayList<Point> run(List<Point> points) {
        Point leftmost = leftmost(points);
        Point rightmost = rightmost(points);

        this.hull.add(leftmost);
        this.hull.add(rightmost);

        points.remove(leftmost);
        points.remove(rightmost);

        List<Point> s1 = new ArrayList<>();
        List<Point> s2 = new ArrayList<>();

        Location loc;
        for (Point p : points) {
            loc = leftmost.sideOfPoint(rightmost, p);

            if (loc == Location.RIGHT) {
                s1.add(p);
            }

            if (loc == Location.LEFT) {
                s2.add(p);
            }
        }

        findHull(s1, leftmost, rightmost);
        findHull(s2, rightmost, leftmost);

        return hull;
    }

    // P = point1, Q = point2, C = farthest
    private void findHull(List<Point> points, Point point1, Point point2) {
        if (points.isEmpty()) {
            return;
        }

        // Find farthest
        Point farthest = points.get(0);
        double maxDistance = 0;
        double tempDistance;
        for (Point p : points) {
            tempDistance = point1.getDistanceFromLine(point2, p);

            if (tempDistance >= maxDistance) {
                farthest = p;
                maxDistance = tempDistance;
            }
        }

        this.hull.add(hull.indexOf(point2), farthest);

        List<Point> s1 = new ArrayList<>();
        List<Point> s2 = new ArrayList<>();

        // P point1, Q is point 2, C is point Farthest
        Location loc;
        for (Point p : points) {
            if (p == point1 || p == farthest || p == point2)
                continue;

            loc = point1.sideOfPoint(farthest ,p);
            if (loc == Location.RIGHT) {
                s1.add(p);
                continue;
            }

            loc = farthest.sideOfPoint(point2, p);
            if (loc == Location.RIGHT) {
                s2.add(p);
                continue;
            }
        }

        findHull(s1, point1, farthest);
        findHull(s2, farthest, point2);
    }

    private Point leftmost(List<Point> points) {
        int minIndex = 0;
        int minValue = 0;

        if (!points.isEmpty()) {
            minValue = points.get(0).getX();
        }

        int tempX;
        int currIndex = 0;
        for (Point p : points) {
            tempX = p.getX();
            if (tempX < minValue) {
                minIndex = currIndex;
                minValue = tempX;
            }
            currIndex += 1;
        }

        return points.get(minIndex);
    }

    private Point rightmost(List<Point> points) {
        int maxIndex = 0;
        int maxValue = 0;

        if (!points.isEmpty()) {
            maxValue = points.get(0).getX();
        }

        int tempX;
        int currIndex = 0;
        for (Point p : points) {
            tempX = p.getX();
            if (tempX > maxValue) {
                maxIndex = currIndex;
                maxValue = tempX;
            }
            currIndex += 1;
        }

        return points.get(maxIndex);
    }
}
