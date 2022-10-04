package com.lastcivilization.usercreatesage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.lastcivilization.usercreatesage.feign")
public class UserCreateSageApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCreateSageApplication.class, args);
	}

}
