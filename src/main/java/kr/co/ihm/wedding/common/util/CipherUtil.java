package kr.co.ihm.wedding.common.util; /**
 * (주)오픈잇 | http://www.openit.co.kr
 * Copyright (c)2006-2021, openit Inc.
 * All right reserved.
 */

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author ihmoon
 * @desc 암호화 관련 Utils
 */
@Component
public class CipherUtil {
	
    private final static String KEY_ALGORITHM_RSA = "RSA";
    private final static String KEY_ALGORITHM_SHA512 = "SHA-512";
    
    private static String PRV_KEY;
    private static String PUB_KEY;

//    @Value("${jwt.private.key}")
//    private void setPrvKeyValue(String key) {
//        PRV_KEY = key;
//    }
//
//    @Value("${jwt.public.key}")
//    private void setPubKeyValue(String key) {
//        PUB_KEY = key;
//    }

    /**
     * RSA 암호화
     * @param plainText
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     */
    public static String encryptRSA(String plainText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {

        String encryptedText = null;

        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
            PublicKey pubKey = (PublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(PUB_KEY)));
            
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);

            byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
            encryptedText = new String(Base64.encodeBase64(encrypted));

        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("NoSuchAlgorithmException : {}" + e.getMessage());
        } catch (NoSuchPaddingException e) {
            throw new NoSuchPaddingException("NoSuchPaddingException : {}" + e.getMessage());
        } catch (InvalidKeySpecException e) {
            throw new InvalidKeySpecException("InvalidKeySpecException : {}" + e.getMessage());
        } catch (InvalidKeyException e) {
            throw new InvalidKeyException("InvalidKeyException : {}" + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            throw new IllegalBlockSizeException("IllegalBlockSizeException : {}" + e.getMessage());
        } catch (BadPaddingException e) {
            throw new BadPaddingException("BadPaddingException : {}" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEncodingException("UnsupportedEncodingException : {}" + e.getMessage());
        }

        return encryptedText;
    }

    /**
     * RSA 복호화
     * @param encryptedText
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String decryptRSA(String encryptedText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        
        String plainText = null;

        try {
            
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
            PrivateKey prvKey = (PrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(PRV_KEY)));
            
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, prvKey);

            byte[] decrypted = Base64.decodeBase64(encryptedText.getBytes());
            plainText = new String(cipher.doFinal(decrypted));

        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("NoSuchAlgorithmException : {}" + e.getMessage());
        } catch (NoSuchPaddingException e) {
            throw new NoSuchPaddingException("NoSuchPaddingException : {}" + e.getMessage());
        } catch (InvalidKeySpecException e) {
            throw new InvalidKeySpecException("InvalidKeySpecException : {}" + e.getMessage());
        } catch (InvalidKeyException e) {
            throw new InvalidKeyException("InvalidKeyException : {}" + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            throw new IllegalBlockSizeException("IllegalBlockSizeException : {}" + e.getMessage());
        } catch (BadPaddingException e) {
            throw new BadPaddingException("BadPaddingException : {}" + e.getMessage());
        }

        return plainText;
    }

    /**
     * SHA512 암호화
     * @param passwordToHash
     * @param salt
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encryptSHA512(String passwordToHash, String salt) throws NoSuchAlgorithmException {

        String generatedPassword = null;
        
        try {
            MessageDigest md = MessageDigest.getInstance(KEY_ALGORITHM_SHA512);
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("NoSuchAlgorithmException : {}" + e.getMessage());
        }
        
        return generatedPassword;
    }
}
