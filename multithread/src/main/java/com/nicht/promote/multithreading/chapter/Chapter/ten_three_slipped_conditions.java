package com.nicht.promote.multithreading.chapter.Chapter;

/**
 * @Author Nicht
 * @Description  Slipped conditions //失控条件
 * @Time 2021/5/8
 * @Link  http://ifeve.com/slipped-conditions/
 */
public class ten_three_slipped_conditions {
    /**
     * 所谓Slipped conditions，就是说， 从一个线程检查某一特定条件到该线程操作此条件期间，
     * 这个条件已经被其它线程改变，导致第一个线程在该条件上执行了错误的操作。这里有一个简单的例子：
     * 简单的来说多线程下同步块没有对控制条件的访问进行限制
     * 导致多个线程可能同时访问该条件并成立进入执行同步块
     *  example
     * */
    /*
    * 1 如果已经上锁就让其等待 否则设置isLocked=true为锁住
    * */
    public class  lock{
        private boolean  isLocked = true;
        public  void lock(){
            synchronized (this){
                while(isLocked){
                    try{
                        this.wait();
                    } catch (InterruptedException e){
                        //do nothing, keep waiting
                    }
                }
            }
            synchronized (this){
                isLocked = true;
            }
        }
        public  synchronized void unlock(){
            isLocked = false;
            this.notify();
        }
    }
    /**
     *我们可以看到，lock()方法包含了两个同步块。第一个同步块执行wait操作直到isLocked变为false才退出，
     *第二个同步块将isLocked置为true，以此来锁住这个Lock实例避免其它线程通过lock()方法。
     *我们可以设想一下，假如在某个时刻isLocked为false， 这个时候，
     *有两个线程同时访问lock方法。如果第一个线程先进入第一个同步块，
     *这个时候它会发现isLocked为false，若此时允许第二个线程执行，它也进入第一个同步块
     *同样发现isLocked是false。现在两个线程都检查了这个条件为false
     *然后它们都会继续进入第二个同步块中并设置isLocked为true。
     *这个场景就是slipped conditions的例子，两个线程检查同一个条件，
     *然后退出同步块，因此在这两个线程改变条件之前，就允许其它线程来检查这个条件。
     *换句话说,条件被某个线程检查到该条件被此线程改变期间，这个条件已经被其它线程改变过了。
     * */
    /**
     为避免slipped conditions，
     条件的检查与设置必须是原子的,
     也就是说,在第一个线程检查和设置条件期间，
     不会有其它线程检查这个条件。
     解决上面问题的方法很简单,只是简单的把isLocked = true这行代码移到第一个同步块中,放在while循环后面即可:
    */
    private boolean  isLocked = true;
    public  void Lock(){
        synchronized (this){
            while(isLocked){
                try{
                    this.wait();
                } catch (InterruptedException e){
                    //do nothing, keep waiting
                }
            }
            isLocked = true; // 这样条件的修改和检查就保持一致了
        }
    }





























}
