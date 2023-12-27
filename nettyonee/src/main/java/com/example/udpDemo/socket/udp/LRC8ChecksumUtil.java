package com.example.udpDemo.socket.udp;

/**
 * Description: LRC8和校验工具
 *
 * @author sjt Administrator
 * @since 2023/12/2 19:33
 */
public class LRC8ChecksumUtil {

    // 计算 LRC 校验值
    public static byte calculateLRC(byte[] data) {
        byte lrc = 0;
        for (byte b : data) {
            lrc += b;
        }
        lrc = (byte) (~lrc + 1); // 取反+1
        return lrc;
    }
}
