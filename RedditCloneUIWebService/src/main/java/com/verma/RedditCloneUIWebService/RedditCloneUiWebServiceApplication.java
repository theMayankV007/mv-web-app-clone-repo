package com.verma.RedditCloneUIWebService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RedditCloneUiWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditCloneUiWebServiceApplication.class, args);
	}

}
