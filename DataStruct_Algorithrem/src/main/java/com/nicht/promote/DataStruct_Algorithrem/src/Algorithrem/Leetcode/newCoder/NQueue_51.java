package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueue_51 {
    // n皇后
/**
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[["Q"]]
 * */


        public static void main(String[] args) {
            char[][] chessBoard = new char[][]{
                    {'.','Q','.','.'},
                    {'.','.','.','Q'},
                    {'Q','.','.','.'},
                    {'.','.','Q','.'}
            };
            System.out.println( String.valueOf(chessBoard[0]) );
            System.out.println(  solveNQueens(4) );

        }



        public static List<List<String>> solveNQueens(int n) {
            List<List<String>>  result = new ArrayList<>();
            char[][] chessBoard = new char[n][n];
            for (int i = 0; i < n ; i++) {
                Arrays.fill(chessBoard[i],'.');
            }

            dfs(n,0,chessBoard,result);
            return  result;
        }

        public static   void dfs(int n , int row, char[][] chessBoard ,List<List<String>> result){
            if(row == n ){
                List<String> path = new ArrayList<>();
                for (int i = 0; i < row ; i++) {
                    path.add(String.valueOf(chessBoard[i]));
                }
                result.add(path);
                return;
            }
            for (int col = 0; col < n; col++) {
                if(isValid(row,col,chessBoard,n)){
                    chessBoard[row][col] = 'Q';
                    dfs(n,row+1,chessBoard,result);
                    chessBoard[row][col] = '.';
                }
            }
        }

        public  static boolean isValid(int row , int col, char[][] chessBoard,int n ){
            for (int i = 0; i < row; i++) {
               if(chessBoard[i][col] == 'Q'){
                   return  false;
               }
            }

            for (int i = row-1,j=col-1; i>=0&&j>=0 ; i--,j--) {
                if(chessBoard[i][j] == 'Q'){
                    return  false;
                }
            }

            for (int i = row-1,j=col+1; i>=0&&j<n ; i--,j++) {
                if(chessBoard[i][j] == 'Q'){
                    return  false;
                }
            }


           return true;
        }


























}
