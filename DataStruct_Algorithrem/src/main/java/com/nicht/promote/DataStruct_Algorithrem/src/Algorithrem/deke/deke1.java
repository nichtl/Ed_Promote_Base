package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.deke;

import java.util.*;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/8/26
 * @Link
 */
public class deke1 {
/*    所谓的水仙花数是指一个n位的正整数其各位数字的n次方的和等于该数本身，
    例如153=1^3+5^3+3^3,153是一个三位数
            输入描述
    第一行输入一个整数N，
    表示N位的正整数N在3-7之间包含3,7
    第二行输入一个正整数M，
    表示需要返回第M个水仙花数
            输出描述
    返回长度是N的第M个水仙花数，
    个数从0开始编号，
    若M大于水仙花数的个数返回最后一个水仙花数和M的乘积，
    若输入不合法返回-1*/
    public static void main(String[] args) {
        int N = 3;
        int M = 3;

        if (N < 3 || N > 7) {
            System.out.println(-1);
            return;
        }
        LinkedList<Integer>  list  = new LinkedList<>();
        int start =(int) Math.pow(10,N-1);//水仙花数起始位置
        int end =(int) Math.pow(10,N);
        int sum =start;
        int index=0;
        int result=0;
        for (int i =start ; i<end;++i){
             if(issxh(i,N)){
                 list.add(index,i);
                 index+=1;
             }
             if(index>M){
                 break;
             }
         }
        System.out.println(list.get(M));
    }
    public static boolean issxh(Integer  num,Integer n){
        int sum =0;
        int temp=num;
        while(temp>0){
            sum+= Math.pow((temp%10),n);
            temp=temp/10;
        }
        return  sum==num;
    }






}
