package com.ed.leone.leonedemo.Client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @Author admin
 * @description
 * @ 2020/12/10
 */

@Data
@Slf4j
public class NettyClient  implements Runnable{
    static final String HOST = "192.168.5.238";
    static final int PORT = 10998;
    static final int SIZE = 256;

    private String content;
    private static final Logger logger = LogManager.getLogger(NettyClient.class);
    public NettyClient(String content) {
        this.content = content;
    }
    @Override
    public void run() {
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            int num = 0;
            boolean boo = true;
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new NettyClientChannelInitializer() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast("decoder", new StringDecoder());
                            p.addLast("encoder", new StringEncoder());
                            p.addLast(new NettyClientHandler());
                        }
                    });

            ChannelFuture future = b.connect(HOST, PORT).sync();
            while (boo) {

                num++;

                future.channel().writeAndFlush(content + "--" + System.currentTimeMillis());

                try { //??????????????????
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //??????????????????????????????????????????
                if (num == 100) {
                    boo = false;
                }
            }

            log.info(content + "-----------------------------" + num);
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }


}
