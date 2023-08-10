package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

/**
 * @Description
 * //字符串有三种编辑操作:插入一个英文字符、删除一个英文字符或者替换一个英文字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 * //
 * //
 * //
 * // 示例 1:
 * //
 * //
 * //输入:
 * //first = "pale"
 * //second = "ple"
 * //输出: True
 * //
 * //
 * //
 * // 示例 2:
 * //
 * //
 * //输入:
 * //first = "pales"
 * //second = "pal"
 * //输出: False
 * //
 * //
 * // Related Topics 双指针 字符串 👍 252 👎 0
 * @Date 2023/8/10
 */
public class oneEditAway {

    public static void main(String[] args) {
        String first = "islander";
        String  second = "slander";

       boolean r =   oneEditAway(first, second);
        System.out.println(r);

    }



    public static boolean oneEditAway(String first, String second) {
        int n = first.length();
        int m = second.length();

        if(Math.abs(n-m) >1){return  false;}

        if(n>m){ return  oneEditAway(second,first); }

        int i =0,j=0,diff_num = 0;
        while(i<n && j<m && diff_num <=1){
            char c1 = first.charAt(i);
            char c2 = second.charAt(j);

            if(c1  == c2 ){
                i++; j++;
            }else{
                // 相同位置不同 也需要 i++ ; j++
                if( (c1 != c2 )&& (n == m)){
                    i++ ; j++;  diff_num++;
                }else {
                    j++;
                    diff_num++;
                }
            }


        }

        return diff_num <=1 ;
    }
}
