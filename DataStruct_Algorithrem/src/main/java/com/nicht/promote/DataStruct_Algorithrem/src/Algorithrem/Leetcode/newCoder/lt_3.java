package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.HashMap;

public class lt_3 {
    // 给定一个字符串 s ，请你找出其中不含有重复字符的 最长
    //子串
    // 的长度。
    //示例 1:
    //输入: s = "abcabcbb"
    //输出: 3
    //解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
    //示例 2:
    //输入: s = "bbbbb"
    //输出: 1
    //解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
    //示例 3:
    //输入: s = "pwwkew"
    //输出: 3
    //解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
    //     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int n = s.length();
        int i = -1;
        int res = 0;
        for (int j = 0; j < n; j++) {
            char c = s.charAt(j);
            if (map.containsKey(c)) {
                i = Math.max(i, map.get(c));
            }
            map.put(c, j);
            res = Math.max(res, j - i);
        }
        return res;
    }
}
