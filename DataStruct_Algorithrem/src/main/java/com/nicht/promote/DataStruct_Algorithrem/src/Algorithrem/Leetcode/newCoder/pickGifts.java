package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.Arrays;

/**
 * @Description
 * @Date 2023/2/8
 */
public class pickGifts {
    public static void main(String[] args) {
        System.out.println( Math.floor(Math.sqrt(10)));
    }

    public long pickGifts(int[] gifts, int k) {



        for (int i = 0; i < k; i++) {
            int maxIndex =0;
            for (int j = 0; j < gifts.length; j++) {
             if(gifts[maxIndex]<=gifts[j]){
                 maxIndex = j;
             }
            }
            int  left = (int) Math.floor(Math.sqrt(gifts[maxIndex]));
            if(left < gifts[maxIndex]){
               gifts[maxIndex] = left;
            }
        }
        return  Arrays.stream(gifts).sum();

    }



}
