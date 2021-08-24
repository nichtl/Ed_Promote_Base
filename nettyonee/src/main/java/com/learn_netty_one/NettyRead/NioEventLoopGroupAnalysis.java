package com.learn_netty_one.NettyRead;

import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/5/19
 * @Link
 */
public class NioEventLoopGroupAnalysis {
  NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
  //NioEventLoop
   /**
    *  NioEventLoopGroup 是由 一组NioEventLoop 为单位组成的
    *  用于管理NioEventLoop 这里就像是线程池和线程的关系一样
    *  这里先从NioEventLoop进行了解
    * */
    /**
    * 我们基于Netty构建服务端还是客户端时，都首先需要创建NioEventLoopGroup 实例
    * 下面看下 NiiEventLoopGroup 都有哪些方法
    * */
    /**
    *  public class NioEventLoopGroup extends MultithreadEventLoopGroup {
    public NioEventLoopGroup() {
        this(0);
    }   默认方法 设置线程数为0   使用默认构造方法时会使用默认的线程公式动态计线程数量

    public NioEventLoopGroup(int nThreads) {
        this(nThreads, (Executor)null);
    }
   // NioEventLoopGroup最终的构造函数中会包含以下几个函数
   //1、nThreads：传入的线程数量
   //2、executor ：线程执行器Executor接口，默认为空
   //3、selectorProvider：用于创建Selector的SelectorProvider
   //4、selectStrategyFactory：传入DefaultSelectStrategyFactory.INSTANCE，  一个使用默认选择策略的工厂。
   //5、RejectedExecutionHandlers.reject()：Netty自定义线程拒绝策略
    public NioEventLoopGroup(int nThreads, Executor executor, SelectorProvider selectorProvider, SelectStrategyFactory selectStrategyFactory) {
        super(nThreads, executor, new Object[]{selectorProvider, selectStrategyFactory, RejectedExecutionHandlers.reject()});
    }

    public NioEventLoopGroup(int nThreads, Executor executor, EventExecutorChooserFactory chooserFactory, SelectorProvider selectorProvider, SelectStrategyFactory selectStrategyFactory) {
        //调用父类MultithreadEventLoopGroup构造方法
        super(nThreads, executor, chooserFactory, new Object[]{selectorProvider, selectStrategyFactory, RejectedExecutionHandlers.reject()});
    }


    public NioEventLoopGroup(int nThreads, Executor executor, EventExecutorChooserFactory chooserFactory, SelectorProvider selectorProvider, SelectStrategyFactory selectStrategyFactory, RejectedExecutionHandler rejectedExecutionHandler, EventLoopTaskQueueFactory taskQueueFactory) {
        super(nThreads, executor, chooserFactory, new Object[]{selectorProvider, selectStrategyFactory, rejectedExecutionHandler, taskQueueFactory});
    }

    public void setIoRatio(int ioRatio) {
        Iterator var2 = this.iterator();

        while(var2.hasNext()) {
            EventExecutor e = (EventExecutor)var2.next();
            ((NioEventLoop)e).setIoRatio(ioRatio);
        }

    }

    public void rebuildSelectors() {
        Iterator var1 = this.iterator();

        while(var1.hasNext()) {
            EventExecutor e = (EventExecutor)var1.next();
            ((NioEventLoop)e).rebuildSelector();
        }

    }

    protected EventLoop newChild(Executor executor, Object... args) throws Exception {
        EventLoopTaskQueueFactory queueFactory = args.length == 4 ? (EventLoopTaskQueueFactory)args[3] : null;
        return new NioEventLoop(this, executor, (SelectorProvider)args[0], ((SelectStrategyFactory)args[1]).newSelectStrategy(), (RejectedExecutionHandler)args[2], queueFactory);
    }
}
    *
    *
    *
    * */
    /** NioEventLoopGroup最终构造方法调用父类MultithreadEventLoopGroup构造方法*/
    /**
    *public abstract class MultithreadEventLoopGroup extends MultithreadEventExecutorGroup implements EventLoopGroup
    *  private static final int DEFAULT_EVENT_LOOP_THREADS = Math.max(1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
    *  protected MultithreadEventLoopGroup(int nThreads, Executor executor, EventExecutorChooserFactory chooserFactory, Object... args) {
    *  super(nThreads == 0 ? DEFAULT_EVENT_LOOP_THREADS : nThreads, executor, chooserFactory, args);}
    *  /如果没有设置io.netty.eventLoopThreads参数项，则会以当前运行系统的核心线程数*2作为线程数
    *  继续调用父类  MultithreadEventExecutorGroup 构造方法
    * 接下来在MultithreadEventExecutorGroup的构造函数中我们会根据传入的线程数，去初始化和创建一组NioEventLoop
    *  protected MultithreadEventExecutorGroup(int nThreads, Executor executor, EventExecutorChooserFactory chooserFactory, Object... args) {
        this.terminatedChildren = new AtomicInteger();
        this.terminationFuture = new DefaultPromise(GlobalEventExecutor.INSTANCE);
        if (nThreads <= 0) {
            throw new IllegalArgumentException(String.format("nThreads: %d (expected: > 0)", nThreads));
        } else {
            if (executor == null) {
            初始化ThreadPerTaskExecutor线程执行器，并传入一个线程创建工厂，用于NioEventLoop对应线程的创建
            // 创建线程工厂，netty根据需要指定了线程的命名方式、优先级、是否是守护线程等属性
            // 该线程池没有任何队列，提交任务后，创建任何线程类型都是 FastThreadLocalRunnable, 并且立即start
                executor = new ThreadPerTaskExecutor(this.newDefaultThreadFactory());
            }
            //初始化一组事件循环执行器
            this.children = new EventExecutor[nThreads];
            int j;
             //根据传入的线程数，初始化一个线程数组
            for(int i = 0; i < nThreads; ++i) {
                boolean success = false;
                boolean var18 = false;
                try {
                    var18 = true;
                    this.children[i] = this.newChild((Executor)executor, args);
                     继续跟踪进入newChild(executor, args)内部，看到它会返回一个NioEventLoop对象
                       protected EventLoop newChild(Executor executor, Object... args) throws Exception {
                         return new NioEventLoop(this, executor, (SelectorProvider) args[0],
                         ((SelectStrategyFactory) args[1]).newSelectStrategy(), (RejectedExecutionHandler) args[2]);
                     }
                    success = true;
                    var18 = false;
                } catch (Exception var19) {
                    throw new IllegalStateException("failed to create a child event loop", var19);
                } finally {
                    if (var18) {
                        if (!success) {
                            int j;
                            for(j = 0; j < i; ++j) {
                                this.children[j].shutdownGracefully();
                            }
                            for(j = 0; j < i; ++j) {
                                EventExecutor e = this.children[j];
                                try {
                                    while(!e.isTerminated()) {
                                        e.awaitTermination(2147483647L, TimeUnit.SECONDS);
                                    }
                                } catch (InterruptedException var20) {
                                    Thread.currentThread().interrupt();
                                    break;
                                }
                            }
                        }

                    }
                }

                if (!success) {
                    for(j = 0; j < i; ++j) {
                        this.children[j].shutdownGracefully();
                    }

                    for(j = 0; j < i; ++j) {
                        EventExecutor e = this.children[j];

                        try {
                            while(!e.isTerminated()) {
                                e.awaitTermination(2147483647L, TimeUnit.SECONDS);
                            }
                        } catch (InterruptedException var22) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }
            }

            this.chooser = chooserFactory.newChooser(this.children);
            FutureListener<Object> terminationListener = new FutureListener<Object>() {
                public void operationComplete(Future<Object> future) throws Exception {
                    if (MultithreadEventExecutorGroup.this.terminatedChildren.incrementAndGet() == MultithreadEventExecutorGroup.this.children.length) {
                        MultithreadEventExecutorGroup.this.terminationFuture.setSuccess((Object)null);
                    }
                }
            };
            EventExecutor[] var24 = this.children;
            j = var24.length;

            for(int var26 = 0; var26 < j; ++var26) {
                EventExecutor e = var24[var26];
                e.terminationFuture().addListener(terminationListener);
            }

            Set<EventExecutor> childrenSet = new LinkedHashSet(this.children.length);
            Collections.addAll(childrenSet, this.children);
            this.readonlyChildren = Collections.unmodifiableSet(childrenSet);
        }
    }
    *
    * */

















}
