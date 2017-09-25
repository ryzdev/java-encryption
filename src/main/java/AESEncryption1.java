import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;


public class AESEncryption1 {

    private static final String SECRET_KEY = "mySuperSecretKey";
    private static final String ALGO = "AES";
    private byte[] secretKeyBytes = SECRET_KEY.getBytes();


    public void doEncryption(String toEncrypt) throws Exception {
        Key secKey = new SecretKeySpec(secretKeyBytes, ALGO);
        byte[] cipherText = encryptText(toEncrypt, secKey);
        String decryptedText = decryptText(cipherText, secKey);

        System.out.println("Original Text:" + toEncrypt);
        System.out.println("Encrypted Text (Hex Form):"+ DatatypeConverter.printHexBinary(cipherText));
        System.out.println("Descrypted Text:"+decryptedText);
    }

    private static byte[] encryptText(String plainText, Key secKey) throws Exception{
        Cipher aesCipher = Cipher.getInstance(ALGO);
        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
        return aesCipher.doFinal(plainText.getBytes());
    }

    public static String decryptText(byte[] byteCipherText, Key secKey) throws Exception {
        Cipher aesCipher = Cipher.getInstance(ALGO);
        aesCipher.init(Cipher.DECRYPT_MODE, secKey);
        byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
        return new String(bytePlainText);
    }
}