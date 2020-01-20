package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;	
import com.fasterxml.jackson.core.JsonParseException;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception, JsonParseException {
		SpringApplication.run(DemoApplication.class, args);
		
		RestClient restClient = new RestClient();
		restClient.requestGetData();
		restClient.requestPostData();
	
	}
}
