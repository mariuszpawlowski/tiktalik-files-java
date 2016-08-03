package pl.mariuszpawlowski.tiktalikfiles;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

/**
 * Created by Mariusz.Pawlowski on 2016-08-03.
 */
public class Tiktalik {

    public AmazonS3 getConnection(String login, String key){
        AWSCredentials credentials = new BasicAWSCredentials(login, key);
        ClientConfiguration clientConfig = new ClientConfiguration();
        AmazonS3 connection = new AmazonS3Client(credentials, clientConfig);
        connection.setEndpoint("sds.tiktalik.com");
        return connection;
    }

}
