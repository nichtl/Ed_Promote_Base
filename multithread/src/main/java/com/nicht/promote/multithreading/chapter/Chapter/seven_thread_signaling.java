package com.nicht.promote.multithreading.chapter.Chapter;

/**
 * @Author Nicht
 * @Description  线程通信
 * @Time 2021/4/25
 * @Link  http://ifeve.com/thread-signaling/
 */
public class seven_thread_signaling {
    /** 线程通信的目标是使线程间能够互相发送信号。另一方面，线程通信使线程能够等待其他线程的信号*/
    /** JAVA线程间通信的主题
     * 1 通过共享对象通信
     * 2 忙等待
     * 3 wait(),notify()和notifyAll()
     * 4 丢失的信号
     * 5 假唤醒  (自旋锁)
     * 6 多线程等待相同信号
     * 7 不要对常量字符串或全局对象调用wait()
     **/
    /**  通过共享对象通信
     * 例如，线程B可以等待线程A的一个信号，这个信号会通知线程B数据已经准备好了
     * 线程间发送信号的一个简单方式是在共享对象的变量里设置信号值。
     *  线程A在一个同步块里设置boolean型成员变量hasDataToProcess为true，
     *  线程B也在同步块里读取hasDataToProcess这个成员变量。
     *  这个简单的例子使用了一个持有信号的对象，并提供了set和check方法:
     *  联想到信号量这个东西 MySignal
     *  线程A和线程B必须获得一个MySignal共享实例的引用(必须是同一个共享实例)
     *  如果它们持有的引用指向不同的MySingal实例，那么彼此将不能检测到对方的信号。
     *  需要处理的数据可以存放在一个共享缓存区里，它和MySignal实例是分开存放的
     *
     * */  //1
    /**  忙等待(Busy Wait)
     * 准备处理数据的线程B 正在等待数据变为可用,换句话说,他在等待线程A的一个信号,这个信号使得hasDataToProcess()返回true.
     * 线程B允许在一个循环中,以等待这个信号.
     *   protected MySignal sharedSignal = ...
     *    (等待线程A 将hasDataToProcess设为true)
     *   while(!sharedSignal.hasDataToProcess()){
     *   //do nothing... busy waiting
     * }
     * */  //2

    /**
     * wait(),notify()和notifyAll()
     * 上述的忙等待 使得线程B一直在循环监听,这样对资源很浪费,其等待的时间可能远超过执行时间
     * 忙等待没有对运行等待线程的CPU进行有效的利用，除非平均等待时间非常短。
     * 否则，让等待线程进入睡眠或者非运行状态更为明智，直到它接收到它等待的信号。
     * Java有一个内建的等待机制来允许线程在等待信号的时候变为非运行状态。
     * java.lang.Object 类定义了三个方法，wait()、notify()和notifyAll()来实现这个等待机制。
     * 一个线程一旦调用了任意对象的wait() 就会变为非运行状态直到另外一个线程调用了这个对象的notify()
     * 为了调用 wait()和notify() 线程必须先获得改对象的锁也就是说线程必须在同步块中调用wait()和notify()
     * wait()和notify()必须是在同步块中使用
     * 下面是wait  notify() 使用案例
     * 等待线程将调用doWait() 而唤醒线程将调用 doNotify() 当一个线程调用一个对象的notify()方法
     * 正在等待该对象的所有线程将有一个线程被唤醒并允许执行(这个将被唤醒的线程是随机的，不可以指定唤醒哪个线程)
     * 同时也提供了一个notifyAll()方法来唤醒正在等待一个给定对象的所有线程。
     * 如你所见，不管是等待线程还是唤醒线程都在同步块里调用wait() 和 notify()
     * 这是强制性的！一个线程如果没有持有对象锁，将不能调用wait()，notify()或者notifyAll()。否则，会抛出IllegalMonitorStateException异常。
     * JVM是这么实现的，当你调用wait时候它首先要检查下当前线程是否是锁的拥有者，不是则抛出IllegalMonitorStateExcept，参考JVM源码的 1422行。
     * 问题: 等待线程在同步块中执行的时候,不是持有该同步块的锁吗?
     * 等待线程不能阻塞唤醒线程进入doNotify()的同步块吗?
     * 答案是：的确不能。一旦线程调用了wait()方法,它就释放了所持有的监视器对象上的锁。这将允许其他线程也可以调用wait()或者notify()。
     *
     * @Link https://blog.csdn.net/y277an/article/details/98697454 notify()和notifyAll()方法不会保存调用它们的方法，因为当这两个方法被调用时，有可能没有线程处于等待状态
     * //丢失的信号(Missed Signals) (即线程在执行等待 wait()操作前先被执行唤醒操作)
     * 等待的线程将错过这个信号。这可能是也可能不是个问题。不过，在某些情况下，这可能使等待线程永远在等待，不再醒来，因为线程错过了唤醒信号。
     * 问题: 难道唤醒信号只会发送一次吗？
     * 这样当线程在执行wait()操作时就可能陷进无限等待的状态
     */
    /**wait  notify() 使用案例*/
    public class MonitorObject {
    }

