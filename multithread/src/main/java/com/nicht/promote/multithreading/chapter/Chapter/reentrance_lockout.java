package com.nicht.promote.multithreading.chapter.Chapter;

/**
 * @Author Nicht
 * @Description  重入锁死
 * @Time 2021/5/12
 * @Link http://ifeve.com/reentrance-lockout/
 */
public class reentrance_lockout {

 /** what is reentrance-lockout
  *  重入锁死与 死锁和嵌套管程锁死很相似
  *  当一个线程重新获取锁，读写锁或其他不可重入的同步器时，
  *  就可能发生重入锁死。可重入的意思是线程可以重复获得它已经持有的锁。
  *  Java的synchronized块是可重入的。因此下面的代码是没问题的：
  * */
  public  class  ReenTrant{

      public  synchronized  void outer(){
           inner();
      }

      public  synchronized  void inner(){
          // do something
      }
      /**
       * 如果某个线程调用了outer()，outer()中的inner()调用是没问题的
       * 因为两个方法都是在同一个管程对象(即this)上同步的
       * 如果一个线程持有某个管程对象上的锁，那么它就有权访问所有在该管程对象上同步的块
       * 这就叫可重入。若线程已经持有锁,那么它就可以重复访问所有使用该锁的代码块。
       * */
      /**
       * synchronized  ReentrantLock 都是可重入的
       * */
      // 下面的锁是不可重入的
      public  class  lock{
          private boolean isLocked = false;
          public  synchronized  void lock() throws  InterruptedException{
              while(isLocked){wait();}
              isLocked = true;
          }
          public  synchronized  void unLock(){
              isLocked = false;
              notify();
          }
      }
     /**避免重入锁死有两个选择：
     编写代码时避免再次获取已经持有的锁
     使用可重入锁
     至于哪个选择最适合你的项目，得视具体情况而定。
     可重入锁通常没有不可重入锁那么好的表现，而且实现起来复杂，
     但这些情况在你的项目中也许算不上什么问题。
     无论你的项目用锁来实现方便还是不用锁方便，可重入特性都需要根据具体问题具体分析。*/













 }















}
