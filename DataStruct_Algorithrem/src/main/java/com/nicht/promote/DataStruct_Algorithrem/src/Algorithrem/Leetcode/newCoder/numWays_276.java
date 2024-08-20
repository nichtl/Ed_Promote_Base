package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

public class numWays_276 {
    /**
     * 有 k 种颜色的涂料和一个包含 n 个栅栏柱的栅栏，请你按下述规则为栅栏设计涂色方案：
     *
     * 每个栅栏柱可以用其中 一种 颜色进行上色。
     * 相邻的栅栏柱 最多连续两个 颜色相同。
     * 给你两个整数 k 和 n ，返回所有有效的涂色 方案数 。
     *
     * 输入：n = 3, k = 2
     * 输出：6
     * 解释：所有的可能涂色方案如上图所示。注意，全涂红或者全涂绿的方案属于无效方案，因为相邻的栅栏柱 最多连续两个 颜色相同。
     * 示例 2：
     *
     * 输入：n = 1, k = 1
     * 输出：1
     * 示例 3：

       totalWays(1) = k, totalWays(2) = k * k
        分为两种情况
         1 前面 2个栅栏柱 一样  此时 可用的颜色 为  k = k-1
         1 前面 2个栅栏柱 不一样  此时 可用颜色为 k
       totalWays(3) = (k-1) * (totalWays(n-1)) + (k-1) * (totalWays(n-2))
     * 输入：n = 7, k = 2
     * 输出：42
     * 提示：
     * 1 <= n <= 50
     * 1 <= k <= 105
     * 题目数据保证：对于输入的 n 和 k ，其答案在范围 [0, 231 - 1] 内
     */

    public int numWays(int n, int k) {
        if(n==1) {return  k;}
        int dp1 = k;
        int dp2 = k * k;

        int res = 0 ;
        for (int i = 3; i <= n ; i++) {
             res = (k-1) * (dp1 + dp2);
             dp1 = dp2;
             dp2 = res;
        }
        return  res;
    }
}
