package com.nicht.promote.DataStruct_Algorithrem.src.DataStruct;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

// 图 的存储结构 邻接表实现 矩阵实现
// 使用二维数组 0 1  表示两个节点有没有关系
/**
 * @author Ni
 * @version 1.0
 * @date 2019/11/22 17:26
 */

public class Graph {
    private ArrayList<String> vertextlist; //存储顶点集合
    private int[][] edges;                //存储图对应的矩阵
    private  int numberOfEdges;          //
    public static void main(String[] args) {



    }
  // public

    /**
     * @param n   多少个顶点数
     */
  public Graph(int n){
      edges =new int [n][n];
      vertextlist= new ArrayList<String>(n);
      numberOfEdges = 0;
    }
       // 插入节点
    public  void  insertVertex(String vertex){
      vertextlist.add(vertex);
  }
        // 添加边




}
