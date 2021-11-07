package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/8/31
 * @Link
 */
public class offer_12 {
    //给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
//
//
//
// 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
//
//
//
//
//
// 示例 1：
//
//
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//ABCCED"
//输出：true
//
//
// 示例 2：
//
//
//输入：board = [["a","b"],["c","d"]], word = "abcd"
//输出：false
//
//
//
//
// 提示：
//
//
// 1 <= board.length <= 200
// 1 <= board[i].length <= 200
// board 和 word 仅由大小写英文字母组成
//
//
//
//
// 注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/
// Related Topics 数组 回溯 矩阵
// 👍 396 👎 0
    public static void main(String[] args) {
        char [] []  chars  = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word ="ABCCED";
        System.out.println(exist(chars,word));
    }

        public static boolean exist(char[][] board, String word) {
        // 使用相同大小的Boolean数组记录每个位置是否被访问过
         boolean[][]  visit = new boolean[board.length][board[0].length];
            for (int i = 0; i < board.length-1; i++) {
                for (int j = 0; j < board[0].length-1 ; j++) {
                  if(dfs(board,word.toCharArray(),visit,i,j,0)){
                      return  true;
                  }
                }
            }
            return  false;

        }

        // 以i j 点为开始点进行一轮搜索,在搜索过程中对每个访问的点进行设置访问状态

        public  static  boolean dfs(char[][] board,char[] chars,boolean[][] visit, int i,int j, int start){
            if(i<0||i>= board.length||j<0||j>= board[0].length||board[i][j]!=chars[start]||visit[i][j]){
                return  false;
            }
            if(start==chars.length-1){
                return  true;
            }
            visit[i][j] = true;
            boolean ans = dfs(board, chars, visit, i+1, j, start+1)
                         || dfs(board, chars, visit, i-1, j, start+1)
                         || dfs(board, chars, visit, i, j+1, start+1)
                         || dfs(board, chars, visit, i, j-1, start+1);
            visit[i][j] = false;
            return  ans;
        }
        




}
