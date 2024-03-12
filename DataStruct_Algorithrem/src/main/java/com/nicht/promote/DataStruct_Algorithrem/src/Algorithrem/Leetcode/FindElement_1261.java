package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode;

import java.util.Set;
import java.util.TreeSet;

public class FindElement_1261 {
    private  TreeNode head;

    private Set<Integer> set ;




    public FindElement_1261(TreeNode root) {
        root.val =0 ;
      this.revertTree(root);
      set = new TreeSet<>();
      head = root;
    }

    public boolean find(int target) {
        return  set.contains(target);
       // return  findTarget(target,this.head);
    }


    public  boolean findTarget(int target,TreeNode tmp){
       if(tmp == null )return  false;
       if(tmp.val == target) return  true;
           return  findTarget(target,tmp.right) ||   findTarget(target,tmp.left);
    }

      // root.val == 0
     // 如果 treeNode.val == x 且 treeNode.left != null，那么 treeNode.left.val == 2 * x +
    //1
   // 如果 treeNode.val == x 且 treeNode.right != null，那么 treeNode.right.val == 2 * x
  //+ 2
    public  void revertTree(TreeNode root){

        if(root==null){return; }

        set.add(root.val);
        if(root.left!=null){
            root.left.val = 2* root.val + 1;
        }
        if(root.right!=null){
            root.right.val = 2* root.val + 2;
        }

        revertTree(root.left);
        revertTree(root.right);
    }

    static class TreeNode {
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

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
