package com.neo;

import org.springframework.boot.SpringApplication;

public class TestStartApp {
	public static void main(String[] args) {
		System.out.println("============" + args);
		SpringApplication.run(Application.class, args);
	}
}
