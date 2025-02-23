package org.magnum.mobilecloud.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@SpringBootApplication
@EnableResourceServer
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

