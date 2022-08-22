package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode;

/**
 * @Description
 * @Date 2022/7/8
 */
public class minCostToMoveChips {
    //有 n 个筹码。第 i 个筹码的位置是 position[i] 。
//
// 我们需要把所有筹码移到同一个位置。在一步中，我们可以将第 i 个筹码的位置从 position[i] 改变为:
//
//
//
//
// position[i] + 2 或 position[i] - 2 ，此时 cost = 0
// position[i] + 1 或 position[i] - 1 ，此时 cost = 1
//
//
// 返回将所有筹码移动到同一位置上所需要的 最小代价 。
//
//
//
// 示例 1：
//
//
//
//
//输入：position = [1,2,3]
//输出：1
//解释：第一步:将位置3的筹码移动到位置1，成本为0。
//第二步:将位置2的筹码移动到位置1，成本= 1。
//总成本是1。
//
//
// 示例 2：
//
//
//
//
//输入：position = [2,2,2,3,3]
//输出：2
//解释：我们可以把位置3的两个筹码移到位置2。每一步的成本为1。总成本= 2。
//
//
// 示例 3:
//
//
//输入：position = [1,1000000000]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= chips.length <= 100
// 1 <= chips[i] <= 10^9
//
// Related Topics 贪心 数组 数学 👍 160 👎 0
    // 奇数移动到奇数 偶数移动到偶数 没有花费
    // 只有 奇数移动到偶数 偶数移动到奇数 花费 1
    // 因此计算奇偶个数取最小值即可
    public int minCostToMoveChips(int[] position) {
        int odlNum  = 0 ;

        for (int i :position) {
            if((i&1)==0){
                odlNum++;
            }
        }

     return  Math.min(odlNum, position.length-odlNum);
    }
}
