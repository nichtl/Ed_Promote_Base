package com.learn_netty_one.Nio;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @Author ni
 * @description
 * @Link  http://ifeve.com/java-nio-vs-io/
 * @ 2021/3/26
 */
public class NioCompareIo {
                //NIO 和IO  之间的区别
               /*
               * 何时选着使用NIO或IO
               * IO  和  NIO 最大的区别
               * 1  IO是面向流的 NIO是面向缓冲区的
               * 流是与文件之间直接读写的形式这意味着读写顺序无法更改并且没有被缓存在任何地方
               * NIO  是将文件先读取到一个缓存区中在这里可以对文件读取得位置顺序进行操作比起IO对文件的操作更灵活
               *
               * 2 IO中的各种流是阻塞的 当一个线程调用read() 或 write()时，该线程被阻塞，直到有一些数据被读取，或数据完全写入。该线程在此期间不能再干任何事情了
               *   在多线程情况下对资源占用很大
               *   NIO的非阻塞模式，使一个线程从某通道发送请求读取数据，但是它仅能得到目前可用的数据，如果目前没有数据可用时
               *   就什么都不会获取。而不是保持线程阻塞，所以直至数据变的可以读取之前，该线程可以继续做其他的事情。 非阻塞写也是如此。一个线程请求写入一些数据到某通道，但不需要等待它完全写入，这个线程同时可以去做别的事情。 线程通常将非阻塞IO的空闲时间用于在其它通道上执行IO操作，所以一个单独的线程现在可以管理多个输入和输出通道（channel）。
               *   Selectors 是JAVA NIO实现非阻塞模式最重要的组件
               * 3 Java NIO的选择器允许一个单独的线程来监视多个输入通道，
               *   你可以注册多个通道使用一个选择器，然后使用一个单独的线程来“选择”通道：
               *   这些通道里已经有可以处理的输入，或者选择已准备写入的通道。这种选择机制，使得一个单独的线程很容易来管理多个通道。
               *
               * */
    public static void main(String[] args) {
        String path = Thread.currentThread().getContextClassLoader().getResource("./readme.txt").getPath(); //C://Users//Administrator//Desktop//test//readme.txt
        System.out.println(path);
        try{
            BufferedReader   is = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
          /*

           for(;;){
            问题    if(is.readLine()!=null){break;} // if(is.read()!=-1){break;}
                //此处判断有问题 is.read()==-1 和is.readLine()!=null 调用了一次read()或readLine()，这样会导致数据丢失一个字符或一行，
                // 而“1”这个字符不论什么编码，都是只用一个byte表示，所以会丢了一个1。
                System.out.println(is.readLine());
            }*/

            /*优化*/
            String str =null;
            for(;;){
                if((str=is.readLine())==null){break;}
                //使用中间变量保存每次读取字符
                System.out.println(str);
            }



        }catch (Exception e){

        }
    }
















}
