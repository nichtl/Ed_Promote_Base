package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

/**
 * 功能描述：
 *
 * @author xujian8
 */
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
// Related Topics 数组 数学 动态规划 👍 141 👎 0

import java.util.Arrays;

/**
 * 把数组逆转跟把乘数逆转是一样的，可以看出有如下规律
 *   4     3     2     6
 *
 *  0*4   1*3   2*2   3*6   F(0)  25
 *
 *  3*4   0*3   1*2   2*6   F(1) = F(0) - SUM(data) + N * data[0];  16    25 -15 + 4
  *
 *  2*4   3*3   0*2   1*6   F(2) = F(1) - SUM(data) + N * data[1];  23
 *
 *  1*4   2*3   3*2   0*6   F(3) = F(2) - SUM(data) + N * data[2];  26
 *
 */
//F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
//F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
//F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
//F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26

public class MaxRotateFunction {

    public int maxRotateFunction(int[] nums) {
        int max=0,sum=0,pre=0,cur =0 ,n=0;

        for (int i = 0,len=nums.length; i <len ; i++) {
            n++;
            sum+=nums[i];
            pre=pre+i*nums[i];
        }
        max=pre;
        for (int j = 1,len= nums.length; j <len ; j++) {
            cur = pre - sum+ n * nums[j-1];
            max = Math.max(max,pre);
            pre = cur;
        }
        return  max;

    }






}
