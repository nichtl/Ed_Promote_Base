package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/5/12
 * @Link
 */
public class lengthOfLongestSubstring {


    //给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
//
//
// 示例 1:
//
//
//输入: s = "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
//
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
//
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
//
// 示例 4:
//
//
//输入: s = ""
//输出: 0
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 5 * 104
// s 由英文字母、数字、符号和空格组成
//
// Related Topics 哈希表 双指针 字符串 Sliding Window
// 👍 5443 👎 0


    public  static  int  lengthOfLongestSubstring(String s){
        Map<Character,Integer> indexMap =  new HashMap<>();
        int  maxStrlen = 0;
        int  leftindex = 0;
        for (int i = 0; i < s.length(); i++) {
            if(indexMap.containsKey(s.charAt(i)) ){
                leftindex = Math.max(leftindex,indexMap.get(s.charAt(i))+1);
            }
            indexMap.put(s.charAt(i),i);
            maxStrlen = Math.max(maxStrlen,i-leftindex+1);
        }
        return maxStrlen;
    }


    public  static  int  maxlenStr(String s){
        HashMap<Character,Integer> hashMap  = new HashMap();
        int maxlen = 0 ;
        int leftindex =0;
        for (int i = 0; i < s.length(); i++) {
            if(hashMap.containsKey(s.charAt(i))){
                leftindex = Math.max(leftindex, hashMap.get(s.charAt(i))+1);  //更新无重复字符串左边界
            }
            hashMap.put(s.charAt(i),i);
            maxlen = Math.max(maxlen,(i+1)-leftindex);
        }
        return  maxlen;
    }
    }
//leetcode submit region end(Prohibit modification and deletion)


