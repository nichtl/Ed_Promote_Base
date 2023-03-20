package com.nicht.promote.multithreading.chapter.Chapter;

/**
 * @Author Nicht
 * @Description  TheadLocal
 * @Time 2021/4/27
 * @Link http://ifeve.com/java-theadlocal/
 */
public class eight_java_theadlocal {
    InheritableThreadLocal<Integer>  parentLocal  = new InheritableThreadLocal(){
        /**
         * Returns the current thread's "initial value" for this
         * thread-local variable.  This method will be invoked the first
         * time a thread accesses the variable with the {@link #get}
         * method, unless the thread previously invoked the {@link #set}
         * method, in which case the {@code initialValue} method will not
         * be invoked for the thread.  Normally, this method is invoked at
         * most once per thread, but it may be invoked again in case of
         * subsequent invocations of {@link #remove} followed by {@link #get}.
         *
         * <p>This implementation simply returns {@code null}; if the
         * programmer desires thread-local variables to have an initial
         * value other than {@code null}, {@code ThreadLocal} must be
         * subclassed, and this method overridden.  Typically, an
         * anonymous inner class will be used.
         *
         * @return the initial value for this thread-local
         */
        @Override
        protected Object initialValue() {
            return new Integer(123);
        }
    };
    public static void main(String[] args) {

       try {
           Thread t1 = new Thread(new TheadLocalRunnable(), "t1");

           Thread t2 = new Thread(new TheadLocalRunnable(), "t2");
           t1.start();
           t2.start();
           t2.join();// join 方法使得等待调用该方法的线程先执行完毕再执行其他线程  控制执行顺序
           t1.join();


       }catch (Exception e){e.printStackTrace();}
    }

   // Java中的ThreadLocal类可以让你创建的变量只被同一个线程进行读和写操作。
   // 因此，尽管有两个线程同时执行一段相同的代码，而且这段代码又有一个指向同一个ThreadLocal变量的引用，
   // 但是这两个线程依然不能看到彼此的ThreadLocal变量域。
   //InheritableThreadLocal类是ThreadLocal的子类。为了解决ThreadLocal实例内部每个线程都只能看到自己的私有值，
   // 所以InheritableThreadLocal允许一个线程创建的所有子线程访问其父线程的值。
    ThreadLocal  threadLocal  = new ThreadLocal();

   public static  class  ParentTheadLocal extends Thread{

       /**
        * If this thread was constructed using a separate
        * <code>Runnable</code> run object, then that
        * <code>Runnable</code> object's <code>run</code> method is called;
        * otherwise, this method does nothing and returns.
        * <p>
        * Subclasses of <code>Thread</code> should override this method.
        * @see #start()
        * @see #stop()
        */
       @Override
       public void run() {
           Thread t1 = new Thread(new TheadLocalRunnable(), "t1");
       }
   }

   public static  class  TheadLocalRunnable implements  Runnable{
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
       //维护一个 theadLocal 变量
       private  ThreadLocal  <Integer>   threadLocal  = new ThreadLocal(){
           @Override
           protected Object initialValue() {
               return new Integer(0);
           }
       };
       @Override
       public void run() {
           for (int  i= 0; i<10;  ++i) {
               threadLocal.set(threadLocal.get()+1);
           }
           System.out.println(Thread.currentThread().getName()+threadLocal.get());

       }
   }
//
//1	测试附加项目关联问题	0	3
//2	你好	8957	2	     satisfy
//3	酒店相关问题	8957	3
//4	三人间	8957	2	satisfy
//5	售前电话	0	2	guide
//6	售前电话	8957	2	guide
//7	你好	0	2	satisfy
//8	这个订单&nbsp;	7953	2	guide
//9	1、4-5人立减800一单，	7953	2	failure
//10	我做增补退给客人了哈	7953	2	failure
//11	没有出来	7953	2	guide
//12	如何更换酒店	0	2	satisfy
//13	当地天气	8957	2	satisfy
//14	当地天气	8957	2	satisfy
//15	当地天气	0	2	satisfy
//16	咨询供应商联系方式	21517	3
//17	云南售前供应商联系方式	21517	3
//18	三人间	8957	2	satisfy
//19	三人间	8957	2	satisfy
//20	三人间	8957	2	satisfy
//21	三人间	8957	2	satisfy
//22	客人问他们离上车地点大概三公里远，有七八人，在诚信钢材市场能去接吗	32672	2	guide
//23	上车点附近有没有停车场？上车点离客人住的地方远不远？能不能在大巴车途经的地方上车？	32672	3
//24	换&nbsp; 28号出游&nbsp;	13364	2	failure
//25	您好。客人问在诚信钢材市场离上车点三公里左右，八人，能否上门接	32672	2	failure
//26	咨询供应商联系方式	31912	3
//27	云南售前供应商联系方式	31912	3
//28	核实下	6181	2	failure
//29	咨询供应商联系方式	16176	3
//30	云南售前供应商联系方式	16176	3
//31	测试附加项目关联问题	6675768	3
//32	客户说加床还收费&nbsp; 不合理&nbsp;	6181	2	failure
//33	预订相关问题	12803	3
//34	你好，核实一下今晚入住的酒店	12803	2	failure
//35	接站的人员信息	12803	2	failure
//36	哎&nbsp;&nbsp; 是的&nbsp; 我们座位同行是理解的&nbsp; 厦门不仅仅你们一家&nbsp; 都是这样的	6181	2	failure
//37	这个169是我们公司的利润	7953	2	failure
//38	我没给客人参加黑卡活动	7953	2	failure
//39	然后补了进来	7953	2	failure
//40	参加了 又被我做平了	7953	2	failure
}
