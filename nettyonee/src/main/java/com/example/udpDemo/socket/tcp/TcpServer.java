package com.example.udpDemo.socket.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wangdachuan
 * @version 1.0
 * @date 2023/3/20 22:31
 **/
@Slf4j
@Component
public class TcpServer  {
    int height;
    int width;

    public void run(){
        // 创建EventLoopGroup实例进行事件的处理
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 引导和绑定TCP服务端
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    // 指定Channel为NIO类型
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    // 指定解析数据的Handler
                    .childHandler(new TcpServerHandler());
            // 绑定端口，并调用sync方法阻塞等待直到绑定完成
            ChannelFuture channelFuture = b.bind(8082).sync();
            log.info("netty构建的TCP服务启动: [port:{}]", 8082);
            // 通道一直等待被关闭
            channelFuture.channel().closeFuture().await();
        }catch (Exception e){
            e.printStackTrace();
            log.error("netty构建TCP服务端启动异常-" + e.getMessage());
        }finally {
            group.shutdownGracefully();
        }
    }
}
