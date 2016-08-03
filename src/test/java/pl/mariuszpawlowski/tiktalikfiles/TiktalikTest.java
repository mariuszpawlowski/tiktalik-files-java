package pl.mariuszpawlowski.tiktalikfiles;

import org.junit.Test;

/**
 * Created by Mariusz.Pawlowski on 2016-08-03.
 */
public class TiktalikTest {


    @Test
    public void testConnect() throws Exception {
        String login = "";
        String key = "";

        Tiktalik tiktalik = new Tiktalik();
        tiktalik.getConnection(login, key);

    }
}
