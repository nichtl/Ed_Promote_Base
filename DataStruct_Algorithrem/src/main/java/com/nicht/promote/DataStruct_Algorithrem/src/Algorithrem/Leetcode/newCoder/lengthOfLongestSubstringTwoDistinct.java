package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.Collections;
import java.util.HashMap;

/**
 * @Description
 * @Date 2023/9/19
 */
public class lengthOfLongestSubstringTwoDistinct {


    /**
     * 给你一个字符串 s ，请你找出 至多 包含 两个不同字符 的最长子串，并返回该子串的长度。
     *
     *
     * 示例 1：
     *
     * 输入：s = "eceba"
     * 输出：3
     * 解释：满足题目要求的子串是 "ece" ，长度为 3 。
     * 示例 2：
     *
     * 输入：s = "ccaabbb"
     * 输出：5
     * 解释：满足题目要求的子串是 "aabbb" ，长度为 5 。
     *
     *
     * 提示：
     *
     * 1 <= s.length <= 105
     * s 由英文字母组成
     *  lt 159
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n  =  s.length();

        if(n<3){
        return  n;
        }
        HashMap<Character,Integer> cache = new HashMap<Character,Integer>();
           int l =0;
           int r =0;
           int len=2;

           while(r < n){
                cache.put(s.charAt(r),r++);
                if(cache.size()==3){ //存在 两个以上元素
                    int minL = Collections.min( cache.values() ); // 移除最左边元素
                    cache.remove(s.charAt(minL));
                    // 移除后更新 区间值
                    l = minL+1;
                }
                len = Math.max(len,r-l); // 每次循环都更新最大区间值
           }
        return  len ;
    }
    public int lengthOfLongestSubstringKDistinct(String s,int k ) {
        int n  =  s.length();

        if(n<3){
            return  n;
        }
        HashMap<Character,Integer> cache = new HashMap<Character,Integer>();
        int l =0;
        int r =0;
        int len=2;

        while(r < n){
            cache.put(s.charAt(r),r++);
            if(cache.size()== k){ //存在 两个以上元素
                int minL = Collections.min( cache.values() ); // 移除最左边元素
                cache.remove(s.charAt(minL));
                // 移除后更新 区间值
                l = minL+1;
            }
            len = Math.max(len,r-l); // 每次循环都更新最大区间值
        }
        return  len ;

    }



}
