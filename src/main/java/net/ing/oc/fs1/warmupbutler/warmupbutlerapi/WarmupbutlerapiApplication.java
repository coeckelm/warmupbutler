package net.ing.oc.fs1.warmupbutler.warmupbutlerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class WarmupbutlerapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarmupbutlerapiApplication.class, args);
	}

	@RequestMapping("/")
	String index() {
		return "index";
	}
}
