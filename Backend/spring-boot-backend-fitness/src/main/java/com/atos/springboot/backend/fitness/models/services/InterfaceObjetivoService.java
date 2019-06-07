package com.atos.springboot.backend.fitness.models.services;


import com.atos.springboot.backend.fitness.models.entity.Objetivo;
import com.atos.springboot.backend.fitness.models.entity.Usuario;

public interface InterfaceObjetivoService {
	
	public Objetivo findById(Long id);
	
	public Objetivo save(Objetivo objetivo);
	
	public int pesoIdeal(Long id);
	
	public Objetivo datosObjetivoUser(Long id);
	
	public double caloriasDiarias(Long id);
	
	public void insertarId(int altura, int edad, double nivel, int peso, String sexo, Long usuario);
	
	

}
