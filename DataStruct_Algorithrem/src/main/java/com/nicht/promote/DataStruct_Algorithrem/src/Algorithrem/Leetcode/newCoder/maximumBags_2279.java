package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.Arrays;

/**
 * @Description
 * @Date 2023/9/20
 */
public class maximumBags_2279 {

    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int maxBag = 0 ;

        for (int i = 0; i < rocks.length; i++) {
            capacity[i] = capacity[i] - rocks[i];
        }

        Arrays.sort(capacity);
        for (int i = 0; i < capacity.length ; i++) {
            if(capacity[i] <= additionalRocks){
                maxBag++;
                additionalRocks-=capacity[i];
            }
        }

        return  maxBag;


    }
}
