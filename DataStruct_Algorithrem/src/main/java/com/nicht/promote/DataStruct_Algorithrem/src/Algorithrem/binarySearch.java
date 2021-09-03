package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/6/23
 * @Link
 */
public class binarySearch {
    public static void main(String[] args) {
        int[] nums = new int[]{2,5};
        System.out.println(search(nums,5));
    }
    public static  int search(int[] nums, int target) {
        int  left  =0,right=nums.length-1;
        int  result = -1;
        if(nums.length==1){
            return  nums[0] == target? 0 : result;
        }
        while (left<right){
            int mid = left+(right-left)/2;
            if(nums[mid]==target){
                result   = mid;
            }
            if(nums[mid] > target)     {
                right  =  mid-1;
            }
            else{
                left  =  mid +1;
            }
        }
        return   result;
    }
}
