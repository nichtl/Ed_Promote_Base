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
}
