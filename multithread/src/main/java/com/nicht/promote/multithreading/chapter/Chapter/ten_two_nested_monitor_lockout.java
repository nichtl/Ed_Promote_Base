package com.nicht.promote.multithreading.chapter.Chapter;

/**
 * @Author Nicht
 * @Description  嵌套管程锁死
 * @Time 2021/5/7
 * @Link  http://ifeve.com/nested-monitor-lockout/
 */
public class ten_two_nested_monitor_lockout {
    public class MonitorObject {
    }
    /*嵌套管程锁死类似于死锁， 下面是一个嵌套管程锁死的场景：*/
    /**
     * 线程1获得对象A的锁
     * 线程1获得对象B的锁(同时持有对象A的锁)
     * 线程1决定等待另外一个线程的信号再继续.
     * 线程1调用B.wait() 从而释放B对象上持有的锁 但仍然持有对象A的锁
     * 线程2 需要同时持有对象A和对象B的锁才能向线程1发信号
     * 线程2 无法获得对象A上的锁因为对象A上的锁当前正被线程1持有
     * 线程2 一直被阻塞等待线程1释放对象A上的锁
     * 线程1一直阻塞 等待线程2的信号 因此不会释放对象A上的
     * 而线程2需要对象A上的锁才能给线程1发信号……
     * */
     /**
      * example lock
      * */
     public class Lock{
         protected MonitorObject monitorObject  =  new MonitorObject();
         protected boolean isLocked =  false;

         public  void lock() throws InterruptedException{
             synchronized (this){
                 while(isLocked){
                     synchronized (this.monitorObject){
                         this.monitorObject.wait();
                     }
                 }
                 isLocked = true;
             }
         }

         public void unlock(){
             synchronized (this){
                 this.isLocked=false;
                 synchronized (this.monitorObject){
                     this.monitorObject.notify();
                 }
             }
         }
     }
    /**
     * 可以看到，lock()方法首先在”this”上同步，然后在monitorObject上同步。
     * 如果isLocked等于false，因为线程不会继续调用monitorObject.wait()，
     * 那么一切都没有问题 。但是如果isLocked等于true，调用lock()方法的线程会在monitorObject.wait()上阻塞。
     * 这里的问题在于，调用monitorObject.wait()方法只释放了monitorObject上的管程对象，
     * 而与”this“关联的管程对象并没有释放。换句话说，这个刚被阻塞的线程仍然持有”this”上的锁。
     * （校对注：如果一个线程持有这种Lock的时候另一个线程执行了lock操作）
     * 当一个已经持有这种Lock的线程想调用unlock(),就会在unlock()方法进入synchronized(this)块时阻塞。
     * 这会一直阻塞到在lock()方法中等待的线程离开synchronized(this)块。
     * 但是，在unlock中isLocked变为false，monitorObject.notify()被执行之后，
     * lock()中等待的线程才会离开synchronized(this)块。
     * 简而言之，在lock方法中等待的线程需要其它线程成功调用unlock方法来退出lock方法，
     * 但是，在lock()方法离开外层同步块之前，没有线程能成功执行unlock()。
     * 结果就是，任何调用lock方法或unlock方法的线程都会一直阻塞。这就是嵌套管程锁死。
     * 一个更现实的例子
     * 你可能会说，这么挫的实现方式我怎么可能会做呢？你或许不会在里层的管程对象上调用wait或notify方法，
     * 但完全有可能会在外层的this上调。
     * 有很多类似上面例子的情况。例如，如果你准备实现一个公平锁。
     * 你可能希望每个线程在它们各自的QueueObject上调用wait()，这样就可以每次唤醒一个线程。
     * 下面是一个比较挫的公平锁实现方式：
     * */

    public class FairLock{
      private boolean isLocked  = false;
      private Thread lockingThread = null;












    }















}
