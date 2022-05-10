package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.wande;

import java.util.concurrent.LinkedBlockingQueue;
/**
 * @author nicht
 * @date 20220322
 */
public class ResourceProducer implements  Runnable{
    private  LinkedBlockingQueue<Integer> resourcePool;

    public ResourceProducer(LinkedBlockingQueue<Integer> resourcePool) {
        this.resourcePool = resourcePool;
    }


    @Override
    public void run() {
        try {
            while(resourcePool.size()<=10000) {
               add();
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    synchronized  void add(){
        try {
                this.resourcePool.put(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
