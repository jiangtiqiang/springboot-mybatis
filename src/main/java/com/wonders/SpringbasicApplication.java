package com.wonders;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.wonders.mapper")
@EnableCaching //spring-catche缓存开启
public class SpringbasicApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbasicApplication.class, args);
	}
}
