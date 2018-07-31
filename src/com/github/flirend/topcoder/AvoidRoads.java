package com.github.flirend.topcoder;
import java.lang.System;

import java.util.HashSet;

public class AvoidRoads {
    private HashSet<String> badRoads;
    public long numWays(int width, int height, String[] bad) {
        badRoads = new HashSet<>(bad.length);
        for (String road: bad) {
            String[] stringPoints = road.split(" ");
            int[] points = new int[4];
            for (int i = 0; i < 4; i++) {
                points[i] = Integer.valueOf(stringPoints[i]);
            }
            if ((points[0] == points[2] && points[1] < points[3])
                    || (points[1] == points[3] && points[0] < points[2])){
                badRoads.add(road);
            } else {
                badRoads.add(String.format("%s %s %s %s", points[2], points[3], points[0], points[1]));
            }
        }

        // calculate the first column
        long[] currentColumn = new long[height+1];
        currentColumn[0] = 1;
        for (int i = 1; i < height+1; i++) {
            if (isBadRoad(0, i-1, 0, i)) {
                currentColumn[i] = 0;
            } else {
                currentColumn[i] = currentColumn[i-1];
            }
        }

        long[] nextColumn = new long[height+1];
        int currentIndex = 1;
        while (currentIndex < width+1) {
            // initialize the first row of "nextColumn" as 0 or 1
            if (isBadRoad(currentIndex-1, 0, currentIndex, 0)) {
                nextColumn[0] = 0;
            } else {
                nextColumn[0] = currentColumn[0];
            }

            // calculate the rest of the column
            for (int i = 1; i < height+1; i++) {
                // first horizontal
                if (isBadRoad(currentIndex-1, i, currentIndex, i)) {
                    nextColumn[i] = 0;
                } else {
                    nextColumn[i] = currentColumn[i];
                }

                // then vertical
                if (!isBadRoad(currentIndex, i-1, currentIndex, i)) {
                    nextColumn[i] += nextColumn[i-1];
                }
            }

            // swap arrays
            long[] tmp = currentColumn;
            currentColumn = nextColumn;
            nextColumn = tmp;
            currentIndex++;
        }
        return currentColumn[height];
    }

    private boolean isBadRoad(int startX, int startY, int stopX, int stopY) {
        String coordinate = String.format("%s %s %s %s", startX, startY, stopX, stopY);
        return badRoads.contains(coordinate);
    }

    public static void main (String[] args) {
        AvoidRoads a = new AvoidRoads();
        long num = a.numWays(1,1,new String[0]);
        System.out.println(String.format("num = %l", num));
    }

}
