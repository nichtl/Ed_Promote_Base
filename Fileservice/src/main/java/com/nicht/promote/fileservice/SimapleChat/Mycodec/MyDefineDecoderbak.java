package com.nicht.promote.fileservice.SimapleChat.Mycodec;

import cn.hutool.core.util.HexUtil;
import com.nicht.promote.fileservice.SimapleChat.Beans.WatchBeans;
import com.nicht.promote.fileservice.SimapleChat.CommandBeans.WatchConstant;
import com.nicht.promote.fileservice.zkNetty.Utils.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author Nicht
 * @description 自定义协议解码器
 * @ 2021/3/31
 */
public class MyDefineDecoderbak extends ByteToMessageDecoder{
    public  final int BASE_LENGTH =  26;  //数据包最小长度
    public  final int MAX_LENGTH  = 4038;  //数据包最大长度
    public  final String  DATA_HEAD="*LOLA";
    public  final String  DATA_END ="#";
    private WatchBeans watchBeans   = new WatchBeans();
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        if(byteBuf.readableBytes() < BASE_LENGTH ){
            System.out.println("小于最小帧长度");
            return;
        }
        if(byteBuf.readableBytes() > MAX_LENGTH ){
            System.out.println("小于最小帧长度");
            return;
        }
        byteBuf.markReaderIndex();
        byte[] lop = new byte[23];
        byteBuf.readBytes(lop);
        byte[] datalen =new  byte[2];
        byteBuf.readBytes(datalen);
        System.out.println("数据长度"+ ByteUtils.getShort(datalen));
        byteBuf.resetReaderIndex();
        System.out.println("前四数据项"+HexUtil.encodeHexStr(lop)+"本次数据大小"+byteBuf.readableBytes());
        byte[] req = new byte[byteBuf.readableBytes()];

        byteBuf.readBytes(req);
        String sreq = ByteUtils.BytesToString(req);

        System.out.println("原始10进制字符串 " + sreq);
        watchBeans.setSecpro(WatchConstant.UploadSecProCode.UploadFileDataResponse);
        out.add(watchBeans);
    }
}