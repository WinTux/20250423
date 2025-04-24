package com.pepe.principal.Controllers;

import java.util.logging.Logger;

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
	
	private static final Logger logger = Logger.getLogger(HomeController.class.getName());
	
	//@RequestMapping(method=RequestMethod.GET)
	@GetMapping
	public String index() {
		return "home/pagina";
	}
	@PostMapping
	public void apagar() {
		
		logger.severe("Este es un mensaje grave");
		logger.warning("Esta es una simple advertencia");
		logger.info("Se procede a finalizar el servicio (ordenado por el usuario mediante context)");
		logger.config("Mensaje de configurción");
		logger.fine("Mensaje fino de depurción");
		logger.finer("Mensaje más fino");
		logger.finest("EL mensaje más fino de depuración");
		
		SpringApplication.exit(context);
	}
}
