package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述：
 *
 * @author nicht
 */
//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[
//b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
//
//
// 0 <= a, b, c, d < n
// a、b、c 和 d 互不相同
// nums[a] + nums[b] + nums[c] + nums[d] == target
//
//
// 你可以按 任意顺序 返回答案 。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
//
//
// 示例 2：
//
//
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 200
// -10⁹ <= nums[i] <= 10⁹
// -10⁹ <= target <= 10⁹
//
// Related Topics 数组 双指针 排序 👍 1212 👎 0

public class FourSum {

    public static void main(String[] args) {
        int [] nums = new int[]{-3,-2,-1,0,0,1,2,3};
        int target = 0 ;
        System.out.println(fourSum(nums,target));
    }
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>>  res = new ArrayList<>();
        HashMap<String, Boolean> map = new HashMap<>();

        Arrays.sort(nums);
        int len = nums.length;
        if(nums==null||len<4){
            return res;
        }
        for (int i = 0; i <len; i++) {
            for (int j = i+1; j <len ; j++) {
                 int l =j+1 ,r=len-1;
                 while (l<r){
                     int tmp = nums[i]+nums[j]+nums[l]+nums[r];
                     String liststr = ""+nums[i]+nums[j]+nums[l]+nums[r];
                     if(tmp == target && !map.containsKey(liststr)) {
                         List t = Arrays.asList(nums[i],nums[j],nums[l],nums[r]);
                        res.add(t);
                        map.put(liststr,true);
                        // break;   一个循环内可能有多个符合的组合 所以不应该找到一个就跳出
                     }
                     else if(tmp >target){r--;}
                     else {l++;}
                 }

            }
        }
        return  res;
    }


}
