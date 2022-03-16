package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

	@GetMapping("/welcome")
	public String welcome() {
		return "You are welcomed here";
	}
	
	@GetMapping("/")
	public String welcomenew() {
		return "You are welcomed here";
	}
}
