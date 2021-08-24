package com.ed.leone.leonedemo.Server;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.impl.Log4jLoggerFactory;
import org.springframework.stereotype.Component;


import java.net.InetSocketAddress;
import java.util.logging.Logger;

/**
 * @Author admin
 * @description
 * @ 2020/12/10
 */
@Slf4j
@Component
public class NettyServer {
public  void start (InetSocketAddress address) throws  Exception{
      //配置服务端的NIO线程组
      EventLoopGroup bossGroup = new NioEventLoopGroup(1);
      EventLoopGroup Group     = new NioEventLoopGroup();
      try{
          ServerBootstrap bootstrap = new ServerBootstrap()
                  .group(bossGroup,Group)
                  .channel(NioServerSocketChannel.class)
                  .localAddress(address)
                  .childHandler(new NettyServerChannelInitializer())
                  .option(ChannelOption.SO_BACKLOG,128)
                  .childOption(ChannelOption.SO_KEEPALIVE,true);
          // 绑定端口，开始接收进来的连接
          ChannelFuture future = bootstrap.bind(address).sync();
          log.info("netty服务器开始监听端口：" + address.getPort() +"telnet 192.168.5.238 10998");

          //关闭channel和块，直到它被关闭
          future.channel().closeFuture().sync();
      }catch (Exception e){
          e.printStackTrace();
          bossGroup.shutdownGracefully();
          Group.shutdownGracefully();
      }

}







}
