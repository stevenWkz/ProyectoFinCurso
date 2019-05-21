package com.atos.springboot.backend.fitness.models.services;


import com.atos.springboot.backend.fitness.models.entity.Objetivo;

public interface InterfaceObjetivoService {
	
	public Objetivo findById(Long id);
	
	public Objetivo save(Objetivo objetivo);

}
