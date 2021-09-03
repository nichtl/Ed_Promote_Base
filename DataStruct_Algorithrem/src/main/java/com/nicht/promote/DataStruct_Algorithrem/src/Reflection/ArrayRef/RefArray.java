package com.nicht.promote.DataStruct_Algorithrem.src.Reflection.ArrayRef;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description  array reflection
 * @Author Nicht
 * @description
 * @ 2020/12/7
 */
public class RefArray {
    public static void main(String[] args) {
        /*
         * This code sample creates an array of int.
         * The first parameter int.class given to the Array.newInstance() method tells
         *  what type each element in the array should be of.
         * The second parameter states how many elements the array should have space for.*/
        // 利用反射来创建数组 指定数组的类型      指定数组容量
        int []    intArray = (int[]) Array.newInstance(int.class, 6);
        Array.set(intArray,0,10);
        Array.set(intArray,1,20);
        Array.set(intArray,2,30);
        Arrays.stream(intArray).forEach(System.out::println);
        List<String> list = new ArrayList<>();
        list.add("sasa");
        list.add("sdada");
        list.add("da");
        list.add("fg");
        list.add("fg");
        list.stream().distinct().forEach(System.out::println);
        StringBuilder  bs = new StringBuilder();


    }











}
