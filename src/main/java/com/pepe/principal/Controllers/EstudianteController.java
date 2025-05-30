package com.pepe.principal.Controllers;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.pepe.principal.Exceptions.EstudianteNoEncontradoException;
import com.pepe.principal.Models.Estudiante;

import jakarta.servlet.http.HttpServletResponse;
@Controller
public class EstudianteController {
	private static Map<String,Estudiante> 
		estudiantes = new HashMap<>();
	@Autowired
	ObjectMapper objectMapper;
	static {
		Estudiante e1 = new Estudiante(1,"Pepe","Perales");
		Estudiante e2 = new Estudiante(2,"Ana","Rocha");
		Estudiante e3 = new Estudiante(3,"Samantha","Roca");
		estudiantes.put("1", e1);
		estudiantes.put("2", e2);
		estudiantes.put("3", e3);
	}
	// Para rescatar la lista completa
	@GetMapping("/estudiante") // http://localhost:5000/estudiante [GET]
	public ResponseEntity<Object> getEstudiantes(){
		return new ResponseEntity<>(estudiantes.values(),HttpStatus.OK);
	}
	
	// Para agregar un nuevo registro
	@PostMapping("/estudiante")
	public ResponseEntity<Object> nuevoEstudiante(@RequestBody Estudiante est){
		estudiantes.put(est.getId()+"", est);
		URI ubicacionRecurso = 
				ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(est.getId())
				.toUri();
		return ResponseEntity.created(ubicacionRecurso).build();
	}
	
	// Para modificar un recurso
	@PutMapping("/estudiante/{id}")
	public ResponseEntity<Object> modificarEstudiante(@PathVariable("id") int id, @RequestBody Estudiante est){
		if(!estudiantes.containsKey(id+""))
			throw new EstudianteNoEncontradoException();
		estudiantes.remove(id+"");
		estudiantes.put(id+"", est);
		return ResponseEntity.noContent().build();
	}
	// Para eliminar un recurso
	@DeleteMapping("/estudiante/{id}")
	public ResponseEntity<Object> eliminarEstudiante(@PathVariable("id") int id){
		if(!estudiantes.containsKey(id+""))
			return ResponseEntity.notFound().build();
		estudiantes.remove(id+"");
		return ResponseEntity.noContent().build();
	}
	@PatchMapping("/estudiante/{id}")
	public ResponseEntity<Object> editarConPatch(
			@PathVariable("id") int id, 
			@RequestBody Map<String,Object> atributosModificados){
		if(!estudiantes.containsKey(id+""))
			return ResponseEntity.notFound().build();
		Estudiante estOriginal = estudiantes.get(id+"");
		atributosModificados.forEach((atributo,nuevoValor)->{
			Field campo =ReflectionUtils
					.findField(Estudiante.class, atributo);
			if(campo != null) {
				campo.setAccessible(true);
				ReflectionUtils.setField(campo, estOriginal, nuevoValor);
			}
		});
		estudiantes.remove(id+"");
		estudiantes.put(id+"", estOriginal);
		return ResponseEntity.noContent().build();
	}
	
	// Implementar el JSON Patch
	@PatchMapping(path="/estudiante/{id}",consumes="application/json-patch+json")
	public ResponseEntity<Object> editarConJsonPatch(@PathVariable("id") int id, @RequestBody JsonPatch atributosModificados){
		// Verificar si existe
		if(!estudiantes.containsKey(id+""))
			return ResponseEntity.notFound().build();
		// Mapear
		try {
			Estudiante estOriginal = estudiantes.get(id+"");
			JsonNode patcheado = atributosModificados
					.apply(
							objectMapper
							.convertValue(estOriginal, JsonNode.class)
					);
			Estudiante estudianteActualizado = 
					objectMapper.treeToValue(patcheado, Estudiante.class);
			// Actualizar
			estudiantes.remove(id+"");
			estudiantes.put(id+"", estudianteActualizado);
			// Responder
			return ResponseEntity.noContent().build();
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	@GetMapping("/estudiante/status")
	public ResponseEntity<String> encabezaosPersonalizados(){
		HttpHeaders headers = new HttpHeaders();
		headers.add("token", "ADFSAJ543SFfdsf55");
		headers.add("edad", "55");
		headers.add("ClientID", "id013");
		return new ResponseEntity<>("Respuesta con cabeceras propias.",headers, HttpStatus.OK);
	}
	
	// La vieja escuela: la forma más cruda
	// Jakarta -> Servlets
	@GetMapping("/estudiante/pruebacruda")
	public void ejemploCrudo(HttpServletResponse response) throws IOException{
		response.setHeader("Codigo", "A2343fdDfgd4");
		response.getWriter().print("RESPUESTA LISTA");
	}
}
