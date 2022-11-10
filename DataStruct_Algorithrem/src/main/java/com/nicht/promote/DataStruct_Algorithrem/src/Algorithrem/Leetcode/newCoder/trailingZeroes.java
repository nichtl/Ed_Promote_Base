package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

/**
 * @Description
 * @Date 2022/11/7
 */
public class trailingZeroes {


    public int trailingZeroes(int n) {
        int ctx = 0;
        while(n>0){
            ctx +=n/5;
            n=n/5;
        }
        return  ctx;
    }
}
