package com.nicht.promote.fileservice.SimapleChat.Server;

import com.nicht.promote.fileservice.SimapleChat.Mycodec.MyFileDecoder;
import com.nicht.promote.fileservice.SimapleChat.Mycodec.MyStringDecoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.nio.charset.Charset;

/**
 * @Author Nicht
 * @description
 * @ 2021/1/5
 */
public class SimpleChatServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyStringDecoder());//执行链顺序很重要
        pipeline.addLast(new MyFileDecoder());
        pipeline.addLast(new StringEncoder(Charset.forName("UTF-8")));
    }}