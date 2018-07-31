package com.github.flirend.topcoder;

import java.util.*;

public class BadNeighbors {
    int maxDonations(int[] donations) {
        int result =0;
        int[] curMax = new int[donations.length - 1];
        int[] noCurMax = new int[donations.length - 1];
        boolean[] curHasFirst = new boolean[donations.length];
        boolean[] noCurHasFirst = new boolean[donations.length];

        curMax[0] = donations[0];
        noCurMax[0] = 0;

        curHasFirst[0] = true;
        noCurHasFirst[0] = false;

        for (int i = 1; i < donations.length - 1; i++) {
            curMax[i] = noCurMax[i-1] + donations[i];
            curHasFirst[i] = noCurHasFirst[i-1];

            if (curMax[i-1] > noCurMax[i-1]) {
                noCurMax[i] = curMax[i-1];
                noCurHasFirst[i] = curHasFirst[i-1];
            } else if (curMax[i-1] < noCurMax[i-1]) {
                noCurMax[i] = noCurMax[i-1];
                noCurHasFirst[i] = noCurHasFirst[i-1];
            } else {
                // equal
                noCurMax[i] = noCurMax[i-1];
                noCurHasFirst[i] = curHasFirst[i - 1] && noCurHasFirst[i - 1];
            }
        }

        if (noCurMax[donations.length-2] > curMax[donations.length-2]) {
            result = noCurMax[donations.length-2] + donations[donations.length-1];
        } else if (noCurMax[donations.length-2] == curMax[donations.length-2]) {
            if (!noCurHasFirst[donations.length-2]) {
                result = noCurMax[donations.length-2] + donations[donations.length-1];
            } else {
                result = curMax[donations.length-2];
            }
        } else {
            // noCurMax[donations.length-2] < curMax[donations.length-2]
            if (!noCurHasFirst[donations.length-2]) {
                result = Math.max(noCurMax[donations.length-2] + donations[donations.length-1],
                        curMax[donations.length-2]);
            } else {
                if (donations[0] < donations[donations.length-1]) {
                    result = Math.max(noCurMax[donations.length-2] - donations[0] + donations[donations.length-1],
                            curMax[donations.length-2]);
                } else {
                    result = curMax[donations.length-2];
                }
            }
        }

        return result;
    }

}
