package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.codeTop;

public class lt14 {


    public static void main(String[] args) {

    }


    public String longestCommonPrefix(String[] strs) {
        String result = strs[0];
        for (int i = 0; i < strs.length; i++) {
             int index = 0;
             int minLen = Math.min(result.length(),strs[i].length());
             while(index < minLen && result.charAt(index) == strs[i].charAt(index) ){
                 index++;
             }
             result = result.substring(0,index);
        }
        return  result;
    }
}
