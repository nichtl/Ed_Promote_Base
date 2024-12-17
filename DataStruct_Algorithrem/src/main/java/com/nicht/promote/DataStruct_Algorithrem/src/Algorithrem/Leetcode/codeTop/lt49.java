package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.codeTop;

import cn.hutool.core.date.DateUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class lt49 {
    public static void main(String[] args) {
        System.out.println(DateUtil.parseDateTime("0000-00-00 00:00:00"));
    }
    //全排列
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums,new ArrayList<>(),result);
        return  result;
    }

    public  void dfs(int[]nums,List<Integer> path,List<List<Integer>> result){
        if(path.size()== nums.length){
            result.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(path.contains(nums[i])){
                continue;
            }
            path.add(nums[i]);
            dfs(nums,path,result);
            path.remove(path.size()-1);
        }
    }
}
