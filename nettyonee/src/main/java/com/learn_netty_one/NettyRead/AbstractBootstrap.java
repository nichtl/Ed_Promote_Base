package com.learn_netty_one.NettyRead;

/**
 * @Author ni
 * @description   ServerBootstrap  Bootstrap 基类 AbstractBootstrap read
 * @ 2021/1/20
 */
public class AbstractBootstrap {
    /*ServerBootstrap 需要创建 两个 NioEventLoopGroup 一个是给AbstractBootstrap 创建的用于管理所有的NioEventLoop   一个是 server自己管理的group
    * */
   // AbstractBootstrap 服务端和客户端的抽象基类 实现对服务端和客户端channel的统一配置和管理

    /*
    * public abstract class AbstractBootstrap<B extends AbstractBootstrap<B, C>, C extends Channel> implements Cloneable {
    * static final Entry<ChannelOption<?>, Object>[] EMPTY_OPTION_ARRAY = new Entry[0]; //空option数组  所有childOptions配置都将放入这里管理
    static final Entry<AttributeKey<?>, Object>[] EMPTY_ATTRIBUTE_ARRAY = new Entry[0];//空属性数组 currentChildAttrs   同上
    volatile EventLoopGroup group;
    private volatile ChannelFactory<? extends C> channelFactory;
    private volatile SocketAddress localAddress;
     // 存放ServerBootstrap Bootstrap 对channel的配置选项   ServerBootstrap Bootstrap 都是直接调用父类的option方法对channel进行配置
    private final Map<ChannelOption<?>, Object> options = new LinkedHashMap(); //通道配置数组 当前启动配置
    private final Map<AttributeKey<?>, Object> attrs = new ConcurrentHashMap(); // 通道属性数组 当前启动配置
    private volatile ChannelHandler handler; //通信处理

    AbstractBootstrap() {
    }


    AbstractBootstrap(AbstractBootstrap<B, C> bootstrap) {   serverBootstrap Bootstrap   super(bootstrap);
        this.group = bootstrap.group;
        this.channelFactory = bootstrap.channelFactory;
        this.handler = bootstrap.handler;
        this.localAddress = bootstrap.localAddress;
        synchronized(bootstrap.options) {
            this.options.putAll(bootstrap.options);
        }

        this.attrs.putAll(bootstrap.attrs);
    }

    public B group(EventLoopGroup group) {
        ObjectUtil.checkNotNull(group, "group");
        if (this.group != null) {
            throw new IllegalStateException("group set already");
        } else {
            this.group = group;
            return this.self();
        }
    }

    private B self() {
        return this;
    }

    public B channel(Class<? extends C> channelClass) {
        return this.channelFactory((io.netty.channel.ChannelFactory)(new ReflectiveChannelFactory((Class)ObjectUtil.checkNotNull(channelClass, "channelClass"))));
    }



    public B channelFactory(ChannelFactory<? extends C> channelFactory) {
        ObjectUtil.checkNotNull(channelFactory, "channelFactory");
        if (this.channelFactory != null) {
            throw new IllegalStateException("channelFactory set already");
        } else {
            this.channelFactory = channelFactory;
            return this.self();
        }
    }

    public B channelFactory(io.netty.channel.ChannelFactory<? extends C> channelFactory) {
        return this.channelFactory((ChannelFactory)channelFactory);
    }

    public B localAddress(SocketAddress localAddress) {
        this.localAddress = localAddress;
        return this.self();
    }

    public B localAddress(int inetPort) {
        return this.localAddress(new InetSocketAddress(inetPort));
    }

    public B localAddress(String inetHost, int inetPort) {
        return this.localAddress(SocketUtils.socketAddress(inetHost, inetPort));
    }

    public B localAddress(InetAddress inetHost, int inetPort) {
        return this.localAddress(new InetSocketAddress(inetHost, inetPort));
    }

    public <T> B option(ChannelOption<T> option, T value) {
        ObjectUtil.checkNotNull(option, "option");
        synchronized(this.options) {
            if (value == null) {
                this.options.remove(option);
            } else {
                this.options.put(option, value);
            }
        }

        return this.self();
    }

    public <T> B attr(AttributeKey<T> key, T value) {
        ObjectUtil.checkNotNull(key, "key");
        if (value == null) {
            this.attrs.remove(key);
        } else {
            this.attrs.put(key, value);
        }

        return this.self();
    }

    public B validate() {
        if (this.group == null) {
            throw new IllegalStateException("group not set");
        } else if (this.channelFactory == null) {
            throw new IllegalStateException("channel or channelFactory not set");
        } else {
            return this.self();
        }
    }

    public abstract B clone();

    public ChannelFuture register() {
        this.validate();
        return this.initAndRegister();
    }

    public ChannelFuture bind() {
        this.validate();
        SocketAddress localAddress = this.localAddress;
        if (localAddress == null) {
            throw new IllegalStateException("localAddress not set");
        } else {
            return this.doBind(localAddress);
        }
    }

    public ChannelFuture bind(int inetPort) {
        return this.bind(new InetSocketAddress(inetPort));
    }

    public ChannelFuture bind(String inetHost, int inetPort) {
        return this.bind(SocketUtils.socketAddress(inetHost, inetPort));
    }

    public ChannelFuture bind(InetAddress inetHost, int inetPort) {
        return this.bind(new InetSocketAddress(inetHost, inetPort));
    }

    public ChannelFuture bind(SocketAddress localAddress) {
        this.validate();
        return this.doBind((SocketAddress)ObjectUtil.checkNotNull(localAddress, "localAddress"));
    }

    private ChannelFuture doBind(final SocketAddress localAddress) {
        final ChannelFuture regFuture = this.initAndRegister();
        final Channel channel = regFuture.channel();
        if (regFuture.cause() != null) {
            return regFuture;
        } else if (regFuture.isDone()) {
            ChannelPromise promise = channel.newPromise();
            doBind0(regFuture, channel, localAddress, promise);
            return promise;
        } else {
            final io.netty.bootstrap.AbstractBootstrap.PendingRegistrationPromise promise = new io.netty.bootstrap.AbstractBootstrap.PendingRegistrationPromise(channel);
            regFuture.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture future) throws Exception {
                    Throwable cause = future.cause();
                    if (cause != null) {
                        promise.setFailure(cause);
                    } else {
                        promise.registered();
                        io.netty.bootstrap.AbstractBootstrap.doBind0(regFuture, channel, localAddress, promise);
                    }
                }
            });
            return promise;
        }
    }
    // initAndRegister
    final ChannelFuture initAndRegister() {
        Channel channel = null;
        try {
            channel = this.channelFactory.newChannel();
            this.init(channel);
        } catch (Throwable var3) {
            if (channel != null) {
                channel.unsafe().closeForcibly();
                return (new DefaultChannelPromise(channel, GlobalEventExecutor.INSTANCE)).setFailure(var3);
            }

            return (new DefaultChannelPromise(new FailedChannel(), GlobalEventExecutor.INSTANCE)).setFailure(var3);
        }

        ChannelFuture regFuture = this.config().group().register(channel);
        if (regFuture.cause() != null) {
            if (channel.isRegistered()) {
                channel.close();
            } else {
                channel.unsafe().closeForcibly();
            }
        }

        return regFuture;
    }

    abstract void init(Channel var1) throws Exception;

    private static void doBind0(final ChannelFuture regFuture, final Channel channel, final SocketAddress localAddress, final ChannelPromise promise) {
        channel.eventLoop().execute(new Runnable() {
            public void run() {
                if (regFuture.isSuccess()) {
                    channel.bind(localAddress, promise).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                } else {
                    promise.setFailure(regFuture.cause());
                }

            }
        });
    }

    public B handler(ChannelHandler handler) {
        this.handler = (ChannelHandler)ObjectUtil.checkNotNull(handler, "handler");
        return this.self();
    }

    public final EventLoopGroup group() {
        return this.group;
    }

    public abstract AbstractBootstrapConfig<B, C> config();

    final Map.Entry<ChannelOption<?>, Object>[] newOptionsArray() {
        synchronized(this.options) {
            return (Map.Entry[])this.options.entrySet().toArray(EMPTY_OPTION_ARRAY);
        }
    }

    final Map<ChannelOption<?>, Object> options0() {
        return this.options;
    }

    final Map<AttributeKey<?>, Object> attrs0() {
        return this.attrs;
    }

    final SocketAddress localAddress() {
        return this.localAddress;
    }

    final ChannelFactory<? extends C> channelFactory() {
        return this.channelFactory;
    }

    final ChannelHandler handler() {
        return this.handler;
    }

    final Map<ChannelOption<?>, Object> options() {
        synchronized(this.options) {
            return copiedMap(this.options);
        }
    }

    final Map<AttributeKey<?>, Object> attrs() {
        return copiedMap(this.attrs);
    }

    static <K, V> Map<K, V> copiedMap(Map<K, V> map) {
        return map.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(new HashMap(map));
    }

    static void setAttributes(Channel channel, Map.Entry<AttributeKey<?>, Object>[] attrs) {
        Map.Entry[] var2 = attrs;
        int var3 = attrs.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Map.Entry<AttributeKey<?>, Object> e = var2[var4];
            AttributeKey<Object> key = (AttributeKey)e.getKey();
            channel.attr(key).set(e.getValue());
        }

    }

    static void setChannelOptions(Channel channel, Map.Entry<ChannelOption<?>, Object>[] options, InternalLogger logger) {
        Map.Entry[] var3 = options;
        int var4 = options.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Map.Entry<ChannelOption<?>, Object> e = var3[var5];
            setChannelOption(channel, (ChannelOption)e.getKey(), e.getValue(), logger);
        }

    }

    private static void setChannelOption(Channel channel, ChannelOption<?> option, Object value, InternalLogger logger) {
        try {
            if (!channel.config().setOption(option, value)) {
                logger.warn("Unknown channel option '{}' for channel '{}'", option, channel);
            }
        } catch (Throwable var5) {
            logger.warn("Failed to set channel option '{}' with value '{}' for channel '{}'", new Object[]{option, value, channel, var5});
        }

    }

    public String toString() {
        StringBuilder buf = (new StringBuilder()).append(StringUtil.simpleClassName(this)).append('(').append(this.config()).append(')');
        return buf.toString();
    }

    static final class PendingRegistrationPromise extends DefaultChannelPromise {
        private volatile boolean registered;

        PendingRegistrationPromise(Channel channel) {
            super(channel);
        }

        void registered() {
            this.registered = true;
        }
        *
        protected EventExecutor executor() {
            return (EventExecutor)(this.registered ? super.executor() : GlobalEventExecutor.INSTANCE);
        }
    }
**/
}
