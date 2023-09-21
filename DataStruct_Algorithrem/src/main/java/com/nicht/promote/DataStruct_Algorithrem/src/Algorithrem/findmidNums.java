package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/5/12
 * @Link 两个正序数组中位数
 */
public class findmidNums {

    /**
    * 两个正序数组
    * A     | 6 7  8  9         5
    * B     1 2 3  4  5 |
    * */
   // 思路一  直接合并AB数组求
    public static void main(String[] args) {
          int[]  A = new int[]{1,3};
          int[]  B = new int[]{2};
    }


    // 思路一  直接合并AB数组求 O(m+n)
    public static   Double method1 (int[] A ,int[] B){
        int alen  =  A.length;
        int blen  =  B.length;
        int [] temp =  new int[alen+blen];
        int count=0,ai=0,bj=0;
        if(alen == 0){
            return  (blen%2)==0?(B[(blen/2)-1]+B[blen/2])/2.0 : B[blen/2] ;
        }
        if(blen == 0){
            return  (alen%2)==0?(A[(alen/2)-1]+A[alen/2])/2.0 : A[alen/2] ;
        }
        while(true){
            if(ai==alen){
                while(bj!=blen){
                    temp[count++] = B[bj++];
                }
                break;
            }

            if(bj == blen){
                while(ai!=alen){
                    temp[count++] = A[ai++];
                }
                break;
            }
            if(A[ai]<B[bj]) {
             temp[count++] =  A[ai++] ;
            }else {
                temp[count++] = B[bj++];
            }
        }
        return   (count%2) == 0 ? (temp[(count/2)-1]+temp[count/2])/2.0 : temp[count/2];
    }
    // 思路二  二分查找法 O(log(m+n))
    public static  double method2(int[] nums1, int[] nums2){
     if(nums1.length>nums2.length){   //使得 nums1 成为最短的数组
         int [] temp = nums1;
         nums1=nums2;
         nums2=temp;
     }
     int  m = nums1.length;
     int  n = nums2.length;
     int  totalleft =  (m+n+1)/2;  //分割线左边元素需要满足的个数
     int  left=0, right=m;
     // 定义两个变量来表示左边 元素的个数和右边元素的个数
     /* 在最短数组上 寻找中位分割线 使得分割线满足
      *1.这条分割线需满足一下条件
      *2.分割线左侧的,最小数中的最大数小于右侧最大数中的最小数
      *3.分割线左边元素需要满足(m+n+1)/2个数的要求
      *4.在数组nums1[0,m]区间上寻找分割线
      * */
        // 2 3 4 6 8 9 10 12 18 20
        //由于是数组下标  可以直接得到 i 之前的元素个数
        /*  left = 0   right = 4    //  i=0+4-0/2 =2  i=2   j
        * [3,8|,9,10]
        * [2,4,6|,12,18,20]
        *  3 8  | 9 10  分割线  i =2
        * 2 4 6 | 12 18 20  分割线   j= 3
        * */
        //在nums1 上不断缩小找寻分隔线的位置
        while(left<right){  //条件一::一直找到左边元素个数大于等于右边元素个数
            System.out.println("left "+left +"right : "+right);
           int   i = left +(right-left)/2; //求中位数的index
            /** 取[LEFT,RIGHT]区间中间位置下标开始检测目标元素二分查找的过程是不断缩小的*/
            /**
             * 我们知道 这条分隔线左侧元素个数为 (m+n+1)/2
             * i+j= (m+n+1)/2
             * 根据 nums1的i的下标可以知道nums1左侧元素个数
             * 既可以知道 nums2 的个数为    (m+n+1)/2- i
             * totalleft -i
             * */
           int   j = totalleft -i ;
           //找 nums1 中分割线左边界最大数的index
           if(nums1[i-1]>nums2[j]){
          // 如果  nums1 此刻中位数的左边最大值大于nums右边最小值说明  在nums1中 中位线的位置在当前分割线的  左侧
          // 让右边界 舍去当前中位线位置的右区间   搜索区间变为[left , i-1];
               right  =  i-1;
           }else{
               //搜索区间变为[i,right];
               // 注意当[i,right] 区间上只有两个数的时候 left +(right-left)/2 永远会向下取整 == i了 那么就会进入死循环
               // left +(right-left)/2 改为 left +(right-left+1)/2 让偶数数组向上取整
               // 下一轮循环会永远死循环
               left  =  i ;
           }
        }
        int  i  = left;
        int  j  = totalleft - i;

        /*
        *     * A     | 6 7  8  9    避免下边越界
         *    *B    1 2 3  4  5 |
         *
         *  A     1 2 3  4  5 |    避免下边越界
         * B  | 6 7  8  9 10 11
        *
        * */
        int  nums1LeftMax = i==0?Integer.MIN_VALUE  : nums1[i-1];
        int  nums1RightMin = i==m?Integer.MAX_VALUE  : nums1[i];
        int  nums2LeftMax = j==0?Integer.MIN_VALUE : nums2[j-1];
        int  nums2RightMin= j==n? Integer.MAX_VALUE : nums2[j];
        if(((m+n)%2)==0){
            return Math.max(nums1LeftMax,nums2LeftMax);
        }else{
            return (Math.max(nums1LeftMax,nums2LeftMax)+Math.max(nums1RightMin,nums2RightMin))/2.0;
        }

    }

    public static  double method3(int[] A, int[] B){
        if(A.length < B.length){
            return   method3(B,A);
        }
        int  alen  = A.length;
        int  blen  = B.length;
        int  totalleft =  (alen+blen+1)/2;
        int  aleft=0,aright=alen;
        while (aleft<aright){
            int ai   = aleft+(aright-aleft+1)/2;
            int bj   = totalleft-aleft;
            if(A[ai -1]>B[bj]){
                aright = ai-1;
            }else{
                aleft  = ai;
            }
        }

        return  1.0;
    }

    public  static boolean isNumber(String s) {
          int n =1;

        return  s.replaceAll("\\\\s*[+-]?((\\\\d*\\\\.?\\\\d+)|(\\\\d+\\\\.?))([eE][+-]?\\\\d+)?\\\\s*","").length()==0;
    }
    //00000000000000000000000000001011
    public static int hammingWeight(int n) {
        String  s  = n+"";
        String  b = s.replaceAll("1","");
        return  s.length() -  b.length();
    }















}
