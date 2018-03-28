package com.css.nsfw.dp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class NsfwdpzsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NsfwdpzsApplication.class, args);

	}
}
