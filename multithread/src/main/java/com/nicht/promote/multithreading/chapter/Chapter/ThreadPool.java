package com.nicht.promote.multithreading.chapter.Chapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author Nicht
 * @Description 线程池
 * @Time 2021/5/12
 * @Link http://ifeve.com/thread-pools/
 */
public class ThreadPool {
  /**
   *  线程池（Thread Pool）对于限制应用程序中同一时刻运行的线程数很有用。
   *
   *  因为每启动一个新线程都会有相应的性能开销，每个线程都需要给栈分配一些内存等等。
   *  我们可以把并发执行的任务传递给一个线程池,来代替为每个并发执行的任务都启动为一个
   *  新的线程,只要池里有空闲的线程,任务就会分配给一个线程执行.
   *
   *   线程池经常应用于多线程服务器上.每个通过网络到达服务器的连接
   *   都被包装成一个任务并且传递给线程池,线程池的线程会并发处理连接请求
   *   以后会再深入有关 Java 实现多线程服务器的细节。
   *   Java5在java.util.concurrent 中自带了内置的线程池,所以你不用非得自己实现
   * */
     /*简单实现一个线程池  */
    public  class  MyThreadPool {
      private ArrayBlockingQueue taskQueue = null;
      private List<PoolThread> threads = new ArrayList<>();
      public void ThreadPool(int noOfThreads, int maxNoOfTasks){
          taskQueue =  new ArrayBlockingQueue(maxNoOfTasks);
      }






    }

    public  class  PoolThread extends  Thread {
        private ArrayBlockingQueue<Runnable> taskQueue = null;
        private boolean   isStopped = false;
        public  PoolThread(ArrayBlockingQueue<Runnable> queue){
            taskQueue = queue;
        }
        @Override
        public  void  run(){
            while (!isStopped){
                try{
                    Runnable runnable = taskQueue.take();
                    runnable.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public  synchronized  void toStop(){
            isStopped = true;
            this.isInterrupted();//打断池中线程的 dequeue() 调用.
        }

        public  synchronized  boolean isStopped(){
            return  isStopped;
        }
    }






















}
