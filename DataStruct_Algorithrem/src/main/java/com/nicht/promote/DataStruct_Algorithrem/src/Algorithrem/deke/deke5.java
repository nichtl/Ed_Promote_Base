package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.deke;

import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/8/26
 * @Link
 */

public class deke5 {
/*    输入一个由N个大小写字母组成的字符串
            按照ASCII码值从小到大进行排序
    查找字符串中第K个最小ASCII码值的字母(k>=1)
    输出该字母所在字符串中的位置索引(字符串的第一个位置索引为0)
    k如果大于字符串长度则输出最大ASCII码值的字母所在字符串的位置索引
            如果有重复字母则输出字母的最小位置索引
    输入描述
            第一行输入一个由大小写字母组成的字符串
    第二行输入k k必须大于0 k可以大于输入字符串的长度
            输出描述
    输出字符串中第k个最小ASCII码值的字母所在字符串的位置索引
            k如果大于字符串长度则输出最大ASCII码值的字母所在字符串的位置索引
    如果第k个最小ASCII码值的字母存在重复  则输出该字母的最小位置索引*/

  /*
    比赛射箭，提供了n比赛次数，每个选手有多次出场和射箭的机会。x选手的出场顺序，y对应的选手射箭的分数。要按照每个选手的最高三次比分之和，进行排序
（1）出场次数小于3次的不纳入排名
（2）同样分数的情况按照选手序号进行排序
            输入
    n:13
    x: 3 3 3 4 4 5 4 6 4 5 6 6 5
    y:45 34 56 93 76 65 84 82 63 77 69 79 85*/
public static void main(String[] args) {
    /*
    * n
    *  y
    *  输出选手排名
    * */

    String  n = "3 3 3 4 4 5 4 6 4 5 6 6 5";
    String  m = "45 34 56 93 76 65 84 82 63 77 69 79 85";
    String [] ns=n.split(" ");
    String [] ms=m.split(" ");
    IdentityHashMap identityHashMap  = new IdentityHashMap();
    for (int i = 0; i <ns.length-1; i++) {
        identityHashMap.put(ns[i],ns[i]);
    }
    List<String> list = Arrays.stream(ns).sorted().distinct().collect(Collectors.toList());

    for (String s: list) {

    }




}


}
