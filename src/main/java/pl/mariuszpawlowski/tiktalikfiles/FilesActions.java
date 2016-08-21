package pl.mariuszpawlowski.tiktalikfiles;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Mariusz.Pawlowski on 2016-08-04.
 */
public interface FilesActions {

    Bucket createBucket(String bucketName);

    List<Bucket> getBucketList();

    ObjectListing listObjects(String bucketName);

    PutObjectResult createFolder(String bucketName, String folderName);

    void deleteBucket(String bucketName);

    PutObjectResult uploadFile(String bucketName, String key, InputStream fileStream);

    PutObjectResult uploadFile(String bucketName, String key, File file);

    void deleteFile(String bucketName, String keyName);

    InputStream getFileContent(String bucketName, String key);

    S3Object getS3Object(String bucketName, String key);
}
