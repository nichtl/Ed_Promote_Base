package com.nicht.promote.fileservice.SimapleChat.Client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Author ni
 * @description
 * @ 2021/1/5
 */
public class SimpleChatClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        ByteBuf delimiter = Unpooled.copiedBuffer("#".getBytes());
        pipeline.addLast(new DelimiterBasedFrameDecoder(4038,delimiter));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder",new StringEncoder());
        pipeline.addLast("handler", new SimpleChatClientHandler());
    }
}
