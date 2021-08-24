package com.nicht.promote.multithreading.chapter.Chapter;

import com.nicht.promote.multithreading.chapter.TestExample.TestSynchronized;

/**
 * @Author Nicht
 * @Description  Java同步块
 * @Time 2021/4/23
 * @Link http://ifeve.com/synchronized-blocks/
 */
public class six_synchronized_blocks {

    /**
     * Java 同步块（synchronized block）用来标记方法或者代码块是同步的。
     * Java 同步块用来避免竞争。本文介绍以下内容：
     * Java同步关键字（synchronzied）
     * 实例方法同步
     * 静态方法同步
     * 实例方法中同步块
     * Java同步示例
     * Java 同步关键字（synchronized）
     *   Java中的同步块用synchronized标记 同步块在Java中是同步在某个对象上
     *   所有同步在一个对象上的同步块在同时只能被一个线程进入并执行操作。
     *   所有其他等待进入该同步块的线程将被阻塞,直到执行该同步块中的线程退出。
     *   有四种不同的同步块
     *    1 实例方法
     *    2 静态方法
     *    3 实例方法中的同步块
     *    4 静态方法中的同步块
     * */
    /** 实例方法同步  案例
     *  public synchronized void add(int value){
     *      this.count += value;
     *  }
     *  在方法上使用 synchronized 关键字向java 声明该方法同步的
     *  Java实例方法同步是同步在拥有该方法的对象上。这样,每个实例其方法同步都同步在不同的对象上,
     *  即该方法所属的实例。只有一个线程能够在实例方法同步块中运行。如果有多个实例存在,
     *  那么一个线程一次可以在一个实例同步块中执行操作。一个实例一个线程。
     *  静态方法同步
     *  静态方法同步和实例方法同步方法一样,也使用synchronized 关键字。Java静态方法同步如下示例：
     *  public static synchronized void add(int value){
     *    count += value;
     *  }
     *  同样,这里synchronized 关键字告诉Java这个方法是同步的。  （对象锁和类锁）
     *  静态方法的同步是指同步在该方法所在的类对象上。因为在Java虚拟机中一个类只能对应一个类对象,
     *  所以同时只允许一个线程执行同一个类中的静态同步方法。
     *    静态方法和非静态方法 的加锁方式是不一样的  （对象锁和类锁）
     *    对象锁又称为 (对象锁)--实例锁  非静态方法的锁(对象锁/实例锁) 静态方法的锁(类锁)
     *    非静态方法的同步是同步在实例对象上的,该实例的同步只针对于该实例存在线程
     *    多个线程可以有多个实例并同时方法非静态方法的同步块,不会出现同步阻塞问题,前提是每个线程都拥有一个该实例
     *    (静态方法和非静态方法)
     *    静态方法在类加载的时候就已经存在了,第一次调用这个方法时,这个类对象就会加载如jvm中（内存）,并且只会加载一次,
     *    全类公有,可以用类名加,访问,也可以用这个类的对象调用,当用这个对象调用该方法时方法,
     *    且这个类对象在内存中只有一个,所以无论是哪个对象调用该方法时,都会调用事先加载好的方法,故不用new 这,就会调用jvm事先加载好的类对象的这个个类的对象,直接可以用类名加,调用
     *    为什么叫类锁  这是应为静态方法的同步是直接在.class文件中生效的一个class可以有多个对象但始终只有一个class文件
     *    即使多个线程通过多个该类的实例调用该静态同步方法也一样会被阻塞这就导致看上去像是这个类被锁住了一样
     *    类锁只是一种概念
     *    　由于一个class不论被实例化多少次，其中的静态方法和静态变量在内存中都只有一份。
     *    所以，一旦一个静态的方法被申明为synchronized。此类所有的实例化对象在调用此方法，
     *    共用同一把锁，我们称之为类锁。对象锁是用来控制实例方法之间的同步，类锁是用来控制静态方法
     *    （或静态变量互斥体）之间的同步。
     *    类锁只是一个概念上的东西，并不是真实存在的，它只是用来帮助我们理解锁定实例方法和静态方法的区别的。　　　
     *    java类可能会有很多个对象，但是只有1个Class对象，也就是说类的不同实例之间共享该类的Class对象。
     *    Class对象其实也仅仅是1个java对象，只不过有点特殊而已。由于每个java对象都有1个互斥锁，
     *    而类的静态方法是需要Class对象。所以所谓的类锁，不过是Class对象的锁而已。获取类的Class对象有好几种，
     *    最简单的就是［类名.class］的方式。
     *
     *
     *
     *
     * */
    public static void main(String[] args) {
       /** 对象锁测试*/
        TestSynchronized   tts =  new TestSynchronized();
        Thread  thread1  = new Thread(new Runnable() {
            @Override
            public void run() {
                tts.test1();
            }
        },"thread1");
        Thread  thread1_2=new Thread(new Runnable() {
            @Override
            public void run() {
                tts.test1_2();
            }
        },"thread1_2");
        //这两个thread调用的是同一个实例对象的非静态同步方法 因此是一个对象锁 会出现同步阻塞等待一个线程执行完毕释放锁后再继续执行
        //start线程测试
       /*    thread1.start();
        thread1_2.start();*/

        //类锁测试
        Thread  thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
             TestSynchronized.test2();
            }
        },"thread3");
        thread3.start();

        //测试类锁是否会锁住这个类  在thread3sleep    threadp成功执行 TestSynchronized  static方法
        //类锁只是一直方便理解静态同步的概念并不是真实存在的
        //说明类锁只是锁住静态同步变量或静态同步块  其他非同步静态方法仍然可以执行 因为普通方法的调用并不需要获得锁。
        Thread threadp = new Thread(new Runnable() {
            @Override
            public void run() {
                TestSynchronized.testprint();
            }
        },"threadp");
        threadp.start();

        Thread thread4  =new Thread(new Runnable() {
            @Override
            public void run() {
             tts.test2();
            }
        },"thread4");
        thread4.start();
        //thread3打印结果是和上面两个线程的交替打印的
        //在TestSynchronized中synchronized同时修饰静态方法和实例方法，
        // 但是运行结果是交替进行的，这证明了类锁和对象锁是两个不一样的锁，
        // 控制着不同的区域，它们是互不干扰的。同样，线程获得对象锁的同时，也可以获得该类锁，即同时获得两个锁，这是允许的。

    }
































}
