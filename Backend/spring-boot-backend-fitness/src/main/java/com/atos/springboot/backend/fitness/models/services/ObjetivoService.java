package com.atos.springboot.backend.fitness.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.atos.springboot.backend.fitness.models.dao.InterfaceObjetivoDAO;
import com.atos.springboot.backend.fitness.models.entity.Objetivo;
import com.atos.springboot.backend.fitness.models.entity.Usuario;

@Service
public class ObjetivoService implements InterfaceObjetivoService {
	
	@Autowired
	private InterfaceObjetivoDAO objetivoDao;
	

	@Override
	@Transactional(readOnly = true)
	public Objetivo findById(Long id) {
	
		return objetivoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Objetivo save(Objetivo objetivo) {
		
		return objetivoDao.save(objetivo);
	}

	@Override
	public int pesoIdeal(Long id) {

		return objetivoDao.pesoIdeal(id);
	}

	@Override
	public Objetivo datosObjetivoUser(Long id) {
		
		return objetivoDao.datosObjetivoUser(id);
	}

	@Override
	public double caloriasDiarias(Long id) {
	
		return objetivoDao.caloriasDiarias(id);
		
	}

	@Override
	public void insertarId(int altura, int edad, double nivel, int peso, String sexo, Long usuario) {
		
		objetivoDao.insertarId(altura, edad, nivel, peso, sexo, usuario);
		
	}



}
