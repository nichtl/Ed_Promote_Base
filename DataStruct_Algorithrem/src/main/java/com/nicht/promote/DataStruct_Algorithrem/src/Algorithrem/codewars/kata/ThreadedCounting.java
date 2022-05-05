package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.codewars.kata;

import java.util.concurrent.Semaphore;

/**
 * 功能描述：
 *
 * @author xujian8
 */
//Your task is simple.
// You have to call counter.count(int) with the numbers 1 to 100 inclusive.
// So a simple solution might look like this:
//
//for (int i = 1; i <= 100; i++) {
//counter.count(i);
//}
//But there's a catch. Your solution also has to satisfy the following conditions:
//
//Three different threads must be used
//Numbers of the form 3n (multiples of 3) must be called in one thread
//Numbers of the form 3n + 1 must be called in a second
//Numbers of the form 3n + 2 must be called in a third
//The numbers have to be called in sequence 1 to 100
//Also, make sure your method doesn't return until all three threads have completed.
// Otherwise the tests may not work even if your solution is correct.
//

public class ThreadedCounting {
    public static void countInThreads(Counter counter) {
        Semaphore a = new Semaphore(1);
        Semaphore b = new Semaphore(0);
        Semaphore c = new Semaphore(0);
        int tmp = 0;
        Thread thread1 = new Thread(()->{
            try {
                a.acquire();
                counter.count(tmp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             b.release();
        });

        for (int i = 1; i <=100 ; i++) {

        }



        // 1-100...go!
    }




class Counter{
public  void  count(int i){
}
}


}
