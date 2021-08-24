package com.ed.leone.leonedemo.Client;

import com.ed.leone.leonedemo.Server.NettyServerHandler;
import io.netty.channel.ChannelInitializer;

import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;



/**
 * @Author admin
 * @description
 * @ 2020/12/10
 */
public class NettyClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast("decoder",new StringDecoder(CharsetUtil.UTF_8));
        channel.pipeline().addLast("encoder",new StringEncoder(CharsetUtil.UTF_8));
        channel.pipeline().addLast(new NettyServerHandler());
    }

}
