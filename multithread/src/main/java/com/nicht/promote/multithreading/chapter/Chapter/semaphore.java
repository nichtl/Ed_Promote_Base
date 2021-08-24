package com.nicht.promote.multithreading.chapter.Chapter;

/**
 * @Author Nicht
 * @Description 信号量
 * @Time 2021/5/12
 * @Link http://ifeve.com/semaphore/
 */
public class semaphore {
     /*菊花 chrysanthemum*/
    /**Semaphore（信号量） 是一个线程同步结构，用于在线程间传递信号，
     * 以避免出现信号丢失（译者注：下文会具体介绍），
     * 或者像锁一样用于保护一个关键区域。
     * 自从5.0开始，jdk在java.util.concurrent包里提供了Semaphore 的官方实现，
     * 因此大家不需要自己去实现Semaphore。
     * 但是还是很有必要去熟悉如何使用Semaphore及其背后的原理*/
   /**简单的Semaphore实现*/
   public  class  Semaphore{
       private boolean signal = false;

       public  synchronized void take() throws  InterruptedException{
           this.signal = true;
           this.notify();
       }

       public  synchronized void release() throws InterruptedException{
           while (!this.signal) {wait();}
           this.signal =false;
       }



   }
















}
