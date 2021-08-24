package com.learn_netty_one.SimapleChat.Client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author ni
 * @description
 * @ 2021/1/5
 */
public class SimpleChatClient {
    public static void main(String[] args) throws  Exception {
        new SimpleChatClient("127.0.0.1", 8082).run();
    }

    private final String host;
    private final int port;

    public SimpleChatClient(String host, int port){
        this.host = host;
        this.port = port;
    }
    public  void run ()throws Exception {
        EventLoopGroup  group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new SimpleChatClientInitializer())
                    .option(ChannelOption.SO_BACKLOG,128);
            Channel channel =  b.connect(host,port).sync().channel();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                channel.writeAndFlush(in.readLine()+"\r\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }

    }



}
