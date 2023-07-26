package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.wande.old;

/**
 * @author nicht
 * @date 20220322
 */
public interface Pool {
    Task getTask();
    void  addTask(Task task);
    void  distributionResource();
    void  addResource(Object t);
}
