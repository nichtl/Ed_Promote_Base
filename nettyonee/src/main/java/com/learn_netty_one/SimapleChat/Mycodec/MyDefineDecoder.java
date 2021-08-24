package com.learn_netty_one.SimapleChat.Mycodec;

import cn.hutool.core.util.HexUtil;
import com.learn_netty_one.WatchNetty.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author ni
 * @description 自定义协议解码器
 * @ 2021/3/31
 */
public class MyDefineDecoder extends ByteToMessageDecoder {
    /*
     * 开始符5手机串号IMEI16主协议号1从协议号1数据长度data_len结束符1
     */
    public  final int BASE_LENGTH =  26;  //数据包最小长度
    public  final int MAX_LENGTH  = 4038;  //数据包最大长度
    public  final String  DATA_HEAD="*LOLA";
    public  final String  DATA_END ="#";
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
      if(byteBuf.readableBytes() < BASE_LENGTH ){
          System.out.println("小于最小帧长度");
          return;
      }
        int beginReaderIndex;//维护读下标
        while (true) {
            beginReaderIndex = byteBuf.readerIndex();  //维护数据包完整
            //标记读下标
            byteBuf.markReaderIndex();
            byte[] Head = new byte[5];
            byteBuf.readBytes(5).readBytes(Head);
            System.out.println("自定义解码器 读取5byte"+HexUtil.decodeHex(ByteUtils.byteToArray(Head).toCharArray()));
            //读到开始标志了
            if(HexUtil.decodeHex(ByteUtils.byteToArray(Head).toCharArray()).equals(DATA_HEAD)){
                System.out.println("匹配到*Lola");
                break;//跳出循环
            }
            //回退readerIndex
            byteBuf.resetReaderIndex();
            byteBuf.readByte();//跳过一个字符
            //如果是最小帧 那么就不满足最小长度了直接return
            if(byteBuf.readableBytes() < BASE_LENGTH){
               return;
            }
            //上行数据包长度
            short dataLen = byteBuf.readShort();
            System.out.println(dataLen);
              //判断数据是否全部到达
             if(byteBuf.readableBytes() <dataLen){
                 System.out.println("数据包未完全到达等待数据包");
                 //还原读指针 让buffer先不释放数据  继续缓存下一个包
                 internalBuffer().readerIndex(beginReaderIndex);
                 return;
             }
             //当数据包全部到达已经完整时
            //将数据全部取出让buf清空
            //读取data
             System.out.println("数据包全部到达,取数据");
             byte[]  data=new byte[dataLen];
             byteBuf.readBytes(data);
             //将完整数据包交给handler处理
            System.out.println("转发handler");
             out.add(data);
        }
    }
}
