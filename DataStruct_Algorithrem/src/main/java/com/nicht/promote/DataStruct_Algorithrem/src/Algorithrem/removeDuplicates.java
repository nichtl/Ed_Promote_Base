package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem;

import java.util.Arrays;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/8/25
 * @Link
 */
public class removeDuplicates {

    public static void main(String[] args) {
        int [] s = new int[]{1,2,3,4,5,6,1,2,3,4,5,6};
        System.out.println(Arrays.stream(s).distinct());
    }


}
