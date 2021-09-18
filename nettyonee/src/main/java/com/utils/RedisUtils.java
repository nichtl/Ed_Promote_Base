package com.utils;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/7/5
 * @Link
 */
public class RedisUtils {

    public static void main(String[] args) {
        String  urlList ="";
        String path =urlList.substring(urlList.lastIndexOf("/"),urlList.length());
        System.out.println(path);
    }



}
