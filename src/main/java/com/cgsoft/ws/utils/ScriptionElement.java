package com.cgsoft.ws.utils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Component
public class ScriptionElement {

    private static final String AES_ALGORITHM = "AES";
    private static final String AES_MODE = "AES/ECB/PKCS5Padding";
    private static final String AES_SECRET_KEY = "eANp2iewi2Uywjiiplwe1245";

    public static String encrypt(String text) throws Exception {
        byte[] key = AES_SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(AES_MODE);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    public static String decrypt(String text) throws Exception {
        byte[] key = AES_SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(AES_MODE);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decordedValue = Base64.getDecoder().decode(text);
        byte[] decValue = cipher.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }




    /*
    private static final String AES_ALGORITHM = "AES";
    private static final String AES_MODE = "AES/ECB/PKCS5Padding";
    private static final String AES_SECRET_KEY = "my-secret-key-123";

    public String encrypt(String text) throws Exception {

        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM);
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        Cipher cipher = Cipher.getInstance(AES_MODE);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(text.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
*/
}

