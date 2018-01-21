package org.elsys.remote_control_car.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {
		"org.elsys.remote_control_car.controller",
		"org.elsys.remote_control_car.service",
		"org.elsys.remote_control_car.repository",
		"org.elsys.remote_control_car.model",
		"org.elsys.remote_control_car.service"
		})
public class RemoteControlCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemoteControlCarApplication.class, args);
	}
}
