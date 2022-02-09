package com.nicht.promote.multithreading.chapter.Chapter;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author Nicht
 * @Description 阻塞队列
 * @Time 2021/5/12
 * @Link  http://ifeve.com/blocking-queues/
 */
public class BlockQueue {
   /** 阻塞队列与普通队列的区别在于，
    当队列是空的时，从队列中获取元素的操作将会被阻塞，
    或者当队列是满时，往队列里添加元素的操作会被阻塞。
    试图从空的阻塞队列中获取元素的线程将会被阻塞，
    直到其他的线程往空的队列插入新的元素。同样，
    试图往已满的阻塞队列中添加新元素的线程同样也会被阻塞，
    直到其他的线程使队列重新变得空闲起来，
    如从队列中移除一个或者多个元素，
    或者完全清空队列，下图展示了如何通过阻塞队列来合作：*/
  // BlockingQueue b  = new LinkedBlockingQueue(10);  juc 已经给出官方的实现
    //利用list实现
     public  class  ListBlockQueue{
       private List queue  = new LinkedList();
       private int limit = 10 ;
       private ListBlockQueue(int limit) {
           this.limit  = limit;
       }

       public  synchronized  void enqueue(Object  item) throws  InterruptedException{
            // 队列已满阻塞
            while(this.queue.size() == this.limit){wait();}
            // 初始化的时候 queue.size==0,此时notifyAll所有等待线程竞争进入阻塞队列
            if(this.queue.size() == 0){ // 等于下限
                notifyAll();
            }
            this.queue.add(item);
       }

       public synchronized  Object dequeue() throws  InterruptedException{
           while(this.queue.size() == 0 ) {wait();}
           if(this.queue.size() == this.limit){notifyAll();}  // 等于上限
           return this.queue.remove(0);
       }
       /** 通知策略*/
       /**
        * 在enqueue和dequeue方法内部,只有队列的大小等于上限（limit）或者下限（0）时，
        * 才调用notifyAll方法。如果队列的大小既不等于上限,也不等于下限,任何线程调用enqueue或者dequeue方法时，
        * 都不会阻塞，都能够正常的往队列中添加或者移除元素。
        * */
       /** 为什么当只有队列的大小等于上限（limit）或者下限（0）时,才调用notifyAll方法
       *  入队满时唤醒出队    出队queue==0时唤醒入队   即  线程的公平与饥饿问题  保证wait()的线程不会永远wait
       *  这是为了在队列的大小等于上限（limit）或者下限（0）时 保证能够唤醒其他正在等待的出队或入队线程
       *  使用notify() 无法保证一定唤醒出队或入队线程,可能入队唤醒的是入队 出队唤醒的是出队
       * */
       /** 为什么当只有队列的大小等于上限（limit）或者下限（0）时,才调用notifyAll方法
       *  假设有两个线程 A B 线程 queue.size == 0
       *  B先调用 dequeue  A再调用 enqueue
       *  看下流程 B 调用 dequeue 时发现 queue.size==0 开始陷入wait(释放锁  然后A开始执行了enqueue
       *  然后A 检测到queue.size 为0 notifyall() 唤醒了B 然后执行add
       *  B被唤醒了然后 顺利执行remove
       *  再看 有四个线程时  A B C D  queue.size  == 9
       *  A B enqueue  C D dequeue  按 abcd 执行
       *  当A执行enqueue 时顺利入队  开始执行B B检测队列满了
       *  开始wait, C开始执行dequeue 发现队列满了 notifyall 然后执行出队
       *  B这个时候再入队就可以入队了
       */










     }











}
