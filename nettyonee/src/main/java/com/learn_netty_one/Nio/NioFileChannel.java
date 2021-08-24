package com.learn_netty_one.Nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @Author ni
 * @description
 * @ 2021/3/24
 */
public class NioFileChannel {

/*
*Java NIO中的FileChannel是一个连接到文件的通道。可以通过文件通道读写文件。
*FileChannel无法设置为非阻塞模式，它总是运行在阻塞模式下
* FileChannel的使用步骤如下
* 1 打开FileChannel
* 在使用FileChannel之前，必须先打开它。但是，我们无法直接打开一个FileChannel
* 需要通过使用一个InputStream、OutputStream或RandomAccessFile来获取一个
* Example
  1  RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
     FileChannel inChannel = aFile.getChannel();
* 2 从FileChannel中读取数据 到指定位置
    ByteBuffer buf = ByteBuffer.allocate(48);
    int bytesRead = inChannel.read(buf);
* 向FileChannel中写入数据
* 使用FileChannel.write()  向FileChannel 中写入数据  该方法的参数是一个Buffer 如:
* 此方法会从给的数据缓存区中写数据到FileChannel
*   String newData = "New String to write to file..." + System.currentTimeMillis();
    ByteBuffer buf = ByteBuffer.allocate(48);
    buf.clear(); //清空buf
    buf.put(newData.getBytes());
    buf.flip(); //重置position 准备读
    for(;;) {
    if(!buf.hasRemaining()){
       break
    }
    channel.write(buf);
    }
*   使用完后关闭channel     channel.close()
*
*
*
*
*
*
*
*
* */


    public static void main(String[] args) {
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "readme.txt";
        try {
            RandomAccessFile  randomAccessFile  =new RandomAccessFile(path,"rw");
            FileChannel  fileChannel  = randomAccessFile.getChannel();
            System.out.println("文件大小"+Double.toString(fileChannel.size()));
            //fileChannel.truncate(1024);  截取前1024字节数据 舍弃1024后所有数据
            // FileChannel.force()方法将通道里尚未写入磁盘的数据强制写到磁盘上。出于性能方面的考虑，操作系统会将数据缓存在内存中，所以无法保证写入到FileChannel里的数据一定会即时写到磁盘上。要保证这一点，需要调用force()方法。
            //force()方法有一个boolean类型的参数，指明是否同时将文件元数据（权限信息等）写到磁盘上。
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





















}
