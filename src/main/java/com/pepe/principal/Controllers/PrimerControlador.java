package com.pepe.principal.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PrimerControlador {
	
	// Endpoint
	@RequestMapping("/") // http://localhost:8080/
	@ResponseBody
	public String Saludo() {
		return "Hola a todos";
	}
}
