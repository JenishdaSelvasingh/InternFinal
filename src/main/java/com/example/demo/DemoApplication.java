package com.example.demo;

import com.example.demo.Entity.AdminEntity;
import okhttp3.OkHttpClient;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
		scanBasePackages = {"com.example.demo"}
)
public class DemoApplication {
	public DemoApplication() {
	}

	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}

	@Bean
	public OkHttpClient okHttpClient() {

		return (new OkHttpClient.Builder()).build();
	}

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

}
