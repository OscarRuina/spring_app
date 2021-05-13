package com.oruina.spring_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.oruina.spring_app.helper.ViewRouteHelper;

@Controller
public class LoginController {
	
	@GetMapping({"/","/login"})
	public String login() {
		return ViewRouteHelper.LOGIN; //pagina de inicio login
	}
	
	@GetMapping("/home")
	public String home() {
		return ViewRouteHelper.HOME;
	}
	
	@GetMapping("/admin")
	public String admin() {
		return ViewRouteHelper.ADMIN;
	}
	
	@GetMapping("/user")
	public String user() {
		return ViewRouteHelper.USER;
	}

}
