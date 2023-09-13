package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

/**
 * @Description
 * @Date 2023/9/13
 */
public class reverseBetween {


      public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public ListNode reverseBetween(ListNode head, int left, int right) {
         ListNode resultNode  = new ListNode(-1);
            resultNode.next = head;
            ListNode pre =resultNode;
        for (int i = 0; i < left-1; i++) {
            pre =  pre.next;
        }
        ListNode curNode=pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = curNode.next;
            curNode.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return  resultNode.next;
    }

    public ListNode reverseList(ListNode head) {
        ListNode curNode = head;

        ListNode  pre=null;

        while(curNode!=null){

            ListNode tmpNode = curNode.next;
            curNode.next = pre;
            pre =curNode;
            curNode = tmpNode;
        }
        return  pre;
    }

}
