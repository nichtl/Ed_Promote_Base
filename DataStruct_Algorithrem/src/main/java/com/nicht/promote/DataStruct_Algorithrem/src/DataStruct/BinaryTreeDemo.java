package com.nicht.promote.DataStruct_Algorithrem.src.DataStruct;

/**
 * @author Ni
 * @version 1.0
 * @date 2019/11/22 17:26
 */
//实现树的遍历
public class BinaryTreeDemo {


    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNodeTree root = new HeroNodeTree(1,"宋江");
        HeroNodeTree node2 = new HeroNodeTree(2,"李逵");
        HeroNodeTree node3 = new HeroNodeTree(3,"林冲");
        HeroNodeTree node4 = new HeroNodeTree(4,"卢俊义");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        System.out.println("前序遍历");
        binaryTree.setRoot(root);
        binaryTree.preoder();
        System.out.println("中序遍历");
        binaryTree.indixoder();
        System.out.println("后序遍历");
        binaryTree.postoder();


    }
}


class  BinaryTree{
    private  HeroNodeTree root;
    public void setRoot(HeroNodeTree root) {
        this.root = root;
    }

    // 前序
    public  void preoder() {
        if (this.root != null) {
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空无法遍历");
        }
    }
    // 中序
    public  void indixoder() {
        if (this.root != null) {
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空无法遍历");
        }
    }
    // 前序
    public  void postoder() {
        if (this.root != null) {
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空无法遍历");
        }
    }

}
class HeroNodeTree {
    private int no;
    private String name;
    private HeroNodeTree left;
    private HeroNodeTree right;
    // 前序
    public  void   preOrder(){
        // 先输出当前节点  即 父节点
        System.out.println(this);
        //递归 左子树
        if(this.left !=null){
            // 递归左子树前序遍历
            this.left.preOrder(); // 递归调用
        }
        //递归向右子树
        if (this.right !=null){
            this.right.preOrder();
        }

    }
    // 中序
    public void  infixOrder(){
        if(this.left !=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right !=null){
            this.right.infixOrder();
        }
    }
    // 后序
    public  void postOrder(){
        if(this.left !=null){
            this.left.postOrder();
        }
        if(this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    public HeroNodeTree(int no, String name) {
        super();
        this.no = no;
        this.name = name;
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNodeTree getLeft() {
        return left;
    }

    public void setLeft(HeroNodeTree left) {
        this.left = left;
    }

    public HeroNodeTree getRight() {
        return right;
    }

    public void setRight(HeroNodeTree right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }


}




