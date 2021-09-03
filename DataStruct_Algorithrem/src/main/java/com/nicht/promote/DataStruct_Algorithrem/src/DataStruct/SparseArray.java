package com.nicht.promote.DataStruct_Algorithrem.src.DataStruct;
/**
 * @author Ni
 * @version 1.0
 * @date 2019/11/22 17:26
 */
 // 利用稀疏数组 模拟保存一个暂停的五子棋盘状态
//二维 转 稀疏
// 稀疏 转 二维

public class SparseArray {
     public static void main(String[] args) {
         //创建一个原始的二维数据11*11
         // 0 表示没有棋子，1 表示 黑子，2 表示白子
         int chessArray[][] = new int[11][11];
         chessArray[1][2] =  1;
         chessArray[2][4]  = 2;
         // 输出查看
         for ( int[] row : chessArray){
             for (int data : row){
                 System.out.printf("%d\t",data);
             }
             System.out.println();
         }
         // 二维数组 转  稀疏数组
         // 记录非 0 值的地址 和个数
         int sum = 0;
         for(int i = 0 ;i< 11;i++){
             for ( int j = 0 ; j<11; j++){
                 if(chessArray[i][j] != 0){
                     sum++;
                 }
             }
         }
         System.out.println(sum);
         // 创建对应的稀疏数组
         int spareArr[][] = new int[sum+1][3];
         // 稀疏数组第一行记录整个数组的行列 以及不同值的数量
         //剩下每一行都 记录了每一个值的行列位置 和值
         spareArr[0][0] = 11;
         spareArr[0][1] = 11;
         spareArr[0][2] = sum;
         // 遍历位置
          int count =0;
         for(int i = 0 ;i< 11;i++){
             for ( int j = 0 ; j<11; j++){
                 if(chessArray[i][j] != 0){
                     count++;
                     spareArr[count][0] = i ;
                     spareArr[count][1] = j ;
                     spareArr[count][2] =chessArray[i][j] ;
                 }
             }
         }
         //输出稀疏数组
         System.out.println();
         System.out.println("得到的稀疏数组");
         for (int i =0;i<3;i++){
             System.out.printf("%d\t%d\t%d\t\n",spareArr[i][0] ,spareArr[i][1],spareArr[i][2]) ;
         }
         // 稀疏还原二维
          int Back[][] = new int [spareArr[0][0]][spareArr[0][1]];
         for (int i =1 ; i<spareArr.length;i++){
             Back[spareArr[i][0]][spareArr[i][1]] = spareArr[i][2];
         }
          System.out.println("恢复二维数组");
          for (int[] row : Back){
              for (int data : row){
                  System.out.printf("%d\t", data);
              }
              System.out.println();
          }
     }


 }
