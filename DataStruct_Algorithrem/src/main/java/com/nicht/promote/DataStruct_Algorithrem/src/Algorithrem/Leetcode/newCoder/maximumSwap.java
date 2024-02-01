package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.Arrays;
import java.util.Collections;

public class maximumSwap {


    public static void main(String[] args) {
       maximumSwap(2882);
    }

    public  static  int maximumSwap(int num) {

        char[] charArr = String.valueOf(num).toCharArray();
        char[] maxCharArr =  String.valueOf(num).toCharArray();
        int len = charArr.length;

        Arrays.sort(maxCharArr);
        int swapIndex = 0;

        while(swapIndex < len && charArr[swapIndex] == maxCharArr[(len -1) - swapIndex]) swapIndex++;

        if(swapIndex == len ){return  num;}

        int maxDigitNum = maxCharArr[(len-1) - swapIndex];

        int maxDigitNumIndex = 0;
        for (int i = swapIndex; i < len; i++) {
             if(  charArr[i]  >=maxDigitNum ){
                 maxDigitNum = charArr[i];
                 maxDigitNumIndex = i;
             }
        }

        //swap
        charArr[swapIndex] ^= charArr[maxDigitNumIndex];
        charArr[maxDigitNumIndex] ^= charArr[swapIndex];
        charArr[swapIndex] ^= charArr[maxDigitNumIndex];

        return  Integer.parseInt( String.valueOf(charArr) );
    }
}
