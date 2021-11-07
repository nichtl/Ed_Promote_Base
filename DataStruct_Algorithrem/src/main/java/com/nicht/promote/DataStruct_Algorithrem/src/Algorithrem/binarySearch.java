package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/6/23
 * @Link
 */
public class binarySearch {
    public static void main(String[] args) {

        int[][] matrix  = new int[][]{
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}};
        int[][] nums = new int[][]{{-5}};
        System.out.println(searchMatrix(nums,5));



        Date now = new Date();
        System.out.println("当前日期：" + DATE_FORMAT.format(now));
        Date newDate = stepMonth(now, -9);
        System.out.println("当前时间前13个月的日期：" + DATE_FORMAT.format(newDate));
        String  s  = "2012-10-01";
        System.out.println();
        //int[] nums = new int[]{-5};

        //System.out.println(search(nums,5));
    }

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 在给定的日期加上或减去指定月份后的日期
     *
     * @param sourceDate 原始时间
     * @param month      要调整的月份，向前为负数，向后为正数
     * @return
     */
    public static Date stepMonth(Date sourceDate, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.add(Calendar.MONTH, month);

        return c.getTime();
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


    public List<Integer> majorityElement(int[] nums) {
        List<Integer>  res  = new ArrayList<>();
        HashMap<Integer,Integer> map  = new HashMap();
        for (int i = 0,len=nums.length; i <len ; i++) {
            if(map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
                continue;
            }
            map.put(nums[i],1);
        }
        int len = nums.length/3;
        map.forEach((k,v)->{
            if(v>len){
                res.add(k);
            }
        });
        return  res;


    }

    public static boolean searchMatrix(int[][] matrix, int target) {
     int row = matrix.length;
     int col = matrix[0].length;
     int x=row-1,y=0;

     while(x>=0&&y<col){
         if(matrix[x][0]>target||matrix[x][y]>target){
             --x;
             continue;
         }
         if(matrix[x][y]==target){
             return true;
         }
         ++y;
     }
     return  false;



    }


    public void setZeroes(int[][] matrix) {
      int row = matrix.length; int col = matrix[0].length;



    }
}
