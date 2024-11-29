package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.codeTop;

/**
 * 162. 寻找峰值
 */
public class lt162 {

    /**
     *
     峰值元素是指其值严格大于左右相邻值的元素。
     给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
     你可以假设 nums[-1] = nums[n] = -∞ 。
     你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
     示例 1：
     输入：nums = [1,2,3,1]
     输出：2
     解释：3 是峰值元素，你的函数应该返回其索引 2。
     示例 2：
     输入：nums = [1,2,1,3,5,6,4]
     输出：1 或 5
     解释：你的函数可以返回索引 1，其峰值元素为 2；
     或者返回索引 5， 其峰值元素为 6。
     */

    public int findPeakElement(int[] nums) {
        // 二分
        int left = 0 ;
        int right = nums.length;
        while (left < right){
            int mid = left+right >> 1;
            if (nums[mid] < nums[mid+1]) {
                // 如果mid+1更大， 说明 mid 之后肯定还在爬升，mid+1 之后有峰
                left = mid + 1;
            } else {
                // 如果 mid 更大， 说明 mid 之前有峰
                right = mid;
            }
            if(nums[left] < nums[mid]){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return  right;
    }





}
