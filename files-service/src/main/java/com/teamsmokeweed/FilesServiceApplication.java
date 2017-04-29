package com.teamsmokeweed;

import com.teamsmokeweed.files.FileSystemStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(FileSystemStorageService.class)
public class FilesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilesServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner init(FileSystemStorageService fileSystemStorageService) {
		return (args) -> {
//			storageService.deleteAll();
//			storageService.init();
		};
	}
}
