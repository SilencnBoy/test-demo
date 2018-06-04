package com.xul.util;

import java.security.Key;    

import javax.crypto.Cipher;    
import javax.crypto.KeyGenerator;    
import javax.crypto.SecretKey;    
import javax.crypto.SecretKeyFactory;    
import javax.crypto.spec.DESKeySpec;    
import javax.crypto.spec.IvParameterSpec;    
    
   
/**
  * TODO DES 算法       1972美国IBM研制，对称加密算法  
  * @author xl
  * @date 2017年3月1日 上午9:07:46
  */
public class DESUtil {
	
    public static final String KEY_ALGORITHM = "DES"; // 算法名称       
    public static final String CIPHER_ALGORITHM_ECB = "DES/ECB/PKCS5Padding";// 算法名称/加密模式/填充方式        
    public static final String CIPHER_ALGORITHM_CBC = "DES/CBC/PKCS5Padding"; // 算法名称/加密模式/填充方式       
        
    public static byte[] getIV() {    
        String iv = "asdfivh7"; //IV length: must be 8 bytes long    
        return iv.getBytes();    
    }    
    
    //生成密钥  
    private static byte[] generateKey() throws Exception {    
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);    
        keyGenerator.init(56); //des 必须是56, 此初始方法不必须调用    
        SecretKey secretKey = keyGenerator.generateKey();    
        return secretKey.getEncoded();    
    }    
    
    //还原密钥  
    private static Key toKey(byte[] key) throws Exception {    
        DESKeySpec des = new DESKeySpec(key);    
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);    
        SecretKey secretKey = keyFactory.generateSecret(des);    
        return secretKey;    
    }    
        
    //加密   
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {    
        Key k = toKey(key);    
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);    
        cipher.init(Cipher.ENCRYPT_MODE, k);    
        return cipher.doFinal(data);    
    }    
    
    //解密  
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {    
        Key k = toKey(key);    
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);    
        cipher.init(Cipher.DECRYPT_MODE, k);    
        return cipher.doFinal(data);    
    }
    //测试
    public static void main(String[] args) throws Exception { //使用 ECB mode 密钥生成器 生成密钥      
        
        byte[] key = generateKey();     
        byte[] encrypt = encrypt("胃炎F#*（x）".getBytes(), key);    
        System.out.println(new String(decrypt(encrypt, key)));    
        // 使用CBC mode   使用密钥工厂生成密钥，加密 解密  
        DESKeySpec dks = new DESKeySpec(key);    
        SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_ALGORITHM);    
        SecretKey secretKey = factory.generateSecret(dks);    
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);    
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(getIV()));    
        byte[] enc = cipher.doFinal("胃炎A%F#*（x）".getBytes()); //加密    
            
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(getIV()));    
        byte[] dec = cipher.doFinal(enc); // 解密    
        System.out.println(new String(dec));    
    }    
}
