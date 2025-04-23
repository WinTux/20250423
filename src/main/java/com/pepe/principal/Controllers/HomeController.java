package com.pepe.principal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/home") //http://localhost:8080/home
public class HomeController {
	@Autowired
	private ApplicationContext context;
	//@RequestMapping(method=RequestMethod.GET)
	@GetMapping
	public String index() {
		return "home/pagina";
	}
	@PostMapping
	public void apagar() {
		SpringApplication.exit(context);
	}
}
