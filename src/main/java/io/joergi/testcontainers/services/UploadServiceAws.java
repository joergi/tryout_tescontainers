package io.joergi.testcontainers.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UploadServiceAws {

    public static final String S3_BUCKET = "io.joergi.testbucket";

    private AmazonS3 s3Client;
    
    public UploadServiceAws(AmazonS3 amazonS3) {
        this.s3Client = amazonS3;
    }
    
    public String uploadFile(File file) throws FileNotFoundException, IOException {

        String filename = "test-" + new Random().nextLong() + ".jpg";
        
        PutObjectResult result = s3Client.putObject(S3_BUCKET, filename, file);
        
        //TODO compare md5 checksum
        log.info("hash of uploaded file {}", result.getContentMd5());;
        deleteFile(filename);
        return result.getContentMd5();
    }

    // for later use 
    private void deleteFile(String filename) {
        s3Client.deleteObject(S3_BUCKET, filename);
        log.info("file {} is deleted", filename);
    }
}
