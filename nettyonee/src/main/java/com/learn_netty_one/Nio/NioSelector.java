package com.learn_netty_one.Nio;

import java.io.IOException;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;

/**
 * @Author ni
 * @description
 * @Link https://ifeve.com/selectors/
 * @Link reactor模式
 * @ 2021/3/16
 */
public class NioSelector {
    //五种 io模式  涉及到操作系统用户态和内核态的互相转换    https://zhuanlan.zhihu.com/p/115912936
    // 阻塞IO、非阻塞IO、、信号驱动IO以及异步IO。
    /*
    * 1 阻塞IO 最传统的一种IO模型，即在读写数据过程中会发生阻塞现象。当用户线程发出IO请求之后，
    *   内核会去查看数据是否就绪，如果没有就绪就会等待数据就绪，而用户线程就会处于阻塞状态，
    *   用户线程交出CPU。当数据就绪之后，内核会将数据拷贝到用户线程，并返回结果给用户线程，
    *   用户线程才解除block状态。
    *   使用单用户线程
    * 2 非阻塞IO模型 当用户线程发起一个read操作后，并不需要等待，而是马上就得到了一个结果。
    *   如果结果是一个error时，它就知道数据还没有准备好，于是它可以再次发送read操作。
    *   一旦内核中的数据准备好了，并且又再次收到了用户线程的请求，那么它马上就将数据拷贝到了用户线程，然后返回。
    *   所以事实上，在非阻塞IO模型中，用户线程需要不断地询问内核数据是否就绪，也就说非阻塞IO不会交出CPU，而会一直占用CPU。
    * 3 多路复用IO javaNio使用的io模式 多路复用IO模型是目前使用得比较多的模型
    * 　在多路复用IO模型中，会有一个线程不断去轮询多个socket的状态，只有当socket真正有读写事件时，才真正调用实际的IO读写操作。因为在多路复用IO模型中，
    *   只需要使用一个线程就可以管理多个socket，系统不需要建立新的进程或者线程，也不必维护这些线程和进程，并且只有在真正有socket读写事件进行时，
    *   才会使用IO资源，所以它大大减少了资源占用
    *   javaNio就是使用这种方式
    *   Selector（选择器）是Java NIO中能够检测一到多个NIO通道，并能够知晓通道是否为诸如读写事件做好准备的组件。这样，一个单独的线程可以管理多个channel，
    *   在Java NIO中，是通过selector.select()去查询每个通道是否有到达事件，如果没有事件，则一直阻塞在那里，因此这种方式会导致用户线程的阻塞。
    * 4 事件驱动IO //略过
    * 5 异步IO
    * */

    /*NioSelector 就是多路复用io模式的体现
    * 为什么使用Selector?
     *仅用单个线程来处理多个Channels的好处是，只需要更少的线程来处理通道。事实上，可以只用一个线程处理所有的通道。对于操作系统来说，
     * 线程之间上下文切换的开销很大，而且每个线程都要占用系统的一些资源（如内存）。因此，使用的线程越少越好。
    * **/
    public static void main(String[] args) {
       /* 使用一个Selector处理3个channel的示例
        * Channel与Selector一起使用时，Channel必须处于非阻塞模式下  这意味着不能将FileChannel与Selector一起使用，
        * 因为FileChannel不能切换到非阻塞模式。而套接字通道都可以。
        **/
        try {
            ServerSocketChannel  serverSocketChannel  =  ServerSocketChannel.open();
            SocketChannel  socketChannel1 =  SocketChannel.open();
            Selector selector = Selector.open();
            SelectableChannel selectableChannel = new SelectableChannel() {
                @Override
                public SelectorProvider provider() {
                    return null;
                }

                @Override
                public int validOps() {
                    return 0;
                }

                @Override
                public boolean isRegistered() {
                    return false;
                }

                @Override
                public SelectionKey keyFor(Selector sel) {
                    return null;
                }

                @Override
                public SelectionKey register(Selector sel, int ops, Object att) throws ClosedChannelException {
                    return null;
                }

                @Override
                public SelectableChannel configureBlocking(boolean block) throws IOException {
                    return null;
                }

                @Override
                public boolean isBlocking() {
                    return false;
                }

                @Override
                public Object blockingLock() {
                    return null;
                }

                @Override
                protected void implCloseChannel() throws IOException {

                }
            };
            Channel channel= new Channel() {
                @Override
                public boolean isOpen() {
                    return false;
                }

                @Override
                public void close() throws IOException {

                }
            };
           // SelectableChannel
        }catch (Exception e){
            e.printStackTrace();
        }
    }

























}
