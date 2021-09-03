package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode;
//用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的
//功能。(若队列中没有元素，deleteHead 操作返回 -1 )
//
//
//
// 示例 1：
//
// 输入：
//["CQueue","appendTail","deleteHead","deleteHead"]
//[[],[3],[],[]]
//输出：[null,null,3,-1]
//
//
// 示例 2：
//
// 输入：
//["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
//[[],[],[5],[2],[],[]]
//输出：[null,-1,null,null,5,2]
//
//
// 提示：
//
//
// 1 <= values <= 10000
// 最多会对 appendTail、deleteHead 进行 10000 次调用
//
// Related Topics 栈 设计 队列
// 👍 301 👎 0

//2021/8/30/10:45
import java.util.Stack;

class CQueue {

    private  static Stack<Object> stack1 ;
    private  static Stack<Object> stack2 ;
    public CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
          stack1.push(value);
    }

    public int deleteHead() {
        if (stack2.isEmpty()&&!stack1.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()){
          return  -1;
        }
        int res  = (int)stack2.pop();
        return res;

    }
}
