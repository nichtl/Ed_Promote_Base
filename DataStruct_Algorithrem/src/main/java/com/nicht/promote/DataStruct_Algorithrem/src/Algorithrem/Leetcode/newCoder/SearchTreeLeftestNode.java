package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;


/**
 * 功能描述：
 * 513. 找树左下角的值
 * @author nicht
 */
public class SearchTreeLeftestNode {
    int MaxHeight =0;
    int curVal    =0;


    public int findBottomLeftValue(TreeNode root) {
       dfs(root,1);
       return  curVal;
    }

    public  void dfs(TreeNode root ,int height){
        if(root == null) {
            return;
        }
        if(MaxHeight < height){
            MaxHeight = height;
            curVal = root.val;
        }
        dfs(root.left,height+1);
        dfs(root.right,height+1);

    }


    
    
     public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }


}
