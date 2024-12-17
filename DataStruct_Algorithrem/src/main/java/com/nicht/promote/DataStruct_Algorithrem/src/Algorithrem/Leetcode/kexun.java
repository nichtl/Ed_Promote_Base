package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Date 2023/9/6
 */
public class kexun {
    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * <p>
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * <p>
     * 输入: s = "pww kew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * @param s
     * @return
     */

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> cache = new HashMap<>();
        int maxLen = 0;

        int l = -1;

        for (int r = 0; r < s.length(); r++) {
            if (cache.containsKey(s.charAt(r))) {
                l = Math.max(l, cache.get(s.charAt(r)));
            }
            cache.put(s.charAt(r), r);
            maxLen = Math.max(maxLen, r - l);
        }
        return maxLen;

    }
}
