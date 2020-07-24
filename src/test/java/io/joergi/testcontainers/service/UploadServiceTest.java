package io.joergi.testcontainers.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.localstack.LocalStackContainer.Service;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import io.joergi.testcontainers.services.UploadServiceAws;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Testcontainers
class UploadServiceTest {

    @Container
    public static LocalStackContainer localstack = new LocalStackContainer().withServices(Service.S3);

    private static AmazonS3 s3; 
    
    @BeforeAll
    static void setup() {
        // TODO change to sdk v2
        // AWS SDK v1
       s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(localstack.getEndpointConfiguration(Service.S3))
                .withCredentials(localstack.getDefaultCredentialsProvider())
                .build();
       s3.createBucket(UploadServiceAws.S3_BUCKET);
    }

    @Test
    void test() throws FileNotFoundException, IOException {

        File file = new File(this
                .getClass()
                .getClassLoader()
                .getResource("github.jpg")
                .getPath());
                
        log.info("filesize {}", file.length());
    
        UploadServiceAws uploadService = new UploadServiceAws(s3);
        
        assertEquals("1H+unZ3mMYHFDS8MZmI4MA==", 
                uploadService
                .uploadFile(file));
    }

}
