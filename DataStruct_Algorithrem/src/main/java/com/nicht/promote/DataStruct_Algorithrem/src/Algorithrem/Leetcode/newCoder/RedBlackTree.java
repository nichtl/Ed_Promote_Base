package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Date 2023/8/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RedBlackTree <K extends Comparable<K>,V>{

    // true 表示红色
    private static final boolean RED = true;
    // false 表示黑色
    private static final boolean BLACK = false;
    // 根节点
    private TreeNode <K,V> root;
    // 树的大小
    private int size;



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TreeNode <K extends Comparable<K>,V> {
        // 父节点
        private TreeNode<K,V> parent;
        // 左节点
        private TreeNode<K,V> left;
        // 右节点
        private TreeNode<K,V> right;
        // 颜色
        private boolean color;
        // 键
        private K key;
        // 值
        private V value;
    }


}
