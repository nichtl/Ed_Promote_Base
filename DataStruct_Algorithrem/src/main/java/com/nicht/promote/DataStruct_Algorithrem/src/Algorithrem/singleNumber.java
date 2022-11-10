package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem;

/**
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 *
 *  你必须设计并实现线性时间复杂度的算法且不使用额外空间来解决此问题。
 *  示例 1：
 *
 *
 * 输入：nums = [2,2,3,2]
 * 输出：3
 *
 *
 *  示例 2：
 *
 *
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 *
 *
 *
 *
 *  提示：
 *
 *
 *  1 <= nums.length <= 3 * 10⁴
 *  -2³¹ <= nums[i] <= 2³¹ - 1
 *  nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 *
 *
 *  Related Topics 位运算 数组 👍 940 👎 0
 *
 *
 * lt 137
 * @Description
 * @Date 2022/10/31
 */

public class singleNumber {




    public int singleNumber(int[] nums) {
     int[] number  = new int[32];
     for(int num : nums) {
         for (int i = 0; i < 32; i++) {
                number[i] += num&1;
                num>>>=1;
         }
     }
     int res = 0 , m =3;
        for (int i = number.length -1; i <  0 ; i--) {
            res <<=1;
            res |= number[i]%m;
        }
        return res;
    }
}
