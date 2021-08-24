package com.learn_netty_one.Udp.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/6/15
 * @Link
 */

public class udpServer {
    private  int  port;

    public udpServer(int port) {
        this.port = port;
    }
    public void run() throws  Exception{
        NioEventLoopGroup  bossGroup  = new NioEventLoopGroup();
        Bootstrap b  =  new Bootstrap();
          b.group(bossGroup)
                  .channel(NioDatagramChannel.class)
                  .option(ChannelOption.SO_BROADCAST,true)
                  .handler( new udpHandle());



    }
}
