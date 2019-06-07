package com.atos.springboot.backend.fitness.models.services;

import java.util.List;


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
	
	//Muestra los alimentos de los usuarios que han ingerido
	public List<Alimento> getAlimentoUsuarios(Long id);
	
	//Suma el total de calorias de todos los alimentos
	public int sumaAlimentos(Long id);
		
	//Agrega el alimento que el usuario inserta en su perfil
	public void insertarAlimentoUser(Long id_usuario, Long id_alimento);
	
	

}
