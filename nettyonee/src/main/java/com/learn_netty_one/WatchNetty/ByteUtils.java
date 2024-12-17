package com.learn_netty_one.WatchNetty;

import cn.hutool.core.util.HexUtil;

import java.math.BigInteger;

/**
 * @Author ni
 * @description
 * @ 2021/3/30
 */
public class ByteUtils {
    /**
     * 用于建立十六进制字符的输出的小写字符数组
     */
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static void main(String[] args) {
        System.out.println(toStringHex2("1129004F8CED4211F1FF410000C842000000001504010E331800000400A8FF04344701A10E5EFF270000FF00"));
    }

    /*@Description
     * byte字节转16进制字符
     * */
    public static String byteToArray(byte[] data) {
        String result = "";
        for (int i = 0; i < data.length; i++) {
            result += Integer.toHexString((data[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3);
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

    /*
     * @Description
     * 16进制字符转byte数组
     * */
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

    /**
     * 将 4字节的16进制字符串，转换为32位带符号的十进制浮点型
     *
     * @param str 4字节 16进制字符
     * @return
     */
    public static float hexToFloat(String str) {
        return Float.intBitsToFloat(new BigInteger(str, 16).intValue());
    }

    /**
     * 将带符号的32位浮点数装换为16进制
     *
     * @param value
     * @return
     */
    public static String folatToHexString(Float value) {
        return Integer.toHexString(Float.floatToIntBits(value));
    }

    public static float tenBitToFloat(byte[] bytes) {
        String encodehex = HexUtil.encodeHexStr(bytes);
        return hexToFloat(encodehex);
    }


    public static short getShort(byte[] b) {
        return (short) (((b[1] << 8) | b[0] & 0xff));
    }

    public static void putShort(byte b[], short s) {
        b[1] = (byte) (s >> 8);
        b[0] = (byte) (s >> 0);
    }

    public static String toStringHex2(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
                        i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }


}
