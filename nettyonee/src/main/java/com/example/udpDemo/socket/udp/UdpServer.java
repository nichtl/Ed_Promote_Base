package com.example.udpDemo.socket.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wangdachuan
 * @version 1.0
 * @date 2023/3/14 23:25
 **/
@Slf4j
@Component
public class UdpServer {

    public void run(){
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new UdpServerHandler());
            ChannelFuture channelFuture = b.bind(30000).sync();
            log.info("netty构建的UDP服务启动: [port:{}]", 30000);
            // 等待服务器socket关闭
            channelFuture.channel().closeFuture().await();
        } catch (Exception e) {
            log.error("netty构建的UDP服务启动异常-" + e.getMessage());
        } finally {
            group.shutdownGracefully();
        }
    }

}
