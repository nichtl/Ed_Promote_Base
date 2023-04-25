package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description
 *
 *@Date 2023/4/25
 * @LT 2418
 */
public class sortPeople {

    public static void main(String[] args) {

    }
    public  String[] sortPeople(String[] names, int[] heights) {
       if(heights.length<=1){
           return  names;
       }
        Map<Integer,String> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2,o1);
            }
        });

            for (int i = 0; i < heights.length ; i++) {
               map.put(heights[i], names[i]);
            }


            return   map.values().toArray(new String[0]);

    }
}
