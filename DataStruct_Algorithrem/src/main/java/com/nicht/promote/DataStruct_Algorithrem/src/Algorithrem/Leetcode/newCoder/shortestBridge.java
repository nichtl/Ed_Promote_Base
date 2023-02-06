package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Date 2022/10/25
 */
//给你一个大小为 n x n 的二元矩阵 grid ，其中 1 表示陆地，0 表示水域。
//
// 岛 是由四面相连的 1 形成的一个最大组，即不会与非组内的任何其他 1 相连。grid 中 恰好存在两座岛 。
//
//
//
// 你可以将任意数量的 0 变为 1 ，以使两座岛连接起来，变成 一座岛 。
//
//
//
// 返回必须翻转的 0 的最小数目。
//
//
//
// 示例 1：
//
//
//输入：grid = [[0,1]
//            ,[1,0]]
//输出：1
//
//
// 示例 2：
//
//
//输入：grid = [[0,1,0],
//             [0,0,0],
//             [0,0,1]]
//输出：2
//
//
// 示例 3：
//
//
//输入：grid = [[1,1,1,1,1],
//             [1,0,0,0,1],
//             [1,0,1,0,1],
//             [1,0,0,0,1],
//             [1,1,1,1,1]]
//输出：1
//
//
//
//
// 提示：
//
//
// n == grid.length == grid[i].length
// 2 <= n <= 100
// grid[i][j] 为 0 或 1
// grid 中恰有两个岛
//
//
// Related Topics 深度优先搜索 广度优先搜索 数组 矩阵 👍 309 👎 0
// lt 934
public class shortestBridge {
    Map<Integer,Integer>  map  = new HashMap<>();
    public int shortestBridge(int[][] grid) {




        return 0 ;
    }

    void dfs(boolean[][] p, int[][] grid,int i,int j){

        if(  i>= grid.length || i<0 || j>= grid[0].length || j <0 || p[i][j] ){
            return;
        }

        p[i][j] = true;


    }
}
