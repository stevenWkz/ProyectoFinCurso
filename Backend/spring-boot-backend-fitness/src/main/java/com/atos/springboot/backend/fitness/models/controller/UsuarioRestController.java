package com.atos.springboot.backend.fitness.models.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.atos.springboot.backend.fitness.models.entity.Role;
import com.atos.springboot.backend.fitness.models.entity.Usuario;
import com.atos.springboot.backend.fitness.models.services.InterfaceUsuarioService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/users")
public class UsuarioRestController {

	@Autowired
	private InterfaceUsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// Mostramos todos los usuarios
	@Secured("ROLE_ADMIN")
	@GetMapping("/usuarios")
	public List<Usuario> index() {

		return usuarioService.findAll();

	}
		
	

	@GetMapping("/datosUsuario/{username}")
	public ResponseEntity<?> devulveDatos(@PathVariable String username) {

		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();

		try {
			usuario = usuarioService.findByUsername(username);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (usuario == null) {
			response.put("mensaje",
					"El cliente ID: ".concat(username.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@GetMapping("/usuario/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();

		try {
			usuario = usuarioService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (usuario == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	
	


	// Creamos un usuario nuevo
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result) {

		Usuario usuarioNuevo = null;
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

			// Encriptamos la password en la base de datos para que no este en texto plano.
			String encriptada = passwordEncoder.encode(usuario.getPassword());

			usuario.setPassword(encriptada);

			usuarioNuevo = usuarioService.save(usuario);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El cliente ha sido creado con éxito");
		response.put("cliente", usuarioNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// Actualizamos el usuario
	@Secured("ROLE_ADMIN")
	@PutMapping("/usuarios/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {

		Usuario clienteActual = usuarioService.findById(id);
		Usuario clienteUpdate = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo'" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (usuario == null) {

			response.put("mensaje", "Error: no se puede editar, el cliente ID: "
					.concat(id.toString().concat(" No existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			clienteActual.setApellido(usuario.getApellido());
			clienteActual.setNombre(usuario.getNombre());
			clienteActual.setEmail(usuario.getEmail());
			clienteActual.setDireccion(usuario.getDireccion());

			clienteUpdate = usuarioService.save(clienteActual);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente ha sido actualizado con éxito");
		response.put("cliente", clienteUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// Eliminamos al usuario con la id
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/usuarios/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {

			usuarioService.delete(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar el usuario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El usuario eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
