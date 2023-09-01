package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Description
 * @Date 2023/9/1
 */
public class hIndex {
    public static void main(String[] args) {

    }

    public int hIndex(int[] citations) {
        int h = 0;
        Arrays.sort(citations);

        for (int i = citations.length-1; i >=0; i--) {

            if(  citations[i] > h){
                h++;
            }
        }
        return  h;
    }
}
