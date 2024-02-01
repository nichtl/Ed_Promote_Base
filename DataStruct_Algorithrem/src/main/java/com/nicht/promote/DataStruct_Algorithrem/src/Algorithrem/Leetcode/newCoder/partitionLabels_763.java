package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;


import java.util.ArrayList;
import java.util.List;

//给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
//
// 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
//
// 返回一个表示每个字符串片段的长度的列表。
//
//
//示例 1：
//
//
//输入：s = "ababcbacadefegdehijhklij"
//输出：[9,7,8]
//解释：
//划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
//每个字母最多出现在一个片段中。
//像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
//
// 示例 2：
//
//
//输入：s = "eccbbbbdec"
//输出：[10]
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 500
// s 仅由小写英文字母组成
//
//
// Related Topics 贪心 哈希表 双指针 字符串 👍 1080 👎 0
public class partitionLabels_763 {
// 记录 每个字符最后一次出现的 index
    // 在遍历的过程最后不断 更新当前片段最大end的index 这个index 内的字母都是闭环的

    public List<Integer> partitionLabels(String s) {
        List<Integer> partitions = new ArrayList<>();
        int [] last = new int[26];

        int len = s.length();

        for (int i = 0; i < len; i++) {
            last[s.charAt(i) -'a'] = i;
        }

        int start=0,end=0;
        for (int i = 0; i < len; i++) {
            end = Math.max(end,last[s.charAt(i)-'a']);
            if(end == i){
               partitions.add((end - start)+1);
                start = end+1;
            }
        }
        return  partitions;
    }






}
