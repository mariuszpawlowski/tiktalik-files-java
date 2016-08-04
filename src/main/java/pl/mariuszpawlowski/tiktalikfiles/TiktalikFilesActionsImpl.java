package pl.mariuszpawlowski.tiktalikfiles;


import com.amazonaws.services.s3.model.*;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Mariusz.Pawlowski on 2016-08-04.
 */
public class TiktalikFilesActionsImpl implements TiktalikFilesActions {

    private final TiktalikFilesConnection tiktalikFilesConnection;

    public TiktalikFilesActionsImpl(TiktalikFilesConnection tiktalikFilesConnection) {
        this.tiktalikFilesConnection = tiktalikFilesConnection;
    }

    @Override
    public Bucket createBucket(String bucketName) {
       return tiktalikFilesConnection.getConnection().createBucket(bucketName);
    }

    @Override
    public void deleteBucket(String bucketName) {
        tiktalikFilesConnection.getConnection().deleteBucket(bucketName);
    }

    @Override
    public PutObjectResult uploadFile(String bucketName, String key, InputStream fileStream){
        ObjectMetadata metadata = new ObjectMetadata();
        return tiktalikFilesConnection.getConnection().putObject(new PutObjectRequest(bucketName, key,
                fileStream, metadata).withCannedAcl(CannedAccessControlList.AuthenticatedRead));
    }

    @Override
    public PutObjectResult uploadFile(String bucketName, String key, File file){
        ObjectMetadata metadata = new ObjectMetadata();
        return tiktalikFilesConnection.getConnection().putObject(new PutObjectRequest(bucketName, key,
                file).withCannedAcl(CannedAccessControlList.AuthenticatedRead));
    }

    @Override
    public void deleteFile(String bucketName, String keyName){
        tiktalikFilesConnection.getConnection().deleteObject(new DeleteObjectRequest(bucketName, keyName));
    }

    @Override
    public InputStream getFileContent(String bucketName, String key){
        S3Object s3Object = tiktalikFilesConnection.getConnection().getObject(new GetObjectRequest(bucketName, key));
        return s3Object.getObjectContent();
    }

    @Override
    public S3Object getS3Object(String bucketName, String key){
        return tiktalikFilesConnection.getConnection().getObject(new GetObjectRequest(bucketName, key));
    }
}
