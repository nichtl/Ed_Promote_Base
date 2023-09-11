package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.Scanner;

/**
 * @Description
 * @Date 2023/9/11
 */
// lt 63
public class uniquePathsWithObstacles {
//一个机器人位于一个
// m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
//
// 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
//
// 网格中的障碍物和空位置分别用 1 和 0 来表示。
//
//
//
// 示例 1：
//
//
//输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
//输出：2
//解释：3x3 网格的正中间有一个障碍物。
//从左上角到右下角一共有 2 条不同的路径：
//1. 向右 -> 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右 -> 向右
//
//
// 示例 2：
//
//
//输入：obstacleGrid = [[0,1],[0,0]]
//输出：1
//
//
//
//
// 提示：
//
//
// m == obstacleGrid.length
// n == obstacleGrid[i].length
// 1 <= m, n <= 100
// obstacleGrid[i][j] 为 0 或 1
//
//
// Related Topics 数组 动态规划 矩阵 👍 1141 👎 0


     public static  void Main(String[] args ) throws Exception{



     }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        return  searchPathNum(obstacleGrid,new boolean[obstacleGrid.length][obstacleGrid[0].length],0,0);
    }
     //递归超时
    public  int searchPathNum(int[][] obstacleGrid,boolean[][]  visit,int i,int j){

         if(i<0 || j<0 ||i>=obstacleGrid.length || j>=obstacleGrid[0].length || visit[i][j] || obstacleGrid[i][j] ==1){
             return  0;
         }
        if( i == (obstacleGrid.length-1)  && j== (obstacleGrid[0].length-1)  ) {
            return  1;
        }
        visit[i][j]= true;
         int total =  searchPathNum(obstacleGrid, visit, i+1,  j)+ searchPathNum(obstacleGrid, visit,     i, j+1);
        visit[i][j]= false;
         return  total;
    }


    // 动态规划

    // 到达 dp[i][j] 的路径数    取决于 到达 dp[i-1][j]  dp[i][j-1]  的路径数之和
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {

         int m = obstacleGrid.length;
         int n = obstacleGrid[0].length;


        int[][] dp = new int[m][n];


        for(int i = 0; i < m; i++){
            if( obstacleGrid[i][0] ==1){break;}
            dp[i][0] =1;
        }

        for (int j = 0; j < n; j++) {
            if(obstacleGrid[0][j] ==1){break;}
            dp[0][j] =1;
        }


        for (int i = 1; i < m; i++) {

            for (int j = 1; j < n; j++) {
                if(obstacleGrid[i][j] !=1){
                    dp[i][j] = dp[i-1][j]+ dp[i][j-1];
                }
            }

        }


        return  dp[m-1][n-1];

    }





    
    
    
    

}
