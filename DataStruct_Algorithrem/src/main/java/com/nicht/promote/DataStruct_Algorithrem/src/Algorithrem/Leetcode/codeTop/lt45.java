package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.codeTop;

public class lt45 {
    /**
     * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
     * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
     * 0 <= j <= nums[i]
     * i + j < n
     * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
     * 示例 1:
     * 输入: nums = [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     */
    public int jump(int[] nums) {
        int end=0;
        int maxPosition=Integer.MIN_VALUE;
        int step=0;
        for (int i = 0; i < nums.length-1; i++) {
            maxPosition = Math.max(maxPosition,i+nums[i]);
            if(end == i){
                end = maxPosition;
                step++;
            }
        }
        return  step;
    }
}
