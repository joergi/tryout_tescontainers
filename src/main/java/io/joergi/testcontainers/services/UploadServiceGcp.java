package io.joergi.testcontainers.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.google.auth.Credentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import io.joergi.testcontainers.config.GCPConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UploadServiceGcp {
    
    private Credentials credentials;
    
    private Storage storage;
    
    private static final String GCP_BUCKET = "testbucket_joergi";

    private static final String GCP_PROJCT_ID = "inbound-domain-284307";

    public UploadServiceGcp(GCPConfig gcpConfig) throws FileNotFoundException, IOException {
        this.credentials = gcpConfig.createCredentials();
        this.storage = StorageOptions
                .newBuilder()
                .setCredentials(credentials)
                .setProjectId(GCP_PROJCT_ID)
                .build()
                .getService();
    }
    
    public void uploadFile(File file) throws IOException {

        String filename = "test" + new Random().nextLong() + ".jpg";

        String filePath = file.getAbsolutePath();
        
        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(GCP_BUCKET, filename)).build();
        
        Blob blob = storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));
        
        log.info("file: {}  with filename {} with blob {} has been uploaded", filePath, filename, blob.toString() ); 
        
        delete(blob.getBlobId(), filename);
    }
    
    private void delete(BlobId blobid, String filename) {
        log.info("filename {}  was deletedt {}", filename, storage.delete(blobid));

    }
}
