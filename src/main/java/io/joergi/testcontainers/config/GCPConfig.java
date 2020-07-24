package io.joergi.testcontainers.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;

@Configuration
public class GCPConfig {

    String filePathToGcpCredentials = "/home/joergi/Downloads/inbound-domain-284307-54fbf52904c5.json";
    
    @Bean
    public Credentials createCredentials() throws FileNotFoundException, IOException {
        return GoogleCredentials.fromStream(new FileInputStream(filePathToGcpCredentials));
    }
          
}
