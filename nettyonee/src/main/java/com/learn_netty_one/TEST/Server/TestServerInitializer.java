package com.learn_netty_one.TEST.Server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.boot.convert.Delimiter;

import java.nio.channels.Channel;

/**
 * @Author ni
 * @description
 * @ 2021/1/26
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
      ChannelPipeline pipeline= ch.pipeline();
      pipeline.addLast(new DelimiterBasedFrameDecoder(8192,Delimiters.lineDelimiter()));
      pipeline.addLast(new HttpServerCodec());
      pipeline.addLast("decoder",new StringDecoder());
      pipeline.addLast("encoder",new StringEncoder());
      pipeline.addLast("handler",new TestServerChannelHandle());
    }
}
