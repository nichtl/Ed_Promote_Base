package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

public class maxRotateFunction_396 {

//给定一个长度为 n 的整数数组 nums 。
//
// 假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数 F 为：
//
//
// F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
//
//
// 返回 F(0), F(1), ..., F(n-1)中的最大值 。
//
// 生成的测试用例让答案符合 32 位 整数。
//
//
//
// 示例 1:
//
//
//输入: nums = [4,3,2,6]
//输出: 26
//解释:
//F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
//F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
//F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
//F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
//所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。
//
//
// 示例 2:
//
//
//输入: nums = [100]
//输出: 0
//
//
//
//
// 提示:
//
//
// n == nums.length
// 1 <= n <= 10⁵
// -100 <= nums[i] <= 100
//
//
// Related Topics 数组 数学 动态规划 👍 284 👎 0

    //F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25

    //F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
    //F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
    //F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
    // arr = [A0,A1,A2,A3]

    // F(0) =  (0 * A0) + (1 * A1) + (2 * A2) + (3 * A3)
    // F(1) =  F(0) +  A0 + A1 + A2 -(3*A3) = F(0) + (numSum-A3 )-(3*A3)
    // = F(0) + numSum- 4*A3
    // F(2) = F(1) +  A3+A1+A2 -
    // nums: [A0,A1,A2,A3]
    //
    // F0 = 0*A0 + 1*A1 + 2*A2 + 3*A3
    //
    // F1 = 0*A3 + 1*A0 + 2*A1 + 3*A2
    //    = F0 + A0 + A1 + A2 - 3*A3
    //    = F0 + sum-A3 - 3*A3
    //    = F0 + sum - 4*A3
    //
    // F2 = 0*A2 + 1*A3 + 2*A0 + 3*A1
    //    = F1 + A0 + A1 + A3 - 3*A2
    //    = F1 + sum - 4*A2
    //
    // F3 = 0*A1 + 1*A2 + 2*A3 + 3*A0
    //    = F2 + A2 + A3 + A0 - 3*A1
    //    = F2 + sum - 4*A1
    //  F(i) = F(i-1) + sum - n * A(n-i)
    public int maxRotateFunction(int[] nums) {
        int f  = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum+=nums[i];
            f+=(i*nums[i]);
        }

        int result = 0;
        int n =nums.length;
        for (int i = 0; i < n; i++) {
             f = f+sum-( n* nums[n-i]);
             result = Math.max(f,result);
        }
        return  result;
    }
}
