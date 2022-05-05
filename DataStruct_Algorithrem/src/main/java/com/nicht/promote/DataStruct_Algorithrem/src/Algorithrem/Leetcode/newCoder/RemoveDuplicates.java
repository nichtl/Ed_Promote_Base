package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

/**
 * 功能描述：
 *
 * @author nicht
 */
//给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致
//。
//
// 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个
//元素应该保存最终结果。
//
// 将最终结果插入 nums 的前 k 个位置后返回 k 。
//
// 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
//
// 判题标准:
//
// 系统会用下面的代码来测试你的题解:
//
//
//int[] nums = [...]; // 输入数组
//int[] expectedNums = [...]; // 长度正确的期望答案
//
//int k = removeDuplicates(nums); // 调用
//
//assert k == expectedNums.length;
//for (int i = 0; i < k; i++) {
//    assert nums[i] == expectedNums[i];
//}
//
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,2]
//输出：2, nums = [1,2,_]
//解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
//
//
// 示例 2：
//
//
//输入：nums = [0,0,1,1,1,2,2,3,3,4]
//输出：5, nums = [0,1,2,3,4]
//解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
// 0
// nums[i] = nums[i-1]
//
//
// 提示：
//
//
// 0 <= nums.length <= 3 * 10⁴
// -10⁴ <= nums[i] <= 10⁴
// nums 已按 升序 排列
//
// Related Topics 数组 双指针 👍 2598 👎 0
// 0,0,1,1,1,2,2,3,3,4
//   j
public class RemoveDuplicates {

    public static void main(String[] args) {
        int []  arr = new int[]{0,0};
        System.out.println(removeDuplicates(arr));
    }
    public  static  int removeDuplicates(int[] nums) {
        if(nums == null || nums.length ==0){
          return nums.length==1?1:0;
        }
         int  count =1;
         int  r=nums.length;
         int len= nums.length;
         //初始化第一个不同点；
        for (int j = 0; j <len ; j++) {
            if(nums[0] != nums[j]){
                r=j;break;
            }
        }
        for (int i = 1; i <=len ; i++) {
           if(i==len || r>=len){
               break;
           }
           nums[i]=nums[r];
           count++;
           while (++r<len&&nums[r] == nums[i]){
           }
        }
        return  count;

    }
}
