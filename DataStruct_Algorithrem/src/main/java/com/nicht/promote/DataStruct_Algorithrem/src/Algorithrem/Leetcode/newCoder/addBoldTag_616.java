package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

/**
 * @Description
 * @Date 2023/9/22
 */
public class addBoldTag_616 {


    /**
     *
     * 给你一个字符串 s 和一个字符串列表 words ，你需要将在字符串列表中出现过的 s 的子串添加加粗闭合标签 <b> 和 </b> 。
     *
     * 如果两个子串有重叠部分，你需要把它们一起用一对闭合标签包围起来。同理，如果两个子字符串连续被加粗，那么你也需要把它们合起来用一对加粗标签包围。
     *
     * 返回添加加粗标签后的字符串s
     * 示例 1：
     *
     * 输出："<b>abc</b>xyz<b>123</b>"
     * 示例 2：
     *
     * 输入：s = "aaabbcc", words = ["aaa","aab","bc"]
     * 输出："<b>aaabbc</b>c"
     *
     *
     *
     * 1 <= s.length <= 1000
     * 0 <= words.length <= 100
     * 1 <= words[i].length <= 1000
     * s 和 words[i] 由英文字母和数字组成
     * words 中的所有值 互不相同
     */
    public static void main(String[] args) {
    }

    /**
     * int N = S.length();
     *         boolean[] mask = new boolean[N];
     *         for (int i = 0; i < N; ++i)
     *             for (String word: words) search: {
     *                 for (int k = 0; k < word.length(); ++k)
     *                     if (k+i >= S.length() || S.charAt(k+i) != word.charAt(k))
     *                         break search;
     *
     *                 for (int j = i; j < i+word.length(); ++j)
     *                     mask[j] = true;
     *             }
     *
     *         StringBuilder ans = new StringBuilder();
     *         int anchor = 0;
     *         for (int i = 0; i < N; ++i) {
     *             if (mask[i] && (i == 0 || !mask[i-1]))
     *                 ans.append("<b>");
     *             ans.append(S.charAt(i));
     *             if (mask[i] && (i == N-1 || !mask[i+1]))
     *                 ans.append("</b>");
     *         }
     *         return ans.toString();

     */

    // 打标记
    public String addBoldTag(String s, String[] words) {
        int  N  = s.length();
        boolean mask[] = new boolean[N];

        for (int i = 0; i < N; i++) {

            for(String word:words) search:{
               // 从当前 位置开始 逐一和words 比对存在的mask置为true

                for(int k = 0 ; k < word.length();++k){
                    if( (k+i)>=N ||  s.charAt(k+i)!= word.charAt(k) ){
                        break search;
                    }
                }

                for (int j = i ; j < i+word.length(); j++) {
                    mask[j] = true;
                }
            }
        }


        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < N; i++) {
             if(mask[i] && (i==0 || !mask[i-1])){
                 ans.append("<b>");
             }
             ans.append(s.charAt(i));

             if(mask[i] && (i==N-1 || !mask[i+1] ) ){
                ans.append("</b>");
             }

        }

        return  ans.toString();

    }

}
