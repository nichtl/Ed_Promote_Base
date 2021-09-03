package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/8/30
 * @Link
 */
public class test {
    public static void main(String[] args) {
        String [] nums = new String[]{"2","21","12","1"};
        ListNode  node = new ListNode(1);
        node.next=new ListNode(3);
        node.next.next=new ListNode(2);
        System.out.println(reversePrint(node));


    }
    public static int findRepeatNumber(int[] nums) {
        int  res = -1;int temp;
        for (int i = 0; i < nums.length; i++) {
            if(nums[nums[i]]==nums[i]){
                res  =  nums[i];
                break;
            }
            temp = nums[nums[i]];
            nums[nums[i]] =nums[i];
            nums[i] =temp;
        }
        return  res;
    }
     /*if(matrix.length == 0 || matrix[0].length == 0) return false;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int i = rows - 1, j = 0;
        while(i >= 0 && j < cols) {
            if(matrix[i][j] < target){
                j++;
            } else if(matrix[i][j] > target){
                i--;
            } else {
                return true;
            }
        }
        return false;*/
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length==0 || matrix[0].length==0){return  false;}
        boolean res = false;
        int row=matrix[0].length;
        int x=matrix.length-1;
        int y=0;
        while (x>=0&&y<row){
            int num  = matrix[x][y];
            if(num==target){
                  res= true; break;
            }
            if(num<target){y++;}
            if(num>target){x--;y=0;}

        }

        System.out.println(x+":"+y);
        return  res;
    }
     public static int minimumDifference(int[] nums, int k) {
        if(nums.length==1)return nums[0];
        nums=Arrays.stream(nums).sorted().toArray();
        int min = Integer.MAX_VALUE;
         for (int i = 0; i <= nums.length-k; i++) {
             min = Math.min(min,nums[i+k-1]-nums[i]);
         }
         return  min;

    }
    public static String kthLargestNumber(String[] nums, int k) {
       Arrays.sort(nums, ((o1, o2) -> {
           return  o1.length()==o2.length()?o1.length()-o2.length():o1.compareTo(o2);
       }));
     return nums[nums.length-k];
    }
    public  static  int numWays(int n) {
         int a = 0,b=1,sum=0;
        for (int i = 0; i <n ; i++) {
             sum=(a+b)%1000000007;
             a=b;
             b=sum;
        }
        return sum;
    }
   //[3,4,5,1,2]
    /* int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (numbers[pivot] < numbers[high]) {
                high = pivot;
            } else if (numbers[pivot] > numbers[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return numbers[low];*/
    public static int minArray(int[] numbers) {
        if(numbers==null||numbers.length==0){return   -1;}
        int min=Integer.MIN_VALUE;
        int left = 0;
        int right = numbers.length-1;
        while(left<right){
            int mid = left+ (right-left)/2;
            if(numbers[mid]<numbers[right]){
                right = mid;
            }
            if(numbers[mid]>numbers[right]){
                left = mid+1;//  [3,4,5,1,2]
            }else{
                right-=1;
            }
        }
        return numbers[left];
    }
    public static int movingCount(int m, int n, int k) {
        boolean  [][]   visited  = new boolean[m][n];
        int sum = 0;
       return  mvcdfs(0,0,visited,k);
    }
    public static int mvcdfs(int i ,int j,boolean[][]visited, int k){
        if(i<0||j<0||i>=visited.length||j>= visited[0].length ||k<getsum(i, j)||visited[i][j] ){
            return  0;
        }
        visited[i][j] = true;
        return  mvcdfs(i, j+1, visited, k)+mvcdfs(i+1,j,visited,k)+1;
    }
    public static int getsum(int i ,int j){
        int sum=0;
        while (i!=0){
          sum+=i%10;
          i/=10;
        }
        while (j!=0){
          sum+=j%10;
          j/=10;
        }
        return  sum;
    }

    public static int[] reversePrint(ListNode head) {
        if(head==null){
            return  new int []{};
        }
        if(head.next==null){
            return  new int []{head.val};
        }
        LinkedList<Integer>  listNodes = new LinkedList<>();
        while (head!=null){
            listNodes.add(head.val);
            head= head.next;
        }
        int [] nums = new int[listNodes.size()];
        for (int i = listNodes.size()-1; i >=0 ; i--) {
            nums[i] = listNodes.pop();
        }
        return  nums;
    }

 static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }



}
