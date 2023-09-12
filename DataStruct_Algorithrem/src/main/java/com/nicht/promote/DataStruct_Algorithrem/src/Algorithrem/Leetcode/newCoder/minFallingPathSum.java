package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.Arrays;

/**
 * @Description
 * @Date 2023/9/12
 */
//给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
//
// 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第
//一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1
//, col + 1) 。
//
//
//
// 示例 1：
//
//
//
//
//输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
//输出：13
//解释：如图所示，为和最小的两条下降路径
//
//
// 示例 2：
//
//
//
//
//输入：matrix = [[-19,57],[-40,-5]]
//输出：-59
//解释：如图所示，为和最小的下降路径
//
//
//
//
// 提示：
//
//
// n == matrix.length == matrix[i].length
// 1 <= n <= 100
// -100 <= matrix[i][j] <= 100
//
//
// Related Topics 数组 动态规划 矩阵 👍 307 👎 0
// lt 931
public class minFallingPathSum {

        public  static  void main(String[] args) throws Exception{
         int[][]  arr = new int[][]{ {2,1,3},{6,5,4},{7,8,9}};
            int i=0, j=0;
            int min=Integer.MAX_VALUE;
            for(int t=0; t<arr[0].length; ++t){
                if(min > arr[i][t] ){
                    min = arr[i][t];
                    j = t;
                }
            }
            System.out.println(j);

        }

        public int minFallingPathSum(int[][] matrix){
            int row = matrix.length;
            int col = matrix[0].length;
            int[][] dp  = new int[row][col];
            System.arraycopy(matrix[0], 0, dp[0], 0, row);

            for (int i = 1; i < row; i++) {

                for (int j = 0; j < col; j++) {

                    int  mn = matrix[i][j];
                    if(j>0){
                        mn = Math.min(mn, matrix[i-1][j-1]);
                    }
                    if(j<col-1){
                        mn = Math.min(mn, matrix[i-1][j+1]);
                    }

                    dp[i][j] = mn+matrix[i][j];
                }
            }

            return Arrays.stream(dp[0]).min().getAsInt();

        }


}
