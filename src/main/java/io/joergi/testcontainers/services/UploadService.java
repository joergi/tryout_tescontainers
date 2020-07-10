package io.joergi.testcontainers.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UploadService {

    private static final String S3_BUCKET = "io.joergi.testbucket";

    // TODO use application yml
    AWSCredentials credentials = new BasicAWSCredentials(
            "ENTER_HERE_ACCESSKEY",
            "ENTER_HERE_SECRETKEY");

    AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.US_EAST_1) // US East (N. Virginia)
            .build();

    public void uploadFile(File file) throws FileNotFoundException, IOException {
        log.info(file.getName() + " - " + file.length());
        String filename = "test" + new Random().nextLong();
        
        
        PutObjectResult result = s3client.putObject(S3_BUCKET, filename, file);
        
        //TODO compare md5 checksum
        log.info("hash of uploaded file {}", result.getContentMd5());;
        
        deleteFile(filename);
    }

    private void deleteFile(String filename) {
        s3client.deleteObject(S3_BUCKET, filename);
        log.info("file {} is deleted", filename);
    }
}
