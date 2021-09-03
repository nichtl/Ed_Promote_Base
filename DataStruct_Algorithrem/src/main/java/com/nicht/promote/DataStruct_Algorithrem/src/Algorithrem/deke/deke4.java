package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.deke;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/8/26
 * @Link
 */
public class deke4 {
/*给定一个字符串S
        变化规则:
         交换字符串中任意两个不同位置的字符
        输入描述：
         一串小写字母组成的字符串
        输出描述：
         按照要求变换得到最小字符串
        实例1：
         输入：、
         abcdef
        输出
         abcdef
        实例2：
         输入
         bcdefa
         输出
         acdefb
        s都是小写字符组成
        1<=s.length<=1000*/
public static void main(String[] args) {
String  str ="asdbuiodevauufgh";
String[] str2  = str.split("[^aeiouAEIOU]");
    int len =0;
    for (int i = 0; i < str2.length-1; i++) {
        if(len< str2[i].length()){
            len= str2[i].length();
        }
    }
    System.out.println(len);

}
}
