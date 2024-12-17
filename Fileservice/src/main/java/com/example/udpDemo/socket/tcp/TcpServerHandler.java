package com.example.udpDemo.socket.tcp;

import cn.hutool.core.util.HexUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

import java.util.*;

/**
 * @author wangdachuan
 * @version 1.0
 * @date 2023/3/19 22:34
 **/
@Slf4j
public class TcpServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /**
     * 消息接收处理类
     *
     * @param ctx ctx
     * @param msg 消息
     * @throws Exception 异常
     */
    protected void messageReceived(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        try {
            // 由于从串口服务器上传的数据，都是按照16进制的数据进行上传，所以在这里需要进行数据转换
            byte[] data = new byte[msg.readableBytes()];
            msg.readBytes(data);
            // 将byte数组通过16进制的转换为字符串
            String dataStr = HexUtil.encodeHexStr(data);
            log.info("串口服务器： IP：{}， 收到数据：{}", getClientIp(ctx), dataStr);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("接收数据异常：" + e.getMessage());
        } finally {
            // 释放资源
            ReferenceCountUtil.release(msg);
        }
    }

    /**
     * 通道激活的方法，在客户端连接时会进入该方法
     *
     * @param ctx ctx
     * @throws Exception 异常
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("串口服务器：IP:{}，上线了", getClientIp(ctx));
    }

    /**
     * 异常掉线方法，当客户端异常掉线时会调用该方法
     *
     * @param ctx   ctx
     * @param cause 导致
     * @throws Exception 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("串口服务器：IP:{}，下线了", getClientIp(ctx));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        this.messageReceived(channelHandlerContext,byteBuf);
    }

    /**
     * 获取客户端ip
     *
     * @param ctx ctx
     * @return {@link String}
     */
    private String getClientIp(ChannelHandlerContext ctx){
        InetSocketAddress inSocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = inSocket.getAddress().getHostAddress();
        return clientIp;
    }
}
