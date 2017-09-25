import org.junit.Test;

public class AESEncryptionTest {

    @Test
    public void excrypytsAndDecrypts() throws Exception {
        AESEncryption aesEncryption = new AESEncryption();
        String plainText = "super secrets!";

        String encryptedText = aesEncryption.encryptText(plainText);
        System.out.println(encryptedText);

        String s = aesEncryption.decryptText(encryptedText);
        System.out.println(s);
    }

}