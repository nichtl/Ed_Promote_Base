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
        pipeline.addLast(new LoggingHandler(LogLevel.TRACE));
/*      pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(1024*64));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));*/
        pipeline.addLast(new SimpleChatServerHandle());
        //SocketChannelConfig  config  =  ch.config();// 配置bytebuf 类型
        //config.setAllocator(UnpooledByteBufAllocator.DEFAULT);//
        //设置io.netty.noUnsafe属性为true，则默认会使用Heap堆内存创建ByteBuf，下面我们在启动时设置-Dio.netty.noUnsafe为true进行测试
        //利用ch.config().setAllocator或Bootstrap.option(ChannelOption.ALLOCATOR, ByteBufAllocator)，结合-Dio.netty.noUnsafe，可以灵活的在如下四种ByteBuf之间进行切换：
        //· UnpooledHeapByteBuf
        //· PooledHeapByteBuf
        //· UnpooledDirectByteBuf
        //· PooledDirectByteBuf
        System.out.println("SimplerClient   "+ch.remoteAddress()  +"连接上");
    }
}
