package com.nicht.promote.multithreading.chapter.TestExample;

/**
 * @Author Nicht
 * @Description 通过共享对象
 * @Time 2021/4/25
 * @Link http://ifeve.com/thread-signaling/
 */
public class MySignal {
    protected  boolean  hasDataToProcess = false ;
    public  synchronized  boolean  hasDataToProcess(){
        return  this.hasDataToProcess;
    }

    public synchronized void setHasDataToProcess(boolean hasDataToProcess) {
        this.hasDataToProcess = hasDataToProcess;
    }
}
