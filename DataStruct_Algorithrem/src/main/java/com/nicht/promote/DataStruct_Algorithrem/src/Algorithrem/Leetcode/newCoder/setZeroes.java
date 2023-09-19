package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.Arrays;

/**
 * @Description
 * @Date 2023/9/18
 */
public class setZeroes {

    public void setZeroes(int[][] matrix) {
        boolean setColZero = false;
        boolean setRowZero = false;

        for (int i = 0; i < matrix[0].length; i++) {

              if(matrix[0][i] == 0){
                  setRowZero =true; break;
              }
        }

        for (int i = 0; i < matrix.length; i++) {

            if(matrix[i][0] == 0){
                setColZero = true; break;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {

                if(matrix[i][0] ==0 || matrix[0][j] ==0 ){
                    matrix[i][j] = 0;
                }

            }
        }

        if(setRowZero){
            Arrays.fill(matrix[0],0);
        }
        if(setColZero){
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }




    }










}
