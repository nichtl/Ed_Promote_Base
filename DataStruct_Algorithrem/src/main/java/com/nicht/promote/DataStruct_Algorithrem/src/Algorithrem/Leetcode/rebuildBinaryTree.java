package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode;

import java.util.HashMap;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/9/1
 * @Link
 */
public class rebuildBinaryTree {
//输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
//
// 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
//
//
//
// 示例 1:
//
//
//Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//Output: [3,9,20,null,null,15,7]
//
//
// 示例 2:
//
//
//Input: preorder = [-1], inorder = [-1]
//Output: [-1]
//
//
//
//
// 限制：
//
// 0 <= 节点个数 <= 5000
//
//
//
// 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-
//preorder-and-inorder-traversal/
// Related Topics 树 数组 哈希表 分治 二叉树
// 👍 550 👎 0
// preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    static public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    }
    HashMap<Integer,Integer>  map  = new HashMap<>();  // key:index  value:num
    int[] preorder;
    public static void main(String[] args) {
        int [] preorder = new int[]{3,9,20,15,7}, inorder = {9,3,15,20,7};
        System.out.println(search(inorder,3));

    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder; // 保存先序遍历的值
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }


     return  reduce(0,0, inorder.length-1);
    }
    public  TreeNode reduce(int root,int left,int right){
        if(left>right){
            return  null;
        }
        //  获取中序遍历中当前根节点的index,方便计算当前节点左子树lenth
        int in_dex=map.get(preorder[root]);
        TreeNode  node = new TreeNode(preorder[root]);
        // 当前节点的下个左子树的根节点 为当前根节点index+1
        // 左子树左边界为
        // 右边界为 中序遍历中  当前根节点index -1
        System.out.println("left"+left+"right"+right);
        node.left =    reduce(root+1,left,in_dex-1);
        // 当前节点的下个右子树的根节点 为当前根节点index+左子树的长度+1
        // 当前节点的下个右子树的左边界为当前 根节点在中序遍历中的index+1
        node.right =   reduce(root+(in_dex-left)+1,in_dex+1,right);
        return  node;
    }





    public static int search(int [] nums,int target){
      if(nums==null) return -1;
      if (nums.length==0) return  nums[0]==target?0:-1;
      int left =0,right= nums.length-1;
      int res= -1;
      while (left<right){
          if(nums[right]==target||nums[left]==target){
              res= nums[right]==target?right:left;
              break;
          }
          if(nums[left]!=target){
              left++;
          }
          if(nums[right]!=target){
              right--;
          }
      }
      return  res;
    }
}
