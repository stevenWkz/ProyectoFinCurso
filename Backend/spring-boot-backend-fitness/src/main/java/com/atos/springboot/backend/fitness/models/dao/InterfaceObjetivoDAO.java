package com.atos.springboot.backend.fitness.models.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.atos.springboot.backend.fitness.models.entity.Objetivo;


public interface InterfaceObjetivoDAO extends CrudRepository<Objetivo, Long> {

	
	//Metodo que permite calcular el peso ideal del usuario, se pasa por parametro la id del usuario.
	@Query(value = "select DISTINCT o.altura-100 from objetivos o, usuarios u where o.usuario = :id", nativeQuery = true)
	public int pesoIdeal(Long id); 
	
	
	//Metodo que muestra el objetivo del usuario, se pasa por parametro la id.
	@Query(value = "select * from objetivos o where o.usuario = :id", nativeQuery = true)
	public Objetivo datosObjetivoUser(Long id);
	
	
	//Metodo, calcula las calorias diarias, segun los datos fisicos del usuario
	@Query(value = "select ROUND(((10*o.peso) + (6.25*o.altura) - (5*o.edad)+5)*o.nivel) from objetivos o where o.usuario = :id", nativeQuery = true)
	public double caloriasDiarias(Long id);
	
	
	//Metodo que nos permite relacionar un objetivo con el usuario.
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO `objetivos` (altura, edad, nivel, peso, sexo, usuario) VALUES (:altura, :edad, :nivel, :peso, :sexo, :usuario)", nativeQuery = true)
	public void insertarId(int altura, int edad, double nivel, int peso, String sexo, Long usuario);
	
	
}
