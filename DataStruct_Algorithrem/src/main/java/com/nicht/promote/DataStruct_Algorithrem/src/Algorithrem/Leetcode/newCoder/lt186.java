package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

/**
 * @Description
 * @Date 2023/9/19
 */
public class lt186 {


    public static void main(String[] args) {

    }

    /**
     * 给你一个字符数组 s ，反转其中 单词 的顺序。
     *
     * 单词 的定义为：单词是一个由非空格字符组成的序列。s 中的单词将会由单个空格分隔。
     *
     * 必须设计并实现 原地 解法来解决此问题，即不分配额外的空间。
     * 示例 1：
     *            0  13
     * 输入：s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
     * 输出：["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
     * 示例 2：
     *
     * 输入：s = ["a"]
     * 输出：["a"]
     *
     * 提示：
     *
     * 1 <= s.length <= 105
     * s[i] 可以是一个英文字母（大写或小写）、数字、或是空格 ' ' 。
     * s 中至少存在一个单词
     * s 不含前导或尾随空格
     * 题目数据保证：s 中的每个单词都由单个空格分隔
     */
    public void reverseWords(char[] s) {
        reverse(0,s.length-1,s);

        int start = 0 ;
        for (int i = 0; i <s.length ; i++) {

            if(s[i] != ' '){continue;}
            reverse(start,i-1,s);
            start = i+1;
        }
        reverse(start,s.length-1,s);
    }
    public  void reverse(int start, int end,char [] s) {
        while (start < end){
            char tmp = s[start];
            s[start] = s[end];
            s[end] = tmp;
            start++; end--;
        }
    }


}
