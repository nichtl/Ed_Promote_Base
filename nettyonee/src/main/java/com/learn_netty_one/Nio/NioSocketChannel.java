package com.learn_netty_one.Nio;

import com.learn_netty_one.Socket.UniteSocket.Socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author ni
 * @description
 * @Link https://ifeve.com/socket-channel/
 * @ 2021/3/24
 */
public class NioSocketChannel {
    /*
    * Java NIO中的SocketChannel是一个连接到TCP网络套接字的通道
    * 可以通过以下2种方式创建SocketChannel：
      1 打开一个SocketChannel并连接到互联网上的某台服务器。 (显示发起)
      2 一个新连接到达ServerSocketChannel时，会创建一个SocketChannel。  (ServerSocketChannel 一般是服务端主动创建 隐式)
    *
    * 使用SocketChannel 步骤
    *  1 打开 SocketChannel
    * */

    public static void main(String[] args) {
        try {
            SocketChannel socket = SocketChannel.open();
            //socket.configureBlocking(false); //开启阻塞模式
            /**可以设置 SocketChannel 为非阻塞模式（non-blocking mode）.设置之后，就可以在异步模式下调用connect(), read() 和write()了。
            connect()
            如果SocketChannel在非阻塞模式下，此时调用connect()，该方法可能在连接建立之前就返回了。为了确定连接是否建立，可以调用finishConnect()的方法。像这样：
            **/
            /* 异步模式下read write  connect 可以同时调用因此未来保证read write  能够按照正确顺序执行
            、isConnected是判断是否连接成功的，如果连接成功，则true，否则false；源码如下：
            使用finishConnect判断连接是否完成  如果已连接（状态为2），则true；否则，如果不处于已连接状态，并且不是正在连接(pending)，就会抛异常，然后其他情况旧放那会false
            */
             socket.connect(new InetSocketAddress("localhost",8082));
           // ByteBuffer  byt2  =ByteBuffer.allocate(80);
           // while (!socket.finishConnect()){
           //     int byteReadInbyt = socket.read(byt2);
           // }  //不在连接中直接断开
            ByteBuffer  byt  =ByteBuffer.allocate(80);
            //System.out.println("read tobuf");
            int byteReadInbyt = socket.read(byt);// read()方法返回的int值表示读了多少字节进Buffer里。如果返回的是-1，表示已经读到了流的末尾（连接关闭了）。
            /*
            * 2  向SockteChannel 中写入数据
            * */
            String   newData  = "New String to write to  file ..."+ System.currentTimeMillis();
            ByteBuffer  byteBuffer = ByteBuffer.allocate(50);
            byteBuffer.clear();
            byteBuffer.put(newData.getBytes());
            byteBuffer.flip();
            for(;;) {
                if(!byteBuffer.hasRemaining()){break;}
                socket.write(byteBuffer);
            }



        }catch (IOException  e)
        {e.printStackTrace();}




        }




















}
