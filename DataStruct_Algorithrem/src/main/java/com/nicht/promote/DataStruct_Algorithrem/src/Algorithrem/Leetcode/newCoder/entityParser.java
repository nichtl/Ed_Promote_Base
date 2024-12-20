package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.HashMap;

/**
 * @author xujian8
 * @description
 * @date 2023/11/23
 * @link
 */
//「HTML 实体解析器」 是一种特殊的解析器，它将 HTML 代码作为输入，并用字符本身替换掉所有这些特殊的字符实体。
//
// HTML 里这些特殊字符和它们对应的字符实体包括：
//
//
// 双引号：字符实体为 &quot; ，对应的字符是 " 。
// 单引号：字符实体为 &apos; ，对应的字符是 ' 。
// 与符号：字符实体为 &amp; ，对应对的字符是 & 。
// 大于号：字符实体为 &gt; ，对应的字符是 > 。
// 小于号：字符实体为 &lt; ，对应的字符是 < 。
// 斜线号：字符实体为 &frasl; ，对应的字符是 / 。
//
//
// 给你输入字符串 text ，请你实现一个 HTML 实体解析器，返回解析器解析后的结果。
//
//
//
// 示例 1：
//
//
//输入：text = "&amp; is an HTML entity but &ambassador; is not."
//输出："& is an HTML entity but &ambassador; is not."
//解释：解析器把字符实体 &amp; 用 & 替换
//
//
// 示例 2：
//
//
//输入：text = "and I quote: &quot;...&quot;"
//输出："and I quote: \"...\""
//
//
// 示例 3：
//
//
//输入：text = "Stay home! Practice on Leetcode :)"
//输出："Stay home! Practice on Leetcode :)"
//
//
// 示例 4：
//
//
//输入：text = "x &gt; y &amp;&amp; x &lt; y is always false"
//输出："x > y && x < y is always false"
//
//
// 示例 5：
//
//
//输入：text = "leetcode.com&frasl;problemset&frasl;all"
//输出："leetcode.com/problemset/all"
//
//
//
//
// 提示：
//
//
// 1 <= text.length <= 10^5
// 字符串可能包含 256 个ASCII 字符中的任意字符。
//
//
// Related Topics 哈希表 字符串 👍 46 👎 0

public class entityParser {
    public static void main(String[] args) {
        String testStr ="&amp; is an HTML entity but &ambassador; is not.";
        System.out.println( entityParser(testStr) );
    }



    public  static  String entityParser(String text) {
        StringBuilder sb = new StringBuilder();

        HashMap<String,String> cacheMap = new HashMap<>(16);

          cacheMap.put("&quot;","\"");
          cacheMap.put("&apos;","'");
          cacheMap.put("&amp;","&");
          cacheMap.put("&gt;",">");
          cacheMap.put("&lt;","<");
          cacheMap.put("&frasl;","/");

          char startChar='&';
          char endChar=';';



          int i = 0;
          int len =text.length();
          while( i < len ){
             if( startChar  == text.charAt(i) ){
                 int j =i+1;
                 while (j<len && j-i<6  &&endChar!=text.charAt(j)  ) j++;

                 String subStr = text.substring(i,j+1);

                 if(cacheMap.containsKey(subStr)){
                     sb.append(cacheMap.get(subStr));
                     i = j;
                     continue;
                 }
             }
                 sb.append(text.charAt(i));
                 i++;


          }


          return  sb.toString();

    }






}
