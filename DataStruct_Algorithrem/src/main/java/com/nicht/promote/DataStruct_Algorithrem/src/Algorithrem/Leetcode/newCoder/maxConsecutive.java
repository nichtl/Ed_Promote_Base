package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: ethan xu
 * @date: 2025/1/6 19:25
 */
public class maxConsecutive {

    public static void main(String[] args) {
        maxConsecutive(3,15,new int[]{7,9,13});
    }

    public  static  int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int ans = 0 ;
        ans = Math.max(special[0] - bottom, ans);
        ans = Math.max(top - special[special.length-1],ans);
        for (int i = 1;i<special.length;i++){
            ans = Math.max(special[i] - special[i-1]-1,ans); //-1 是因为 特殊楼层  special[i] 不计算在内
        }
        return ans;
    }


}
