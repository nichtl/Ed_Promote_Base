package com.nicht.promote.multithreading.chapter.Chapter;

import cn.hutool.core.date.DateUtil;

import java.util.concurrent.*;

/**
 * @Author Nicht
 * @Description  线程创建的三种方式
 * @Time 2021/4/19
 * @Link  http://ifeve.com/creating-and-starting-java-threads/
 */
public class one_creating_and_starting_java_threads {

    public static void main(String[] args) {
      //method 1 start
        Thread  thread  = new Thread(new CreatedThreads1());
        thread.start();
      //method2 start
        Thread  thread1  = new CreatedThread2();
        thread1.start();
        //method3 use
     try {
         //生成具有两个线程的线程池
         ExecutorService executorService = Executors.newFixedThreadPool(2);
         Future<Integer> integerFuture1 = executorService.submit(new CreatedThread3());
         Future<Integer> integerFuture2 = executorService.submit(new CreatedThread3.SubThread());
         Integer result = integerFuture1.get() + integerFuture2.get();
         System.out.println(result);
     }catch (Exception  exception){
         exception.printStackTrace();}



    }
    /** method1 实现 Runable  run 方法  借助 Thread  start 开启线程*
     *
     */
    public static class CreatedThreads1 implements  Runnable  {
         /**
          * When an object implementing interface <code>Runnable</code> is used
          * to create a thread, starting the thread causes the object's
          * <code>run</code> method to be called in that separately executing
          * thread.
          * <p>
          * The general contract of the method <code>run</code> is that it may
          * take any action whatsoever.
          *
          * @see Thread#run()
          */
         @Override
         public void run() {
             System.out.println("Runable run start"+ DateUtil.now());
         }
     }
     /**
      * method2 继承 Thread 实现 run
      */
    public static class CreatedThread2  extends  Thread{
         /**
          * If this thread was constructed using a separate
          * <code>Runnable</code> run object, then that
          * <code>Runnable</code> object's <code>run</code> method is called;
          * otherwise, this method does nothing and returns.
          * <p>
          * Subclasses of <code>Thread</code> should override this method.
          *
          * @see #start()
          * @see #stop()
          */
         @Override
         public void run() {
             System.out.println("继承Thread 实现"+DateUtil.now());
         }
     }


     /**
      * 通过Runnable与Thread的方式创建的线程，是没有返回值的。
      * 然而在有些情况下，往往需要其它线程计算得到的结果供给另外线程使用
      * （ 例如：计算1+100的值，开启三个线程，一个主线程，两个计算线程，主线程需要获取两个计算线程的结算结果
      * （一个计算线程计算1+2+...+50，另外一个线程计算51+52+..+100），进行相加，
      * 从而得到累加结果），这个时候可以采用Runnable与Thread的方式创建的线程，并通过自行编写代码实现结果返回，
      * 但是不可避免的会出现很多错误和性能上的问题。基于此，JUC（java.util.concurrent)提供了解决方案，
      * 实现Callable的call()方法（这个类似Runnable接口），使用Future的get()方法进行获取
     */
     //Callable  类似有返回值的run()方法
    public static class CreatedThread3 implements Callable{
         /**
          * Computes a result, or throws an exception if unable to do so.
          * @Descrption  计算  1到50累加的值
          * @return computed result
          * @throws Exception if unable to compute a result
          */
         public static void main(String[] args) throws ExecutionException,InterruptedException  {
             //生成具有两个线程的线程池
             ExecutorService executorService  = Executors.newFixedThreadPool(2);
             Future<Integer>   integerFuture1  =  executorService.submit(new CreatedThread3());
             Future<Integer>   integerFuture2  = executorService.submit(new SubThread());
              Integer  result  =   integerFuture1.get() +  integerFuture2.get();
             System.out.println(result);
         }


         @Override
         public Object call() throws Exception {
          Integer  count=0;
             for (int i = 1; i <=50 ; i++) {
                   count  = count+i;
             }
             return count;
         }
         static class   SubThread implements  Callable{
             /**
              * Computes a result, or throws an exception if unable to do so.
              *
              * @return computed result
              * @throws Exception if unable to compute a result
              */
             @Override
             public Object call() throws Exception {
                 Integer count = 0;
                 for (int i = 51; i <=100 ; i++) {
                     count +=i;
                 }

                 return count;
             }
         }
     }










}
