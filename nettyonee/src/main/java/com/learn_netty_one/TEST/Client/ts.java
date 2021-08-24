package com.learn_netty_one.TEST.Client;

import cn.hutool.core.util.HexUtil;
import com.learn_netty_one.WatchNetty.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.ByteProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.util.stream.Stream;

/**
 * @Author ni
 * @description
 * @ 2021/3/30
 */
public class ts {

    public static void main(String[] args) throws UnsupportedEncodingException {
        //String  =  "*LOLAT0X0054DSADSADA#";
         String s = "2a4c4f4c4138363339393531353133343330313600541129004f8ced4214f1ff410000c84200000000150402110e1100000400a3ff04344701880f17ff490000ff0023\n";
         String[] sj =s.split(s,2);

        byte[]  lbs  = new byte[4];
        lbs[0]=117;lbs[1]=-116; lbs[2]=-19; lbs[3]=66;

        System.out.println(ByteUtils.hexToFloat(s));


        short sn  = 50;

       // System.out.println(HexUtil.encodeHexStr());
        //byte []  bs = s.replace(" " ,"").getBytes();
        //System.out.println(byteToArray(bs));
        //System.out.println(new String(decodeHex(byteToArray(bs).toCharArray())));  //16进制字符数组转字符

        byte []  bs= HexUtil.decodeHex(s.replace(" ","").replace(" ",""));
        //System.out.println(decodedStr);
        for (int i = 0; i < bs.length; i++) {
            System.out.println(bs[i]);
        }
        System.out.println(bs.toString());
        //System.out.println(decodedStr.length());
        System.out.println(HexUtil.encodeHexStr("*LOLA865588#&"));
        //System.out.println(HexUtil.decodeHexStr("2A4C4F4C41541102004F4B23".trim().toUpperCase()));



    }


    public static String byteToArray(byte[]data){
        String result="";
        for (int i = 0; i < data.length; i++) {
            result+=Integer.toHexString((data[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3);
        }
        return result;
    }
    protected static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("非法16进制字符 " + ch
                    + " 在索引 " + index);
        }
        return digit;
    }


    /**
     * 用于建立十六进制字符的输出的小写字符数组
     */
    private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };



    public static byte[] decodeHex(char[] data) {
        int len = data.length;
        if ((len & 0x01) != 0) {
            throw new RuntimeException("未知的字符");
        }
        byte[] out = new byte[len >> 1];
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }

}
