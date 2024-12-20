package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.codeTop;
import java.util.*;

public class lt199 {
    /**
     * 一个概念怪物  当人认为他是无解便会成型并且成型不收后续的观念影响,有超高智商,思维
     *  一个未知的人 以自己的名字命名了一本书籍可以将意识具现化为存在,在辗转波折中最终被怪物知道
     * 从怪物的本源分离出 一个名为百里落心的概念人,最终怪物认为的怪物
     * @return
     */
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
   Stack<String> stack = new Stack<>();
    /**
     * 二叉树的右视图
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
         // 遍历保存每一层
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
          int levelSize = queue.size();
          TreeNode lastNode = null;
          for (int i = 0; i <levelSize ; i++) {
              lastNode = queue.poll();
              if(lastNode.left!=null){
                  queue.add(lastNode.left);
              }
              if(lastNode.right!=null){
                  queue.add(lastNode.right);
              }
          }
            result.add(lastNode.val);
        }
        return result;
    }


    public List<Integer> rightSideView2(TreeNode root) {
           List<Integer> result = new ArrayList<>();
           Queue<TreeNode> queue  = new LinkedList<>();
           queue.add(root);
           while (!queue.isEmpty()){
               TreeNode lastNode = null;
               for (int i = 0; i < queue.size() ; i++) {
                   lastNode = queue.poll();
                   if(lastNode.left!=null){
                       queue.add(lastNode.left);
                   }
                   if(lastNode.right!=null){
                       queue.add(lastNode.right);
                   }
               }
               result.add(lastNode.val);
           }
           return  result;
    }

    }
