package pl.mariuszpawlowski.tiktalikfiles;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by Mariusz.Pawlowski on 2016-08-03.
 */
public class FilesTest {


    @Test
    public void testConnect() throws Exception {

        FilesConnection tiktalikConnection = new FilesConnection(aWSAccessKeyId, aWSSecretKey);
        FilesActions filesActions = new FilesActionsImpl(tiktalikConnection );

        // filesActions.createFolder("psychoffice-test", "client/wizyta");

        ObjectListing listObjects = filesActions.listObjects("psychoffice-test");
        List<S3ObjectSummary> summaries = listObjects.getObjectSummaries();

        File file = new File("pom.xml");

        //filesActions.uploadFile("psychoffice-test", "client/wizyta/", file);

        System.out.println(summaries);

    }
}
