package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem;

import java.util.HashMap;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/6/28
 * @Link
 */
public class longestPalindrome {
    /**
     *  a b a b a     i>2
     *      i
     * 分别求i为中心的中心扩散(子回文串为奇数的情况)   以及  i与 i+1的中心扩散(字回文串为偶数情况)
     *  Math.max(len1,len2)  // 即以
     *
     *
     * */
    public static void main(String[] args) {

        HashMap  map  =new HashMap();
        map.put("name","zhangsan");
        System.out.println(map.toString());
       // System.out.println(longestPalin("ccc"));
    }

     public static String longestPalin(String s){
       // centerSpead
       // 1 枚举每个字符 以当前这个字符为中心  向两边进行扩散 求该回文子串的最大长度
         if(s==null || s.length()<2){return   "";}
         int start =0, end = 0; // 记录当前回文字串的开始结束下标位置
         char[] charArray = s.toCharArray();
         for (int i = 0; i < s.length()-1; i++) {
             System.out.println(i);
              int j1 = expandAroundCenter(s,i,i);
              int o2 = expandAroundCenter(s,i,i+1);
              int len = Math.max(j1,o2); //如果有更长的 回文串 替换
              if(len > end - start){
                  start  =  i -(len-1)/2;
                  end   = i+len/2;
              }
         }
         return   s.substring(start,end+1);
     }

     private static  int expandAroundCenter(String s,int  left ,int right){
        while (left>=0 && right <=s.length()-1 && s.charAt(left)==s.charAt(right) ){
            right++;
            left--;
        }
        return   right-left-1;
     }



}
