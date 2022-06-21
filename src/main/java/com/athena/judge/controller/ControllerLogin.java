package com.athena.judge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class ControllerLogin {
	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
