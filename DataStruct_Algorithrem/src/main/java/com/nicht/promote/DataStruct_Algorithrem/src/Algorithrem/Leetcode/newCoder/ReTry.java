package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.Arrays;

/**
 * @Description
 * @Date 2023/9/18
 */
public class ReTry {

//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。
//
// 给定一个代表每个房屋存放金额的非负整数数组,计算你不触动警报装置的情况下,一夜之内能够偷窃到的最高金额。
//
//
//
// 示例 1：
//
//
//输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ,然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。
//
// 示例 2：
//
//
//输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 400
//
//
// Related Topics 数组 动态规划 👍 2779 👎 0
    public int rob(int[] nums) {

        if(nums.length ==1 ){
            return  nums[0];
        }
         int [] dp = new int[nums.length];
         dp[0]=nums[0];
         dp[1]=Math.max(nums[1],nums[0]);

        for (int i = 2; i < nums.length; i++) {
            dp[i]  = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }

        return  dp[nums.length-1];
    }



//你是一个专业的小偷，计划偷窃沿街的房屋,每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的
//房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
//
//
// 示例 1：
//
//输入：nums = [2,3,2]
//输出：3
//解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3,1]
//输出：4
//解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//     偷窃到的最高金额 = 1 + 3 = 4 。
//
// 示例 3：
//
//
//输入：nums = [1,2,3]
//输出：3
//
// 提示：
//
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 1000
//
// Related Topics 数组 动态规划 👍 1494 👎 0

    public int rob2(int[] nums) {

        if(nums.length ==1 ) {
            return  nums[0];
        }

        if(nums.length ==2){
            return  Math.max(nums[0],nums[1]);
        }

        return Math.max( rob(Arrays.copyOfRange(nums,0,nums.length-1)), rob(Arrays.copyOfRange(nums,1,nums.length)) );
    }


    // 161  cab   ab  i=0 j=0
    public  boolean isOneEditDistance(String s, String t){
        int n = s.length();
        int m = t.length();
        if(Math.abs(n-m) >1){
            return  false;
        }

        if(n>m){
            // 以短的字符开始比较
            return  isOneEditDistance(t,s);
        }
        int i=0,j=0,diff=0;

        while( i<n && j<m  && diff<=1){
            char c1 = s.charAt(i);
            char c2 = t.charAt(j);
            if(c1==c2){
                i++; j++;
            }else{
                // 如果找到不同的位置 让 短的下标不同qianj
                if(n==m){i++;}
                j++;
                diff++;
            }
        }
        return diff == 0 ? n != m : diff == 1;
    }




}
