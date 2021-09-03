package com.nicht.promote.DataStruct_Algorithrem.src.DataStruct;
/**
 * @author Ni
 * @version 1.0
 * @date 2019/11/22 17:26
 */
// 查找算法
public class Select_Algorithm {
    public  static  int maxsize = 20;
    public static void main(String[] args) {
        int arr[] ={1,9,11,-1,34,89};
        int a[] = {1,8,10,89,1000,1034,1210,1280,15216,1025,1211,1563,542,326,7562};
        int s = binarySearch(a,0,a.length,1025);  // index
        System.out.println(s);
    }
    /**
     *  @param a    目标数组
     * @param value      要查找的对象
     * @return
     */
     public static int SeqSearch(int[] a, int value) {

         //线性查找是逐一比对，发现有相同的值返回下标
         for (int i = 0; i < a.length; i++) {
             if (a[i] == value) {
                 return i;
             }
         }
         return '0';
     }

    /**
     *
     * @param a
     * @param left
     * @param right
     * @param findval
     * @return
     */
      //二分查找
     public static int binarySearch(int[] a,int left,int right,int findval){
      int mid = (left+right)/2;
      int midvale = a[mid];
      if(left >right){
          return -1;
      }
       if(findval < midvale){  // 如果要找的数小于 中间值 说明 要找的数在左侧 ，否则在右侧
          return  binarySearch(a,left,mid-1,findval);   //左侧递归                重复折半的过程就能最后确定要找的数
       }else if(findval >midvale) {
         return   binarySearch(a,mid+1,right,findval);   //右侧递归
       }else {
           return mid;
       }
     }
     // 插值查找
    //要实现插值查找， 只要把这里的1/2替换成我们所预测的关键字的位置占数组总长度的比例就可以了。
    // mid = left +(right-left)*(findval-a[left]/a[right] - a[left]);   通过这样的求 left-关键字/整个区域  求出 关键字的占比  ，这样就能快速定位到关键字的位置
    // 但是这只适用于数组元素数值分布均匀的情况 【1，2，3，4，5，6，7，8，9】；这样求出的占比是比较准确的  但是 如果元素分布波动比较大 插值可能比二分更慢   【8，89，513，7894，2145622，4】，这样求出的区域根本无法用来计算关键字的在整个数值区域中的占比
     public static int search (int [] a, int key) {
         int low = 0;
         int high = a.length-1;
         int mid;
         while(a[low]!=a[high]&&key>=a[low]&&key<=a[high]){
             mid = low + (high - low)*(key - a[low])/(a[high] - a[low]); //mid
             /*
             * mid
             * (key - a[low])/(a[high] - a[low])   查找key 减去最小值  / 最大-最小值 占比
             * */
             if(key<a[mid]) {
                 high = mid - 1;
             }
             else if(key>a[mid]) {
                 low = mid + 1;
             }
             else {
                 return mid;
             }
         }
         if(key == a[low]) return low; // 如果是 2,2,2,2,2这种全部重复元素，返回第一个2
         else return -1;
     }







          //斐波那契 查找
     public static  int[] FibonacciQueue(){
       int[] f = new int [maxsize];
        f[0] = f[1] =1;
        for (int i =2; i<maxsize;i++){
            f[i] = f[i-1]+f[i-2];
        }
            return  f;
     }

    /**
     *
     * @param a            数组
     * @param key          匹配关键字
     * @return
     */
     public  static  int FibonacciSearch(int [] a,int key){
         int low  = 0;
         int high = a.length-1;
         int k    =  0;
         int mid =0;
         // 利用菲比拿起 数列 中的 数 对比
         return  -1;
     }
}
