package com.learn_netty_one.Nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @Author ni
 * @description
 * @Link   https://ifeve.com/datagram-channel/
 * @ 2021/3/25
 */
public class NioDataGram {
    /*
    *Java NIO中的DatagramChannel是一个能收发UDP包的通道。
    * 因为UDP是无连接的网络协议，所以不能像其它通道那样读取和写入。它发送和接收的是数据包。
    *下面是 DatagramChannel 的使用方式：
    * */

    public static void main(String[] args) {
        try {
            DatagramChannel datagramChannel = DatagramChannel.open();
            datagramChannel.bind(new InetSocketAddress(9090));  //绑定9090udp端口接收udp数据包
            ByteBuffer  buffer  = ByteBuffer.allocate(100);
            buffer.clear();
            datagramChannel.receive(buffer); //从datagramChannel写数据到buffer
            String   newData  = "New  Stringt write to file..." + System.currentTimeMillis();
            ByteBuffer buffer1   =  ByteBuffer.allocate(80);
            buffer.clear();
            buffer.put(newData.getBytes());
            buffer.flip();
           //udp  发送数据
           int byteSent = datagramChannel.send(buffer,new InetSocketAddress("192.168.5.238",8090)); //服务端udp监听接口
           // 这个例子发送一串字符到”192.168.5.238”服务器的UDP端口80。 因为服务端并没有监控这个端口
           // 所以什么也不会发生。也不会通知你发出的数据包是否已收到，因为UDP在数据传送方面没有任何保证。
           // 可以将DatagramChannel“连接”到网络中的特定地址的。由于UDP是无连接的，
           // 连接到特定地址并不会像TCP通道那样创建一个真正的连接。而是锁住DatagramChannel ，
           // 让其只能从特定地址收发数据。
           // 连接到UDP服务端8089端口
           datagramChannel.connect(new InetSocketAddress("192.168.5.238",9090));
           int  bytesRead = datagramChannel.read(buffer);
           int byteWritten =  datagramChannel.write(buffer1);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
