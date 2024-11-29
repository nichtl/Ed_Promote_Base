package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.codeTop;

public class lt322 {

//    给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
//
//    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
//
//    你可以认为每种硬币的数量是无限的。

    //dp[c][a] = min(dp[c-1][a],dp[c][a-coins[i]]+1)
    public int coinChange(int[] coins, int amount) {
        // c a
        int[][] dp = new int[coins.length + 1][amount + 1];

        for (int a = 1; a <= amount; a++) {
            dp[0][a] = Integer.MAX_VALUE - 1; // 避免溢出
        }

        for (int c = 1; c <= coins.length; c++) {
            for (int a = 1; a <= amount; a++) {
                if (coins[c - 1] > a) {
                    dp[c][a] = dp[c - 1][a];
                } else {
                    dp[c][a] = Math.min(dp[c - 1][a], dp[c][a - coins[c - 1]] + 1);
                }
            }
        }
        return dp[coins.length][amount] == Integer.MAX_VALUE - 1 ? -1 : dp[coins.length][amount];
    }




}
