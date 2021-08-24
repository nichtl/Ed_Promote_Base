package com.learn_netty_one.nettyonee.DiscardServerHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @Author admin
 * @description
 * @ 2020/12/11
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx ,Object msg){
        //收到的消息的类型是 ByteBuf
       ByteBuf in = (ByteBuf) msg;

        //ctx.writeAndFlush(msg);

     try{
         if(in.isReadable()){
             System.out.println((char) in.readByte());
         }
         ctx.writeAndFlush(msg);
         System.out.flush();
     }catch (Exception e){
         e.printStackTrace();
     }finally {
         ReferenceCountUtil.release(msg);
         System.out.println("释放msg");
     }

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }











}
