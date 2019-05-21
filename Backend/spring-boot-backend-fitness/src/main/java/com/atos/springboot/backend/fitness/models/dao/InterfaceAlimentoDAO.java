package com.atos.springboot.backend.fitness.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.atos.springboot.backend.fitness.models.entity.Alimento;

public interface InterfaceAlimentoDAO extends CrudRepository<Alimento, Long> {
	
	@Query(value="SELECT a.nombre as nombreAlimento, a.proteinas,  a.carbohidratos, a.grasas, a.peso, a.valor_energetico FROM"
			+ " usuarios_alimentos ua INNER JOIN alimentos a ON ua.alimentos_id =  a.id INNER JOIN usuarios u ON ua.usuario_id = :id_usuario", nativeQuery = true)
	public List<Alimento> getAlimentoUsuarios(@Param("id_usuario") Long id_usuario);
	
	
	
	

	
	

}
