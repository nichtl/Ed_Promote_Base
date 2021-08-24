package com.learn_netty_one.SimapleChat.Server;

import com.learn_netty_one.SimapleChat.Mycodec.MyDefineDecoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Author ni
 * @description
 * @ 2021/1/5  excel Encryption cracking
 */
public class SimpleChatServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
      //  pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder",new MyDefineDecoder ());
        pipeline.addLast("encoder",new StringEncoder());
/*      pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(1024*64));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));*/
        pipeline.addLast("handler",new SimpleChatServerHandle());
        System.out.println("SimplerClient   "+ch.remoteAddress()  +"连接上");
    }
}
