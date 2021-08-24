package com.nicht.promote.multithreading.chapter.Chapter;

/**
 * @Author Nicht
 * @Description 线程安全与共享资源
 * @Time 2021/4/20
 * @Link http://ifeve.com/thread-safety/
 */
public class three_thread_safety {
    public static void main(String[] args) {

    }
        /**
        *  允许被多个线程同时执行的代码称作线程安全的代码。
        *  线程安全的代码不包含竞态条件。当多个线程同时更新共享资源时会引发竞态条件。
        *  因此，了解Java线程执行时共享了什么资源很重要。
        * */
        /**需要了解局部变量和全局变量的概念*/
        /**  局部变量和全局变量的区别
        *   java中没有全局变量这个概念只分为 成员变量和局部变量 。
        *   成员变量：Static修饰成为类变量或静态变量，还有就是方法外的变量。生命周期与类相同。
        *   局部变量：就是方法中的变量。生命周期就是存在方法中。 伴随生命周期的创建而创建  销毁而销毁
        *   在Java中Static修饰的类或变量 可以类似是c语言的的全局变量  可以被其他类所访问
        *   static变量在内存中只有一个，存放在方法区，属于类变量，被所有实例所共享
        *   局部变量生命周期是伴随着其所在方法创建消亡的
        *   定义在类中的静态变量或静态类 它的生命周期比起一般的方法要长可能会伴随着整个程序的创建和消亡
        *   想要用static存一个变量，使得下次程序运行时还能使用上次的值是不可行的。
        *   因为静态变量生命周期虽然长（就是类的生命周期），但是当程序执行完，也就是该类的所有对象都已经被回收，
        *   或者加载类的ClassLoader已经被回收，那么该类就会从jvm的方法区卸载，即生命期终止。
        *   static变量终究是存在jvm的内存中的，jvm下次重新运行时，肯定会清空里边上次运行的内容，包括方法区、常量区的内容。
        *   要实现某些变量在程序多次运行时都可以读取，那么必须要将变量存下来，即存到本地文件中。
        *   常用的数据存取格式: XML、JSON、Propertities类（类似map的键值对）等
        * */
        /** java线程执行时共享了什么资源 */
        /**  局部变量的分为基础类型局部变量和局部变量的引用
        * 在方法中声明的变量，即该变量是局部变量，每当程序调用方法时，系统都会为该方法建立一个方法栈，
        * 其所在方法中声明的变量就放在方法栈中，当方法结束系统会释放方法栈，其对应在该方法中声明的变量随着栈的销毁而结束，
        * 这就局部变量只能在方法中有效的原因
        * 在方法中声明的变量可以是基本类型的变量，也可以是引用类型的变量。
        * 当声明是基本类型的变量的时，其变量名及值（变量名及值是两个概念）是放在JAVA虚拟机栈中  （当变量为基本类型会直接在栈中创建）
        * 当声明的是引用变量时，所声明的变量（该变量实际上是在方法中存储的是内存地址值）是放在JAVA虚拟机的栈中，该变量所指向的对象是放在堆类存中的。
        * */
        /** 局部变量  (线程安全)
        局部变量存储在线程自己的栈中。也就是说，局部变量永远也不会被多个线程共享。
        所以，基础类型的局部变量是线程安全的。下面是基础类型的局部变量的一个例子：
        java 中的基础类局部变量存在方法体里只会被该方法所使用
          public void someMethod(){
          long threadSafeInt = 0;
          threadSafeInt++;
          }
        */
        /** 局部的对象引用 ()
        *  对象的局部引用和基础类型的局部变量又不太一样
        *  在方法中 创建的变量非基本类型时,该变量存放内存地址指向堆中的对象
        *  如 Interger String  尽管这个变量的存放的内存地址没有共享 但引用所指向的对象并没有存储在线程的栈内
        *  所有的对象都存在共享堆中。如果在某个方法中创建的对象不逃逸出(即该对象不会被其他方法获得,也不会被非局部变量引用到)该方法,
        *  那么它就是线程安全的。实际上，哪怕将这个对象作为参数传给其它方法，
        *  只要别的线程获取不到这个对象，那它仍是线程安全的。下面是一个线程安全的局部引用样例：
        * public void someMethod(){
        *   LocalObject localObject = new LocalObject();
        *   localObject.callMethod();
        *   method2(localObject);
        * }
        * public void method2(LocalObject localObject){
        *   localObject.setValue("value");
        * }
        *   LocalObject对象没有被方法返回，也没有被传递给someMethod()方法外的对象。
        *   每个执行someMethod()的线程都会创建自己的LocalObject对象，并赋值给localObject引用。
        *   因此，这里的LocalObject是线程安全的。事实上，
        *   整个someMethod()都是线程安全的。即使将LocalObject作为参数传给同一个类的其它方法或其它类的方法时，
        *   它仍然是线程安全的。当然，如果LocalObject通过某些方法被传给了别的线程,那它就不再是线程安全的了。(比如在方法放将LocalObject传给一个新开的线程这时就出现
        *   LocalObject内存出现逃逸 那么它不是不再是线程安全)
        * */
        /**
        *  对象成员存储在堆上。如果两个线程同时更新同一个对象的同一个成员，
        *  那这个代码就不是线程安全的。下面是一个样例：
        *   public class NotThreadSafe{
        *   StringBuilder builder = new StringBuilder();
        *   public add(String text){
        *   this.builder.append(text);
        *    }  }
        *    如果两个线程同时调用同一个NotThreadSafe实例上的add()方法，
        *   就会有竞态条件问题。例如
        *  NotThreadSafe sharedInstance = new NotThreadSafe();
        *  //使用统一 sharedInstance 会出现  竞态条件
        *  new Thread(new MyRunnable(sharedInstance)).start();
        *  new Thread(new MyRunnable(sharedInstance)).start();
        *
        *  public class MyRunnable implements Runnable{
        *
        *  NotThreadSafe instance = null;
        *
        * public MyRunnable(NotThreadSafe instance){
        *  this.instance = instance; }
        * public void run(){
        *  this.instance.add("some text");
        * } }
        *  要解决竞态条件只需要保证两个线程不会共享一个资源
        *  new Thread(new MyRunnable(new NotThreadSafe())).start();
        *  new Thread(new MyRunnable(new NotThreadSafe())).start();
        * */
        /**   线程控制逃逸规则
        *   线程控制逃逸规则可以帮助你分析代码中对某些资源的访问是否是线程安全的。
        *   如果一个资源的创建，使用，销毁都在同一个线程内完成，
        *   且永远不会脱离该线程的控制，则该资源的使用就是线程安全的。
        *   //资源可以是对象 数组 文件 数据连接  套接字等等
        *  Java中你无需主动销毁对象,
        *  所以“销毁”指不再有引用指向对象。 ( 拓展 jvm中的内存回收释放的机制 ,
        *  其中 引用计数发 即对资源分配 一个 引用计数器当该资源引用为0时, 便会由jvm的进行回收.)
        *
        *  但是值得注意的是 即使线程使用的对象是线程安全的 但是当这个对象中持有其他资源时时也许就不是线程安全的了
        *  例如
        *    new Thread(new MyRunnable(new NotThreadSafe())).start();
        *    new Thread(new MyRunnable(new NotThreadSafe())).start();
        *    这时候   NotThreadSafe是线程安全的但是改造NotThreadSafe一下
        *    public class NotThreadSafe{
        *     StringBuilder builder;
        *     public  NotThreadSafe(StringBuilder builder){
        *      builder = builder;
        *     }
        *      public add(String text){
        *      this.builder.append(text);
        *      }
        *  }
        *   StringBuilder builder  = new StringBuilder();
        *   new Thread(new MyRunnable(new NotThreadSafe(builder))).start();
        *   new Thread(new MyRunnable(new NotThreadSafe(builder))).start();
        *   这个时候虽然NotThreadSafe这个对象是线程安全的但是这两个对象里都持有同一个builder资源并对它进行操作
        *   这个时候就出现竞态条件  线程不再安全
        *   (总结)
        *   即使对象本身线程安全，但如果该对象中包含其他资源（文件，数据库连接），整个应用也许就不再是线程安全的了。比如2个线程都创建了各自的数据库连接，每个连接自身是线程安全的，但它们所连接到的同一个数据库也许不是线程安全的。比如，2个线程执行如下代码：
        * 检查记录X是否存在，如果不存在，插入X
        * 如果两个线程同时执行，而且碰巧检查的是同一个记录，那么两个线程最终可能都插入了记录：
        * 线程1检查记录X是否存在。检查结果：不存在
        * 线程2检查记录X是否存在。检查结果：不存在
        * 线程1插入记录X
        * 线程2插入记录X
        * 同样的问题也会发生在文件或其他共享资源上。因此，区分某个线程控制的对象是资源本身，还是仅仅到某个资源的引用很重要。
        * */















































}
