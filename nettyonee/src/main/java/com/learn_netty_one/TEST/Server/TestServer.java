package com.learn_netty_one.TEST.Server;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @Author ni
 * @description
 * @ 2021/1/26
 */
public class TestServer  {
    private   int port ;
    private static final long serialVersionUID = -1551318468896556534L;

    public TestServer(int port) {
        this.port = port;
    }

    public  void run() throws  RuntimeException{
        EventLoopGroup  bossGroup = new NioEventLoopGroup();

    }
}
