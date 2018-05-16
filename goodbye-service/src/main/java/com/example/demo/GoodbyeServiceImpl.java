package com.example.demo;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class GoodbyeServiceImpl implements GoodbyeService {

	@Override
	@HystrixCommand(fallbackMethod="getDefaultMessage")
	public String getMessage() {
		if (Math.random() > 0.5) {
			throw new RuntimeException();
		}
		return "Later";
	}
	
	String getDefaultMessage() {
		return "Goodbye";
	}
	
}
