package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode;

import java.util.Arrays;

/**
 * 功能描述：
 *
 * @author xujian8
 */

public class WiggleSort {
//给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
//
// 你可以假设所有输入数组都可以得到满足题目要求的结果。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,5,1,1,6,4]
//输出：[1,6,1,5,1,4]
//解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
//
//
// 示例 2：
//
//
//输入：nums = [1,3,2,2,3,1]
//输出：[2,3,1,3,1,2]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 5 * 10⁴
// 0 <= nums[i] <= 5000
// 题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
//
//
//
//
// 进阶：你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
// Related Topics 数组 分治 快速选择 排序 👍 414 👎 0

    public static void main(String[] args) {
        int[] nums = new int[]{1,5,1,1,6,4};
        int  mid = nums.length/2;
        int [] numsLeft =  Arrays.copyOfRange(nums,0,mid+1);
        int [] numsRight = Arrays.copyOfRange(nums,mid+1,nums.length);
        wiggleSort(nums);
    }

    public static void wiggleSort(int[] nums) {
        int i=0,j=0;
        Arrays.sort(nums);
        int  mid = (nums.length+1)/2;
        int leftEnd = mid -1;
        int rightEnd = nums.length-1;

        for (int k = 0; k < nums.length; k++) {
            nums[i++] =nums[leftEnd--];
            if(rightEnd>mid) {
                nums[i++] = nums[rightEnd--];
            }
        }
        System.out.println(Arrays.toString(nums));
    }


    public  void swap(int[] nums ,int i,int j){
        nums[i]^=nums[j];
        nums[j]^=nums[i];
        nums[i]^=nums[j];
    }




    


}
