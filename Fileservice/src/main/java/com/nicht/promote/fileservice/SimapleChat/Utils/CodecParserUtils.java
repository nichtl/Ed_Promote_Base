package com.nicht.promote.fileservice.SimapleChat.Utils;

import cn.hutool.core.util.HexUtil;
import com.nicht.promote.fileservice.zkNetty.Utils.ByteUtils;
import io.netty.buffer.ByteBuf;

/**
 * @Author Nicht
 * @description
 * @ 2021/4/2
 */
public class CodecParserUtils {
    public static void main(String[] args) {

        byte [] b  = new byte[8];
        b[0] = 0;
        b[1]=4;
        b[2]=2;
        b[3]=13;
        b[4]=27;
        b[5]=15;
        b[6]=0;
        b[7]=0;
        String   sb  =  Byte.toString(b[0]);
        System.out.println(sb);

    }
    public  static  String ParserTdateTime(byte[] msg) {
       StringBuilder   hex =  new StringBuilder(); 

        String year = Byte.toString(msg[0]);
        hex.append("20"+year).append("-");
        
        String month = Byte.toString(msg[1]);
        hex.append(month).append("-");
        
        
        String day = Byte.toString(msg[2]);
        hex.append(day).append(" ");
        
        
        String hour = Byte.toString(msg[3]);
        hex.append(hour).append(":");
        
        
        String min = Byte.toString(msg[4]);
        hex.append(min).append(":");
        
        
        String sec = Byte.toString(msg[5]);
        hex.append(sec).append(":");

        byte[] temp = new  byte[2];
        temp[0] = msg[6];
        temp[1] = msg[7];
        String msec = Short.toString(ByteUtils.getShort(temp));
        hex.append(msec+"0");
        return hex.toString();
    }
    /*解析前四个公共数据*/
    public  static  String ParserPublicData(ByteBuf byteBuf){
        StringBuilder  sb =new StringBuilder();
        byte [] msg  ;
        msg=new byte[5];
        byteBuf.readBytes(msg);
        String begin =  new String(msg);
        sb.append(begin).append(",");
        msg=  new byte[16];
        byteBuf.readBytes(msg);
        String imei=  new String(msg);
        sb.append(imei).append(",");
        msg  =new byte[1];
        byteBuf.readBytes(msg);
        String mainprotro=  HexUtil.encodeHexStr(msg);
        sb.append(mainprotro).append(",");
        msg  =new byte[1];
        byteBuf.readBytes(msg);
        String secondprotro = HexUtil.encodeHexStr(msg);
        sb.append(secondprotro).append(",");
        msg  =new byte[2];
        byteBuf.readBytes(msg);
        String datalen = Short.toString(ByteUtils.getShort(msg));
        sb.append(datalen).append(",");
        return  sb.toString();
    }

    public static byte int2Byte(int i){
        byte r = (byte) i;
        return r;
    }


    public static Integer ByteToInt(Byte i){
         return   i.intValue();
    }

    public static boolean isNullOrEmpty(String param) {
        return null == param || param.length() == 0;
    }












}
