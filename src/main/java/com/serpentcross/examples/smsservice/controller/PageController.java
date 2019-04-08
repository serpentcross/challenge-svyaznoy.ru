package com.serpentcross.examples.smsservice.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tst")
public class PageController {
	@GetMapping
	public String loadMainPage() {
		return "smspanel";
	}
}