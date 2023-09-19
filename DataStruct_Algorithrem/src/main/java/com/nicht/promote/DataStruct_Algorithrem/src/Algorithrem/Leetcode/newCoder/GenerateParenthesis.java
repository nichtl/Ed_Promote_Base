package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述：
 *
 * @author xujian8
 */
//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：["()"]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 8
//
// Related Topics 字符串 动态规划 回溯 👍 2621 👎 0

public class GenerateParenthesis {

    public  List<String>  generateParenthesis2(int n){
        // 括号生成
        HashMap  map = new HashMap();
          map.put(null,"");

        ConcurrentHashMap<String,Object> hashMap = new ConcurrentHashMap<String,Object>();
          hashMap.keys();

          hashMap.put(null,"");




     return  null;
    }

    // 左括号一定要比右括号先使用完
    public List<String> generateParenthesis(int n) {


        List<String> result = new ArrayList<String>();
        dfs(result,"",n,0,0);
        return  result;
    }

     void dfs( List<String> parenthesis, String sb,  int n, int l,int r){
        //左括号数一定大于等于右括号
        if(l>n || r>n || r>l){
           return;
        }

        if(l==n && r==n){
            parenthesis.add(sb);
            return;
        }
        dfs(parenthesis, sb+"(", n, l+1, r);
        dfs(parenthesis, sb+")", n, l, r+1);
     }



}
