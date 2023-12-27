package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

//给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
//
//
// 示例 2：
//
//
//输入：head = [1,1,1,2,3]
//输出：[2,3]
//
//
//
//
// 提示：
//
//
// 链表中节点数目在范围 [0, 300] 内
// -100 <= Node.val <= 100
// 题目数据保证链表已经按升序 排列
//
//
// Related Topics 链表 双指针 👍 1219 👎 0

// lt 82
public class deleteDuplicates {

      public class ListNode {
         int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    public ListNode deleteDuplicates(ListNode head) {


        ListNode result = new ListNode(0, head);
        ListNode pre = result;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            while (next != null && cur.val == next.val) {
                next = next.next;
            }

            if (next != cur.next) {
                //next 移动过，说明中间存在重复元素，将 pre 的 next 指针指向
                //当前的 next，这样就跳过了重复元素
                pre.next = next;
                cur = next;
            } else {
                //next 没有移动过，pre 和 cur 向后移动一位
                pre = cur;
                cur = next;
            }

        }
       return  result.next;
    }
}
