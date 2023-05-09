package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Date 2023/5/9
 */
public class threeSum {

    public static void main(String[] args) {
        threeSum(new int[]{0,0,0,0});
    }
    public static  List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }

            int leftIndex = i+1;

            int rightIndex = nums.length-1;

            while(leftIndex < rightIndex){
                int r = nums[i] + nums[leftIndex] + nums[rightIndex];
                if( r == 0  ){
                    result.add( Arrays.asList(nums[i] , nums[leftIndex] , nums[rightIndex]));
                    while (leftIndex<rightIndex && nums[rightIndex] == nums[--rightIndex]);
                    while (leftIndex<rightIndex && nums[leftIndex] == nums[++leftIndex]);
                }

                if(r >0){
                    rightIndex--;
                }else {
                    leftIndex++;
                }
            }

        }

        return result ;

    }




}
