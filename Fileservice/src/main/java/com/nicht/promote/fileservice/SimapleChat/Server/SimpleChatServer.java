package com.nicht.promote.fileservice.SimapleChat.Server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author Nicht
 * @description
 * @ 2021/1/5
 */
public class SimpleChatServer {
    private int port ;
    public  SimpleChatServer(int port){
        this.port = port;
    }
    public  void run() throws  Exception{
        EventLoopGroup  bossGroup =  new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)//通道类型
                    .childHandler(new SimpleChatServerInitializer())// 配置handle处理器
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //服务端配置bytebuf接收Buffer的类型    EventExecutor
                    .childOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(true))
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(65535));
            System.out.println("SimpleChatServer 服务端 启动了");

            // 绑定端口，开始接收进来的连接 buff
            ChannelFuture f = b.bind(port); // (7)

            // 等待服务器  socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            f.channel().closeFuture().sync();

        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            System.out.println("SimpleChatServer 关闭了");
        }
    }


}
