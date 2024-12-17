package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.codewars.kata;

import java.util.concurrent.atomic.AtomicBoolean;

public class al121 {
    //线程轮流打印 A-Z 1-26
    // 1A2B3C4D

    public static void main(String[] args) throws Exception {
        Object lock = new Object();
        AtomicBoolean printNum = new AtomicBoolean(true);
        Thread printNumThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 26; i++) {
                    synchronized (lock) {
                        while (!printNum.get()) { // 等待轮到数字线程
                            lock.wait();
                        }
                        System.out.print(i); // 打印数字
                        printNum.set(false); // 切换到字母线程
                        lock.notifyAll(); // 唤醒字母线程
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread printCharThread = new Thread(() -> {
            try {
                for (char i = 'A'; i <= 'Z'; i++) {
                    synchronized (lock) {
                        while (printNum.get()) { // 等待轮到字母线程
                            lock.wait();
                        }
                        System.out.print(i); // 打印字母
                        printNum.set(true); // 切换到数字线程
                        lock.notifyAll(); // 唤醒数字线程
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        printNumThread.start();
        printCharThread.start();
        printNumThread.join();
        printCharThread.join();
    }

}
