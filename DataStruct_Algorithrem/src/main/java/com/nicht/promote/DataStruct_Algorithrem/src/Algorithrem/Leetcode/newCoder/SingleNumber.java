package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.Arrays;

/**
 * @Description
 * @Date 2022/8/22
 */
public class SingleNumber {
    //给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
//
//
//
// 示例 1：
//
//
//输入：nums = [2,2,3,2]
//输出：3
//
//
// 示例 2：
//
//
//输入：nums = [0,1,0,1,0,1,100]
//输出：100
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 3 * 10⁴
// -2³¹ <= nums[i] <= 2³¹ - 1
// nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
//
//
//
//
// 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
//
//
//
// 注意：本题与主站 137 题相同：https://leetcode-cn.com/problems/single-number-ii/
// Related Topics 位运算 数组 👍 93 👎 0
    public static void main(String[] args) {
        int [] params = new int[]{1,1,1,3};
        singleNumber(params);
    }
    public static int singleNumber(int[] nums) {
        int[] result = new int[32];
        int ans  =0;
        for(int e:nums){
            for (int i = 0,len = 32; i < len; i++) {
                if(((e>>i)&1)==1){
                    result[i]++;
                }
                ans|=(result[i]%3)<<i;
            }
        }
        return ans;
    }





















}
