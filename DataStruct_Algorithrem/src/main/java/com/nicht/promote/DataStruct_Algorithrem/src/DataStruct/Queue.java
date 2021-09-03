package com.nicht.promote.DataStruct_Algorithrem.src.DataStruct;

import java.util.Scanner;
/**
 * @author Ni
 * @version 1.0
 * @date 2019/11/22 17:26
 */
//数组实现队列
public class Queue {
    public static void main(String[] args) {
           ArrayQueue arrayQueue =new ArrayQueue(3);
            char  key =' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            System.out.println("s(show) : 显示队列");
            System.out.println("e(exit) : 退出程序 ");
            System.out.println("a(add)  : 添加数据到队列");
            System.out.println("g(get)  : 从队列取出数据 ");
            key = scanner.next().charAt(0);
            switch (key){
                case  's':
                    arrayQueue.show();
                    break;
                case  'a' :
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try{
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出");
    }
}
// 使用数据模拟 单向队列
class ArrayQueue{
    private  int maxsize; //数组最大容量
    private  int font ;   // 队列头
    private  int rear ;   // 队列尾
    private  int[] arr ;// 数组
    //创建队列构造器
    public ArrayQueue(int arrmaxsize){
        maxsize = arrmaxsize;
        arr = new int[maxsize];
        font = -1 ; //头指针 -1
        rear = -1 ; //尾指针 -1
    }
    // 判断队列 是否满了
    public  boolean isFull(){
        return  rear == maxsize -1 ;
    }
    // 判断是否为空
    public  boolean isEmpty(){
        return  rear == font;
    }
    //添加数据 到队列
    public  void  addQueue(int n){
     if(isFull()){
         System.out.println("队列已满");
         return;
     }
     rear++ ; //rear 后移
     arr[rear] = n;
    }
    // 获取队列数据
    public  int getQueue(){
        if (isEmpty()){
            throw  new RuntimeException("没有数据");
        }
        font++;
        return  arr[font];
    }
    //显示数据
    public void show (){
        if (isEmpty()){
            System.out.println("没有数据");
        }
        for(int i =0 ;i<arr.length;i++){
            System.out.printf("%d\n",i,arr[i]);
        }
    }

}
class  CircleArray{







}