    public class MyWaitNotify {
        MonitorObject monitorObject = new MonitorObject();

        public void doWait() {
            synchronized (monitorObject) {   //必须在同步块中
                try {
                    monitorObject.wait();//休眠
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void doNotify() {
            synchronized (monitorObject) {
                try {
                    monitorObject.notify();//唤醒
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 信号丢失改造
     * 留意doNotify()方法在调用notify()前把wasSignalled变量设为true。
     * 同时，留意doWait()方法在调用wait()前会检查wasSignalled变量。
     * 事实上，如果没有信号在前一次doWait()调用和这次doWait()调用之间的时间段里被接收到，
     * 它将只调用wait()。
     */
    public class MyWaitNotify2 {
        MonitorObject myMonitorObject = new MonitorObject();
        boolean wasSignalled = false;   //记录该MyWaitNotify2的实例是否被通知过

        public void dowait() {
            synchronized (myMonitorObject) {
                try {
                    if (!wasSignalled) { // 检查是否被通知过 // 如果没有被通知 才进行wait操作
                        myMonitorObject.wait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //如果跳过说明在wait 操作前就被通知了  设置通知信号为false
                wasSignalled = false;
            }
        }

        public void doNotify() {
            synchronized (myMonitorObject) {
                if (wasSignalled) {
                    return;   // 被通知过直接返回
                } else {
                    wasSignalled = true;  // 设置通知过
                    myMonitorObject.notify();
                }
            }
        }
    }

    /**
     * 假唤醒
     * 由于莫名其妙的原因 线程可能在没有调用过notify()和notifyAll()的情况下醒来.这就是所谓的假唤醒.无端端地醒过来了
     * 如果在MyWaitNotify2的方法中发生了假唤醒,等待线程即使没有收到正确的信号
     * 也能够执行后续的操作,这样可能会导致应用程序出现严重问题
     * 为了防止假唤醒,保存信号的成员变量将在一个while里接收检查,而不是在一个if表达式中这样的
     * (这种做法要慎重，目前的JVM实现自旋会消耗CPU，如果长时间不调用doNotify方法，doWait方法会一直自旋，CPU会消耗太大)
     * 被唤醒的线程会自旋直到自旋锁(while循环)中的条件变为flase   //即不符合唤醒条件时  让该线程不断自行休眠
     */

    public class MyWaitNotify3{
        MonitorObject myMonitorObject = new MonitorObject();
        boolean waitSignal = false;

        public  void dowait(){
            synchronized (myMonitorObject){
                try {
                    while (!waitSignal) {
                        myMonitorObject.wait();  // 自旋不断让线程调用自己wait
                    }
                }catch (Exception e){e.printStackTrace();}
                //如果跳过说明在wait 操作前就被通知了  设置通知信号为false
                waitSignal=false;
            }
        }

        public void doNotify() {
            synchronized (myMonitorObject) {

                    waitSignal = true;  // 设置通知过
                    myMonitorObject.notify();
                }
        }

    }

   /**多个线程等待相同的信号
    * 如果你有多个线程在等待，被notifyAll()唤醒，
    * 但只有一个被允许继续执行，使用while循环也是个好方法。
    * 每次只有一个线程可以获得监视器对象锁，意味着只有一个线程可以退出wait()调用并清除wasSignalled标志（设为false）。
    * 一旦这个线程退出doWait()的同步块，其他线程退出wait()调用，并在while循环里检查wasSignalled变量值。
    * 但是，这个标志已经被第一个唤醒的线程清除了，所以其余醒来的线程将回到等待状态，直到下次信号到来。
    * MyWaitNotify3  notifyAll()
    * */

   public class MyWaitNotify4{
       MonitorObject myMonitorObject = new MonitorObject();
       boolean waitSignal = false;

       public  void dowait(){
           synchronized (myMonitorObject){
               try {
                   while (!waitSignal) {
                       myMonitorObject.wait();
                       // 当notifyAll()唤醒了所有等待的线程这个时候所有的线程都会进入锁池中争夺锁
                       //当某个线程程抢到锁后继续执行开始退出wait同步块 其他线程退出wait()调用,并在while循环里检查wasSignalled变量值
                       // 这个时候唤醒线程退出while循环 执行了waitSignal=false;  这样其他推出wait调用的线程又陷入自旋
                   }
               }catch (Exception e){e.printStackTrace();}
               //bei 设置通知信号为false
               waitSignal=false;
           }
       }
       public void doNotify() {
           synchronized (myMonitorObject) {
               waitSignal = true;  // 设置通知过
               myMonitorObject.notify();
           }
       }



   }

   /** 不要在字符串常量或全局对象中调用 wait()
    * ：本章说的字符串常量指的是值为常量的变量）
    * 字符串常量是是唯一的 当所有使用这个值为常量的变量
    * 即使是在不同线程中声明的,其实最后引用都是字符串常量池里的同一个对象
    * 这样两个不相关的的线程可能因为使用了同一个字符串常量的变量而引起阻塞
    *
    * 在空字符串作为锁的同步块(或者其他常量字符串)里调用wait()和notify()产生的问题是，
    * JVM/编译器内部会把常量字符串转换成同一个对象。这意味着，即使你有2个不同的MyWaitNotify实例，
    * 它们都引用了相同的空字符串实例。同时也意味着存在这样的风险：
    * 在第一个MyWaitNotify实例上调用doWait()的线程会被在第二个MyWaitNotify实例上调用doNotify()的线程唤醒。
    * 这样可能导致线程之间被错误的唤醒和执行
    *  起初这可能不像个大问题。毕竟，如果doNotify()在第二个MyWaitNotify实例上被调用
    *  ，真正发生的事不外乎线程A和B被错误的唤醒了,这个被唤醒的线程（A或者B）将在while循环里检查信号值
    *  然后回到等待状态，因为doNotify()并没有在第一个MyWaitNotify实例上调用，而这个正是它要等待的实例。
    *  这种情况相当于引发了一次假唤醒。线程A或者B在信号值没有更新的情况下唤醒。但是代码处理了这种情况，
    *  所以线程回到了等待状态。记住，即使4个线程在相同的共享字符串实例上调用wait()和notify()，doWait()
    *  和doNotify()里的信号还会被2个MyWaitNotify实例分别保存。在MyWaitNotify1上的一次doNotify()调用
    *  可能唤醒MyWaitNotify2的线程，但是信号值只会保存在MyWaitNotify1里。
    * 问题在于，由于doNotify()仅调用了notify()而不是notifyAll()
    * 即使有4个线程在相同的字符串（空字符串）实例上等待，只能有一个线程被唤醒。
    * 所以，如果线程A或B被发给C或D的信号唤醒，它会检查自己的信号值，看看有没有信号被接收到，
    * 然后回到等待状态。而C和D都没被唤醒来检查它们实际上接收到的信号值，这样信号便丢失了。
    * 这种情况相当于前面所说的丢失信号的问题。C和D被发送过信号，只是都不能对信号作出回应。
    * 如果doNotify()方法调用notifyAll()，而非notify()，所有等待线程都会被唤醒并依次检查信号值。
    * 线程A和B将回到等待状态，但是C或D只有一个线程注意到信号，并退出doWait()方法调用。
    * C或D中的另一个将回到等待状态，因为获得信号的线程在退出doWait()的过程中清除了信号值(置为false)。
    * 看过上面这段后，你可能会设法使用notifyAll()来代替notify()，但是这在性能上是个坏主意。
    * 在只有一个线程能对信号进行响应的情况下，没有理由每次都去唤醒所有线程。
    * 所以：在wait()/notify()机制中，不要使用全局对象，字符串常量等。应该使用对应唯一的对象。
    * 例如，每一个MyWaitNotify3的实例（前一节的例子）拥有一个属于自己的监视器对象，而不是在空字符串上调用wait()/notify()
    * */

   public class MyWaitNotify5{
       String myMonitorObject = "";  // 字符串常量是是唯一的 当所有使用这个值为常量的变量
       // 即使是在不同线程中声明的,其实最后引用都是字符串常量池里的同一个对象
       boolean waitSignal = false;

       public  void dowait(){
           synchronized (myMonitorObject){
               try {
                   while (!waitSignal) {
                       myMonitorObject.wait();
                       // 当notifyAll()唤醒了所有等待的线程这个时候所有的线程都会进入锁池中争夺锁
                       //当某个线程程抢到锁后继续执行开始退出wait同步块 其他线程退出wait()调用,并在while循环里检查wasSignalled变量值
                       // 这个时候唤醒线程退出while循环 执行了waitSignal=false;  这样其他推出wait调用的线程又陷入自旋
                   }
               }catch (Exception e){e.printStackTrace();}
               //bei 设置通知信号为false
               waitSignal=false;
           }
       }
       public void doNotify() {
           synchronized (myMonitorObject) {
               waitSignal = true;  // 设置通知过
               myMonitorObject.notify();
           }
       }



   }























   public static void main(String[] args) {

    }






}
