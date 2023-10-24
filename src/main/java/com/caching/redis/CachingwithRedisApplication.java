package com.caching.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CachingwithRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachingwithRedisApplication.class, args);
	}

}
