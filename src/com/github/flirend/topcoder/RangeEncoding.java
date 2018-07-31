package com.github.flirend.topcoder;

public class RangeEncoding {

    public int minRanges(int[] arr) {
        int countRanges = 1;
        int cursor = arr[0];
        int index = 1;

        while (index < arr.length) {
            if (arr[index] - cursor > 1) {
                // need a new range pair
                countRanges++;
            }
            cursor = arr[index];
            index++;
        }
        return countRanges;
    }

}
