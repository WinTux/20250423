package com.pepe.principal.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PrimerControlador {
	@Value("${valor.secreto}")
	private String val01;
	@Value("${edad}")
	private int val02;
	@Value("${otro.valor}")
	private String val03;
	
	// Endpoint
	@RequestMapping("/") // http://localhost:8080/
	@ResponseBody
	public String Saludo() {
		return "Hola a todos";
	}
	@RequestMapping("/props") // http://localhost:8080/props
	@ResponseBody
	public String Propiedades() {
		return val01+" "+val02+" "+val03;
		
	}
}
