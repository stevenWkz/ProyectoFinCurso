package com.atos.springboot.backend.fitness.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.atos.springboot.backend.fitness.models.dao.InterfaceObjetivoDAO;
import com.atos.springboot.backend.fitness.models.entity.Objetivo;

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

}
