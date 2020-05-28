package listener;

import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

// a relatively insecure usage of AES
public class InsecureAES
{
    private byte[] key;
    private static final String ALGORITHM = "AES";

    public InsecureAES(byte[] key)
    {
        this.key = key;
    }

    // encrypt a string
    public String encrypt(String plainText) throws Exception
    {
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        Base64.Encoder encoder = Base64.getEncoder();

        return encoder.encodeToString(cipher.doFinal(plainText.getBytes()));
    }

    // decrypt a string
    public String decrypt(String cipherText) throws Exception
    {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] cipherBytes = decoder.decode(cipherText);
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        return new String(cipher.doFinal(cipherBytes));
    }
}
