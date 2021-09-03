package com.nicht.promote.DataStruct_Algorithrem.src.DataStruct;

import java.util.Arrays;
/**
 * @author Ni
 * @version 1.0
 * @date 2019/11/22 17:26
 */
public class Sort_Algorithm {

    public static void main(String[] args) {
        int a[] = {3, 9, -1, 10, -2};
        int b[] = {2,9,3,1,8};
        int c[] = {-9,78,0,23,-567,70};
        int d[] = {8,4,5,7,1,3,6,2};
        int e[] = {53,3,542,748,14,214};
        fastSort(b,0,a.length-1);
        Arrays.toString(b);
        //String s =twosum(d,8);
       //System.out.println(s);

    }
    //冒泡排序
    public  static int[] BubbleSort(int[] a){
        int temp = 0;
        for (int j = 0; j < a.length - 1; j++) {
            for (int i = 0; i < a.length - 1 - j; i++) {
                if (a[i] > a[i + 1]) {
                    temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                }
            }
            System.out.println(Arrays.toString(a));
        }
                  return a;

    }
    //选择排序
    public  static int[] SelectSort(int[] a) {
        for (int j = 0; j < a.length - 1; j++) {
            int minindex = j;
            int min = a[j];
            for (int i = j+1; i < a.length-1 ; i++) {
                if (min < a[i]) {
                    min = a[i];
                    minindex = i;
                }
            }
            a[minindex] = a[j];
            a[j] = min;


        }
            return a;
        }
    //插入排序
    // 组内插入
    public  static  int[] insertSort(int[] a) {
        //{101，34，95，120}  （101 34)， 95 ，120
        for(int i=1 ;i<a.length; i++){   //从第二个数开始
            for(int j=i;j>0;j--){            // 遍历某一个 I 并和前面 i- 1 个值做比较   不行 i-2   -3   -4.。
                if(a[j]<a[j-1]){
                    swap(a,j,j-1);
                }
            }
        }
         System.out.println(Arrays.toString(a));
       return  a;
    }
    //希尔排序  插入排序优化 缩小移动的距离
    // 把数组进行分割为多个数组
    public static int[] ShellSort(int[] a){
       // 定义一个增量 用于跳跃
     for(int gap = a.length/2;gap> 0 ;gap/=2){ //gap 增连间隔控制
         //交换法                                                                        for(int i=gap;i<a.length;i++){
         //             for(int j =  i-gap ; j>=0 ; j-=gap){  //找寻各组的元素并对比
         //                 if(a[j] > a[j+gap]){
         //                     swap(a,j,j+gap);           //交换
         //                 }
         //             }
         //         }
         // 移位法
         // 从第gap个元素 逐个对齐所在的组进行直接插入
         for(int i=gap;i<a.length;i++){  //9876543210
          //保存下标和值                  // 5
           int j =i;              //5     6    7
           int temp=a[j];       //4       3    2    //用来保存原始的 a[J] 的值
           if(a[j] < a[j-gap]){    //        4 8765 9 3210      4 3 765 9 8 210     43 2 65 98 7 10
               while (j-gap >= 0 && temp <a[j-gap]){
                a[j] = a[j-gap];
                j-=gap   ;          //整个while最后一定留下来的是最后最合适的 放置位置
               }
               a[j] =temp;  //在经过while循环后的 J 的位置已经变成改组 中最终 适合的 的位置  把原始的a[J] 放入 最终的位置
           }


         }
     }
        System.out.println(Arrays.toString(a));
        return  a;
    }

    //快速排序
 public  static  void QuickSort(int [] a,int left,int right){
        int l  =left;  // 左索引
        int r = right; // 右索引
         int temp =0;
        int pivot = a[(left+right)/2]; // 中间值
        //while 循环的目的是遍历让比pivot小的值放到坐标，比pivot大的值放在右边
        while(l<r){
          while(a[l] <pivot){
              l+=1;            // 如果找的值小于 给定的基准值 一直找下去 直到 找到比 基准值大的 下标
          }
          while(a[r] > pivot){
              r-=1; // 一直找到比 中间值小 =的 小标
          }
            if(l>=r){
                break;   // 两侧的 l r 向 中间值 index 逼进 当 l > r 说明已经完成了寻找过程
            }
          // 此时 交换
          temp = a[l];
          a[l] = a[r];
          a[r] = temp;
          //在交换完毕后判断  在交换完毕后 可能左右侧存在等于 中间值的 情况
            // 那么在下次进行遍历时可能回一直 卡住，一直在交换 两个相同位置的值
        }
        if(l == r){  //当 l 和 r 重合了  也满足while（l<r） 会死循环，让他们错开
            l+=1;
            r-=1;
        }
        //  递归完成全部排序
        // r 为 基准数前一位
        // l 为基准数后一位
        // 此时第一躺 排序完成了 基准数 左小  右大
         if(left < r){    //对基准数左侧进行排序
             QuickSort(a, left,r );        // 所有小于中间值的 组
         }
         if (right> l){
             QuickSort(a,l,right);         // 所有大于中间值的组
         }

 }




  public static void swap(int[] a,int i ,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
  }

    // 基数排序
    // 每次遍历取 个位
    // 基数排序由数组中最大的数个数决定
  public  static  void Radix(int [] a){
      //得到数组中最大的数
      int max = a[0];
      for(int l =1;l<a.length;l++){
          if(max < a[l]){
              max = a[l];
          }
      }
      //得到最大的位数  把数字变成字符串，利用字符串length 得到 个数
      int maxlength = (max+"").length();

      //定义 一个二维数组表示 桶
      //桶的个数 固定为10，但是每个桶存放数据的长度是不定的 所以 牺牲空间  防止越界溢出
   int [][] bucket =new int[10][a.length];
   int [] bucketElementsCounts = new int [10]; //记录某一个通里存放了多少个元素
      //
      for (int p = 0,n=1;p <maxlength; p++,n*=10){
          for(int j= 0; j<a.length;j++){
              //取出每个元素对应位
              int digit = a[j] /n %10; //取 个位
              bucket[digit][bucketElementsCounts[digit]] = a[j];
              bucketElementsCounts[digit]++;
          }
          // 按桶的序号 依次还原到数组中
          int index = 0;//从  0 号桶
          for(int k = 0;k<bucketElementsCounts.length;k++){
              if (bucketElementsCounts[k] !=0){
                  for (int s=0;s < bucketElementsCounts[k];s++){
                      a[index] =bucket[k][s];
                      index++;
                  }
              }
              bucketElementsCounts[k] =0;
          }
          System.out.println("第"+(p+1)+"轮"+Arrays.toString(a));
      }
  }
    // two sum 给定一个数组  和一个期望值   从数组中找出满足期望值的 的两个数的下标
    public static String twosum(int [] a,int target){
      for(int i =0;i<a.length-1;i++){
          for ( int j=i+1;j<a.length;j++){
                if( (a[i]+a[j]) == target ){
                    System.out.println("i::"+a[i]+"j::"+a[j]);
                    return  "i="+i+"j="+j;
                }
          }
      }
      return "wws";
    }

    public static void  fastSort(int [] a ,int left, int right){
        int l =  left;
        int r = right;
        // 取基准数
        int mid  = a[(left+right)/2];
        while(left<right){
            while (a[l]<mid){
                l+=1;
            }
            while(a[r]>mid){
                r-=1;
            }
            swap(a,l,r);
            if(r==l){break;}
        }
        if(l == r){
          l++;
          r--;
        }

        if(l<right){
            fastSort(a,l,right);
        }
        if(r>left){
            fastSort(a,left,r);
        }
    }




}
