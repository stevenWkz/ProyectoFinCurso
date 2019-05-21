package com.atos.springboot.backend.fitness.models.dao;

import org.springframework.data.repository.CrudRepository;



import com.atos.springboot.backend.fitness.models.entity.Usuario;

public interface InterfaceUsuarioDAO extends CrudRepository<Usuario, Long> {
	
	public Usuario findByUsername(String username);
	


}
