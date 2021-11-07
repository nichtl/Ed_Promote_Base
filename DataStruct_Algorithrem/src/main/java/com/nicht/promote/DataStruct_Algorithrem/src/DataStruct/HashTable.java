package com.nicht.promote.DataStruct_Algorithrem.src.DataStruct;


/**
 * @author Ni
 * @version 1.0
 * @date 2019/11/22 17:26  反射
 */
public class HashTable {
    public static void main(String[] args) {

    }

}
    class HashTab {
        private EmpLinkedList[] empLinkedListsarr;
        private  int size;


        //构造器
        public HashTab(int size) {
            this.size =size;
            empLinkedListsarr = new EmpLinkedList[size];
        }
        // 添加元素到hashtable 中链表
        public void add(Emp emp){
            // 根据id 选择添加到那个数组中的链表
            int empLinkedListsNo =  HashFun(emp.id);
            //获得数组对应的链表head
            empLinkedListsarr[empLinkedListsNo].add(emp);

        }

        //遍历所有链表，遍历hashtab
        public  void list(){
            for(int i=0; i< size ;i++){
                empLinkedListsarr[i].list();
            }

        }



        public  int HashFun(int id){
            return  id % size; //取模判断
        }





    }

    class Emp {
        public int id;
        public String name;
        public Emp next;

        public Emp(int id, String name) {
            super();
            this.id = id;
            this.name = name;
        }

    }


    // 创建一个EmpLinkedList ,表示链表cla
    class EmpLinkedList {
        // 头指针 执行第一个 Emp
        private Emp head; // 默认null

        //添加 雇员到链表
        // 说明
        //加入链表最后
        public void add(Emp emp) {
            // 如果链表没有元素 让head == emp
            if (head == null) {
                head = emp;
                return;
            }
            //如果不是第一个元素，使用一个辅助的指针，帮助定位到最后
            Emp curemp = head;
            while (true) {
                if (curemp.next == null) {
                    break;
                }
                //此时位链表最后一个元素
                curemp = curemp.next; //  元素后移 形成遍历
            }
            // while 退出时赋值
            curemp.next = emp;
        }

        // 遍历
        public void list() {
            if (head == null) {
                System.out.println("当前链表为空");
                return;
            }
            System.out.println("当前链表的信息为");
            Emp curemp = head; // 辅助指针
            while (true) {
                System.out.printf("=> id =%d name = %s\t", curemp.id, curemp.name);
                if (curemp.next == null) {
                    break;
                }
                curemp = curemp.next;
            }


        }

    }
