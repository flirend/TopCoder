package com.github.flirend.topcoder;

import java.util.HashMap;

public class ANewHope {
    public int count(int[] firstWeek, int[] lastWeek, int D) {
        int stepSize = firstWeek.length - D;
        HashMap<Integer, Integer> indexMap = new HashMap<>(firstWeek.length);

        // setup map
        for (int i = 0; i < firstWeek.length; i++) {
            indexMap.put(firstWeek[i], i);
        }

        int maxDistance = 0;
        for (int i = 0; i < lastWeek.length; i++) {
            int distance = indexMap.get(lastWeek[i]) - i;
            if (distance > maxDistance) {
                maxDistance = distance;
            }
        }

        int result;
        if (maxDistance == 0) {
            // first week == last week
            return 1;
        } else {
            result = maxDistance / stepSize + ((maxDistance%stepSize > 0) ? 1:0) + 1;
        }

        return result;
    }
}
