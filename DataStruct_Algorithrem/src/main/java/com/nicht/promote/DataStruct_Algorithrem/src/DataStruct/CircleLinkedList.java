package com.nicht.promote.DataStruct_Algorithrem.src.DataStruct;
/**
 * @author Ni
 * @version 1.0
 * @date 2019/11/22 17:26
 */
// 双向链表
// 约瑟夫问题
public class CircleLinkedList {
    public static void main(String[] args) {

        DoubleHeroNode hero1 = new DoubleHeroNode(1, "宋江", "及时雨");
        DoubleHeroNode hero2 = new DoubleHeroNode(6, "卢俊义", "玉麒麟");
        DoubleHeroNode hero3 = new DoubleHeroNode(4, "吴用", "智多星");
        DoubleHeroNode hero4 = new DoubleHeroNode(5, "李逵", "黑旋风");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();
        // 修改
        DoubleHeroNode hero5 = new DoubleHeroNode(5, "公孙胜", "入云龙");
        doubleLinkedList.update(hero5);
        doubleLinkedList.list();
        // 删除
        doubleLinkedList.RemoveNodeById(1);
        doubleLinkedList.list();

    }

}

class  DoubleHeroNode{
    public  int no         ;
    public  String name    ;
    public  String nickname;
    public  DoubleHeroNode next  ; //指向下一个
    public  DoubleHeroNode pre   ;//指向上一个
    //构造器
    public DoubleHeroNode(int no,String name,String nickname){
        this.no= no;
        this.name = name;
        this.nickname = nickname;

    }
    @Override
    public  String toString(){ return "Hero[no = "+no+",name = "+name+", nickname="+nickname+"]";
    }
}


class  DoubleLinkedList{
    // 初始化一个头节点,头节点不要动，不存放具体的数据
    private  DoubleHeroNode head = new DoubleHeroNode(0,"","");
    //返回头节点
    public  DoubleHeroNode getHead(){
        return head;
    }
    //遍历双向链表
    // 显示遍历链表
    public  void  list(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //保证不改变头节点
        DoubleHeroNode temp = head.next;// 经过判断 至少有一个数据
        while (true){
            //判断 是否到链表最后
            if (temp == null){
                break;
            }
            // 不为空输出
            System.out.println(temp);
            // 把 temp后移到下一个节点
            temp = temp.next;
        }
    }
    //添加 到尾部
    public  void  add (DoubleHeroNode heroNode){
        // 因为 head 节点不能动需要一个辅助 遍历temp
        DoubleHeroNode temp = head ;
        while(true){
            // 遍历链表 找到最后
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后，将 temp 后移
            temp = temp.next;
        }
        // 遍历链表 ，当遍历到next节点为空时退出，这意味着temp指向链表的最后
        //将最后这个节点的next 指向新的节点
        temp.next = heroNode ;// 把新的节点加入最后一位
        heroNode.pre = temp  ; //加入节点的 pre 指向当前temp
    }
    public  void delete(DoubleHeroNode hreonode){
        DoubleHeroNode temp = head;
        boolean flag = false;
    }
    //修改链表节点
    //思路 1 根据 根据 节点的  no 下标遍历匹配 到 节点 替换 该节点
    public  void  update(DoubleHeroNode heroNode){
        DoubleHeroNode temp = head;
        boolean flag = false; // 标记查找状态
        while (true){
            if (temp.next == null ){
                break;// 说明已经遍历完毕没有找到 退出
            }
            if (temp.next.no == heroNode.no){
                flag =true;
                // 找到 要修改的节点
                break;
            }
            // 当前为找到后移temp
            temp = temp.next;
        }
        // 注意此时的节点为要修改节点的前一个节点
        if (flag){        // 替换 节点 值
            temp.next.name = heroNode.name;
            temp.next.nickname = heroNode.nickname;
        }else {
            System.out.println("对不起没有该节点，无法修改");
        }
    }
    //删除
    // 移除节点
    // 根据 no
    public  void  RemoveNodeById(int no) {
        DoubleHeroNode temp = head.next;
        boolean flag = false; // 标记是否查找到
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 此时的temp 为移除节点的前一个节点
        // 把前一个节点的next指向删除节点的next
        if (flag) {
            temp.next.pre = temp.pre;
            if (temp.next != null) {
                temp.pre.next = temp.next; // 断开 对删除节点的引用，等待   GC 回收
            } else {
                System.out.println("未找到节点");
            }
        }


    }
}
























