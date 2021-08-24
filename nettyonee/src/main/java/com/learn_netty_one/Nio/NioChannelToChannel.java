package com.learn_netty_one.Nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @Author ni
 * @description
 * @Link http://tutorials.jenkov.com/java-nio/channel-to-channel-transfers.html
 * @ 2021/3/16
 */
public class NioChannelToChannel {
        // 前提如果两个channel中有一个是filechannel，那么两个channel就可以互相传输数据。
        // Channel 与Channel 利用 tranferTo() 和 transferFrom  进行通道间数据传输 实现file文件内容互相传输
    // 吧readme 数据 传到 readme2
    public static void main(String[] args) {
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("./readme.txt").getPath(); //C://Users//Administrator//Desktop//test//readme.txt
            String path2 = Thread.currentThread().getContextClassLoader().getResource("./readme2.txt").getPath();//C://Users//Administrator//Desktop//test//readme2.txt
            System.out.println(path);
            RandomAccessFile fromRandomAccessFile  = new RandomAccessFile(path,"rw");
            RandomAccessFile toRandomAccessFile = new RandomAccessFile(path2,"rw");
            FileChannel  fromChannel = fromRandomAccessFile.getChannel();
            FileChannel  toChannel   = toRandomAccessFile.getChannel();
            Integer position = 0;
            Long count = fromChannel.size(); // 通道数据大小
            System.out.println(count+"tochannelSize" + toChannel.size());
            toChannel.transferFrom(fromChannel,position,count); //从数据来源写入数据到当前channel 位置 position 从from哪个地方开始读数据，可写入多少数据
            //toChannel.transferTo(position,count,fromChannel); //把当前管道数据写入到另一个channel
            fromChannel.close();
            toChannel.close();
            fromRandomAccessFile.close();
            toRandomAccessFile.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }




















}
