package org.elsys.remote_control_car.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.elsys.remote_control_car.controller")
public class RemoteControlCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemoteControlCarApplication.class, args);
	}
}
