package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.deke;

import java.util.concurrent.Semaphore;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/8/26
 * @Link
 */
public class deke65 {
    /*
    * */
    public static void main(String[] args) throws InterruptedException {
      // thread print
        Semaphore semaphore =new Semaphore(1);
        semaphore.acquire();

    }

    class  MyRunnable implements Runnable {
        private Semaphore semaphore;
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
        @Override
        public void run() {

        }
    }

}
