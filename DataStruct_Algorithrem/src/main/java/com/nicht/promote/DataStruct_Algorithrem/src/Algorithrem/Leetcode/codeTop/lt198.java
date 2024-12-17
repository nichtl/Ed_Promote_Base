package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.codeTop;

public class lt198 {


    public static void main(String[] args) {

    }
     //i-2 i-1  i i+1 i+2
     public int rob(int[] nums) {
         if (nums.length == 0) {
             return 0;
         }
         if (nums.length == 1) {
             return nums[0];

         }
         int[] ans = new int[nums.length];
         ans[0] = nums[0];
         ans[1] = Math.max(nums[0], nums[1]);
         for (int i = 2; i < nums.length; i++) {
             ans[i] = Math.max(ans[i - 2] + nums[i], nums[i - 1]);
         }
         return ans[nums.length - 1];
     }
}
