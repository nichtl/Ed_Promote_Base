package com.nicht.promote.multithreading.chapter.Chapter;
/**
 * @Author Nicht
 * @Description  Java中的锁
 * @Time 2021/5/10
 * @Link  http://ifeve.com/locks/
 */
public class ten_four_locks {
    public  class  lockclass{

    }
    /**
     *  锁像synchronized 同步块一样 是一种线程同步机制
     *  但比Java中的synchronized 同步块更复杂因为锁
     *  因为锁（以及其它更高级的线程同步机制）是由synchronized同步块的方式实现的
     *  所以我们还不能完全摆脱synchronized关键字（译者注：这说的是Java 5之前的情况）。
     *  自java5开始java.util.concurrent.locks包中包含了一些锁的实现，因此你不用去实现自己的锁了。
     *  但是你仍然需要去了解怎样使用这些锁，且了解这些实现背后的理论也是很有用处的。
     *  可以参考我对java.util.concurrent.locks.Lock(@Link http://tutorials.jenkov.com/java-util-concurrent/lock.html)的介绍，以了解更多关于锁的信息。
     *  以下是本文所涵盖的主题：
     * 一个简单的锁
     * 锁的可重入性
     * 锁的公平性
     * 在finally语句中调用unlock()
     * */
    public  class Counter{
        private  int count =0;
        public  int  inc(){
            synchronized (this){
                return  ++count;
            }
        }
    }
    /**
     * 可以看到
     * 可以看到在inc()方法中有一个synchronized(this)代码块。
     * 该代码块可以保证在同一时间只有一个线程可以执行return ++count。
     * 虽然在synchronized的同步块中的代码可以更加复杂，
     * 但是++count这种简单的操作已经足以表达出线程同步的意思。
     * */
    /**
     * Counter类用Lock代替synchronized达到了同样的目的
     * */
    public  class Counter1{
        private Lock lock = new Lock();
        private  int count =0;
        public  int  inc() throws InterruptedException {
         lock.lock();
         int newcount = ++count;
         lock.unlock();
         return newcount;
        }
    }
    /**
     * 锁的可重入性  synchronized， ReentrantLock是可重入的
     * */
    public class Reentrant{

        public synchronized void outer(){
            inner();
        }
       public synchronized void inner(){
            //do something
        }

    }
    /**
     * 注意outer()和inner()都被声明为synchronized，
     * 这在Java中和synchronized(this)块等效。
     * 如果一个线程调用了outer(),在outer()里调用inner()就没有什么问题，
     * 因为这两个方法（代码块）都由同一个管程对象（”this”)所同步。
     * 如果一个线程已经拥有了一个管程对象上的锁，
     * 那么它就有权访问被这个管程对象同步的所有代码块。
     * 这就是可重入。线程可以进入任何一个它已经拥有的锁所同步着的代码块。
     * */
    /**
     * 前面给出的锁实现不是可重入的。
     * 如果我们像下面这样重写Reentrant类，
     * 当线程调用outer()时，会在inner()方法的lock.lock()处阻塞住。
     * */
    public class Reentrant2{
        Lock   lock = new Lock();
        public   void outer(){
            try {
                lock.lock();
                inner();
            }catch (InterruptedException  e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
         }
         public  void inner(){
           try {
               lock.lock();
               //do something
           }catch (InterruptedException e){
               e.printStackTrace();
           }finally {
               lock.unlock();
           }
         }
    }
    /**
     * 调用outer()的线程首先会锁住Lock实例，然后继续调用inner()。
     * inner()方法中该线程将再一次尝试锁住Lock实例
     * 结果该动作会失败（也就是说该线程会被阻塞）,
     * 因为这个Lock实例已经在outer()方法中被锁住了。
     * */

    /**在原先实现的lock 类中
     一个线程是否被允许退出lock()方法是由while循环（自旋锁）中的条件决定的。
     当前的判断条件是只有当isLocked为false时lock操作才被允许，
     而没有考虑是哪个线程锁住了它。
    为了让这个Lock类具有可重入性，我们需要对它做一点小的改动：*/
    public class Lock{
        boolean isLocked = false;
        Thread  lockedBy = null;
        int lockedCount = 0;
        public synchronized void lock() throws InterruptedException{
            Thread callingThread =
            Thread.currentThread();
            while(isLocked && lockedBy != callingThread){
                wait();
            }
            isLocked = true;
            lockedCount++;
            lockedBy = callingThread;
        }
        public synchronized void unlock(){
            if(Thread.currentThread() ==
            this.lockedBy){
                lockedCount--;
                if(lockedCount == 0){
                    isLocked = false;
                    notify();
                }
            }
        }
       // ...
    }
    /**
     * 注意到现在的while循环（自旋锁）也考虑到了已锁住该Lock实例的线程。
     * 如果当前的锁对象没有被加锁(isLocked = false)，或者当前调用线程已经对该Lock实例加了锁，
     * 那么while循环就不会被执行，调用lock()的线程就可以退出该方法
     * (译者注：“被允许退出该方法”在当前语义下就是指不会调用wait()而导致阻塞)。
     * 除此之外，我们需要记录同一个线程重复对一个锁对象加锁的次数。否则，
     * 次unblock()调用就会解除整个锁，即使当前锁已经被加锁过多次。
     * 在unlock()调用没有达到对应lock()调用的次数之前，我们不希望锁被解除。
     * 现在这个Lock类就是可重入的了。
     * */

    /**
     * 在finally语句中调用unlock()
     * 如果用Lock来保护临界区，并且临界区有可能会抛出异常，
     * 那么在finally语句中调用unlock()就显得非常重要了。
     * 这样可以保证这个锁对象可以被解锁以便其它线程能继续对其加锁。以下是一个示例：
     * lock.lock();
     * try{
     *     //do critical section code,
     *     //which may throw exception
     * } finally
     *     lock.unlock();
     * }
     * 这个简单的结构可以保证当临界区抛出异常时Lock对象可以被解锁。
     * 如果不是在finally语句中调用的unlock()，
     * 当临界区抛出异常时，Lock对象将永远停留在被锁住的状态，
     * 这会导致其它所有在该Lock对象上调用lock()的线程一直阻塞。
     * */
//
//
//82.156.165.91  hadoop-tx-82
//
//101.43.154.160 hadoop-tx-101
//
//120.26.11.94   hadoop-aliyun-120





























}
