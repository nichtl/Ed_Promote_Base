package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Date 2023/2/8
 */
public class vowelStrings {

    public static void main(String[] args) {

    }


    public static int[] vowelStrings(String[] words, int[][] queries) {
        Map<Character,Boolean> cacheMap = new HashMap<Character,Boolean>();
        cacheMap.put('a', true); cacheMap.put('e', true); cacheMap.put('i', true);
        cacheMap.put('o', true); cacheMap.put('u', true);
        int[] res = new int[queries.length];
        Arrays.fill(res,0);
        for (int i = 0; i < queries.length; i++) {
            int [] query = queries[i];

            for (int j = query[0]; j < query[1]; j++) {
                String  word = words[j];
                if(cacheMap.getOrDefault(word.charAt(0),false)&&
                   cacheMap.getOrDefault(word.charAt(words.length)-1,false)){
                    res[i]+=1;
                }
            }
        }

       return  res;



    }
}
