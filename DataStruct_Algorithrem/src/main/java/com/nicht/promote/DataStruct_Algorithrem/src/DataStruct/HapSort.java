package com.nicht.promote.DataStruct_Algorithrem.src.DataStruct;

/**
 * @author Ni
 * @version 1.0
 * @date 2019/11/22 17:26
 */
public class HapSort {
   //
    public static void main(String[] args) {
      int  [] arr ={4,6,8,5,9};

      try {
          boolean flag = true;
          Integer  i = null;
          int j = 1;
          Integer  k=flag  ? i : j;  // 三元表达式的自动拆箱 导致 空指针异常
          System.out.println(k);
      } catch (NullPointerException e){
          e.printStackTrace();
      }



    }
    public  static  void heapsort(int [] arr){


    }
    // 将一个数组（二叉树调整为大顶堆）

    /**
     * 功能 i 对应着每一个非叶子节点，把该叶子节点对应的节点调整为大顶堆， 把该数组中对应的数进行交换
     * @param arr 目标数组
     * @param i    表示非叶子节点在数组中的索引
     * @param length   表示多少个元素继续调整 ，length是在 逐渐减少
     */
    public static void adjustHeap(int  [] arr,int i,int length){
        int temp = arr[i];
        //非叶子节点的 左子节点
     for ( int k = i*2+1; k<length;k=k*2+1 ){




     }

    }




}
