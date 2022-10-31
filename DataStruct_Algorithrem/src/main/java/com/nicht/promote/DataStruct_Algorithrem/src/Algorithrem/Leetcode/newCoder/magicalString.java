package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

/**
 * @Description
 * @Date 2022/10/31
 */

/**
 * 神奇字符串 s 仅由 '1' 和 '2' 组成，并需要遵守下面的规则：
 *
 *
 *  神奇字符串 s 的神奇之处在于，串联字符串中 '1' 和 '2' 的连续出现次数可以生成该字符串。
 *
 *
 *  s 的前几个元素是 s = "1221121221221121122……" 。如果将 s 中连续的若干 1 和 2 进行分组，可以得到 "1 22 11
 * 2 1 22 1 22 11 2 11 22 ......" 。每组中 1 或者 2 的出现次数分别是 "1 2 2 1 1 2 1 2 2 1 2 2 ...
 * ..." 。上面的出现次数正是 s 自身。
 * 1
 *  给你一个整数 n ，返回在神奇字符串 s 的前 n 个数字中 1 的数目。
 *
 *
 *
 *  示例 1：
 *
 *
 * 输入：n = 6
 * 输出：3
 * 解释：神奇字符串 s 的前 6 个元素是 “122112”，它包含三个 1，因此返回 3 。
 *
 *
 *  示例 2：
 *
 *
 * 输入：n = 1
 * 输出：1
 *
 *
 *
 *
 *  提示：
 *
 *
 *  1 <= n <= 10⁵
 *
 *
 *  Related Topics 双指针 字符串 👍 90 👎 0
 */

/**
 *  lt 481
 *
 */
public class magicalString {


    public static void main(String[] args) {
        System.out.println(magicalString(4));
    }
    //  122
    public static int magicalString(int n) {
        StringBuilder  s = new StringBuilder("122");
        int countIndex = 2;
        boolean  isOne = true;
        while (s.length()<n){
            Integer  times = Integer.valueOf(String.valueOf(s.charAt(countIndex)));
            for (int i = 0; i <times ; i++) {
                if(isOne){
                    s.append("1");
                }else{
                    s.append("2");
                }
            }
            isOne = !isOne;
            countIndex++;
        }
        String substring = s.substring(0, n);
        int appearCount = 0 ;
        for (int i = 0; i <substring.length() ; i++) {
            if(substring.charAt(i) == '1'){appearCount++;}
        }
        return  appearCount;

    }












}
