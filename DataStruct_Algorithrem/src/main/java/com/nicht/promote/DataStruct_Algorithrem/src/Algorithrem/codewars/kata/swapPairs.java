package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.codewars.kata;

/**
 * 功能描述：
 *
 * @author nicht
 */
//给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
//
//
// 示例 2：
//
//
//输入：head = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：head = [1]
//输出：[1]
//
//
//
//
// 提示：
//
//
// 链表中节点的数目在范围 [0, 100] 内
// 0 <= Node.val <= 100
//
// Related Topics 递归 链表 👍 1381 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.sumtwo;

import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class swapPairs {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next ==null) return head;

        ListNode tmpHead = new ListNode();
        tmpHead.next = head;
        backTrack(tmpHead);
        return  tmpHead.next;
    }
    public  void backTrack(ListNode node){
        if(node.next ==null ||node.next.next == null) {
            return ;
        }
        ListNode pre = node;
        ListNode node1 = node.next;
        ListNode node2 = node.next.next;

        backTrack(swap(node1,node2,pre));
    }


    public ListNode swap(ListNode node1 , ListNode node2, ListNode pre){
        pre.next = node2;
        node1.next = node2.next;
        node2.next = node1;
        //此时的node1就是交换后的第二个节点
        return  node1;
    }


public class ListNode {
int val;
ListNode next;
ListNode() {}
ListNode(int val) { this.val = val; }
ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
}
