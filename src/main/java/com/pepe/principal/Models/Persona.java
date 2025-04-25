package com.pepe.principal.Models;

public class Persona {
	public int Edad;
	public String Nombre;
	public String Apellido;
	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Persona(int edad, String nombre, String apellido) {
		super();
		Edad = edad;
		Nombre = nombre;
		Apellido = apellido;
	}
	public int getEdad() {
		return Edad;
	}
	public void setEdad(int edad) {
		Edad = edad;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	
}
