package com.ed.leone.leonedemo.nettyServer;

import com.ed.leone.leonedemo.Client.NettyClient;

/**
 * @Author admin
 * @description
 * @ 2020/12/11
 */
public class NettyTest {
    public static void main(String[] args) {
        //开启10条线程，每条线程就相当于一个客户端
        for (int i = 1; i <= 10; i++) {
            new Thread(new NettyClient("thread" + "--" + i)).start();
        }
    }
}
