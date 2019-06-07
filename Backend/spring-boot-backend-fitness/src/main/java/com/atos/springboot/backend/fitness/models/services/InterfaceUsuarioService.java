package com.atos.springboot.backend.fitness.models.services;

import java.util.List;


import com.atos.springboot.backend.fitness.models.entity.Usuario;

public interface InterfaceUsuarioService {

	// Le va permitir al administrador mostrar todos los usuarios de la base de
	// datos.
	public List<Usuario> findAll();

	// Buscar un usuario atraves del id
	public Usuario findById(Long id);

	// Nos va permitir hacer un insert en la base de datos de un nuevo usuario
	public Usuario save(Usuario usuario);

	// Eliminaremos al usuario.
	public void delete(Long id);

	public Usuario findByUsername(String username);



	
}
