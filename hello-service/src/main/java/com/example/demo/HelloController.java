package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private GreetingService gs;

	public HelloController(GreetingService gs) {
		this.gs = gs;
	}
	
	@GetMapping("/hello")
	public String greeting() {
		return gs.getGreeting();
	}
	
}
