package com.nicht.promote.multithreading.chapter.TestExample;

/**
 * @Author Nicht
 * @Description  同步测试 对象锁和类锁的区别
 * @Time 2021/4/25
 * @Link https://www.cnblogs.com/owenma/p/8609348.html
 */
public class TestSynchronized {
  public  static  int  str =2;
  /** 对象锁  两种加锁方式   1在方法内部使用  synchronized (this){}  锁住关键代码  this 指向的是当前对象*/
  public  void test1(){
      synchronized (this){
          for (int i = 0; i < 10; i++) {
              System.out.println(Thread.currentThread().getName()+"实例锁打印"+i);
          }
          try {
              Thread.sleep(500);
          }catch (Exception  e){
              e.printStackTrace();
          }
      }
  }
  /** 对象锁  两种加锁方式直接使用synchronized 修饰方法名这两种效果是一样的 注意锁是加载实例对象上面的*/
  public  synchronized void test1_2(){
      for (int i = 0; i < 10; i++) {
          System.out.println(Thread.currentThread().getName()+"实例锁打印"+i);
      }
      try {
          Thread.sleep(500);
      }catch (Exception  e){
          e.printStackTrace();
      }
  }


  /**类锁 类锁中不可使用this 并使用非静态方法 因为静态方法是不需要实例即可使用的*/
  public static  synchronized void test2(){
/*      for (int i = 0; i < 10; i++) {
          System.out.println(Thread.currentThread().getName()+"类锁打印"+i);
      }*/
          str = 1;
      try {

          System.out.println("test2"+Thread.currentThread().getName()+"开始睡眠"+str);
          Thread.sleep(2000);
          System.out.println("test2"+Thread.currentThread().getName()+"结束睡眠"+str);
      }catch (Exception  e){
          e.printStackTrace();
      }
  }

  public static  void testprint(){
      str = 3;
      System.out.println("testprint"+Thread.currentThread().getName()+"打印：飒飒阿萨撒所"+str);
  }

  /**  上面演示同步静态方法调用时可以同时调用普通方法
   *   但是实际上应该对str 进行同步保护的
   * */

























}
