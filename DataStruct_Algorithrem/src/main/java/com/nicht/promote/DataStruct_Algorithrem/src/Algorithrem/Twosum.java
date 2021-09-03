package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem;


import java.util.HashMap;
import java.util.Map;
// question
//给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
// 你可以按任意顺序返回答案。
// 示例 1：
//
//
//输入：nums = [2,7,11,15], target = 9
//输出：[0,1]
//解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
// 示例 2：
//
//
//输入：nums = [3,2,4], target = 6
//输出：[1,2]
//
//
// 示例 3：
//
//
//输入：nums = [3,3], target = 6
//输出：[0,1]
// 提示：
// 2 <= nums.length <= 103
// -109 <= nums[i] <= 109
// -109 <= target <= 109
// 只会存在一个有效答案
//
// Related Topics 数组 哈希表
// 👍 10933 👎 0

/**
 * @author Administrator
 */
public class Twosum {
    public static void main(String[] args) {
        System.out.println(printNumbers(3));
    }

    public static int[] printNumbers(int n) {
        int [] temp = null;
        if(n<=0){
            return  new int[1];
        }

        String maxnum = "";
        for(;;){
            if(n==1){
                break;
            }
            maxnum+="9";
            --n;
        }
        temp = new int[Integer.parseInt(maxnum)];
        for (int i = 1; i <Integer.parseInt(maxnum) ; i++) {
            temp[i-1] = i;
        }
        return  temp;
    }

    public String replaceSpace(String s) {
              return  s.replace(" ","%20");
        }
        // 利用hashmap 将数组的value作为 map的key  下标作为值
        // 遍历nums数组判断  target  - 当前nums[i]是否存在若有 说明有结果 直接返回
       // 若无直接存入map  继续遍历   结束后若没有就是没有
    public int[] twoSum(int[] nums, int target) {
             Map<Integer,Integer> hashMap  = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(hashMap.containsKey(target-nums[i])){
                return   new int[]{hashMap.get(target-nums[i]),i};
            }else
            {
                hashMap.put(nums[i],i);
            }
        }
        return   new  int[0];
    }




}
