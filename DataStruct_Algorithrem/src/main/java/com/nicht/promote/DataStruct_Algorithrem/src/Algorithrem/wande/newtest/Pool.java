package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.wande.newtest;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Description
 * @Date 2023/5/11
 */
public  abstract  class Pool<T> {

   protected  abstract void create(int poolsize) throws Exception;

   protected  abstract void create(Queue queue) throws Exception;

   protected  abstract   void   enqueue(T  t) throws Exception;


   protected  abstract  T   dequeue() throws Exception;



   protected  abstract  boolean canEnqueue() throws  Exception;


   protected  abstract  int getQueueSize();


   protected  abstract  int getLeftFreeSize();
















}
