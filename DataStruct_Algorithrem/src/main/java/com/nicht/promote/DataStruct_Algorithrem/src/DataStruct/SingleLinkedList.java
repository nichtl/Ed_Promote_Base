package com.nicht.promote.DataStruct_Algorithrem.src.DataStruct;

import java.util.Stack;

// 本次的单链表都是带头节点的
// 单向链表模拟 水浒传108英雄的排序
public class SingleLinkedList {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(6, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(4, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(5, "李逵", "黑旋风");

        HeroNode hero5 = new HeroNode(2, "宋江", "及时雨");
        HeroNode hero6 = new HeroNode(3, "卢俊义", "玉麒麟");
        HeroNode hero7 = new HeroNode(7, "吴用", "智多星");
        HeroNode hero8 = new HeroNode(8, "李逵", "黑旋风");
        Singlenode singlenode = new Singlenode();
        singlenode.Byorder(hero1);
        singlenode.Byorder(hero2);
        singlenode.Byorder(hero3);
        singlenode.Byorder(hero4);
        singlenode.list();
        System.out.println("合并前");
        Singlenode  singlenode12 = new Singlenode();
        singlenode12.Byorder(hero5);
        singlenode12.Byorder(hero6);
        singlenode12.Byorder(hero7);
        singlenode12.Byorder(hero8);
        singlenode12.list();
        System.out.println("合并后");
        HeroNode node = mergeList(singlenode.getHead(),singlenode12.getHead());
        while (node!=null){
            System.out.println(node);
            node = node.next;
        }
        int day = getYearmonth(2019,11);
        System.out.println("2019的11月份天数"+day);



   /*     System.out.println("移除5号节点");
        singlenode.RemoveNodeById(5);
        singlenode.list();*/
    /*    System.out.println("倒数第三个节点");
        singlenode.reverseget(3);
        System.out.println("反转链表");
        reversalSingleList(singlenode.getHead());
        singlenode.list();*/



    }




    // 遍历节点 个数
    public static int getNodeNUm(HeroNode heroNode) {
        /*
         * @param head  链表头节点
         * @return
         * */
        int length = 0;
        return 0;
    }

    public  static  int getYearmonth(int Year,int month){
        int day=0;
        Boolean isLeapYear = false; //标记是否为润年 默认 false
        if ((Year%4==0&&Year%100!=0)||(Year%400==0)){
             isLeapYear = true;
        }
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:    day=31; break;
            case  4:
            case  6:
            case  9:
            case  11:   day = 30; break;
            case   2: if (isLeapYear = false){day = 28; }else {day = 29;} break;
        }
        int sum =0;
        int weekday =  CaculateWeekDay(Year,month,1);
        System.out.println("日\t一\t二\t三\t四\t五\t六");
        for(int i=1;i<=weekday;i++){
            System.out.print("\t");
            sum++;
        }

        for(int i=1;i<=day;i++){
            sum+=1;
            if(sum%7==0){
                System.out.print("\n");
            }
            System.out.print( i+"\t");
        }
        return  day;
    }
    public  static int CaculateWeekDay(int y, int m, int d) {
        int n=0;
        if (m <= 2) {
            m += 12;
            y--;
        }
        int iWeek = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;
        switch (iWeek) {
            case 0:
                n= 1;
                break;
            case 1:
                 n=2;
                break;
            case 2:
                 n =3;
                break;
            case 3:
               n=4;
                break;
            case 4:
                 n=5;
                break;
            case 5:
                n=6;
                break;
            case 6:
               n=7;
                break;
        }
        return n;
    }



