package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.wande;

/**
 * @author nicht
 * @date 20220322
 */
public interface Pool {
    TASK getTask();
    void  addTask(TASK task);
    void  distributionResource();
    void  addResource(Object t);
}
