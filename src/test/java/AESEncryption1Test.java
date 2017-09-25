import org.junit.Test;

public class AESEncryption1Test {

    @Test
    public void encryptsText() throws Exception {
        AESEncryption1 aesEncryption = new AESEncryption1();
        aesEncryption.doEncryption("secret info aklsjhdfoiahsndoifhnasodhfoinasdnoihaosdnfsha");
    }
}