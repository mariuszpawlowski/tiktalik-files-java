package pl.mariuszpawlowski.tiktalikfiles;

import com.amazonaws.services.s3.AmazonS3;
import org.junit.Test;

/**
 * Created by Mariusz.Pawlowski on 2016-08-03.
 */
public class TiktalikTest {


    @Test
    public void testConnect() throws Exception {
        String login = "";
        String key = "";

        TiktalikConnection tiktalikConnection = new TiktalikConnection(login, key);
        AmazonS3 connection = tiktalikConnection.getConnection();
    }
}
