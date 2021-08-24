package com.learn_netty_one.Nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @Author ni
 * @description
 * @Link  http://ifeve.com/pipe/
 * @ 2021/3/26
 */
public class NioPipe {
                /*
                * Channel 是通讯的载体 数据从这里读与写
                * 每一个客户端在新加入服务端的时候 服务端都会创建一个channel
                * 每一个Channel中又有channelPipline
                * 数据从channel中进出  channelPipline就是channel中对数据的处理工厂 这里存放一系列的处理器  如解码 编码 handle等
                 * 这存放多个channelHandle用于处理channel中的数据
                * */
    public static void main(String[] args) {
        try {
            Pipe pipe = Pipe.open();
            // 管道分为上行和下行
            //向管道中写入数据你需要  使用Pipe.sinkChannel
            Pipe.SinkChannel sinkChannel= pipe.sink();
            String newData = "New String to write to file..." + System.currentTimeMillis();
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());
            buf.flip();
            for(;;){
                if(!buf.hasRemaining()){
                    break;
                }
                sinkChannel.write(buf);  //把buf的数据写入sinkChannel
            }
            //从通道中读去数据到buf
            buf.clear();
            Pipe.SourceChannel  sourceChannel = pipe.source();
            sourceChannel.read(buf);

        }catch (IOException e){
            e.printStackTrace();
        }




    }
























}
