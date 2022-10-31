package com.nicht.promote.fileservice.SimapleChat.Mycodec;

import com.nicht.promote.fileservice.SimapleChat.Beans.WatchBeans;
import com.nicht.promote.fileservice.SimapleChat.CommandBeans.WatchConstant;
import com.nicht.promote.fileservice.SimapleChat.Utils.CodecParserUtils;
import com.nicht.promote.fileservice.zkNetty.Utils.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * @Author Nicht
 * @description 自定义协议解码器
 * @ 2021/3/31
 */
public class MyFileDecoder extends ByteToMessageDecoder{

    public  final int BASE_LENGTH =  26;  //数据包最小长度
    public  final int MAX_LENGTH  = 4038;  //数据包最大长度
    private boolean isFile = false;//判断是否为文件
    private Integer FileTotalSize =0;//文件总大小
    private static ByteBuffer cacheFile = ByteBuffer.allocate(10485760);//临时存储文件 10M大小
    StringBuilder  sb  =new StringBuilder();
    static WatchBeans   watchBeans  = new WatchBeans();
    byte[] msg;
    static ThreadLocal<Integer> filePackNum =ThreadLocal.withInitial(()->-1);
    /*字节序 设备是小端  平台是大端 接受int  float  long 都转为数据需要将数据还原再解析 */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        byteBuf.markReaderIndex();
        msg = new byte[5];
        byteBuf.readBytes(msg);
        byteBuf.resetReaderIndex();
        byteBuf.markReaderIndex();
        String parserPubdata = CodecParserUtils.ParserPublicData(byteBuf);
        watchBeans.setBegin(parserPubdata.split(",")[0]);
        watchBeans.setImei(parserPubdata.split(",")[1]);
        watchBeans.setMainpro(parserPubdata.split(",")[2]);
        watchBeans.setSecpro(parserPubdata.split(",")[3]);
        watchBeans.setDatalen(parserPubdata.split(",")[4]);
        FileTotalSize=Integer.parseInt(watchBeans.getDatalen())-12;

        //目前文件标识无法传递 暂时舍弃
        byteBuf.readBytes(8);
        msg=new byte[2];  //103
        if(filePackNum.get()==-1){  //若为-1说明为第一次上传 设置packnum
            byteBuf.readBytes(msg);
            String pack_num = Short.toString(ByteUtils.getShort(msg));
            filePackNum.set(Integer.parseInt(pack_num)-1);  //初始包序号为0
        }else{byteBuf.readBytes(msg);}
        msg =new byte[2];
        byteBuf.readBytes(msg);
        String pack_ind =Short.toString(ByteUtils.getShort(msg));
        System.out.println("当前文件包num"+pack_ind);
        try {
            msg = new byte[Integer.parseInt(watchBeans.getDatalen())-12];//  文件数据长度为 datalen-12
            System.out.println("可读字节"+byteBuf.readableBytes());
            byteBuf.readBytes(msg);
            watchBeans.putBytesdata(msg);
            //cacheFile.put(msg);//缓存数据
            byteBuf.readByte();//舍弃结束符
        }catch (Exception  e){e.printStackTrace();}
        if(filePackNum.get()==0){
            filePackNum.set(-1); //上传完成恢复初始状态 如果包数为0说明全部到达
            watchBeans.setSecpro(WatchConstant.UploadSecProCode.UploadFileData);
            out.add(watchBeans);
        }
        else {
            filePackNum.set(filePackNum.get()-1);
            //回复07继续上传
            watchBeans.setSecpro(WatchConstant.UploadSecProCode.UploadFileDataResponse); //07回复
            out.add(watchBeans);
        }
    }

}

