package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.codeTop;

public class lt415 {


    public String addStrings(String num1, String num2) {
        if (num1.length() < num2.length()) {
            return addStrings(num2, num1);
        }
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int num1Len = num1.length() - 1;
        int num2Len = num2.length() - 1;

        while (num1Len >= 0 || num2Len >= 0) {
            String num1Char = String.valueOf(num1.charAt(num1Len));
            String num2Char = num2Len < 0 ? "0" : String.valueOf(num2.charAt(num2Len));
            int sum = Integer.parseInt(num1Char) + Integer.parseInt(num2Char) + carry;
            carry = sum / 10;
            result.append(sum % 10);
            num1Len--;
            num2Len--;
        }
        if (carry > 0) {
            result.append(carry);
        }
        return result.reverse().toString();
    }
}
