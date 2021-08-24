package com.learn_netty_one.Socket.UniteSocket;

/**
 * socket监听
 * Created by Jason Zhang on 2017/06/29.
 */
public interface OnSocketListener {

    /***
     * 启动状态
     * @param isSuccess true:启动成功，false:启动失败
     */
    void onStart(boolean isSuccess);

    /***
     * 新客户端接入成功
     * @param channel
     */
    void channelActive(Object channel);

    void onReceive(Object channel,Object message);

    void onSend(Object channel,Object message,boolean isSuccess);

    void onClose(Object channel,boolean isSuccess);

}
