package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

/**
 * @Description
 * @Date 2023/9/21
 */
public class findLonelyPixel_531 {

    /**
     * 给你一个大小为 m x n 的图像 picture ，图像由黑白像素组成，'B' 表示黑色像素，'W' 表示白色像素，请你统计并返回图像中 黑色 孤独像素的数量。
     * 黑色孤独像素 的定义为：如果黑色像素 'B' 所在的同一行和同一列不存在其他黑色像素，那么这个黑色像素就是黑色孤独像素。
     */
    public int findLonelyPixel(char[][] picture) {
           int[] rows = new int[picture.length];
           int[] cols = new int[picture[0].length];

           for (int i = 0; i < picture.length; i++) {
               for (int j = 0; j < picture[i].length; j++) {
                 if(picture[i][j] == 'B')
                   rows[i]++;
                   cols[j]++;
               }
           }

           int cnt = 0 ;
            for (int i = 0; i < picture.length; i++) {
                for (int j = 0; j < picture[i].length; j++) {
                    if(picture[i][j] == 'B'  && rows[i] ==1 &&  cols[j] ==1 ){
                         cnt++;
                    }
                }
            }

         return  cnt;

    }
}
