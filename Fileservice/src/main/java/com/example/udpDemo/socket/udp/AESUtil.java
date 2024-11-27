package com.example.udpDemo.socket.udp;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import io.netty.buffer.ByteBufUtil;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Description:
 *
 * @author sjt Administrator
 * @since 2023/11/30 14:16
 */

@Slf4j
public class AESUtil    {
    /**
     * 输出大写的16进制密文字符串
     * @param key     密钥
     * @param content 加密内容
     * @auther sjt Administrator
     * @since 2023/12/3 20:05
     */
    public static String encrypt(String key, byte[] content) {
        SymmetricCrypto symmetricCrypto = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getBytes());
        return symmetricCrypto.encryptHex(content).toUpperCase();
    }

    /**
     * 解密
     *
     * @param key     密钥
     * @param content 解密内容
     * @return
     * @auther sjt Administrator
     * @since 2023/12/3 20:07
     */
    public static byte[] decrypt(String key, String content) {
        byte[] bytes = dataSupplement(content.getBytes());
        SymmetricCrypto symmetricCrypto = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getBytes());
        return symmetricCrypto.decryptStr(content).getBytes();
    }

    /**
     * 解密
     *
     * @param key     密钥
     * @param content 解密内容
     * @return
     * @auther sjt Administrator
     * @since 2023/12/3 20:07
     */
    public static byte[] decrypt(String key, byte[] content) {
        byte[] bytes = dataSupplement(content);
        log.info("解密报文：{}",HexUtil.encodeHexStr(bytes).toUpperCase());
        SymmetricCrypto symmetricCrypto = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getBytes());
        return symmetricCrypto.decrypt(bytes);
    }

    /**
     * 不足16的倍数 后面补0
     * @auther sjt Administrator
     * @since 2023/12/19 20:34
     * @return
     */
    public static byte[] dataSupplement(byte[] originalData) {
        int blockSize = 16;
        // 计算需要填充的字节数
        int paddingSize = blockSize - (originalData.length % blockSize);
        if (paddingSize == 0) {
            paddingSize = blockSize;  // 如果原始数据长度已经是 blockSize 的倍数，需要填充一个新的块
        }
        // 创建新的字节数组并填充
        return Arrays.copyOf(originalData, originalData.length + paddingSize);
    }
    public static short getShort ( byte[] b){//大端序
        return (short) (((b[1] << 8) | b[0] & 0xff));
    }

    public static byte[] decrypt2(String key, byte[] content)  throws  Exception{
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decryptedBytes = cipher.doFinal(content);
        return decryptedBytes;
    }
    public static String BytesToString ( byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(bytes[i]);
        }
        return sb.toString();
    }
    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String codestr = "5E003D 01 01000101000186450707082140900013010119991231021210001707F2AEE15A1AEF602A4DF0076CD4134E758E13B64A78B7D75919D6B9060DEAAB885B";
        String content = "9E09AE74D68CE20F11C4E287465D0498CFDCE6D25EB28B0233AF5B4FFE0C8982FC66BFD6804E6DC905D33A8FED8EC8FEA2C8329AC2973B043DFB5042700EA038B96E2D4305E1EE515C227A6E3A3F0895EF1678BD57DE43A6A199A2270AC82895389DF60494468D768FDAC4E84CB8FE10";


        byte[] chars = decrypt2("eVeX2g43w!$NrmKi",content.getBytes(StandardCharsets.UTF_8));


        // 生成密钥
        byte[] encoded = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded(); //"eVeX2g43w!$NrmKi"
        byte[] encoded2 = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        byte[] encoded3 = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        // 打印生成的密钥
        String s = Arrays.toString(encoded);
        byte[] bytes1 = "eVeX2g43w!$NrmKi".getBytes(StandardCharsets.UTF_8);
        String s1 = new String(bytes1);
        String s2 = new String(encoded2);
        String s3 = new String(encoded3);
        System.out.println("AES密钥: " + s1);
        System.out.println(ByteBufUtil.hexDump(encoded2));
        String s4 = RandomUtil.randomString("eVeX2g43w!$NrmKi".length());
        System.out.println("AES密钥: " + s4);
        System.out.println("AES密钥: " + RandomUtil.randomString("eVeX2g43w!$NrmKi".length()));


        SymmetricCrypto symmetricCrypto = new SymmetricCrypto(SymmetricAlgorithm.AES, s4.getBytes());
        String encrypt = symmetricCrypto.encryptHex("AES密钥");
        String de = symmetricCrypto.decryptStr(encrypt);
        System.out.println("ok");



    }
}

