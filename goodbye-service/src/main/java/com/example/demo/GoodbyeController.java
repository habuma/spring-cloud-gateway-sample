package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodbyeController {

	private GoodbyeService gs;

	public GoodbyeController(GoodbyeService gs) {
		this.gs = gs;
	}
	
	@GetMapping("/bye")
	public String bye() {
		return gs.getMessage();
	}
	
}
