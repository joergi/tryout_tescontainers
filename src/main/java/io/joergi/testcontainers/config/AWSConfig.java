package io.joergi.testcontainers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AWSConfig {
    
 // TODO use application yml
    AWSCredentials credentials = new BasicAWSCredentials(
            "ENTER_HERE_ACCESSKEY",
            "ENTER_HERE_SECRETKEY");

    @Bean
    public AmazonS3 s3client() {
        return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.US_EAST_1) // US East (N. Virginia)
            .build();
    }
}
