package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.*;

/**
 * 功能描述：
 *
 * @author nicht
 */
//有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
//
// 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上
//单元格 高于海平面的高度 。
//
// 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
//
// 返回 网格坐标 result 的 2D列表 ，其中 result[i] = [ri, ci] 表示雨水可以从单元格 (ri, ci) 流向 太平洋和大西洋
// 。
//
//
//
// 示例 1： column
// rows =  5 cols =5
//  对角线分割
// 那些边界点属于右上   row =0 || row < rows-1 && col=0
// 那些边界点属于左下   col = cols-1 || row=rows-1
// 1,2,2,3,5
// 3,2,3,4,4
// 2,4,5,3,1
// 6,7,1,4,5
// 5,1,1,2,4  4 0
//输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
//输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
//
//
// 示例 2：
//
//
//输入: heights = [[2,1],[1,2]]
//输出: [[0,0],[0,1],[1,0],[1,1]]
//
//
//
//
// 提示：
//
//
// m == heights.length
// n == heights[r].length
// 1 <= m, n <= 200
// 0 <= heights[r][c] <= 10⁵
//
// Related Topics 深度优先搜索 广度优先搜索 数组 矩阵 👍 379 👎 0

public class PacificAtlantic {

    public static void main(String[] args) {
        int [][]  heights = new int[][]{{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(pacificAtlantic(heights));
    }

// rows =  5 cols =5
// 以对角线分割
// 那些边界点属于右上   row =0 || row < rows-1 && col=0
// 那些边界点属于左下   col = cols-1 || row=rows-1
// 1,2,2,3,5
// 3,2,3,4,4
// 2,4,5,3,1
// 6,7,1,4,5
// 5,1,1,2,4
public static List<List<Integer>> pacificAtlantic(int[][] heights) {
    List<List<Integer>>  res  = new LinkedList<>();
    if(heights.length<=1){
        return  res ;
    }
    int m = heights.length;
    int n = heights[0].length;
    boolean[][] pacific = new boolean[m][n];
    boolean[][] atlantic = new boolean[m][n];
    for (int i = 0; i < m; i++) {
        dfsSerach(heights,pacific,i, 0,heights[i][0]);
    }
    for (int j = 1; j < n; j++) {
        dfsSerach(heights,pacific,0, j,heights[0][j]);
    }
    for (int i = 0; i < m; i++) {
        dfsSerach(heights,atlantic,i, n-1,heights[i][n-1]);
    }
    for (int j = 0; j < n - 1; j++) {
        dfsSerach(heights,atlantic,m-1, j,heights[m-1][j]);
    }

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (pacific[i][j] && atlantic[i][j]) {
                List<Integer> cell = new ArrayList<Integer>();
                cell.add(i); cell.add(j); res.add(cell);
            }
        }
    }
    return  res;
}

public static void dfsSerach(int [][] heights, boolean [][] visited  ,int i,int j,int pre)  {
        if(i>= heights.length || i<0 || j<0 || j>= heights.length  || visited[i][j] || heights[i][j]< pre ){
            return ;
        }
        visited[i][j] =true;
        dfsSerach(heights, visited, i, j++, pre);
        dfsSerach(heights, visited, i, j--, pre);
        dfsSerach(heights, visited, i++, j, pre);
        dfsSerach(heights, visited, i--, j, pre);
    }
public static Boolean dfsSerach2(int [][] heights, boolean [][] visited  ,int i,int j,int pre)  {
        if(i>= heights.length || i<0 || j<0 || j>= heights.length  || heights[i][j]> pre ){
            return false;
        }

        if(j == heights[0].length-1 || i== heights.length-1){
            return  true;
        }
        pre = heights[i][j];
        boolean ans =   dfsSerach2(heights, visited, --i, j, pre)
                || dfsSerach2(heights, visited, i, --j, pre)
                || dfsSerach2(heights, visited, ++i, j, pre)
                || dfsSerach2(heights, visited, i, ++j, pre);
        return  ans;
    }





}
