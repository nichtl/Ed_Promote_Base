package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode;

import java.util.ArrayList;
import java.util.List;

//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 8
// -10 <= nums[i] <= 10
//
//
// Related Topics 数组 回溯 👍 1539 👎 0

public class permuteUnique {

    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean visit[] = new boolean[nums.length];

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        dfs(nums,visit,path,result);
        return  result;
    }


   void  dfs(int[] nums, boolean[] visit,List<Integer> path ,List<List<Integer>> result){
         if(path.size() == nums.length) {
             result.add(path);
             return;
         }
       for (int i = 0; i < nums.length; i++) {
            if(visit[i]){
                continue;
            }
            visit[i] = true;
            path.add(nums[i]);
            dfs(nums,visit,path,result);
            visit[i] = false;
            path.remove(path.size()-1);
       }
    }


}
