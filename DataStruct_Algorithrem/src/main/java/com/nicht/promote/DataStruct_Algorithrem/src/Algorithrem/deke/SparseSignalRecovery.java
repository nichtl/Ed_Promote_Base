package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.deke;
import org.apache.commons.math3.linear.*;

import java.util.Random;

public class SparseSignalRecovery {
    public static void main(String[] args) {

    }


    public  void one(){
        int n = 50; // 信号维度
        int m = 30; // 矩阵行数，应该远小于 n
        int numNonzero = 5; // 非零元素个数

        // 生成 50 维的二值信号 x
        double[] x = new double[n];
        Random random = new Random();
        int[] nonzeroIndices = new int[numNonzero];
        double[] nonzeroValues = new double[numNonzero];
        for (int i = 0; i < numNonzero; i++) {
            int index = random.nextInt(n);
            while (x[index] != 0) {
                index = random.nextInt(n);
            }
            nonzeroIndices[i] = index;
            nonzeroValues[i] = random.nextBoolean() ? 1 : -1;
            x[index] = nonzeroValues[i];
        }

        // 创建随机高斯矩阵 A
        RealMatrix A = new Array2DRowRealMatrix(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                A.setEntry(i, j, random.nextGaussian());
            }
        }

        // 计算 b = Ax
        RealVector xVector = new ArrayRealVector(x);
        RealVector b = A.operate(xVector);

        // 打印结果
        System.out.println("x = " + xVector);
        System.out.println("A = " + A);
        System.out.println("b = " + b);
    }




}
