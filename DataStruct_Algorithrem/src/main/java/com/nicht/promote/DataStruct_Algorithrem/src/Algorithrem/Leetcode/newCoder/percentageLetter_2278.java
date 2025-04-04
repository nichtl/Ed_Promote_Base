package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

/**
 * @Author: ethan xu
 * @Date: 2025/4/1
 */
public class percentageLetter_2278 {
//给你一个字符串 s 和一个字符 letter ，返回在 s 中等于 letter 字符所占的 百分比 ，向下取整到最接近的百分比。
//
//
//
// 示例 1：
//
//
//输入：s = "foobar", letter = "o"
//输出：33
//解释：
//等于字母 'o' 的字符在 s 中占到的百分比是 2 / 6 * 100% = 33% ，向下取整，所以返回 33 。
//
//
// 示例 2：
//
//
//输入：s = "jjjj", letter = "k"
//输出：0
//解释：
//等于字母 'k' 的字符在 s 中占到的百分比是 0% ，所以返回 0 。
//
//
//
// 提示：
//
//
// 1 <= s.length <= 100
// s 由小写英文字母组成
// letter 是一个小写英文字母
//
//
// Related Topics 字符串 👍 37 👎 0


    public int percentageLetter(String s, char letter) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == letter) {
                count++;
            }
        }
        double percentage = (double) count / s.length() * 100;
        return (int) Math.floor(percentage);
    }

}