package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author Administrator
 * @Time 2022/1/7
 * @Link
 */
public class LRUCache {
    //head -> node1 -> node2 -> node3 -> tail
    //get node1
    //head -> node2 -> node3 ->  node1 -> tail
    private int capacity;
    private Map<Integer,Node>  cacheMap;
    private Node  head;
    private Node  tail;

    // 删除最久未使用的元素
    public LRUCache(int capacity) {
      this.capacity =capacity;
      this.cacheMap =new HashMap<Integer, Node>(16,0.75f);
      this.head= new Node(-1,-1);
      this.tail= new Node(-1,-1);
      head.next=tail;
      tail.pre=head;
    }

    public int get(int key) {
        if(!cacheMap.containsKey(key)){
            return -1;
        }
         //存在 将该节点move到最后
         Node cur = cacheMap.get(key);
         cur.pre.next = cur.next;
         cur.next.pre = cur.pre;
         moveToTail(cur);
         return  cur.val;
    }

    public void  moveToTail(Node  node){
        node.pre = tail.pre;
        tail.pre = node;
        node.pre.next = node;
        node.next=tail;
    }

    public void put(int key, int value) {
           if(this.get(key)!=-1){
               //直接更新
               Node  cur = cacheMap.get(key);
               cur.val = value;
               return;
           }
           //不存在,插入到最后
           Node cur = new Node(key,value);
           cacheMap.put(key,cur);
           moveToTail(cur);

           if(cacheMap.size()>capacity){
               // remove 移除最久未使用元素，也就是第一个节点
               cacheMap.remove(head.next.key);
               head.next = head.next.next;
               head.next.pre = head;
           }
    }

    //  node
    public  class  Node{
        private int key;
        private int val;
        private  Node pre;
        private  Node next;

       public Node(int key, int val) {
           this.key = key;
           this.val = val;
           this.pre = null;
           this.next = null;
       }
   }











}
