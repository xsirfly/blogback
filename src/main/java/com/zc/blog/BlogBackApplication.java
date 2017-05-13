package com.zc.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zc.blog.mapper")
public class BlogBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogBackApplication.class, args);
	}
}
