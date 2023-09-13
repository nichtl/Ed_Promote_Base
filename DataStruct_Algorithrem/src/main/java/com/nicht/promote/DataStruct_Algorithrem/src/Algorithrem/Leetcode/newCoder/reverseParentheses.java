package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.Stack;

/**
 * @Description
 * @Date 2023/9/13
 */

//给出一个字符串 s（仅含有小写英文字母和括号）。
//
// 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
//
// 注意，您的结果中 不应 包含任何括号。
// 示例 1：
//输入：s = "(abcd)"
//输出："dcba"
//
//
// 示例 2：
//
//
//输入：s = "(u(love)i)"
//输出："iloveu"
//解释：先反转子字符串 "love" ，然后反转整个字符串。
//
// 示例 3：
//
//
//输入：s = "(ed(et(oc))el)"          -> ed
//输出："leetcode"
//解释：先反转子字符串 "oc" ，接着反转 "etco" ，然后反转整个字符串。
//
// 示例 4：
//
//
//输入：s = "a(bcdefghijkl(mno)p)q"
//输出："apmnolkjihgfedcbq"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 2000
// s 中只有小写英文字母和括号
// 题目测试用例确保所有括号都是成对出现的
//
//
// Related Topics 栈 字符串 👍 276 👎 0
// lt 1190
public class reverseParentheses {

    public String reverseParentheses(String s) {

          StringBuilder sb = new StringBuilder();
          Stack<String> stack = new Stack<String>();

        for (int i = 0,len=s.length(); i <len ; i++) {
            int ch = s.charAt(i);
            if (ch == '(') {
                stack.push(sb.toString());
                sb.setLength(0);
            } else if (ch == ')') {
                sb.reverse();
                sb.insert(0, stack.pop());
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }


}
