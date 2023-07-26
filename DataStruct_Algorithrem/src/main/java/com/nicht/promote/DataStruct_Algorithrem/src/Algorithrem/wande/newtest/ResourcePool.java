package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.wande.newtest;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Description
 * @Date 2023/5/12
 */
public class ResourcePool<T> extends Pool<T> {
    private volatile  Integer poolSize;

    private static volatile PriorityQueue resourceQueue;


    @Override
    protected void create(int poolsize) throws Exception {

    }

    @Override
    protected void create(Queue queue) throws Exception {

    }

    @Override
    protected void enqueue(T t) throws Exception {

    }

    @Override
    protected T dequeue() throws Exception {
        return null;
    }

    @Override
    protected boolean canEnqueue() throws Exception {
        return false;
    }

    @Override
    protected int getQueueSize() {
        return 0;
    }

    @Override
    protected int getLeftFreeSize() {
        return 0;
    }
}
