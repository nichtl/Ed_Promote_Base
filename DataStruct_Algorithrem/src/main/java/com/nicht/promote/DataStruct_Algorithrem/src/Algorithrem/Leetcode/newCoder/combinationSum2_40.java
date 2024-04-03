package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class combinationSum2_40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);
        boolean[] visit = new boolean[candidates.length];
        List<List<Integer>> res = new ArrayList<>(16);
        dfs(candidates,target,0,res,new ArrayList<>(),visit);

        return  res;
    }

    public  void dfs(int[] candidates, int target, int start, List<List<Integer>> res, List<Integer> path , boolean[] visit){
        if(target < 0 ) {return;}
        if(target ==0 ){ res.add(new ArrayList<>(path));}
        for (int i = start ;i< candidates.length; i++){
            if(visit[i] || (i>start &&candidates[i] == candidates[i-1] )){
                continue;
            }
            path.add(candidates[i]);
            visit[i] = true;
            dfs(candidates, target - candidates[i], i+1, res, path,visit);
            path.remove(path.size()-1);
            visit[i] = false;
        }
    }
}
