package com.nicht.promote.fileservice.SimapleChat.Mycodec;

import cn.hutool.core.util.HexUtil;
import com.nicht.promote.fileservice.SimapleChat.Beans.FileInfo;
import com.nicht.promote.fileservice.SimapleChat.Beans.LocateMsg;
import com.nicht.promote.fileservice.SimapleChat.Beans.Sms;
import com.nicht.promote.fileservice.SimapleChat.Beans.WatchBeans;
import com.nicht.promote.fileservice.SimapleChat.CommandBeans.WatchConstant;
import com.nicht.promote.fileservice.SimapleChat.Utils.CodecParserUtils;
import com.nicht.promote.fileservice.zkNetty.Utils.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author Nicht
 * @description 处理文本消息
 * @ 2021/4/1
 */
public class MyStringDecoder extends ByteToMessageDecoder {
    public  final int BASE_LENGTH =  26;  //数据包最小长度
    public  final int MAX_LENGTH  = 4038;  //数据包最大长度
    static WatchBeans watchBeans  = new WatchBeans();
    private Integer beginReaderIndex=-1;//记录首读指针
    private LocateMsg   locateMsg;
    private Sms  sms;
    private FileInfo fileInfo;
    byte[] msg;
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        beginReaderIndex = byteBuf.readerIndex();
        byte[] req = new byte[byteBuf.readableBytes()];
        byteBuf.markReaderIndex();
        byteBuf.readBytes(req);
        byteBuf.resetReaderIndex();
        String sreq = HexUtil.encodeHexStr(req);
        System.out.println("req  ==" + ByteUtils.BytesToString(req));
        System.out.println("10进制 转 16 进制 " + sreq);
        byteBuf.markReaderIndex();
        if (byteBuf.readableBytes() < BASE_LENGTH) {
            System.out.println("小于最小帧长度");
            byteBuf.clear();
            return;
        }
        if (byteBuf.readableBytes() > MAX_LENGTH) {
            System.out.println("数据包大于最大长度");
            byteBuf.clear();
            return;
        }
        msg = new byte[5];
        byteBuf.readBytes(msg);
        if (!WatchConstant.Begin.equals(new String(msg))) {
                System.out.println("无效数据舍弃");
                return;
        }
        byteBuf.resetReaderIndex();
        byteBuf.markReaderIndex();
        String parserPubdata = CodecParserUtils.ParserPublicData(byteBuf);
        watchBeans.setBegin(parserPubdata.split(",")[0]);
        watchBeans.setImei(parserPubdata.split(",")[1]);
        watchBeans.setMainpro(parserPubdata.split(",")[2]);
        watchBeans.setSecpro(parserPubdata.split(",")[3]);
        watchBeans.setDatalen(parserPubdata.split(",")[4]);
        if(byteBuf.readableBytes() < Integer.parseInt(watchBeans.getDatalen())-26){
            System.out.println("数据包未完全到达等待数据包  可读字节"+byteBuf.readableBytes()+"datalen="+watchBeans.getDatalen());
            //还原读指针 让buffer先不释放数据  继续缓存下一个包
            internalBuffer().readerIndex(beginReaderIndex);
            //bytebuf  writeandflush 后会被释放   retain  引用计数加一
            return;
        }
        /*是文件内容字节退出  数据大于500说明是文件传入*/
        if(byteBuf.readableBytes() >500 || WatchConstant.UploadSecProCode.UploadFileData.equals(watchBeans.getSecpro())){
                byteBuf.resetReaderIndex();
                System.out.println("转发文件解码器");
                byteBuf.retain(1); //  fireChannelRead 会使buteBuf  ref-1 导致引用异常
                ctx.fireChannelRead(byteBuf);
        }
        System.out.println(watchBeans.getMainpro());
        if(WatchConstant.UploadMainPro.equals(watchBeans.getMainpro())){
            /*文件上传请求*/
            if (WatchConstant.UploadSecProCode.UploadFileRequest.equals(watchBeans.getSecpro())) {
                if(fileInfo==null){fileInfo  =new FileInfo();}
                msg = new byte[1];
                byteBuf.readBytes(msg);
                String file_type = Byte.toString(msg[0]);
                fileInfo.setFile_type(file_type);
                msg = new byte[4];
                byteBuf.readBytes(msg);
                String file_size =Integer.toString(ByteUtils.byteArrayToInt(ByteUtils.reverseBytes(msg)));  //单位byte
                fileInfo.setFile_size(file_size);
                msg = new byte[2];
                byteBuf.readBytes(msg);
                Integer file_name_len = Byte.toUnsignedInt(msg[0]);
                fileInfo.setFile_name_len(Integer.toString(file_name_len));

                msg = new byte[file_name_len + 1];
                byteBuf.readBytes(msg);
                String file_name = new String(msg);
                fileInfo.setFile_name(file_name);

                msg = new byte[12];
                byteBuf.readBytes(msg);
                String pos_info = "";
                fileInfo.setPos_info(pos_info);

                msg = new byte[8];
                byteBuf.readBytes(msg);
                String time = CodecParserUtils.ParserTdateTime(msg);
                fileInfo.setTime(time);
                byteBuf.readByte();//舍弃结束符
                System.out.println("readablebytes"+byteBuf.readableBytes());
                //将完整数据包交给handler处理
                System.out.println("请求上传直接转发handler通知可以上传" );
                watchBeans.setFileInfo(fileInfo);
                out.add(watchBeans);
            }
            /*短信*/
            else if (WatchConstant.UploadMainPro.equals(watchBeans.getMainpro())&&WatchConstant.UploadSecProCode.TextMsg.equals(watchBeans.getSecpro())) {
                if(sms==null){sms=new Sms();}
                msg = new byte[2];
                byteBuf.readBytes(msg);
                String textlen = Short.toString(ByteUtils.getShort(msg));
                sms.setText_len(textlen);
                msg = new byte[Integer.parseInt(textlen)+1];
                byteBuf.readBytes(msg);
                String  text = new String(msg);
                sms.setText(text.substring(0,text.length()-1));
                msg =new byte[4];
                byteBuf.readBytes(msg);
                float lon = ByteUtils.hexToFloat(HexUtil.encodeHexStr(ByteUtils.reverseBytes(msg)));
                sms.setLon(Float.toString(lon));
                msg =new byte[4];
                byteBuf.readBytes(msg);
                float lat = ByteUtils.hexToFloat(HexUtil.encodeHexStr(ByteUtils.reverseBytes(msg)));
                sms.setLat(Float.toString(lat));
                msg =new byte[4];
                byteBuf.readBytes(msg);
                float alt = ByteUtils.hexToFloat(HexUtil.encodeHexStr(ByteUtils.reverseBytes(msg)));
                sms.setAlt(Float.toString(alt));
                msg =new byte[8];
                byteBuf.readBytes(msg);
                sms.setTime(CodecParserUtils.ParserTdateTime(msg));
                byteBuf.readByte();//舍弃结束符
                //将完整数据包交给handler处理
                System.out.println("文本消息转发handler");
                watchBeans.setSms(sms);
                out.add(watchBeans);
            }
            /*定位上报    此处float数据应该是大端序但在数据是小端序 需要将小端序转换为大端序后才能使用*/
            else if (WatchConstant.UploadMainPro.equals(watchBeans.getMainpro())&&WatchConstant.UploadSecProCode.UploadLocateData.equals(watchBeans.getSecpro())) {
                if (locateMsg==null){
                    locateMsg  = new LocateMsg();
                }
                msg = new byte[4];
                byteBuf.readBytes(msg);
                float lon = ByteUtils.hexToFloat(HexUtil.encodeHexStr(ByteUtils.reverseBytes(msg)));
                locateMsg.setLon(Float.toString(lon));

                msg = new byte[4];
                byteBuf.readBytes(msg);
                float lat = ByteUtils.hexToFloat(HexUtil.encodeHexStr(ByteUtils.reverseBytes(msg)));
                locateMsg.setLat(Float.toString(lat));

                msg = new byte[4];
                byteBuf.readBytes(msg);
                float alt = ByteUtils.hexToFloat(HexUtil.encodeHexStr(ByteUtils.reverseBytes(msg)));
                locateMsg.setAlt(Float.toString(alt));

                msg = new byte[4];
                byteBuf.readBytes(msg);
                float speed = ByteUtils.hexToFloat(HexUtil.encodeHexStr(ByteUtils.reverseBytes(msg)));
                locateMsg.setSpeed(Float.toString(speed));


                msg = new byte[8];
                byteBuf.readBytes(msg);
                locateMsg.setTime(CodecParserUtils.ParserTdateTime(msg));


                msg = new byte[4];
                byteBuf.readBytes(msg);  //暂时舍弃
                float mileage = ByteUtils.hexToFloat(HexUtil.encodeHexStr(ByteUtils.reverseBytes(msg)));
                locateMsg.setMileage("0");


                msg = new byte[1];
                byteBuf.readBytes(msg);
                byte[] gps_status = HexUtil.decodeHex(HexUtil.encodeHex(msg));
                locateMsg.setGps_status(ByteUtils.BytesToString(gps_status));

                msg = new byte[6];
                byteBuf.readBytes(msg); //Wrist_id 固定为字符直接赋值000000
                locateMsg.setWrist_id("000000");

                msg = new byte[1];
                byteBuf.readBytes(msg);
                byte[] data_type = HexUtil.decodeHex(HexUtil.encodeHex(msg));
                locateMsg.setData_type(ByteUtils.BytesToString(data_type));

                msg = new byte[1];
                byteBuf.readBytes(msg);
                byte[] battery = HexUtil.decodeHex(HexUtil.encodeHex(msg));
                locateMsg.setBattery(ByteUtils.BytesToString(battery));

                msg = new byte[1];
                byteBuf.readBytes(msg);
                byte[] Cutoff_times = HexUtil.decodeHex(HexUtil.encodeHex(msg));
                locateMsg.setCutoff_times(ByteUtils.BytesToString(Cutoff_times));

                msg = new byte[1];
                byteBuf.readBytes(msg);
                byte[] Cutoff_status = HexUtil.decodeHex(HexUtil.encodeHex(msg));
                locateMsg.setCutoff_status(ByteUtils.BytesToString(Cutoff_status));

                msg = new byte[1];
                byteBuf.readBytes(msg);
                byte[] Sparate_status = HexUtil.decodeHex(HexUtil.encodeHex(msg));
                locateMsg.setSparate_status(ByteUtils.BytesToString(Sparate_status));

                msg = new byte[1];
                byteBuf.readBytes(msg);
                byte[] Heart_rate = HexUtil.decodeHex(HexUtil.encodeHex(msg));
                locateMsg.setHeart_rate(ByteUtils.BytesToString(Heart_rate));
                byteBuf.readByte();//舍弃结束符
                //将完整数据包交给handler处理
                System.out.println("定位上报消息转发handler");
                watchBeans.setLocateMsg(locateMsg);
                out.add(watchBeans);
            }
        }
        if(WatchConstant.IssueMainPro.equals(watchBeans.getMainpro())) {
            /**下发类回复*/
            /**终端回复设备同步参数*/
            if (WatchConstant.IssueSecProCode.SyncDeviceSetting.equals(watchBeans.getSecpro())) {
                msg  = new byte[Integer.parseInt(watchBeans.getDatalen())];
                byteBuf.readBytes(msg);
                watchBeans.setStr_Data(new String(msg));
                watchBeans.setCommandSendSuccess(true);
                byteBuf.readByte();
                out.add(watchBeans);
            }
            /**终端接受短信回复*/
            else if (WatchConstant.UploadSecProCode.TextMsg.equals(watchBeans.getSecpro())) {
                if (sms == null) {
                    sms = new Sms();
                }
                msg = new byte[8];
                byteBuf.readBytes(msg);
                watchBeans.setSendTime(CodecParserUtils.ParserTdateTime(msg));
                if (!CodecParserUtils.isNullOrEmpty(watchBeans.getSendTime())) {
                    watchBeans.setCommandSendSuccess(true);  //短信发送成功
                }
                out.add(watchBeans);
            }
            /**其他命令统一格式回复*/
            else {
                msg=new byte[Integer.parseInt(watchBeans.getDatalen())];
                byteBuf.readBytes(msg);
                watchBeans.setStr_Data(new String(msg));
                watchBeans.setCommandSendSuccess(true);
              out.add(watchBeans);
            }
        }

    }
    }


