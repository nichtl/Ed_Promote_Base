package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

/**
 * @Description
 * @Date 2023/10/20
 */
//给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
//
//
// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
//
//
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
//
//
//
// 示例 1：
//
//
//输入：text1 = "abcde", text2 = "ace"
//输出：3
//解释：最长公共子序列是 "ace" ，它的长度为 3 。
//
//
// 示例 2：
//
//
//输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc" ，它的长度为 3 。
//
//
// 示例 3：
//
//
//输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0 。
//
//
//
//
// 提示：
//
//
// 1 <= text1.length, text2.length <= 1000
// text1 和 text2 仅由小写英文字符组成。
//
//
// Related Topics 字符串 动态规划 👍 1446 👎 0

public class longestCommonSubsequence {
    /**
     * text1 =  a b c d e   text2 = a c e
     * i                   j
     * i = 0 j =0  时  都是 i-1 j-1 都是 空字符 公共子序=0 使用 第一列 及第一行均为 0
     * 当
     * i >1 && j>1 时
     * 当 text1 [i] = text2 [j] 时    为 dp[i-1][j-1]+1
     * 当 text1 [i] != text2 [j] 时    dp[i][j] 的最长公共子串要看text1   0 i-1 以及
     * <p>
     * <p>
     * dp[i][j] 表示 text1 0 - i   以及text2 0 - j 的最长公共子序列
     * n=5+1      m=3+1
     *      0   1  2  3
     * 0  ｜0 ｜0 ｜0 ｜0｜
     * 1  ｜0 ｜  ｜  ｜ ｜
     * 2  ｜0 ｜  ｜  ｜ ｜
     * 3  ｜0 ｜  ｜  ｜ ｜
     * 4  ｜0 ｜  ｜  ｜ ｜
     * 5  ｜0 ｜  ｜  ｜ ｜
     */

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

}
