package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.wande.old;

import java.util.concurrent.LinkedBlockingQueue;
/**
 * @author nicht
 * @date 20220322
 */
public class test {

    public static void main(String[] args) {
         LinkedBlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();
         LinkedBlockingQueue<Integer> resourcePool = new LinkedBlockingQueue<>();
         DispatchPool dispatchPool = new DispatchPool(taskQueue,resourcePool);
         TaskProducer taskProducer  = new TaskProducer(taskQueue);
         ResourceProducer resourceProducer  = new ResourceProducer(resourcePool);
         RunTask runTask  = new RunTask(dispatchPool);
        for (int i = 0; i < 10; i++) {
            new Thread(taskProducer,"taskProducerThread-"+i).start();
            new Thread(resourceProducer,"resourceProducerThread-"+i).start();
            new Thread(runTask,"runTaskThread-"+i).start();
        }

    }
}
