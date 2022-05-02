package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {


    static List<Point> loadPoints() {
        List<Point> points = new ArrayList<>();
        Path path = Paths.get("points.txt");

        try {
             Files.lines(path)
                .map(line -> Point.createPoint(line))
                .forEach(point -> points.add(point));

        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        return points;
    }

    static void writeResults(List<Point> points) {
        try {
            String fileName = "results.txt";
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            for (var point : points) {
                String newLine = point.getX() + " " + point.getY() + "\n";
                bw.write(newLine);
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Failed to write file :(");
        }
    }

    public static void main(String[] args) {
        List<Point> points = loadPoints();
        //writeResults(points);

        QuickHull qh = new QuickHull();

        var res = qh.run(points);
        for (var r : res) {
            System.out.println(r.getX() + " " + r.getY());
        }
    }
}
