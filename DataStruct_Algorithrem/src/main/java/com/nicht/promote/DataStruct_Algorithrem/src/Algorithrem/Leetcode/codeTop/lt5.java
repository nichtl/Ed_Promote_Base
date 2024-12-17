package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.codeTop;

public class lt5 {

    public static void main(String[] args) {
        String s ="acdebbedca";
        int i = 4;
        int j = 5;
        System.out.println(s.charAt(i));
        System.out.println(s.charAt(j));
        while (i >= 0 && j < s.length() && s.charAt(i--) == s.charAt(j++)) {
        }
        System.out.println(i);
        System.out.println(j);
        System.out.println(longestPalindrome(s));
    }

    public static String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String str1 = expand(i, i + 1, s);
            String str2 = expand(i, i, s);
            res = str1.length() > res.length() ? str1 : res;
            res = str2.length() > res.length() ? str2 : res;
        }
        return res;
    }

    public  static String expand(int i, int j, String s) {
        String res = "";
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        res = s.substring(i+1, j);
        return res;
    }
}
