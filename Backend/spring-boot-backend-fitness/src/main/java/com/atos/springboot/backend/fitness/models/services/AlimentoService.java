package com.atos.springboot.backend.fitness.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atos.springboot.backend.fitness.models.dao.InterfaceAlimentoDAO;
import com.atos.springboot.backend.fitness.models.entity.Alimento;

@Service
public class AlimentoService implements InterfaceAlimentoService {
	
	@Autowired
	private InterfaceAlimentoDAO alimentoDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Alimento> findAll() {
		
		return (List<Alimento>) alimentoDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Alimento findById(Long id) {
		return alimentoDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Alimento save(Alimento alimento) {
		return alimentoDAO.save(alimento);
	}


	@Override
	@Transactional
	public void delete(Long id) {
		alimentoDAO.deleteById(id);
	}

	@Override
	public List<Alimento> getAlimentoUsuarios(Long id) {
		return alimentoDAO.getAlimentoUsuarios(id);
	}

	@Override
	public int sumaAlimentos(Long id) {

		return alimentoDAO.sumaAlimentos(id);
	}

	@Override
	public void insertarAlimentoUser(Long id_usuario, Long id_alimento) {
		
		 alimentoDAO.insertarAlimentoUser(id_usuario, id_alimento);
		 
	}

}
