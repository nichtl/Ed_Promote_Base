package com.nicht.promote.asm;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyUtils {


    /**
     * 自动生成公私钥
     * @return
     * @throws Exception
     */
    public static String[] generateSmKey() throws Exception {
        KeyPairGenerator keyPairGenerator = null;
        SecureRandom secureRandom = new SecureRandom();
        ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
        keyPairGenerator = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
        keyPairGenerator.initialize(sm2Spec);
        keyPairGenerator.initialize(sm2Spec, secureRandom);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        //String[0] 公钥
        //String[1] 私钥
        String[] result = {
                new String(Base64.getEncoder().encode(publicKey.getEncoded()))
                , new String(Base64.getEncoder().encode(privateKey.getEncoded()))
        };
        return result;
    }
    public static PublicKey createPublicKey(String publicKey) {
        PublicKey publickey = null;
        try {
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
            KeyFactory keyFactory = KeyFactory.getInstance("EC", new BouncyCastleProvider());
            publickey = keyFactory.generatePublic(publicKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publickey;
    }
    public static PrivateKey createPrivateKey(String privateKey) {
        PrivateKey publickey = null;
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
            KeyFactory keyFactory = KeyFactory.getInstance("EC", new BouncyCastleProvider());
            publickey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publickey;
    }

    /**
     * 文件获取公私密钥
     * @param pem
     * @return
     */
    public  static   String getPrivateKey(File pem){
            String content = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(pem))) {
            String line;
            StringBuilder pemContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("-----BEGIN") || line.startsWith("-----END")) {
                    continue; // Skip header and footer lines
                }
                pemContent.append(line);
            }
            String pemData = pemContent.toString();
            content =pemData;
            System.out.println("PEM file content:\n" + pemData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  content;
    }



}
