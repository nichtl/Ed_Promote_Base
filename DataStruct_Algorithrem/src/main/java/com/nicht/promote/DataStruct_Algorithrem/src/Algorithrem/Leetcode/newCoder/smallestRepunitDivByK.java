package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.HashMap;

/**
 * @Description
 * @Date 2023/5/10
 *
 * 给定正整数 k ，你需要找出可以被 k 整除的、仅包含数字 1 的最 小 正整数 n 的长度。
 *
 *  返回 n 的长度。如果不存在这样的 n ，就返回-1。
 *
 *  注意： n 不符合 64 位带符号整数。
 *
 *  示例 1：
 *
 *
 * 输入：k = 1
 * 输出：1
 * 解释：最小的答案是 n = 1，其长度为 1。
 *
 *  示例 2：
 *
 *
 * 输入：k = 2
 * 输出：-1
 * 解释：不存在可被 2 整除的正整数 n 。
 *
 *  示例 3：
 *
 *
 * 输入：k = 3
 * 输出：3
 * 解释：最小的答案是 n = 111，其长度为 3。
 *
 *
 *
 *  提示：
 *
 *
 *  1 <= k <= 10⁵
 *
 *
 *  Related Topics 哈希表 数学 👍 124 👎 0
 *
 *  lt 1015
 */

public class smallestRepunitDivByK {





    public int smallestRepunitDivByK(int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int result = 1 % k , len=1;
        map.put(result,result);
        while(result!=0){
            result =( (result *10)+1 ) %k;
            len++;
            if(map.containsKey(result)) {return  -1;}
            map.put(result,result);
        }
        return  len;
    }
}