    /*从尾到头打印单链表 反向遍历*/
    //方法一  先反转链表，再遍历
    // 方法二  利用栈 的数据结构 后进先出的 特点完成逆序 顺序遍历每一个节点 压入栈再输出
    public static void reverseprint(HeroNode head) {
        if (head.next == null || head.next.next == null) { // 节点数小于一 直接返回
            return;
        }
        // 否则遍历链表节点 压入栈中
        HeroNode next = head.next;
        Stack<HeroNode> stack = new Stack<HeroNode>();
        while (next != null) {
            stack.push(next);
            next = next.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
    /*合并两个有序的链表 合并完成后任然有序*/

    public static HeroNode mergeList(HeroNode head1, HeroNode head2) {
        if (head1.next == null || head2.next == null) {
            return null ;
        }
        HeroNode pr ;
        HeroNode node1 = head1.next;
        HeroNode node2 = head2.next;
        HeroNode newhead = new HeroNode(0,"","");
        HeroNode kheroNode = new HeroNode(0,"","");
        while (node1 != null && node2 != null) {
            // 第一次记录下newhead 的头部
            if(newhead.no == 0 ){
              kheroNode = newhead;  // 记录 newhead 的头节点
            }
            if (node1.no < node2.no) { // 同时遍历两个链表 并 选择最小的加入 newnode
                pr = node1.next;
                node1.next = null;
                newhead.next = node1;
                node1        = pr;     //后移node1
            } else {
                pr = node2.next;
                node2.next = null;
                newhead.next = node2;
                node2        = pr;
            }
            newhead = newhead.next;//指针不停后移造成最后指针只指向了最后一位，前面被丢弃了
        }
        if(node1 != null){
            newhead.next = node1;
        }
        if(node2 != null){
            newhead.next = node2;
        }
        /*如果有一方先遍历完成 另一方剩下的也是顺序排列的直接加入next*/
        return kheroNode.next ;
    }

    // 单链表反转
    public static   void  reversalSingleList(HeroNode head) {
        // 思路 反转 把链表节点 的位置反转互换
        // 方法一 新建一个链表 依次遍历到最后一个节点加入新链表  (效率低下)
        // 方法二 根据链表节点个数 进行找寻对应下标互换
        // 方法三 遍历 插入 每次新加入的节点成为第一个节点
        // 整体思路 新 件一个 空节点  ，一个 备份节点 指向 head.next ，一
        if (head.next == null || head.next.next == null) {  //链表为空或只有一个节点
            return;
        }
        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode ResverHead = new HeroNode(0, "", "");
        while (cur != null) {
            next = cur.next;
            cur.next = ResverHead.next; //每次元素都插入为第一个元素
            ResverHead.next = cur;     //
            cur = next;
        }
        head.next = ResverHead.next;
    }


}

class  HeroNode{
    public  int no;
    public  String name;
    public  String nickname;
    public  HeroNode next;
    //构造器
    public HeroNode(int no,String name,String nickname){
        this.no= no;
        this.name = name;
        this.nickname = nickname;

    }
    @Override
    public  String toString(){ return "Hero[no = "+no+",name = "+name+", nickname="+nickname+"]";
    }
}
class   Singlenode{
    // 初始化一个头节点,头节点不要动，不存放具体的数据
    private  HeroNode head = new HeroNode(0,"","");
    // 添加节点到单向链表
    // 不考虑顺序直接加到最后一个
    public HeroNode getHead(){
        return head;
    }
    public  void  add (HeroNode heroNode){
        // 因为 head 节点不能动需要一个辅助 遍历temp
        HeroNode temp = head ;
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
    }
    //插入添加 ，插入到指定位置节点前 根据 no 进行排名插入
    public  void  Byorder(HeroNode heroNode){
        HeroNode temp = head;
        boolean   flag = false;
        while (true){
            if (temp.next == null){
                break;
            }
            if(temp.next.no > heroNode.no){
                // 当前节点指向的下一个节点 的 no 和 要加入的 节点 的 no
                //如果 当前节点 no 小于 下一个节点的no 说明就应该插入 当前节点的next
                break;
            }else if (temp.next.no == heroNode.no){
                flag = true ; // 说明已经有了
                break;
            }
            // 如果不满足条件继续下一个节点
            temp = temp.next; //后移temp节点
        }
        if(flag){
            System.out.println("已经存在");
        }else {
            // 插入
            heroNode.next = temp.next;
            temp.next     = heroNode;
        }
    }
    // 显示遍历链表
    public  void  list(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //保证不改变头节点
        HeroNode temp = head.next;// 经过判断 至少有一个数据
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
    //修改链表节点
    //思路 1 根据 根据 节点的  no 下标遍历匹配 到 节点 替换 该节点
    public  void  update(HeroNode heroNode){
        HeroNode temp = head;
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
    // 移除节点
    // 根据 no
    public  void  RemoveNodeById(int no){
        HeroNode temp  = head;
        boolean flag = false ; // 标记是否查找到
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no ==no){
                //找到
                flag =true;
                break;
            }
            temp = temp.next;
        }
        // 此时的temp 为移除节点的前一个节点
        // 把前一个节点的next指向删除节点的next
        if (flag){
            temp.next = temp.next.next; // 断开 对删除节点的引用，等待   GC 回收
        }else {
            System.out.println("未找到节点");
        }
    }
    // 单链表问题
    // 查找单链表倒数第 K 个节点值
    public void   reverseget(int index ){
         // 把逆向变为正向
         // 遍历 单向链表 得到单向链表 长度
         boolean flag = false;
         HeroNode temp =  head;
         int count = 0;
         while (true){
             if (temp.next == null ){
                 break;
             }
             count++;
             temp = temp.next;
         }
         System.out.println(count);
         int unreverse= (count-index)+1 ; //得到反向 正序 index
         System.out.println(unreverse);
         // 请注意 此时的temp指向队列最后  此时遍历为空
        // 重置 temp
          temp = head;
         while (true){
             if (temp.next == null){
                 break;
             }
             if(unreverse == 0){
                 flag =true;
                 break;
             }
             unreverse--;
             temp = temp.next;
         }
         if (flag){
             System.out.println("倒数第"+index+"的节点为"+temp.no+" name:"+temp.name+" nickname:" +temp.nickname);
         }else {
             System.out.println("该节点不存在");
         }
    }
    }



