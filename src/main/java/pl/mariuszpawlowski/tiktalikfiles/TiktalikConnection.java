package pl.mariuszpawlowski.tiktalikfiles;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

/**
 * Created by Mariusz.Pawlowski on 2016-08-03.
 */
public class TiktalikConnection {

    private AmazonS3 connection;

    public TiktalikConnection(String login, String key) {
        AWSCredentials credentials = new BasicAWSCredentials(login, key);
        ClientConfiguration clientConfig = new ClientConfiguration();
        connection = new AmazonS3Client(credentials, clientConfig);
        connection.setEndpoint("sds.tiktalik.com");
    }

    public AmazonS3 getConnection() {
        return connection;
    }
}
