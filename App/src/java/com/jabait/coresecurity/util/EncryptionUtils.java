package com.jabait.coresecurity.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * Created by IntelliJ IDEA.
 * User: hossaindoula
 * Date: 2/22/12
 * Time: 9:25 AM
 * To change this template use File | Settings | File Templates.
 */

public class EncryptionUtils {


    Cipher ecipher;
    Cipher dcipher;

    /**
     * Pad a string with spaces on the right.
     *
     * @param str String to add spaces
     * @param width Width of string after padding
     */
    public static String pad (String str, int width) {
        StringBuilder stringBuffer = new StringBuilder(str);
        int space = width - stringBuffer.length();
        while(space-- > 0) {
            stringBuffer.append(' ');
        }

        return stringBuffer.toString();
    }

    public EncryptionUtils(String passphrase) {
        int keyWidth = passphrase.length();
        if (keyWidth < 24)
            passphrase = pad(passphrase, 24);
        try {
            byte[] rawKey = passphrase.getBytes();
            DESedeKeySpec keySpec = new DESedeKeySpec(rawKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey key = keyFactory.generateSecret(keySpec);

            ecipher = Cipher.getInstance("DESede/ECB/NoPadding");
            dcipher = Cipher.getInstance("DESede/ECB/NoPadding");
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String str) {
        try {
            int byteLength = str.getBytes("UTF8").length;
            if(byteLength % 8 != 0) {
                byteLength = (byteLength / 8 + 1) * 8;
                str = pad(str, byteLength);
            }
            try {
                byte[] utf8 = str.getBytes("UTF8");
                byte[] enc = ecipher.doFinal(utf8);

                return new sun.misc.BASE64Encoder().encode(enc);
            } catch(Exception e) {
                e.printStackTrace();
            }
        } catch(java.io.UnsupportedEncodingException ue) {
            ue.printStackTrace();
        }

        return str;
    }

    public String decrypt(String str) {
        try {
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
            byte[] utf8 = dcipher.doFinal(dec);

            return (new String(utf8, "UTF8")).trim();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return str;
    }
}
