package com.oruina.spring_app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oruina.spring_app.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("") // se accede al metodo por una peticion GET
    public String index() {
		return ViewRouteHelper.INDEX;
	}

}
