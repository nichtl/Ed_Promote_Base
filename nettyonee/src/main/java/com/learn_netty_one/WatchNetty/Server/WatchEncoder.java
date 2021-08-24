package com.learn_netty_one.WatchNetty.Server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author ni
 * @description  自定义协议编码
 * @ 2021/3/30
 */
public class WatchEncoder  extends MessageToByteEncoder<String> {



    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, String s, ByteBuf byteBuf) throws Exception {

    }
}
