package com.learn_netty_one.TEST.Server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Author ni
 * @description
 * @ 2021/1/18
 */
@ChannelHandler.Sharable
public class TestServerChannelHandle extends ChannelInboundHandlerAdapter {
    public  static ChannelGroup channelGroups = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE); //统一管理所有channel的实例

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception{  //
         // 通道激活时触发
        Channel incoming  = ctx.channel();
        for (Channel channel: channelGroups) {
            if(channel != incoming){
                channel.writeAndFlush("通道 id =" +channel.id()+"被激活\n");
            }
        }



    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception{
     //客户端断开连接时 激活
        Channel incoming = ctx.channel();
        for (Channel channel : channelGroups) {
            if(channel != incoming){
                channel.writeAndFlush("channel id="+channel.id()+"关闭\n");
            }
        }
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        //客户端建立连接触发
        Channel  incoming = ctx.channel();
        for (Channel channel : channelGroups) {
             if(channel!= incoming){
                 channel.writeAndFlush("用户 id =" +incoming.id().asLongText() +"上线\n");
             }
        }
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
        Channel  incoming = ctx.channel();
        // 接收客户端消息
        ByteBuf bt = (ByteBuf) msg;
        bt = bt.alloc().buffer(4);// 4 字节=分割消息
        for(Channel channel : channelGroups){
            if(channel != incoming){
                channel.writeAndFlush("字节分割 bt=" +bt);
            }
        }
    }
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception{
        //新加入handle触发时给现有的channl 发送通知  在内部，将会为每个Channel分配一个EventLoop。
        Channel incomingChannel = ctx.channel();
        for (Channel channel:channelGroups) {
            if(channel != incomingChannel ){
                channel.writeAndFlush("新成员  :id=" + channel.id() +"加入\n");
            }
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception{
     Channel incoming  = ctx.channel();
     for (Channel channel : channelGroups){
         if(channel != incoming){
             channel.writeAndFlush("成员 id=" +channel.id()+"离开\n");
         }
     }

    }












}
