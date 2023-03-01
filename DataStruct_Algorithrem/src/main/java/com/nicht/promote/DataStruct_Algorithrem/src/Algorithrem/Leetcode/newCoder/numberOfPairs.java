package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Date 2023/2/16
 */
public class numberOfPairs {
    public static void main(String[] args) {
        numberOfPairs(new int[] {1,3,2,1,3,2,2});
    }
    public static  int[] numberOfPairs(int[] nums) {
        int[] result = new int[nums.length];
        if(nums.length ==0){return  new int[]{0,0};}
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if(countMap.containsKey(Integer.valueOf(num))) {
                countMap.put(num, countMap.get(Integer.valueOf(num))+1);
                continue;
            }

            countMap.put(Integer.valueOf(num), 1);
        }
        int leaveNum = 0;
        int pairNum  = 0;

        for (Map.Entry<Integer,Integer> e : countMap.entrySet()){
            int count =   e.getValue();
            pairNum  += count/2;
            leaveNum += count%2;
            System.out.println(count);
        }
        return new int[]{pairNum, leaveNum};
    }
}
