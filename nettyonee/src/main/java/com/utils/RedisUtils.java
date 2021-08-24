package com.utils;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/7/5
 * @Link
 */
public class RedisUtils {

    public static void main(String[] args) {
        String  urlList ="http://222.174.84.58:10000/FileService/ImageFile/2021-04/2021041309561672205.jpg";
        String path =urlList.substring(urlList.lastIndexOf("/"),urlList.length());
        System.out.println(path);
    }



}
