package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode;

/**
 * @Description
 * @Date 2022/8/23
 */

//给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
// 示例 1：
//
//输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
//输出：[[1,0,1],[0,0,0],[1,0,1]]
//
//
// 示例 2：

import java.util.Arrays;
//输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
//输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
//
//
//
//
// 提示：
//
//
// m == matrix.length
// n == matrix[0].length
// 1 <= m, n <= 200
// -2³¹ <= matrix[i][j] <= 2³¹ - 1
//
//
//
//
// 进阶：
//
//
// 一个直观的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。
// 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
// 你能想出一个仅使用常量空间的解决方案吗？
//
//
// Related Topics 数组 哈希表 矩阵 👍 763 👎 0
//  lt 73
public class SetZero {


    public void setZeroes(int[][] matrix) {
         boolean row =false;
         boolean col =false;

         int m = matrix.length; //row len
         int n = matrix[0].length;// col len
       // 首列
        for (int i = 0; i < m; i++) {
            if(matrix[i][0] == 0){
                row = true; break;
            }
        }
        //首行
        for (int j = 0; j < n; j++) {
            if(matrix[0][j] == 0){
                col = true; break;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] == 0){
                    matrix[i][0] =0;
                    matrix[0][j] =0;
                }
            }
        }

        // 列置换0
        for (int i=1;i < n; i++ ){
            if(matrix[0][i]==0){
                for (int j = 1; j <m ; j++) {
                    matrix[j][i] =0;
                }
            }
        }

        // 行置换0

        for (int i=1;i < m; i++){
            if(matrix[i][0]==0){
                Arrays.fill(matrix[i],0);
            }
        }


        if(row){
            for(int i=0;i < m; i++){
                matrix[i][0] =0;
            }
        }
        if(col){
            Arrays.fill(matrix[0],0);
        }

    }
}
