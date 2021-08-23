package com.nicht.promote.fileservice.SimapleChat.Client;

import cn.hutool.core.util.HexUtil;
import com.nicht.promote.fileservice.zkNetty.Utils.ByteUtils;
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
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteUtils.send2client(ctx.channel(), "2a4c4f4c4138363339393531353133343330313600541129004e8ced4214f1ff410000000000000000150406082b0d00000400a7ff04344701b40e5f01130000ff0023");
    }

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
