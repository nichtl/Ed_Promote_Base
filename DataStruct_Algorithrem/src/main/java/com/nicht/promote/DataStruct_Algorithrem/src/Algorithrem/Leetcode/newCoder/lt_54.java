package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import com.nicht.promote.DataStruct_Algorithrem.src.DataStruct.Sort_Algorithm;

import java.util.ArrayList;
import java.util.List;

public class lt_54 {
    // 螺旋矩阵
    // [1 2 3]
    // [4 5 6]
    // [7 8 9]
    // 123 698745

    public static void main(String[] args) {

    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        while (true) {
            // left -> right
            for (int i = left; i < right + 1; i++) {
                result.add(matrix[top][i]);
            }
            top += 1;
            if (top > bottom) {
                break;
            }

            // top -> bottom
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right -= 1;
            if (right < left) break;
            ;
            // right -> left
            for (int i = right; i >= left; i--) {
                result.add(matrix[bottom][i]);
            }
            bottom -= 1;
            if (bottom < top) break;
            // bottom -> top
            for (int i = bottom; i >= top; i--) {
                result.add(matrix[i][left]);
            }
            left += 1;
            if (left > right) break;
        }
        return result;
    }

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int nums = 1, target = n * n;
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        while (nums <= target) {
            // left -> right
            for (int i = left; i <= right; i++) {
                result[top][i] = nums++;
            }
            top += 1;
            if (top > bottom) break;
            ;
            // top -> bottom
            for (int i = top; i <= bottom; i++) {
                result[i][right] = nums++;
            }
            right -= 1;
            if (left > right) break;

            // right -> left
            for (int i = right; i >= left; i--) {
                result[bottom][i] = nums++;
            }
            bottom -= 1;
            if (top > bottom) break;

            // bottom -> top
            for (int i = bottom; i >= top; i--) {
                result[i][left] = nums++;
            }
            left += 1;
            if (left > right) break;
        }
        return result;
    }

    public void swap(int[] nums, int i , int j){
        Sort_Algorithm.swap(nums, i, j);
    }
    public  void  quickSort(int[] nums, int left , int right){
         int mid = left + (right-left)/2;
         while(left<right){



         }





    }
}
