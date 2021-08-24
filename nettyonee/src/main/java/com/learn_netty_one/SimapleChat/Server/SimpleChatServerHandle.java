package com.learn_netty_one.SimapleChat.Server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Author ni
 * @description  服务端
 * @ 2021/1/5  SimpleChannelInboundHandler  ChannelInboundHandler 4.x
 */
@ChannelHandler.Sharable
public class SimpleChatServerHandle extends SimpleChannelInboundHandler<String> {   /*TextWebSocketFrame*/
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public  void handlerAdded(ChannelHandlerContext ctx){   //新加入handle 触发 时给现有的channl 发送通知
        Channel incoming = ctx.channel();
        for(Channel channel : channels){
            channel.writeAndFlush("Server - " + incoming.remoteAddress() +"加入\n");
        }
        channels.add(ctx.channel());
    }
    @Override
    public  void  handlerRemoved(ChannelHandlerContext ctx){
        Channel  incoming = ctx.channel();
        for (Channel channel :channels){
            channel.writeAndFlush("Client - " +incoming.remoteAddress()+"离开\n");
        }
        channels.remove(ctx.channel());

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String  s) throws Exception {
        Channel incoming = ctx.channel();  //当前channel 连接
        String msg = s;
        for(Channel channel : channels){ //遍历所有的channle 给每一个连接发送服务器收到的消息
            if(channel != incoming){ //如果不是不是发送消息的连接就发送消息
                channel.writeAndFlush(new TextWebSocketFrame("["+incoming.remoteAddress()+"]"+msg+"\n"));
            }else {// 给所有的channel 发送信息
                System.out.println("客户端消息"+msg);
                channel.writeAndFlush(new TextWebSocketFrame("[服务端消息]"+msg+"\n"));
            }
        }
    }

    @Override
    public  void channelActive(ChannelHandlerContext ctx){
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient  ::"+incoming.remoteAddress()+"在线");
    }

    @Override
    public  void channelInactive(ChannelHandlerContext ctx) {
         Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient" + incoming.remoteAddress()+"掉线");
    }

     @Override
     public  void exceptionCaught (ChannelHandlerContext ctx ,Throwable cause){
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }



}
