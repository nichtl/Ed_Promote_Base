package com.learn_netty_one.WatchNetty.Server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @Author ni
 * @description
 * @ 2021/3/30
 */
public class WatchServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("encoder",new WatchEncoder());
        ch.pipeline().addLast("decoder",new WatchDecoder());
    }
}
