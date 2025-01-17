package com.atos.springboot.backend.fitness.models.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atos.springboot.backend.fitness.models.entity.Objetivo;

import com.atos.springboot.backend.fitness.models.services.InterfaceObjetivoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/objetivos")
public class ObjetivoRestController {

	@Autowired
	private InterfaceObjetivoService objetivoService;

	@GetMapping("/pesoIdealUser/{id}")
	public int sumaAlimentos(@PathVariable Long id) {

		return objetivoService.pesoIdeal(id);
	}

	@GetMapping("/objetivoDatosUser/{id}")
	public Objetivo datosObjetivoUser(@PathVariable Long id) {

		return objetivoService.datosObjetivoUser(id);
	}

	@GetMapping("/caloriasDiarias/{id}")
	public double caloriasDiarias(@PathVariable Long id) {

		return objetivoService.caloriasDiarias(id);
	}

	@PostMapping("/insertarId/{altura}/{edad}/{nivel}/{peso}/{sexo}/{usuario}")
	public void insertarAlimentosUser(@Valid @PathVariable int altura, @PathVariable int edad,
			@PathVariable double nivel, @PathVariable int peso, @PathVariable String sexo, @PathVariable Long usuario) {

		objetivoService.insertarId(altura, edad, nivel, peso, sexo, usuario);
	}
	
	
	//Servicio que muestra el objetivo del usuario
	@GetMapping("/objetivoShow/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Objetivo objetivo = null;
		Map<String, Object> response = new HashMap<>();

		try {
			objetivo = objetivoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (objetivo == null) {
			response.put("mensaje", "El Alimento ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Objetivo>(objetivo, HttpStatus.OK);
	}

	@PostMapping("/registerObjetivo")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Objetivo objetivo, BindingResult result) {

		Objetivo objetivoNuevo = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = new ArrayList<String>();

			for (FieldError err : result.getFieldErrors()) {

				errors.add("El campo '" + err.getField() + "' " + err.getDefaultMessage());
			}

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		

		try {

			objetivoNuevo = objetivoService.save(objetivo);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El Objetivo ha sido creado con éxito");
		response.put("objetivo", objetivoNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// Actualizamos el objetivo
	@PutMapping("/objetivo/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Objetivo objetivo, BindingResult result,
			@PathVariable Long id) {

		Objetivo objetivoActual = objetivoService.findById(id);
		Objetivo objetivoUpdate = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo'" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (objetivo == null) {

			response.put("mensaje", "Error: no se puede editar, el cliente ID: "
					.concat(id.toString().concat(" No existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			objetivoActual.setPeso(objetivo.getPeso());
			objetivoActual.setAltura(objetivo.getAltura());
			objetivoActual.setEdad(objetivo.getEdad());
			objetivoActual.setNivel(objetivo.getNivel());

			objetivoUpdate = objetivoService.save(objetivoActual);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente ha sido actualizado con éxito");
		response.put("cliente", objetivoUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

}
