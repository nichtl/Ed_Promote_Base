package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.HashMap;

/**
 * @Description
 * @Date 2023/9/20
 */
public class numKLenSubstrNoRepeats {

    /**
     * 给你一个字符串 S，找出所有长度为 K 且不含重复字符的子串，请你返回全部满足要求的子串的 数目。
     * @param s
     * @param k
     *
     * 示例 1：
     *
     * 输入：S = "havefunonleetcode", K = 5
     * 输出：6
     * 解释：
     * 这里有 6 个满足题意的子串，分别是：'havef','avefu','vefun','efuno','etcod','tcode'。
     * 示例 2：
     *
     * 输入：S = "home", K = 5
     * 输出：0
     * 解释：
     * 注意：K 可能会大于 S 的长度。在这种情况下，就无法找到任何长度为 K 的子串。
     * 提示：
     * 1 <= S.length <= 10^4
     * S 中的所有字符均为小写英文字母
     * 1 <= K <= 10^4
     * 1100
     */
    public int numKLenSubstrNoRepeats(String s, int k){
           // r< end;
           // l r
           // repeat l ++ ; r++;
           // no repeat  if r -l <k r++;  else if r - l = k ;count++;  l++; r++;
        int len = s.length();
        if( k >len || k>26){
            return 0;
        }

        int count=0; int left = 0; int right = 0;

        int[] repeatArr = new int[26];
        while(right<len){
             repeatArr[s.charAt(right)-'a']++;

             while(repeatArr[s.charAt(right)-'a']>1){
                 repeatArr[s.charAt(left)-'a']--;
                 left++;
             }

             if(right  - left +1 == k){
                 repeatArr[s.charAt(left)-'a']--;
                 count++;
                 left++;
             }

            right++;
        }

       return  count;

    }
}
