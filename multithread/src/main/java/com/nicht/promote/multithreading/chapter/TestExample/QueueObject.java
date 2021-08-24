package com.nicht.promote.multithreading.chapter.TestExample;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/5/7
 * @Link
 */
public class QueueObject {
       private  boolean isNotfied = false;
       public   synchronized  void dowait() throws  InterruptedException{
           while (!isNotfied){
               this.wait();
           }
           this.isNotfied =false;
       }

       public synchronized  void doNotify(){
           this.isNotfied = true;
           this.notify();
       }
       @Override
       public boolean equals(Object o){
           return  this ==o;
       }
}
