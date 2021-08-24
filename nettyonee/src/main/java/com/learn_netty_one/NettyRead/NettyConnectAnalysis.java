package com.learn_netty_one.NettyRead;

/**
 * @Author ni
 * @description
 * @ 2021/3/23
 */
public class NettyConnectAnalysis {
    // 分析服务端客户端的启动时的connect  流程

    /*
    *  ChannelFuture f = b.bind(port).sync(); // (7)
    *  在serverBootStrap 调用的bind方法 实际调用的是AbstractBootstrap中的bind方法
    *  public ChannelFuture bind(int inetPort) {
        return this.bind(new InetSocketAddress(inetPort));}
    *  继续调用  bind
    *   public ChannelFuture bind(SocketAddress localAddress) {
        this.validate();
        return this.doBind((SocketAddress)ObjectUtil.checkNotNull(localAddress, "localAddress"));}
    * 继续调用 dobind
    * 这里开始channel的生成与初始化  为channel注册监听器 并   交由 AbstractBootstrap  进行统一的管理维护
    * private ChannelFuture doBind(final SocketAddress localAddress) {
        final ChannelFuture regFuture = this.initAndRegister();
        * initAndRegister  初始化channel 并注册到 AbstractBootstrap  的EventLoopGroup管理
        *
        *
        * final ChannelFuture initAndRegister() {
        Channel channel = null;
        try {
            channel = this.channelFactory.newChannel();//获取指定的通道类型的实例
            this.init(channel);//初始化
        } catch (Throwable var3) {
            if (channel != null) { //出现异常强制关闭
                channel.unsafe().closeForcibly();
                return (new DefaultChannelPromise(channel, GlobalEventExecutor.INSTANCE)).setFailure(var3);
            }

            return (new DefaultChannelPromise(new FailedChannel(), GlobalEventExecutor.INSTANCE)).setFailure(var3);
        }
        //ChannelFuture//获取注册过程中各种状态
        ChannelFuture regFuture = this.config().group().register(channel);
        //this.config() 实现类(ServerBootStrap BootStrap).向AbstractBootStrapConfig中BoosEventLoopGroup注册管理channel
        if (regFuture.cause() != null) {      regFuture异常不为空
            if (channel.isRegistered()) {       已经注册
                channel.close();                调用方法关闭
            } else {
                channel.unsafe().closeForcibly();   强制关闭
            }
        }
        return regFuture;
        }
        *
        final Channel channel = regFuture.channel();  //获取 channel
        if (regFuture.cause() != null) {
            return regFuture;
        } else if (regFuture.isDone()) {
            ChannelPromise promise = channel.newPromise();
            doBind0(regFuture, channel, localAddress, promise);
            return promise;
        } else {
            final AbstractBootstrap.PendingRegistrationPromise promise = new AbstractBootstrap.PendingRegistrationPromise(channel);
            regFuture.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture future) throws Exception {
                    Throwable cause = future.cause();
                    if (cause != null) {
                        promise.setFailure(cause);
                    } else {
                        promise.registered();
                        AbstractBootstrap.doBind0(regFuture, channel, localAddress, promise);
                    }

                }
            });
            return promise;
        }
    }
    * */




}
