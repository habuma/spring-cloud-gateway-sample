package com.example.demo;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class GreetingServiceImpl implements GreetingService {

	@Override
	@HystrixCommand(fallbackMethod="getFallbackGreeting")
	public String getGreeting() {
		try {
		Thread.sleep(600);
		} catch (Exception e) {}
		
//		if (Math.random() > 0.1) {
//			throw new RuntimeException();
//		}
		return "Hello";
	}
	
	String getFallbackGreeting() {
		return "Hiya";
	}
	
}
