package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/9/2
 * @Link
 */
/*34. 在排序数组中查找元素的第一个和最后一个位置
给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
如果数组中不存在目标值 target，返回 [-1, -1]。
进阶：
你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？*/
public class findFirstLastIndex {

    public static void main(String[] args) {
        int [] nums = new int[]{1,2};
        int  target = 2;
        System.out.println(search(nums,target));
    }


    public static int[] searchRange(int[] nums, int target) {

     int left= 0;
     int right= nums.length-1;
     int min=Integer.MAX_VALUE;
     int max=Integer.MIN_VALUE;
     if(left==right)return target==nums[0]?new int[]{0,0}:new int[]{-1,-1};
     while(left<right&&(min==Integer.MAX_VALUE||max==Integer.MIN_VALUE)){
          if(nums[left]==target){min=Math.min(min,left);}
          if(nums[right]==target){max=Math.max(max,right);}
          if(min==Integer.MAX_VALUE)left++;
          if(max==Integer.MIN_VALUE) right--;

          if(left==right){
             if(nums[left]==target){min=Math.min(min,left);}
             if(nums[right]==target){max=Math.max(max,right);}
             break;
          }


     }

     min = min==Integer.MAX_VALUE ?-1:min;
     max = max==Integer.MIN_VALUE?min:max;

     return  new  int[]{min,max};
    }

    public static int search(int[] nums, int target) {
     int left =0;
     int right= nums.length-1;
     int truemid = (right-left)/2;
        if(nums.length==1)return nums[0]==target?0:-1;
     int res=-1;
     while (left<right){
        if(nums[truemid]>nums[truemid+1]){
       break;
        };
         if(nums[left]<nums[truemid]){
            left=truemid+1;
         }else if(nums[right]>nums[truemid]){
            right=truemid-1;
         }else {
            left++;
            right--;
         }
        if(left==right) {left++;right--;}
        truemid =  left+(right-left)/2;
     }
        //此时找到分隔点
        if(nums[0]<=target&&target<=nums[truemid]){
            res=binarySearch(nums,0,truemid,target);
        }else {
            res=binarySearch(nums,truemid, nums.length-1, target);
        }
     return  res;
    }
    public static int binarySearch(int[] nums ,int left,int right,int target){
        int mid=left+(right-left)/2;
        int res = -1;
        while (left<right){
            if(nums[mid]==target){
                res  = mid;
                break;
            }else  if (target>nums[mid]){
                left = mid+1;
            }else  if(target<nums[mid]){
                right = mid-1;
            }else {
                left++;
                right--;
            }
            if(left == right){
                if(nums[left]==target) {res =left;break;}
                left++;
                right--;
            }
             mid=left+(right-left)/2;

        }
        return  res;
    }



}
