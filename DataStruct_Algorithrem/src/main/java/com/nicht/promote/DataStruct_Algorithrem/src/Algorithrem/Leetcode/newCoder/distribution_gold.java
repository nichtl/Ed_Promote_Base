package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class distribution_gold {
    // 一个 数组 length = n 可以对每个数进行 /2 保留小数 求最少对几个数/2后才能使得 数组存在和为M的子数组 没有输出-1


    int spiltGold(int [] golds, int targetM){

        int splitCount = 0;
        Arrays.sort(golds);

        Map<Integer,Integer> numCache = new HashMap<>(16);
        for (int i = 0; i < golds.length; i++) {
            numCache.put(golds[i],numCache.getOrDefault(golds[i],0)+1);
        }

        for (int i = 0; i < golds.length; i++) {

        }











        }




}
