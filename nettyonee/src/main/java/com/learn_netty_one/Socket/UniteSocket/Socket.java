package com.learn_netty_one.Socket.UniteSocket;

/**
 * socket 统一接口 不同实现均继承
 * @Author ni
 * @description
 * @ 2021/1/18
 */
public interface Socket {
      /***
       * 设置监听
       * @param listener
       */
      void setOnSocketListener(OnSocketListener listener);
      // socket 启动
      void start(int port);
      // socket 关闭
      void shutdown();
      // socket 重设   动态代理
      void reset();
      // sendMsg 发消息
      void sendMessage(Object channel,Object Message);
      //判断是否在线
      boolean isOnline(Object channel);






}
