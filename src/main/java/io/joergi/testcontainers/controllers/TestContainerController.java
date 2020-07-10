package io.joergi.testcontainers.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.joergi.testcontainers.services.UploadService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class TestContainerController {

    private UploadService uploadService;

    @GetMapping(value = "/")
    public void test() throws FileNotFoundException, IOException{
        File file = new File(this.getClass().getClassLoader().getResource("github.jpg").getPath());
        uploadService.uploadFile(file);
        
    }
}
