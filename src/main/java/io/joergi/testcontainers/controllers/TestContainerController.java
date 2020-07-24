package io.joergi.testcontainers.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.joergi.testcontainers.services.UploadServiceAws;
import io.joergi.testcontainers.services.UploadServiceGcp;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class TestContainerController {

    private UploadServiceAws uploadServiceAws;
    private UploadServiceGcp uploadServiceGcp;

    @GetMapping(value = "/aws")
    public void testAws() throws FileNotFoundException, IOException{
        File file = new File(this.getClass().getClassLoader().getResource("github.jpg").getPath());
        uploadServiceAws.uploadFile(file);
        
    }
    

    @GetMapping(value = "/gcp")
    public void testGcp() throws FileNotFoundException, IOException{
        File file = new File(this.getClass().getClassLoader().getResource("github.jpg").getPath());
        uploadServiceGcp.uploadFile(file);
        
    }
}
