package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.List;

/**
 * @Description
 * @Date 2023/9/18
 */
public class week {

    public static void main(String[] args) {


         int i = bitCount(21);
        System.out.println(i);
    }

    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {



     return  0 ;
    }

     public static   int  bitCount( int n){
         int count =0 ;
         while(n>0){
             n&=(n-1);
             count++;
         }
         return  count;


     }

}
