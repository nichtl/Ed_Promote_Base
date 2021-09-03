package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem;

import java.util.Arrays;

/**
 * @Author admin
 * @description 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * @ 2020/11/27
 */
public class Nnum {


     public static void search(int [] a){
         int N = a.length/2;
         if (Arrays.stream(a).distinct().toArray().length > N) return  ;
     }









}
