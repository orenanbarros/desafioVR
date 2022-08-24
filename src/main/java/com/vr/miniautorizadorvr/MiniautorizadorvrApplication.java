package com.vr.miniautorizadorvr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class MiniautorizadorvrApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniautorizadorvrApplication.class, args);
	}

}
