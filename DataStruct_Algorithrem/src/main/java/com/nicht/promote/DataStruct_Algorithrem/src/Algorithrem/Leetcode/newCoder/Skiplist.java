package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

/**
 * @Description
 * @Date 2023/2/28
 */

class Skiplist {


    public Skiplist() {

    }

    public boolean search(int target) {
       return  false;
    }

    public void add(int num) {

    }

    public boolean erase(int num) {
     return  false;
    }


    private static class Node {
        final int value;
        final Node[] next;

        Node(int value, int level) {
            this.value = value;
            next = new Node[level];
        }
    }


}
