package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.wande;

import java.util.Comparator;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * @author nicht
 * @date 20220322
 */
public class DispatchPool {

    private  volatile  Integer status=0;
    private  final  static  int MAXPOOLSIZE = 5;
    private  volatile int CURPOOLSIZE=0;
    private   LinkedBlockingQueue<TASK> TASKQUEUE;
    private   LinkedBlockingQueue<Integer> RESOURCEPOOL;
    private   LinkedBlockingQueue<TASK> POOL;

    public DispatchPool(LinkedBlockingQueue<TASK> taskQueue, LinkedBlockingQueue<Integer> resourcePool) {
        this.TASKQUEUE = taskQueue;
        this.RESOURCEPOOL = resourcePool;
        this.POOL = new LinkedBlockingQueue<TASK>(MAXPOOLSIZE);

    }


    public  synchronized void   addTask(TASK task)  {
        if(status==0) {this.status=1;}
        if(this.POOL.size()==MAXPOOLSIZE || task==null){ System.out.println("can't put task ,queue is full");
          notifyAll();
        }
        try {
            this.POOL.put(task);
            orderByPriority();
        }catch (Exception e){e.printStackTrace();}
    }


    public synchronized TASK getTask() throws InterruptedException {
        if(this.POOL.size() == 0 ) {
            System.out.println("can't get task ,queue is empty");
            notifyAll();
        }
        TASK task = this.TASKQUEUE.take();

        try {
           while(task == null||task.getResourceNum()> (long) RESOURCEPOOL.size()){
              if (RESOURCEPOOL.size()==0){
                  System.out.println("resource exhaustion");
                  return  null;
              }
               this.TASKQUEUE.put(task);
               task=this.TASKQUEUE.poll();
           }
           distributionResource(task.getResourceNum());
           this.TASKQUEUE.remove(task);
           orderByPriority();
           obtainTaskFromTaskQueue();
       }catch (Exception e ){e.printStackTrace();}
        return  task;
    }



    public  synchronized  void distributionResource(Integer resourceNum) throws InterruptedException {
        for (int i = 0; i <resourceNum ; i++) {
            this.RESOURCEPOOL.take();
        }

    }

    public synchronized   void  obtainTaskFromTaskQueue()  {
        try {
            if(this.POOL.size()<MAXPOOLSIZE && this.TASKQUEUE.size()>0){
                for (int i = this.POOL.size(); i <MAXPOOLSIZE ; i++) {
                    this.POOL.put(TASKQUEUE.take());
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public  void orderByPriority(){
        this.POOL.stream().sorted(new Comparator<TASK>() {
            @Override
            public int compare(TASK o1, TASK o2) {
                return Integer.compare(o2.getPriority(), o1.getPriority());
            }
        });
    }










}
