package com.nicht.promote.asm;

import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.crypto.params.ParametersWithRandom;

import java.io.File;
import java.security.*;
import java.util.Base64;


public class Sm2Util {
    public static void main(String[] args) throws Exception {

        String content="testetstsbshsasasasasa";
        String pemFilePath ="/Users/xujian8/IdeaProjects/wx2/Ed_Promote_Base/DataStruct_Algorithrem/src/main/java/com/nicht/promote/asm/client (6).pem";
        PrivateKey privateKey = KeyUtils.createPrivateKey(KeyUtils.getPrivateKey(new File(pemFilePath)));

//        byte[] encrypt = Sm2Util.encrypt(content.getBytes(), publicKey);
//        String encryptBase64Str = Base64.getEncoder().encodeToString(encrypt);
//        System.out.println("加密数据：" + encryptBase64Str);

//        byte[] decode = Base64.getDecoder().decode(encryptBase64Str);
//        byte[] decrypt = Sm2Util.decrypt(decode, privateKey);
//        System.out.println("解密数据：" + new String(decrypt));

        byte[] sign = Sm2Util.signByPrivateKey(content.getBytes(), privateKey);
        System.out.println("数据签名：" + Base64.getEncoder().encodeToString(sign));

       // boolean b = Sm2Util.verifyByPublicKey(content.getBytes(), publicKey, sign);
       // System.out.println("数据验签：" + b);

    }





    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    public static byte[] encrypt(byte[] data, PublicKey publicKey) {
        ECPublicKeyParameters localECPublicKeyParameters = null;

        if (publicKey instanceof BCECPublicKey) {
            BCECPublicKey localECPublicKey = (BCECPublicKey) publicKey;
            ECParameterSpec localECParameterSpec = localECPublicKey.getParameters();
            ECDomainParameters localECDomainParameters = new ECDomainParameters(localECParameterSpec.getCurve(),
                    localECParameterSpec.getG(), localECParameterSpec.getN());
            localECPublicKeyParameters = new ECPublicKeyParameters(localECPublicKey.getQ(), localECDomainParameters);
        }
        SM2Engine localSM2Engine = new SM2Engine();
        localSM2Engine.init(true, new ParametersWithRandom(localECPublicKeyParameters, new SecureRandom()));
        byte[] arrayOfByte2;
        try {
            arrayOfByte2 = localSM2Engine.processBlock(data, 0, data.length);
            return arrayOfByte2;
        } catch (InvalidCipherTextException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static byte[] decrypt(byte[] encodedata, PrivateKey privateKey) {
        SM2Engine localSM2Engine = new SM2Engine();
        BCECPrivateKey sm2PriK = (BCECPrivateKey) privateKey;
        ECParameterSpec localECParameterSpec = sm2PriK.getParameters();
        ECDomainParameters localECDomainParameters = new ECDomainParameters(localECParameterSpec.getCurve(),
                localECParameterSpec.getG(), localECParameterSpec.getN());
        ECPrivateKeyParameters localECPrivateKeyParameters = new ECPrivateKeyParameters(sm2PriK.getD(),
                localECDomainParameters);
        localSM2Engine.init(false, localECPrivateKeyParameters);
        try {
            byte[] arrayOfByte3 = localSM2Engine.processBlock(encodedata, 0, encodedata.length);
            return arrayOfByte3;
        } catch (InvalidCipherTextException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 私钥签名
     *
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] signByPrivateKey(byte[] data, PrivateKey privateKey) throws Exception {
        Signature sig = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(), BouncyCastleProvider.PROVIDER_NAME);
        sig.initSign(privateKey);
        sig.update(data);
        byte[] ret = sig.sign();
        return ret;
    }


    /**
     * 公钥验签
     *
     * @param data
     * @param publicKey
     * @param signature
     * @return
     * @throws Exception
     */
    public static boolean verifyByPublicKey(byte[] data, PublicKey publicKey, byte[] signature) throws Exception {
        Signature sig = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(), BouncyCastleProvider.PROVIDER_NAME);
        sig.initVerify(publicKey);
        sig.update(data);
        boolean ret = sig.verify(signature);
        return ret;
    }



}
