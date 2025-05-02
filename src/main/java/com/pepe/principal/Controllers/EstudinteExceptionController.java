package com.pepe.principal.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pepe.principal.Exceptions.EstudianteNoEncontradoException;

@ControllerAdvice
public class EstudinteExceptionController {
	// Para cada tipo de excepcion
	@ExceptionHandler(EstudianteNoEncontradoException.class)
	public ResponseEntity<Object> unaExcepcion(EstudianteNoEncontradoException ex){
		return new ResponseEntity<>("No se encontró al estudiante!!", HttpStatus.NOT_FOUND);
	}
}
