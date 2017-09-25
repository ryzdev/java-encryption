import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

public class AESEncryption {

    private static final String SECRET_KEY_FROM_SECURE_CONFIG = "mySuperSecretKey";
    private byte[] secretKeyBytes = stringToByteArray(SECRET_KEY_FROM_SECURE_CONFIG);
    private static final int IV_LENGTH = 16;
    private static String ALGORITHM = "AES/CBC/PKCS5Padding";

    public String encryptText(String textToEncrypt) throws Exception {
        Key secKey = new SecretKeySpec(secretKeyBytes, "AES");
        IvParameterSpec ivParameterSpec = generateIvParameterSpec();
        Cipher encryptCipher = Cipher.getInstance(ALGORITHM);
        encryptCipher.init(Cipher.ENCRYPT_MODE, secKey, ivParameterSpec);
        byte[] cypherText = encryptCipher.doFinal(stringToByteArray(textToEncrypt));
        return Base64.getEncoder().encodeToString(ivParameterSpec.getIV()) + ":" + Base64.getEncoder().encodeToString(cypherText);
    }

    public String decryptText(String encrypted) throws Exception {
        String[] split = encrypted.split(":");
        byte[] iv = Base64.getDecoder().decode(split[0]);
        byte[] cypherText = Base64.getDecoder().decode(split[1]);
        Key secKey = new SecretKeySpec(secretKeyBytes, "AES");
        Cipher decryptCypher = Cipher.getInstance(ALGORITHM);
        decryptCypher.init(Cipher.DECRYPT_MODE, secKey, new IvParameterSpec(iv));
        byte[] bytePlainText = decryptCypher.doFinal(cypherText);
        return Base64.getEncoder().encodeToString(bytePlainText);
    }

    private byte[] stringToByteArray(String s) {
        return s.getBytes(StandardCharsets.UTF_8);
    }

    private IvParameterSpec generateIvParameterSpec() {
        byte[] iv = new byte[IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return new IvParameterSpec(iv);
    }

}