package com.learn_netty_one.SimapleChat.CommandBeans;

import java.io.Serializable;

/**
 * @Author ni
 * @description
 * @ 2021/3/29
 */
public class BaseCommBean implements Serializable {


    private static final long serialVersionUID = -272393621576562539L;
    private  String beginDelimiter  = "*LOLA"; //开始符
    private  String imei ;//手机唯一串号，15位，末位补0
    private  String mainPactNum;//主协议号
    private  String slavePactNum;//从协议号
    private  String data;//上下行数据包
    private  String endDelimiter ="#";//结束符


























}
