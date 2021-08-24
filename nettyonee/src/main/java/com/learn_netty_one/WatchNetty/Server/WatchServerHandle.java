package com.learn_netty_one.WatchNetty.Server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author ni
 * @description
 * @ 2021/3/30
 */
public class WatchServerHandle extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf   byt = (ByteBuf)o;
    }


















}
