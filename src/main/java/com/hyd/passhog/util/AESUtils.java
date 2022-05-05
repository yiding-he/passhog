package com.hyd.passhog.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * AES 加密解密
 *
 * @author yiding.he
 */
public class AESUtils {

    public static final byte SPACE = " ".getBytes()[0];

    private static final String DEFAULT_MODE = "AES/CBC/PKCS5Padding";

    private static byte[] toBytes(String content) {
      return content.getBytes(StandardCharsets.UTF_8);
    }

    private static String toString(byte[] bytes) {
      return new String(bytes, StandardCharsets.UTF_8);
    }

    public static String encode256(String content, String key) {
        return Base64.getEncoder().encodeToString(encode256(toBytes(content), toBytes(key)));
    }

    public static String decode256(String encoded, String key) {
        return toString(decode256(Base64.getDecoder().decode(encoded), toBytes(key)));
    }

    /**
     * 128位加密
     *
     * @param content 要加密的内容
     * @param key     密钥
     *
     * @return 加密后的内容
     *
     * @throws EncryptException 如果加密失败
     */
    public static byte[] encode256(byte[] content, byte[] key) throws EncryptException {
        return encode(content, key, 256, DEFAULT_MODE);
    }

    /**
     * 128位加密
     *
     * @param content 要加密的内容
     * @param key     密钥
     * @param mode    加密模式
     *
     * @return 加密后的内容
     *
     * @throws EncryptException 如果加密失败
     */
    public static byte[] encode256(byte[] content, byte[] key, String mode) throws EncryptException {
        return encode(content, key, 256, mode);
    }

    /**
     * 128位解密
     *
     * @param encoded 被加密的内容
     * @param key     密钥
     *
     * @return 原始内容
     *
     * @throws EncryptException 如果解密失败
     */
    public static byte[] decode256(byte[] encoded, byte[] key) throws EncryptException {
        return decode(encoded, key, 256, DEFAULT_MODE);
    }

    /**
     * 128位解密
     *
     * @param encoded 被加密的内容
     * @param key     密钥
     * @param mode    模式
     *
     * @return 原始内容
     *
     * @throws EncryptException 如果解密失败
     */
    public static byte[] decode256(byte[] encoded, byte[] key, String mode) throws EncryptException {
        return decode(encoded, key, 256, mode);
    }

    public static byte[] encode(byte[] content, byte[] key, int length, String mode) throws EncryptException {

        if (length != 128 && length != 192 && length != 256) {
            throw new IllegalArgumentException("Invalid length: " + length);
        }

        key = expand(key, length);

        try {
            // AES/CBC/NoPadding
            // AES/CBC/PKCS5Padding *widely used
            // AES/ECB/NoPadding
            // AES/ECB/PKCS5Padding
            Cipher cipher = Cipher.getInstance(mode);
            SecretKeySpec keyspec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keyspec);
            return cipher.doFinal(content);
        } catch (Exception e) {
            throw new EncryptException(e);
        }
    }

    public static byte[] decode(byte[] encoded, byte[] key, int length, String mode) throws EncryptException {

        if (length != 128 && length != 192 && length != 256) {
            throw new IllegalArgumentException("Invalid length: " + length);
        }

        key = expand(key, length);

        try {
            Cipher cipher = Cipher.getInstance(mode);
            SecretKeySpec keyspec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, keyspec);
            return cipher.doFinal(encoded);
        } catch (Exception e) {
            throw new EncryptException(e);
        }
    }

    public static byte[] expand(byte[] key, int length) {
        byte[] result = new byte[length / 8];

        int end = Math.min(key.length, result.length);

        System.arraycopy(key, 0, result, 0, end);
        Arrays.fill(result, end, result.length, SPACE);
        return result;
    }
}
