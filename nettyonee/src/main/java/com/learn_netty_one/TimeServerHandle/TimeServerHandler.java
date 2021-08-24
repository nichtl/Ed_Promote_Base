package com.learn_netty_one.TimeServerHandle;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

/**
 * @Author admin
 * @description
 * @ 2020/12/11
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
  // now we sink to new levels of horror.
  @Override
  public void channelActive(final ChannelHandlerContext ctx) { // (1) 通道激活时候会调用
      final ByteBuf time = ctx.alloc().buffer(4); //
      time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
      final ChannelFuture  f = ctx.writeAndFlush(time); // 推送客户端时间   writeAndFlush
       f.addListener(new ChannelFutureListener() {
           @Override
           public void operationComplete(ChannelFuture channelFuture) throws Exception {
               assert  f  == channelFuture;
               ctx.close();
           }
       });
  }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception{
      cause.printStackTrace();
      ctx.close();
    }





}
