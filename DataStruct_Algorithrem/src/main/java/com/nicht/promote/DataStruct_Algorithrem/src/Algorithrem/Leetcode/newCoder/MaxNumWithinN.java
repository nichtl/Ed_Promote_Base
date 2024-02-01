package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxNumWithinN {
  //   数组A中给定可以使用的1~9的数，返回由A数组中的元素组成的小于n的最大数。例如A={1, 2, 4, 9}，x=2533，返回2499



    public  int  maxNumWithinN(int n,int [] arr){
        Arrays.sort(arr);
        if(n==0) return 0;
        List<Integer> singleDigit = new ArrayList<>();
        while (n>0){
            singleDigit.add(n%10);
            n=n/10;
        }
         Arrays.sort(singleDigit.toArray(new Integer[0]), Collections.reverseOrder());
        for (int i = singleDigit.size()-1; i < 0; i++) {



        }
       return  0;
    }
}
