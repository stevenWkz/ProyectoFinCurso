package com.atos.springboot.backend.fitness.models.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.atos.springboot.backend.fitness.models.entity.Alimento;


public interface InterfaceAlimentoService {
	
	//Permite al administrador mostrar todos los alimentos de la base de datos.
	public List<Alimento> findAll();
	
	//Permite crear un alimento
	public Alimento save(Alimento alimento);
	
	//Permite encontrar un alimento por id
	public Alimento findById(Long id);
	
	
	//Eliminaremos al alimento
	public void delete(Long id);
	
	
	public List<Alimento> getAlimentoUsuarios(@Param("id_usuario") Long id_usuario);
	
	

}
