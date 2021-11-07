package com.nicht.promote.DataStruct_Algorithrem.src.DataStruct;

import java.util.LinkedList;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/5/25
 * @Link
 */
public class LRU {
    public static void main(String[] args) {
        int [] num = new int[]{5,7,7,8,8,10};
        System.out.println(helper(num,8));
        System.out.println(binarySearch(num,7));
        System.out.println(reverseWords("I am a student. "));
    }

    public static String reverseWords(String s) {
        String [] strs = s.split("\\s+");
        StringBuilder  sb  = new StringBuilder();
        for (int i = strs.length-1; i >=0 ; i--) {
            if(strs[i].contains(" ")){continue;}
            sb.append(strs[i]).append(" ");
        }
        return sb.toString();
    }

    public static int search(int[] nums, int target) {
     return 0;
    }
    LinkedList<Integer> path = new LinkedList<>();
   static int helper(int[] nums, int tar) {
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) / 2;
            if(nums[m] <= tar) i = m + 1;
            else j = m - 1;
        }
        System.out.println(i+""+j);
        return i;
    }

   public static int binarySearch(int[] nums,int target) {
       int left = 0;
       int right = nums.length - 1;
       int mid = 0;
       while (left <= right) {
           mid =  (right +left) / 2;
           if (nums[mid] == target){ mid+=1;break;}
           if(nums[mid]<target){
               left=mid+1;
           }else {
               right=mid-1;
           }
       }
       return  mid;
   }














}
