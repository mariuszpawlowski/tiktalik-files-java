package pl.mariuszpawlowski.tiktalikfiles;


import com.amazonaws.services.s3.model.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Mariusz.Pawlowski on 2016-08-04.
 */
public class FilesActionsImpl implements FilesActions {

    private static final String SUFFIX = "/";
    private final FilesConnection filesConnection;

    public FilesActionsImpl(FilesConnection filesConnection) {
        this.filesConnection = filesConnection;
    }

    @Override
    public Bucket createBucket(String bucketName) {
       return filesConnection.getConnection().createBucket(bucketName);
    }

    @Override
    public List<Bucket> getBucketList(){
        return filesConnection.getConnection().listBuckets();
    }

    @Override
    public ObjectListing listObjects(String bucketName){
        return filesConnection.getConnection().listObjects(new ListObjectsRequest().withBucketName(bucketName));
    }

    @Override
    public PutObjectResult createFolder(String bucketName, String folderName){

        // create meta-data for your folder and set content-length to 0
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);

        // create empty content
        InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
        // create a PutObjectRequest passing the folder name suffixed by /

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
                folderName + SUFFIX, emptyContent, metadata);
        // send request to S3 to create folder

        return filesConnection.getConnection().putObject(putObjectRequest);
    }

    public void deleteFolder(String bucketName, String folderName){
        List<S3ObjectSummary> fileList = filesConnection.getConnection().listObjects(bucketName, folderName).getObjectSummaries();
        for (S3ObjectSummary file : fileList) {
            filesConnection.getConnection().deleteObject(bucketName, file.getKey());
        }
        filesConnection.getConnection().deleteObject(bucketName, folderName);
    }

    @Override
    public void deleteBucket(String bucketName) {
        filesConnection.getConnection().deleteBucket(bucketName);
    }

    @Override
    public PutObjectResult uploadFile(String bucketName, String key, InputStream fileStream){
        ObjectMetadata metadata = new ObjectMetadata();
        return filesConnection.getConnection().putObject(new PutObjectRequest(bucketName, key,
                fileStream, metadata).withCannedAcl(CannedAccessControlList.AuthenticatedRead));
    }

    @Override
    public PutObjectResult uploadFile(String bucketName, String key, File file){
        ObjectMetadata metadata = new ObjectMetadata();
        return filesConnection.getConnection().putObject(new PutObjectRequest(bucketName, key,
                file).withCannedAcl(CannedAccessControlList.AuthenticatedRead));
    }

    @Override
    public void deleteFile(String bucketName, String keyName){
        filesConnection.getConnection().deleteObject(new DeleteObjectRequest(bucketName, keyName));
    }

    @Override
    public InputStream getFileContent(String bucketName, String key){
        S3Object s3Object = filesConnection.getConnection().getObject(new GetObjectRequest(bucketName, key));
        return s3Object.getObjectContent();
    }

    @Override
    public S3Object getS3Object(String bucketName, String key){
        return filesConnection.getConnection().getObject(new GetObjectRequest(bucketName, key));
    }
}
