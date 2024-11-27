package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode;


import cn.hutool.core.date.DateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * 01 背包问题
 */
public class knapsackDP {
    public static void main(String[] args) {
        System.out.println(generateBirthDaysForCurrentMonth());
    }


    private static String generateBirthDaysForCurrentMonth() {
        String month = DateUtil.format(new Date(), "MM");
        int lastDay = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
        StringBuffer days = new StringBuffer();
        for(int i = 6; i <= 9; i++){
            days.append("'").append(month).append("-0").append(i).append("',");
        }
        for(int i = 10; i <= lastDay; i++){
            days.append("'").append(month).append("-").append(i).append("',");
        }
        return days.substring(0, days.length() - 1);
    }

    //  给定 int[] wgt 容量, int[] val 价值   背包最大容量cap

    //  在限定背包容量下能放入物品的最大价值
    int knapsackDP(int[] wgt, int[] val, int cap) {
        int n = wgt.length;
        // 表示 重量 为 i 背包容量 为 c 时 的物品最大价值
         int [][] dp = new  int[wgt.length][cap+1];

        for (int i = 0; i <= n; i++) {
            for (int c = 0; c <= cap; c++) {

            }
        }
        // dp[i] = Math.max()
        return  0;
    }




}
