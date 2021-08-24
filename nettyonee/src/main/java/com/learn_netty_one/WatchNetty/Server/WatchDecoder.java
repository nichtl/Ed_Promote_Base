package com.learn_netty_one.WatchNetty.Server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author ni
 * @description 自定义协议解码
 * @ 2021/3/30
 */
public class WatchDecoder  extends ByteToMessageDecoder {
    /*
    * 开始符5手机串号IMEI16主协议号1从协议号1数据长度data_len结束符1
     */
    public  final int BASE_LENGTH =  26;  //数据包最小长度
    public  final int MAX_LENGTH  = 4038;  //数据包最大长度



    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf msg, List<Object> out) throws Exception {
        String HEXES = "0123456789ABCDEF";   //16进制转字符
        byte[] req = new byte[msg.readableBytes()];
        msg.readBytes(req);
        final StringBuilder hex = new StringBuilder(2 * req.length);
        for (int i = 0; i < req.length; i++) {
            byte b = req[i];
            hex.append(HEXES.charAt((b & 0xF0) >> 4))
                    .append(HEXES.charAt((b & 0x0F)));
        }
        out.add(hex.toString());
    }

}
