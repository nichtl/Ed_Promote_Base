package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.wande.old;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * @author nicht
 * @date 20220322
 */
public class TaskProducer implements Runnable {
    private  LinkedBlockingQueue<Task>  taskQueue;

    public TaskProducer(LinkedBlockingQueue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        try {
            while(taskQueue.size()<=10000) {
                add();
            }
        }catch (Exception e) {e.printStackTrace();}
    }
    synchronized  void add(){
        try {
            Random random  = new Random();
            this.taskQueue.put(new Task(UUID.randomUUID().toString(),random.nextInt(100),random.nextInt(10)));

        }catch (Exception e){e.printStackTrace();}
    }
}
