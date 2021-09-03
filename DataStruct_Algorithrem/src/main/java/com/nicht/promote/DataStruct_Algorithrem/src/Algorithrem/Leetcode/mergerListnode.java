package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/9/2
 * @Link
 */
public class mergerListnode {

 static class ListNode {
    int val;
   ListNode next;
    ListNode(int x) { val = x; }
 }
 static class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
  }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

     ListNode res = new ListNode(0);
     ListNode  head = res;
     while(l1!=null&&l2!=null){
       if(l1.val<= l2.val){
           res.next=l1;
           l1=l1.next;
       }else {
           res.next=l2;
           l2=l2.next;
       }
       res= res.next;
     }
     if(l1!=null){res.next=l1;}
        if(l2!=null){res.next=l2;}

     return  head.next;
    }
    public  int getsize(ListNode l){
     int size=0;
     while(l.next!=null){
         size++;
         l=l.next;
     }
     return  size;
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {

     return  false;
    }


}
