package com.example.GoogleOAuthSignin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class GoogleOAuthSigninApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoogleOAuthSigninApplication.class, args);
	}
	@Scheduled(fixedRate = 1000)
	public void display(){
//		System.out.println("This is schedule");
	}

}
