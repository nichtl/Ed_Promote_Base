package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

/**
 * @Description
 * @Date 2022/10/12
 */
//我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
//
//
//
// 示例:
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
//        1   a= b = c = 0
          //2    min(4，6，10) = 4 a=2


// 说明:
//
//
// 1 是丑数。
// n 不超过1690。
//
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/
//
// Related Topics 哈希表 数学 动态规划 堆（优先队列） 👍 389 👎 0
// lt 263
public class nthUglyNumber {
    // 对于每个丑数来说其本身对应着后面的三个丑数（该丑数自身分别乘2 ，3 ，5） 由于这三个丑数的位置是不确定的 每次都从（乘 2，3，5中取最小 并更新）
    public int nthUglyNumber(int n) {
        int [] nums =new int[]{n};
        int a=0,b=0,c=0;
        for (int i = 1; i < n; i++) {
            int  n1 = nums[a]*2;
            int  n2 = nums[b]*3;
            int  n3 = nums[c]*5;
            nums[i] = Math.min(Math.min(n1,n2),n3);
            if(nums[i] ==n1){a+=1;}
            if(nums[i] ==n2){b+=1;}
            if(nums[i] ==n3){c+=1;}
        }
        return  nums[n-1];

    }


}
