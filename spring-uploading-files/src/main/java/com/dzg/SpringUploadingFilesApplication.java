package com.dzg;

import com.dzg.domain.StorageProperties;
import com.dzg.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SpringUploadingFilesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringUploadingFilesApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService){
        return args -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
