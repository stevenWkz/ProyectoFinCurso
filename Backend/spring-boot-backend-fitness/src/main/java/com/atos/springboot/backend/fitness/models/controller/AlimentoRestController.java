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
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atos.springboot.backend.fitness.models.entity.Alimento;

import com.atos.springboot.backend.fitness.models.services.InterfaceAlimentoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/alimentos")
public class AlimentoRestController {

	@Autowired
	private InterfaceAlimentoService alimentoService;

	// Mostramos todos los alimentos
	
	@GetMapping("/allAlimentos")
	public List<Alimento> showAllAlimentos() {

		return alimentoService.findAll();

	}

	
	 //Servicio para devolver los Alimentos del usuario
	@GetMapping("/alimentosUsuario/{id}")
	public List<Alimento> getAlimentosUser(@PathVariable Long id) {

		return alimentoService.getAlimentoUsuarios(id);

	}
	

	@PostMapping("/insertarAlimentos/{id_usuario}/{id_alimento}")
	public void insertarAlimentosUser(@Valid @PathVariable Long id_usuario, @PathVariable Long id_alimento ) {
		
		 alimentoService.insertarAlimentoUser(id_usuario, id_alimento);
	}
	
	
	//Servicio que devuelve la suma de los alimentos por cada usuario
	@GetMapping("/alimentosSUM/{id}")
	public int sumaAlimentos(@PathVariable Long id) {
		
		return alimentoService.sumaAlimentos(id);
	}
	
	
	
	
	
	@GetMapping("/alimento/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Alimento alimento = null;
		Map<String, Object> response = new HashMap<>();

		try {
			alimento = alimentoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (alimento == null) {
			response.put("mensaje", "El Alimento ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Alimento>(alimento, HttpStatus.OK);
	}

	// Creamos un usuario nuevo

	@PostMapping("/registerAlimento")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Alimento alimento, BindingResult result) {

		Alimento alimentoNuevo = null;
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

			alimentoNuevo = alimentoService.save(alimento);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El cliente ha sido creado con éxito");
		response.put("alimento", alimentoNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// Actualizamos el usuario

	@PutMapping("/alimento/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Alimento alimento, BindingResult result,
			@PathVariable Long id) {

		Alimento alimentoActual = alimentoService.findById(id);
		Alimento alimentoUpdate = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo'" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (alimento == null) {

			response.put("mensaje", "Error: no se puede editar, el cliente ID: "
					.concat(id.toString().concat(" No existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			alimentoActual.setNombre(alimento.getNombre());
			alimentoActual.setProteinas(alimento.getProteinas());
			alimentoActual.setCarbohidratos(alimento.getCarbohidratos());
			alimentoActual.setGrasas(alimento.getGrasas());
			alimentoActual.setPeso(alimento.getPeso());
			alimentoActual.setValorEnergetico(alimento.getValorEnergetico());

			alimentoUpdate = alimentoService.save(alimentoActual);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El alimento ha sido actualizado con éxito");
		response.put("cliente", alimentoUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// Eliminamos al alimento con la id
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/alimento/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {

			alimentoService.delete(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar el usuario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El alimento eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
