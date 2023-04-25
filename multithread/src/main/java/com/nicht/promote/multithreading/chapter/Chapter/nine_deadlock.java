package com.nicht.promote.multithreading.chapter.Chapter;

/**
 * @Author Nicht
 * @Description  死锁
 * @Time 2021/4/28
 * @Link http://ifeve.com/deadlock/
 */
public class nine_deadlock {
      /**
       * 什么是死锁？
       * 所谓死锁，是指多个进程在运行过程中因争夺资源而造成的一种僵局，
       * 当进程处于这种僵持状态时，若无外力作用，它们都将无法再向前推进。
       * 因此我们举个例子来描述，如果此时有一个线程A，按照先锁a再获得锁b的的顺序获得锁
       * 而在此同时又有另外一个线程B，按照先锁b再锁a的顺序获得锁。如下图所示
       * */
      /* https://blog.csdn.net/hd12370/article/details/82814348
      * */
      /**
       * 产生死锁的原因？
       * 可归结为如下两点：
       * .A 竞争资源
       *    系统中的资源可以分为两类
       *    a1 可剥夺资源，是指某进程在获得这类资源后，该资源可以再被其他进程或系统剥夺，CPU和主存均属于可剥夺性资源；
       *    a2 另一类资源是不可剥夺资源，当系统把这类资源分配给某进程后，再不能强行收回，只能在进程用完后自行释放，如磁带机、打印机等。
       *    1产生死锁中的竞争资源之一指的是竞争不可剥夺资源
       *    （例如：系统中只有一台打印机，可供进程P1使用，
       *    假定P1已占用了打印机，若P2继续要求打印机打印将阻塞）
       *    2 产生死锁中的竞争资源另外一种资源指的是竞争临时资源
       *    （临时资源包括硬件中断、信号、消息、缓冲区内的消息等），
       *     通常消息通信顺序进行不当，则会产生死锁
       * B 死锁产生的4个必要条件
       *    1 互斥条件 进程要求对所分配的资源进行排它性控制，即在一段时间内某资源仅为一进程所占用。
       *    2 请求和保持条件  当进程因请求资源而阻塞时，对已获得的资源保持不放
       *    3 不剥夺条件 进程已获得的资源在未使用完之前，不能剥夺，只能在使用完时由自己释放。
       *    4 环路等待  在发生死锁时，必然存在一个进程--资源的环形链。
       * */
      /**解决死锁
       * 资源一次性分配: 一次性分配所有资源 这样就不会再有请求了：(破坏请求条件)
       * 只要有一个资源分配不到: 也不给这个进程分配其他的资源 (破坏保持条件)
       * 可剥夺资源 :即当某进程获得了部分资源,但得不到其他资源,那么就释放它已经持有的资源(破坏不可剥夺条件)
       * 资源有序分配法:系统给每类资源赋予一个编号,每一个进程按编号递增的顺序请求资源，释放则相反(破坏环路等待条件)
       * */
      /**
         *  1 以确定的顺序获得锁
         *   如果必须获取多个锁，那么在设计的时候需要充分考虑不同线程之前获得锁的顺序。
         *   按照上面的例子，两个线程获得锁的时序图如下：
         *
         *
         *
         *
         *
         * */
      /**
       * 死锁案例  死锁是两个或更多线程阻塞着等待其它处于死锁状态的线程所持有的锁。
       * 死锁通常发生在多个线程同时但以不同的顺序请求同一组锁的时候。
       * 该情况如下：
       * Thread 1  locks A, waits for B
       * Thread 2  locks B, waits for A
       * */
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1) {
                sleep(2000);
                System.out.println("线程1, 等lock2");
                synchronized (lock2) {
                    System.out.println("线程1完成");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {
                sleep(2000);
                System.out.println("线程2, 等lock1");
                synchronized (lock1) {
                    System.out.println("线程2完成");
                }
            }
        }).start();
    }
    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

































}
