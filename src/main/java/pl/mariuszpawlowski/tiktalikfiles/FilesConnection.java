package pl.mariuszpawlowski.tiktalikfiles;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

/**
 * Created by Mariusz.Pawlowski on 2016-08-03.
 */
public class FilesConnection {

    private AmazonS3 connection;

    public FilesConnection(String login, String key) {
        AWSCredentials credentials = new BasicAWSCredentials(login, key);
        connection = new AmazonS3Client(credentials);
    }

    public AmazonS3 getConnection() {
        return connection;
    }
}
