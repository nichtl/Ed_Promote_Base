package com.learn_netty_one.WatchNetty;

import java.io.Serializable;

/**
 * @Author ni
 * @description  自定义协议
 * @ 2021/3/30
 */
public class WatchCommand  implements Serializable {

    private static final long serialVersionUID = -272393621576562539L;
    private  String beginDelimiter ;//"*LOLA"开始符
    private  String imei ;//手机唯一串号，15位，末位补0
    private  String mainPactNum;//主协议号
    private  String slavePactNum;//从协议号
    private  String data_len;//上下行数据包长度
    private  String endDelimiter;//"#"结束符



}
