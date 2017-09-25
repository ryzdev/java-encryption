import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ryz on 25/09/2017.
 */
public class SecuredGCMUsageTest {

    private SecuredGCMUsage encryptor = new SecuredGCMUsage();

    @Test
    public void encryptsString() throws Exception {
        encryptor.doEncryption("hi!");

    }
}