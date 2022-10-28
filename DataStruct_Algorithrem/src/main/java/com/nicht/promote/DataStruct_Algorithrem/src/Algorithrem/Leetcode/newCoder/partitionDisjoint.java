package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Date 2022/10/24
 */

//给定一个数组 nums ，将其划分为两个连续子数组 left 和 right， 使得：
//
//
// left 中的每个元素都小于或等于 right 中的每个元素。
// left 和 right 都是非空的。
// left 的长度要尽可能小。
//
//
// 在完成这样的分组后返回 left 的 长度 。
//
// 用例可以保证存在这样的划分方法。
//
//
//
// 示例 1：
//
//
//输入：nums = [5,0,3,8,6]
//输出：3
//解释：left = [5,0,3]，right = [8,6]
//
//
// 示例 2：
//
//
//输入：nums = [1,1,1,0,6,12]
//输出：4
//解释：left = [1,1,1,0]，right = [6,12]
//
//
//
//
// 提示：
//
//
// 2 <= nums.length <= 10⁵
// 0 <= nums[i] <= 10⁶
// 可以保证至少有一种方法能够按题目所描述的那样对 nums 进行划分。
//
// lt 915
public class partitionDisjoint {



    public int partitionDisjoint(int[] nums) {
        int splitIndex = 0;
        int leftMax = nums[0];
        int tempmax =  nums[0];

        for (int i = 0,len= nums.length; i < len ; i++) {
            if(nums[i]  > leftMax ){
                tempmax  = Math.max(tempmax,nums[i]);
            }else{
                splitIndex=i;
                leftMax  =tempmax;
            }
        }
        return splitIndex+1;
    }

    public  boolean targetLessThanAnyNum( List<Integer> nums, final int target) {

        return   nums.stream().anyMatch((num)->target>num);
    }







}
