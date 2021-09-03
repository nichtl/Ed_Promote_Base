package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem;

import java.util.List;
import java.util.Stack;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/6/15
 * @Link
 */
public class foursum {
    public static void main(String[] args) {
         Stack<Object> stack1 = new Stack<>();
         stack1.push(1);
         stack1.push(2);
        System.out.println(stack1.pop());
    }
    public static int fib(int n) {
        if(n==0){return 0;}
        if(n==1||n==2){return 1;}
        int a=0,b=1,sum=0;
        for (int i = 0; i <n-1; i++) {
            sum=(a+b)%1000000007;
            a=b;
            b=sum;
        }
        return  sum;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
       return  null;
    }
}
