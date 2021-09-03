package com.nicht.promote.DataStruct_Algorithrem.src.DataStruct;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ni
 * @version 1.0
 * @date 2019/11/22 17:26
 */
public class Ten_algorithm {


    public static void main(String[] args) {
        Map  map = new ConcurrentHashMap(16);
        int [] arr ={1,3,5,7,9,10};
        hanoitower(3,'a','b','c');
        long i = F(8);
        System.out.println(i);

    }

    /**
     *二分查找算法 （非递归方式）
     * @param a           数组     arr是升序
     * @param target       需要查找的数
     * @return
     */
    public  static  int binarySearch(int [] a,int target){
        int left =0;
        int right =a.length-1;
        while (left <= right){
            int mid =(left+right)/2;
            if(a[mid] == target){
                return mid;
            }
            else if(a[mid]< target) {
                left = mid+1; //变更为 右侧 一半
            }else {
                right = mid-1; //变更为 左侧一半
            }
        }
        return  -1;
       }

       public static int quick(int [] a ,int target){
        int left=0;
        int right= a.length-1;
        while(left <= right){
            int mid = (left+right)/2;
            if (a[mid] == target) {
             return  mid;
            }else  if(a[mid] <target){
                left = mid+1;
            }else {
                right = mid -1;
            }
            }
         return  -1;
        }




      // 分治算法解决  汉诺塔  问题

    /**
     * @param num    移动的盘子数
     * @param a       起始盘
     * @param b       中转盘c
     * @param c       目标盘
     */
       public  static void hanoitower (int num ,char a,char b,char c){
       if(num ==1){
           System.out.println("第"+num+"个盘子"+a +"->" +c);
       }else{
           //num >=2 此时 把整个部分 分为 最低盘 和 所有不是 最底层的盘子的盘子
           //把最底层上的盘子全部 移到 b
           hanoitower(num-1,a,c,b);      //  5  4 3 2 1,
           // num -1  把所有最底盘前的盘子移到 b 盘子 c
           System.out.println("第"+num+"盘从 "+a+"->"+c);
           //到 n-1 =1 自动把 A -> C if(num == 1)
           hanoitower(num-1,b,a,c);

       }



       }
    /**
     * 斐波那契数列的递归写法
     *      核心：一个小的解决终点，然后大的问题可以循环在小问题上解决
     * @param n
     * @return
     */
    public  static  long F(int n){
        if(n<=1) {return n;}
        return F(n-1)+F(n-2);
    }

   /* 动态规划问题      如何装入使得价值最大 且 物品只有 一 件不能重复
     大多数动态规划问题都能被归类成两种类型：
     优化问题
     组合问题
     有一个 背包 总容量 为 4 （ 组合问题）
    名称     重量   价格
    吉他     1       1500
    音响     4       3000
    电脑     3       2000
    */

    /**
     *
     * @param a  背包容量
     * 物品只对应上面给出
     */
   public  static  void  dynamic_programming( int a){
       // 使用二维数组的纵向坐标表示  储存的是价格
       int [][] arr   = new  int[4][1];
       arr[1][0] =1500;
       arr[2][0] = 800;
       arr[3][0] = 2000;
       arr[4][0] =3000;
       arr[4][1] = 3200;
       //找出 容量 = 给定容量中的 价格最大值
       int defa =0;
       while (arr[a].length-1>0){
           for(int i =0;i< arr[a].length-1;i++){
              if(arr[a][i]> defa){
                  defa = arr[a][i];
              }
           }
       }
       for(int j=0;j<a;j++){
           for(int k =0;k<arr[j].length-1;k++){

           }
       }








    }














































}
