package com.atos.springboot.backend.fitness.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.atos.springboot.backend.fitness.models.entity.Objetivo;

public interface InterfaceObjetivoDAO extends CrudRepository<Objetivo, Long> {

}
