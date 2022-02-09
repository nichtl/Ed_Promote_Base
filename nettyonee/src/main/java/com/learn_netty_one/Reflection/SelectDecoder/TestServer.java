package com.learn_netty_one.Reflection.SelectDecoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/9/30
 * @Link
 */
public class TestServer {
    private int port ;
    public  TestServer(int port){
        this.port = port;
    }
    public  void run() throws  Exception{
        EventLoopGroup bossGroup =  new NioEventLoopGroup(); //main  reactor
        EventLoopGroup workerGroup = new NioEventLoopGroup(); //sub  reactor
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)//通道类型
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                           ChannelPipeline  ch = socketChannel.pipeline();
                           ch.addLast(new StringDecoder());
                        }
                    })// 配置handle处理器
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            System.out.println(" 服务端 启动了");

            // 绑定端口，开始接收进来的连接 buff debuff
            ChannelFuture f = b.bind(port).sync(); // (7)

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
