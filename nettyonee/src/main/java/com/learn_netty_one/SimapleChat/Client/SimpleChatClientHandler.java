package com.learn_netty_one.SimapleChat.Client;

import cn.hutool.core.util.HexUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author ni
 * @description
 * @ 2021/1/5
 */
public class SimpleChatClientHandler  extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object  s) throws Exception {
        ByteBuf buf  =  (ByteBuf) s;
        Channel incoming = ctx.channel();  //当前channel 连接
        ctx.name();
        int readbleByteslen = buf.readableBytes();
        byte[] data = new  byte[readbleByteslen];
        buf.readBytes(data);
        String  msg  = HexUtil.decodeHexStr(data.toString());
        System.out.println(msg);
    }
}
