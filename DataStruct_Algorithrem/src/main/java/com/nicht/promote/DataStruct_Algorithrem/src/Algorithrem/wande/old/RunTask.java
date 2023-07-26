package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.wande.old;

/**
 * @author nicht
 * @date 20220322
 */
public class RunTask  implements  Runnable{
    private DispatchPool dispatchPool ;

    public RunTask(DispatchPool dispatchPool) {
        this.dispatchPool = dispatchPool;
    }

    @Override
    public void run() {
        try {

            Task task  = this.dispatchPool.getTask();
            while(task!=null){
                System.out.println( "run task-->"+Thread.currentThread().getName()+  task.toString());
                task  = this.dispatchPool.getTask();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
