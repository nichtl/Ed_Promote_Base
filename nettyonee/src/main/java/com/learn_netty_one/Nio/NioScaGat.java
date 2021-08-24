package com.learn_netty_one.Nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author ni
 * @description Java NIO scatter/gather
 * @Link http://tutorials.jenkov.com/java-nio/scatter-gather.html
 * @ 2021/3/16
 */
public class NioScaGat {
    // Java NIO开始支持scatter(分散)/gather (聚集) 主要用于对数据的分段 以及对数据聚合
    // 这种功能往往运用于 消息的拆分和聚合 ，例如传输一个由消息头和消息体组成的消息，你可能会将消息体和消息头分散到不同的buffer中，这样你可以方便的处理消息头和消息体。
    // 如下 使用分散 聚合
    public static void main(String[] args) {
        try {
            //写 read
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "readme.txt";
            System.out.println(path);
            RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
            FileChannel fileChannel = randomAccessFile.getChannel(); //缓冲区放入什么数据使用对应buf
            ByteBuffer header = ByteBuffer.allocate(24);
            ByteBuffer body = ByteBuffer.allocate(48);
            ByteBuffer[] buffersArray = {header, body};//
            fileChannel.read(buffersArray);
            //Scattering Reads在移动下一个buffer前，必须填满当前的buffer，这也意味着它不适用于动态消息(消息大小不固定)。换句话说，如果存在消息头和消息体，消息头必须完成填充（例如 128byte），Scattering Reads才能正常工作。
            //读
            ByteBuffer writeheader = ByteBuffer.allocate(24);
            ByteBuffer writebody = ByteBuffer.allocate(48);
            ByteBuffer[] WritebuffersArray = {writeheader, writebody};//
            fileChannel.write(WritebuffersArray);
            //buffers数组是write()方法的入参，write()方法会按照buffer在数组中的顺序，将数据写入到channel，注意只有position和limit之间的数据才会被写入。因此，如果一个buffer的容量为128byte，但是仅仅包含58byte的数据，那么这58byte的数据将被写入到channel中。因此与Scattering Reads相反，Gathering Writes能较好的处理动态消息。
        }catch (Exception e){e.printStackTrace();}
    }







}
