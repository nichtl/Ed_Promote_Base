package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

/**
 * @Description
 * @Date 2023/9/8
 */
public class reverseWords {





    // lt 151
    public String reverseWords(String s) {
         StringBuilder result = new StringBuilder();

         s=s.trim();


         int i = s.length()-1;
         int j = s.length()-1;

         while (i>=0){
             while ( i>=0 && s.charAt(i) != ' ') i--;
             result.append(s.substring(i+1,j+1) + " ");
             while (i>=0 && s.charAt(i) == ' ') i--;
             j=i;
         }
         return  result.toString().trim();

    }
















}
