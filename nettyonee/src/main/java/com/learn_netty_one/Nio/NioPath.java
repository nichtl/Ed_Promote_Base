package com.learn_netty_one.Nio;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author ni
 * @description
 * @Link  http://ifeve.com/java-nio-path-2/
 * @ 2021/3/26
 */
public class NioPath {

/*

* Path
* Path接口是java NIO2的一部分。首次在java 7中引入。Path接口在java.nio.file包下，所以全称是java.nio.file.Path。
* java中的Path表示文件系统的路径。可以指向文件或文件夹。也有相对路径和绝对路径之分。
* 绝对路径表示从文件系统的根路径到文件或是文件夹的路径。相对路径表示从特定路径下访问指定文件或文件夹的路径。
* 相对路径的概念可能有点迷糊。不用担心，我将在本文的后面详细介绍相关细节
*
*
*
*
* */
public static void main(String[] args) {
    //Paths.get方法创建了Path的实例。它是一个工厂方法。
    //
    //创建绝对路径Path
    //调用传入绝对路径当做参数的Paths.get()工厂方法，就可以生成绝对路径Path。示例如下：
    Path Abusolutepath = Paths.get("c:\\Users\\Administrator\\Desktop\\xtxx.txt");
    //相对路径  固定路径    +  当前目录下文件
    Path Relativepath= Paths.get("c:\\Users\\Administrator\\Desktop","\\xtxx.txt");



}





















}
