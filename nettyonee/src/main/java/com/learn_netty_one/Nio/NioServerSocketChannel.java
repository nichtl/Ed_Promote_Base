package com.learn_netty_one.Nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author ni
 * @description
 * @Link https://ifeve.com/server-socket-channel/
 * @ 2021/3/24
 */
public class NioServerSocketChannel {
    /*
    *Java NIO中的 ServerSocketChannel 是一个可以监听新进来的TCP连接的通道,
    * 就像标准IO中的ServerSocket一样。ServerSocketChannel类在 java.nio.channels包中
    * */


    public static void main(String[] args) {

        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost",8060));
            serverSocketChannel.configureBlocking(false);
            while(true){
                SocketChannel socketChannel =
                serverSocketChannel.accept();
                if(socketChannel != null){
                    String  date  =  "new d sds  "+System.currentTimeMillis();
                    ByteBuffer  byteBuffer =  ByteBuffer.allocate(80);
                    byteBuffer.clear();
                    byteBuffer.put(date.getBytes());
                    byteBuffer.flip();
                    socketChannel.write(byteBuffer);
                    //do something with socketChannel...
                }
            }


        }catch (IOException e){e.printStackTrace();}




    }



















}
