package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class permute_46 {


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(16);
        List<Integer> path = new ArrayList<>(16);
        dfs(nums, path, result);
        return result;
    }


    public void dfs(int[] nums, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            dfs(nums, path, result);
            path.remove(path.size() - 1);
        }
    }

}
