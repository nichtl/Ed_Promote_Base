package com.learn_netty_one.TimeServerHandle;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @Author admin
 * @description
 * @ 2020/12/12
 */
public class TimeClientHandle extends ChannelInboundHandlerAdapter {
    private ByteBuf buf;
    @Override
    public void handlerAdded(ChannelHandlerContext var1) throws Exception {
         buf = var1.alloc().buffer(4); //

    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        buf.release(); // (1)
        buf = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
        ByteBuf b = (ByteBuf) msg;
        try {
            long currentTimeMillis = (b.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        } finally {
            b.release();
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }





}
