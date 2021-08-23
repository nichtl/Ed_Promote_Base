package com.nicht.promote.fileservice.SimapleChat.Server;

import com.nicht.promote.fileservice.SimapleChat.Beans.WatchBeans;
import com.nicht.promote.fileservice.SimapleChat.CommandBeans.WatchConstant;
import com.nicht.promote.fileservice.zkNetty.Utils.ByteUtils;
import com.nicht.promote.fileservice.zkNetty.Utils.WatchUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Author Nicht
 * @description  服务端处理文件
 * @ 2021/1/5  SimpleChannelInboundHandler  ChannelInboundHandler 4.x
 */
@ChannelHandler.Sharable
public class SimpleFileServerHandle extends SimpleChannelInboundHandler {   /*TextWebSocketFrame*/
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public  void handlerAdded(ChannelHandlerContext ctx){   //新加入handle 触发 时给现有的channl 发送通知
        Channel incoming = ctx.channel();
        String res  =  "2a4c4f4c4153090000#";
        for(Channel channel : channels){
            if(channel ==incoming) {
               // channel.writeAndFlush("Server - " + incoming.remoteAddress() + "加入\n");
              //  channel.writeAndFlush(res);
            }
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
    public  void  channelActive(ChannelHandlerContext ctx){
        Channel incoming = ctx.channel();
        ByteUtils.send2client(incoming, WatchUtils.CreateSMSCommandStr("离开区域(南京)范围报警"));
        System.out.println("SimpleChatClient  ::"+incoming.remoteAddress()+"在线");
    }

    @Override
    public  void channelInactive(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient" + incoming.remoteAddress()+"掉线");
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("文件handler");
        WatchBeans  watchmsg = (WatchBeans) msg;
        /*06时说明是文件数据内容 */
        if(WatchConstant.UploadSecProCode.UploadFileData.equals(watchmsg.getSecpro())){
            System.out.println("文件handler");
        }
    }


    @Override
     public  void exceptionCaught (ChannelHandlerContext ctx ,Throwable cause){
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"异常");
        //当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }



}
