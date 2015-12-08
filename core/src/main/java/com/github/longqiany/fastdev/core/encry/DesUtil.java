package com.github.longqiany.fastdev.core.encry;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @author quzile
 * @date 2015年1月21日 下午6:48:12
 * @since 1.0.0
 */
public class DesUtil {

    private static final String CHARSET = "UTF-8";
    private static final String DES = "DES";
    private static final String password = "OuJYTgF6PzoIu653";

    public static byte[] encrypt(byte[] b) throws Exception {
        return encrypt(b, password);
    }

    public static byte[] encrypt(byte[] b, String password) throws Exception {
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(password.getBytes());

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(desKey);

        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
        return cipher.doFinal(b);
    }

    public static byte[] decrypt(byte[] b) throws Exception {
        return decrypt(b, password);
    }

    public static byte[] decrypt(byte[] b, String password) throws Exception {
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(password.getBytes());

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(desKey);

        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        return cipher.doFinal(b);
    }

    public static String encode(String text) throws Exception {
        return Base64.encode(encrypt(text.getBytes(CHARSET)));
        // byte[] b =
        // Base64.encodeBase64URLSafe(encrypt(text.getBytes("UTF-8")));
        // return new String(b, CHARSET);
    }

    public static String decode(String text) throws Exception {
        return new String(decrypt(Base64.decode(text)), CHARSET);
        // return new String(decrypt(Base64.decodeBase64(text)), "UTF-8");
    }

    public static void main(String[] args) {
        try {
            String text = "中华人民共和国";

            // byte[] result = des.encrypt(text.getBytes("UTF-8"));
            // System.out.println("加密后：" + Arrays.toString(result));

            // byte[] decryResult = des.decrypt(result);
            // System.out.println("解密后：" + new String(decryResult, "UTF-8"));
            String base = encode(text);
            String result = decode(base);

            System.out.println(base);
            System.out.println(result);
            // String crypttext = Base64.encode(text.getBytes());
            // System.out.println(crypttext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
