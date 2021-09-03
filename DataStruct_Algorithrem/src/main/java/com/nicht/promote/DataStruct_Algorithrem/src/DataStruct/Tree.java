package com.nicht.promote.DataStruct_Algorithrem.src.DataStruct;

/**
 * @author Ni
 * @version 1.0
 * @date 2019/11/22 17:26
 */
// 树由链表实线
public class Tree {





}
class  TreeNode{
    public  int no;
    public  String name;
    public  String nickname;
    public  TreeNode rchild;
    public  TreeNode lchild;

    public TreeNode(int no,String name,String nickname){
        this.no= no;
        this.name = name;
        this.nickname = nickname;
    }
    @Override
    public  String toString(){ return "Hero[no = "+no+",name = "+name+", nickname="+nickname+"]";
    }
}




class Treedata1{
    private  TreeNode treeNodehead;
    //add
    public   void addnode(TreeNode node){
        if(treeNodehead.rchild== null || treeNodehead.lchild ==null){
            treeNodehead = node;
        }
            //rightnode
            while (treeNodehead !=null){
                if (treeNodehead.rchild.no < node.no){
                     if(treeNodehead.rchild == null){
                         treeNodehead.rchild = node;
                     }
                     treeNodehead = treeNodehead.rchild;
                }
                else {
                 if(treeNodehead.lchild == null){
                     treeNodehead.lchild =  node;
                     return;
                 }
                 treeNodehead = treeNodehead.lchild;
                }
            }
    }
}
