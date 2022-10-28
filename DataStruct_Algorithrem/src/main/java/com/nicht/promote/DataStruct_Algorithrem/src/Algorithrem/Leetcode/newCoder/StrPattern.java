package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

/**
 * @Description
 * @Date 2022/10/13
 */
//给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
//如果 needle 不是 haystack 的一部分，则返回 -1 。
//
//
//
// 示例 1：
//
//
//输入：haystack = "sadbutsad", needle = "sad"
//输出：0
//解释："sad" 在下标 0 和 6 处匹配。
//第一个匹配项的下标是 0 ，所以返回 0 。
//
//
// 示例 2：
//
//
//输入：haystack = "leetcode", needle = "leeto"
//输出：-1
//解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
//
//
//
//
// 提示：
//
//
// 1 <= haystack.length, needle.length <= 10⁴
// haystack 和 needle 仅由小写英文字符组成
//
//
// Related Topics 双指针 字符串 字符串匹配 👍 1617 👎 0
public class StrPattern {

    public static void main(String[] args) {
       strStr("mississippi","issipi");
    }
    public  static  int strStr(String haystack, String needle) {
        int res = -1 ;
        char [] chars = needle.toCharArray();
        int l = 0, r = chars.length - 1;
        for (int i = 0,len= haystack.length() ; i < len ; i++) {
            if(haystack.charAt(i) == chars[l]  &&( i+r<len&&  haystack.charAt(i+r) == chars[r])){
                if (haystack.substring(i,i+chars.length) .equals(needle)){
                    res = i ;
                    break;
                }
            }
        }
        return  res;
    }
}
