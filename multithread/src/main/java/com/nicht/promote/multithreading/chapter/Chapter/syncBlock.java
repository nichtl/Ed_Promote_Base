package com.nicht.promote.multithreading.chapter.Chapter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Nicht
 * @Description   同步块  anatomy-of-a-synchronizer
 * @Time 2021/5/17
 * @Link  http://ifeve.com/anatomy-of-a-synchronizer/
 */
public class syncBlock {
/**虽然许多同步器（如锁，信号量，阻塞队列等）功能上各不相同，
 * 但它们的内部设计上却差别不大。换句话说，
 * 它们内部的的基础部分是相同（或相似）的。
 * 了解这些基础部件能在设计同步器的时候给我们大大的帮助。
 * 这就是本文要细说的内容*/
  /*状态
  *  同步器的状态是用来确定某个线程是否具有访问锁的权限的
  *  即不同的同步器中控制访问的条件变量，例如:
  *  在Lock中， isLocked 状态是boolean类型的，表示当前Lock对象是否处于锁定状态
  *  在BoundedSemaphore中内部状态包含一个计数器(int 类型)，以及一个上限(int 类型)
  *  分别表示当前已经获取的许可数和最大可获取的许可数。
  *  BlockingQueue的状态是该队列中元素列表以及队列的最大容量。
  * */
  /** 下面是Lock和BoundedSemaphore中的两个代码片段。*/
  public class  lock{
        //state is kept here
      private  boolean isLocked = false;
      public  synchronized  void lock() throws  InterruptedException{
           while(isLocked){
               wait();
           }
           isLocked  = true;
      }
  }
  public  class  BoundSeamphore {
        private  int singals = 0;
        private  int bound = 0 ;

        public  BoundSeamphore(int upperBound){
            this.bound = upperBound;
        }
        public  synchronized  void take() throws  InterruptedException{
            while(this.singals  == bound){
                wait();  //达到上限值  wait()
            }
            this.singals++;
            this.notify();
        }
        public  synchronized  void release() throws  InterruptedException{
            while(this.singals == 0) {
                wait();  //  无可释放进程
            }
            this.singals -- ;
            notify();  //每减少一个线程  唤醒一个等待线程获取锁
        }
  }
  /**Lock中的访问条件只是简单地检查isLocked的值。
   * 根据执行的动作是“获取”还是“释放”，
   * BoundedSemaphore中实际上有两个访问条件。
   * 如果某个线程想“获取”许可，将检查signals变量是否达到上限；
   * 如果某个线程想“释放”许可，将检查signals变量是否为0。
   * */
  /**状态变化
   一旦一个线程获得了临界区的访问权限，它得改变同步器的状态，让其它线程阻塞，防止它们进入临界区。
   换而言之，这个状态表示正有一个线程在执行临界区的代码。其它线程想要访问临界区的时候，该状态应该影响到访问条件的结果。
   在Lock中，通过代码设置isLocked = true来改变状态，在信号量中，改变状态的是signals–或signals++;*/
  /** 通知策略
    *  一旦某个线程改变了同步器的状态，可能需要通知其他等待的线程,状态已经改变了
    *  因为也许这个状态的变化会让其它线程的访问条件变为true
    *  在BlockQueue 中当队列为0或者队列满了时就会调用notifyAll() 用来通知等待的入队或者出队线程
    *  这就是通知策略
    *  通知所有等待的线程非常简单。所有等待的线程都调用的同一个对象上的wait()方法，
    *  某个线程想要通知它们只需在这个对象上调用notifyAll()方法。
    *  通知等待线程中的任意一个也很简单，只需将notifyAll()调用换成notify()即可。
    *  调用notify方法没办法确定唤醒的是哪一个线程，也就是“等待线程中的任意一个”。
    *  有时候可能需要通知指定的线程而非任意一个等待的线程。
    *  例如，如果你想保证线程被通知的顺序与它们进入同步块的顺序一致，
    *  或按某种优先级的顺序来通知。想要实现这种需求，每个等待的线程必须在其自有的对象上调用wait()。
    *  当通知线程想要通知某个特定的等待线程时，调用该线程自有对象的notify()方法即可。
    *  饥饿和公平中有这样的例子。
    * */
  /**Test-and-Set方法*/
  /**
     *  同步器中最常见的有两种类型的方法 test-and-set是第一种(set是另一种)
     *  Test-and-set 的意识是 调用这个方法的线程检查访问条件,如若满足，该线程设置
     *  同步器的内部状态来表示它已经获得了访问权限
     *  状态的改变通常使其它试图获取访问权限的线程计算条件状态时得到false的结果
     *  即被拒绝,但并不是一定就总是如此,
     *  例如在读写锁中,获取读锁的线程会更新读写锁的状态来表示它获取到了读锁
     *   所以在可重入读锁中读锁的状态并不会使请求读锁的线程陷入等待
     *  只要没有线程请求写锁，其它请求读锁的线程也能成功。
     * */
  /**test-and-set很有必要是原子的，也就是说在某个线程检查和设置状态期间，
     * 不允许有其它线程在test-and-set方法中执行*/
  /**test-and-set方法的程序流通常遵照下面的顺序：*/
  /**
   * 1如有必要,在检查前先设置状态
   * 2检查访问条件
   * 3如果访问条件不满足,则等待
   * 4如果访问条件满足,设置状态,如有必要还要通知等待线程
   * */
   /**
    * 下面的ReadWriteLock类的lockWrite()方法展示了test-and-set方法。
    * 调用lockWrite()的线程在检查之前先设置状态(writeRequests++)。
    * 然后检查canGrantWriteAccess()中的访问条件，如果检查通过,
    * 在退出方法之前再次设置内部状态。这个方法中没有去通知等待线程。
    * */
   private  class  WriteReenTrantLock{
       //不同与读线程可以多个 写线程同时只有一个
       private  Thread  writerThread  = null;
       private  int     writeAccess   = 0;
       private  int     writequest    = 0;
       private Map<Thread,Integer> readingThread = new HashMap<>();
       private synchronized void lock() throws InterruptedException{
           Thread  callingThead = Thread.currentThread();
           writequest++;
           if(!canGrantWriteAccess(callingThead)){wait();}
           writeAccess++;
           writequest--;
           writerThread = callingThead;
       }

       private synchronized void  UnLock() throws  InterruptedException{
           writequest --;
           if (writequest==0){
               writerThread=null;
           }
           notifyAll();
       }

       private  boolean canGrantWriteAccess(Thread callingThread){
           if(hasReaders()){return  false;}
           if(writerThread == null){return  true;}
           if(!isWriter(callingThread)){return  false;}
           return  false;
       }

       private  boolean hasReaders(){
           return  readingThread.size()>0;
       }

       private  boolean  isWriter(Thread callingThread){
           return  writerThread==callingThread;
       }

   }
   /**set方法
    set方法是同步器中常见的第二种方法。set方法仅是设置同步器的内部状态，而不先做检查。set方法的一个典型例子是Lock类中的unlock()方法。持有锁的某个线程总是能够成功解锁，而不需要检查该锁是否处于解锁状态。
    set方法的程序流通常如下：
    设置内部状态
    通知等待的线程
    这里是unlock()方法的一个例子：*/
   public  class  Lock{
       private  boolean isLocked = false;
       public synchronized  void unlock (){
           isLocked = false;
           notify();
       }


   }
























}
