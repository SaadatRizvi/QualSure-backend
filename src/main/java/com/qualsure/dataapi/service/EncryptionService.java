package com.qualsure.dataapi.service;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@Service
public class EncryptionService {
	 private byte[] key;

	    private static final String ALGORITHM = "AES";
	    
	    public EncryptionService()
	    {
	    }
	    
	    public void setKey(byte[] key)
	    {
	        this.key = key;
	    }

	    /**
	     * Encrypts the given plain text
	     *
	     * @param plainText The plain text to encrypt
	     */
	    public byte[] encrypt(byte[] plainText) throws Exception
	    {
	        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

	        return cipher.doFinal(plainText);
	    }

	    /**
	     * Decrypts the given byte array
	     *
	     * @param cipherText The data to decrypt
	     */
	    public byte[] decrypt(byte[] cipherText) throws Exception
	    {
	        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, secretKey);

	        return cipher.doFinal(cipherText);
	    }
	}
	
//	private static final String UNICODE_FORMAT = "UTF8";
//    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
//    private KeySpec ks;
//    private SecretKeyFactory skf;
//    private Cipher cipher;
//    byte[] arrayBytes;
//    private String myEncryptionKey;
//    private String myEncryptionScheme;
//    SecretKey key;
//
//    public EncryptionService() throws Exception {
//        
//    }
//
//    public void setKey(String incomingKey) throws Exception {
//    	myEncryptionKey = incomingKey;
//        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
//        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
//        ks = new DESedeKeySpec(arrayBytes);
//        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
//        cipher = Cipher.getInstance(myEncryptionScheme);
//        key = skf.generateSecret(ks);
//    }
//
//
//    public String encrypt(String unencryptedString) {
//        String encryptedString = null;
//        try {
//            cipher.init(Cipher.ENCRYPT_MODE, key);
//            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
//            byte[] encryptedText = cipher.doFinal(plainText);
//            encryptedString = new String(Base64.encode(encryptedText));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return encryptedString;
//    }
//
//
//    public String decrypt(String encryptedString) {
//        String decryptedText=null;
//        try {
//            cipher.init(Cipher.DECRYPT_MODE, key);
//            byte[] encryptedText = Base64.decode(encryptedString.getBytes(UNICODE_FORMAT));
//            byte[] plainText = cipher.doFinal(encryptedText);
//            decryptedText= new String(plainText);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return decryptedText;
//    }
//
//
//    public static void main(String args []) throws Exception
//    {
//    	
//    }
//}
