package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能描述：
 *
 * @author xujian8
 */
public class SearchTreelargestValues {

    Map<Integer,Integer> perFloorMaxValueMap  = new HashMap<>();


    public List<Integer> largestValues(SearchTreelargestValues.TreeNode root) {
        if(root == null)  {
            return  new ArrayList<>();
        }
         perFloorMaxValueMap.put(0,root.val);
         dfs(root.left,1);
         dfs(root.right,1);
         return  perFloorMaxValueMap.values().stream().collect(Collectors.toList());

    }

    public void dfs(SearchTreelargestValues.TreeNode root,Integer perFloor){
        if(root ==null) {
            return;
        }
        if( !perFloorMaxValueMap.containsKey(perFloor)||perFloorMaxValueMap.get(perFloor)< root.val){
            perFloorMaxValueMap.put(perFloor, root.val);
        }
        dfs(root.left,perFloor+1);
        dfs(root.right,perFloor+1);
    }


    public class TreeNode {
        int val;
        SearchTreelargestValues.TreeNode left;
        SearchTreelargestValues.TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, SearchTreelargestValues.TreeNode left, SearchTreelargestValues.TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }







}
