package com.atos.springboot.backend.fitness.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.atos.springboot.backend.fitness.models.entity.Alimento;

public interface InterfaceAlimentoDAO extends CrudRepository<Alimento, Long> {

	/*
	 * @Query(
	 * value="SELECT a.nombre, a.proteinas,  a.carbohidratos, a.grasas, a.peso, a.valor_energetico FROM"
	 * +
	 * " usuarios_alimentos ua INNER JOIN alimentos a ON ua.alimentos_id =  a.id INNER JOIN usuarios u ON ua.usuarios_id = :id_usuario"
	 * , nativeQuery = true)
	 */

	/*
	 * //Funciona pero me devuelve todos los alimentos de todos los usuarios no solo
	 * por carda usuario
	 * 
	 * @Query(
	 * value="SELECT a.id, a.nombre, a.proteinas,  a.carbohidratos, a.grasas, a.peso, a.valor_energetico FROM usuarios_alimentos ua INNER JOIN alimentos a ON ua.alimentos_id =  a.id INNER JOIN usuarios u ON ua.usuarios_id =  u.id;"
	 * , nativeQuery = true) public List<Alimento>
	 * getAlimentoUsuarios(@Param("id_usuario") Long id_usuario);
	 */

	/*
	 * //Funciona es una consulta sin jpa
	 * 
	 * @Query(
	 * value="SELECT DISTINCT a.id, a.nombre, a.proteinas,  a.carbohidratos, a.grasas, a.peso, a.valor_energetico FROM usuarios_alimentos ua INNER JOIN alimentos a ON ua.alimentos_id =  a.id INNER JOIN usuarios u ON ua.usuarios_id = :id"
	 * , nativeQuery = true) public List<Alimento> getAlimentoUsuarios(Long id);
	 */

	/*
	 * No funciona bien del todo, al repetir muchas alimentos sumas todos
	 * //@Query(value =
	 * "SELECT DISTINCT SUM(a.valor_energetico) FROM usuarios_alimentos ua INNER JOIN alimentos a ON ua.alimentos_id =  a.id INNER JOIN usuarios u ON ua.usuarios_id = :id"
	 * , nativeQuery = true) public int sumaAlimentos(Long id);
	 */

	
	//Metodo que nos permite, mostrar todos los alimentos del usuario a través de su id, pasosela por parametros.
	@Query(value = "SELECT a FROM Alimento a JOIN a.usuarios u WHERE u.id = :id")
	public List<Alimento> getAlimentoUsuarios(Long id);


	//Metodo que hace la suma de todos los alimentos que el usuario ha agreado a su perfil
	@Query(value = "SELECT SUM(a.valorEnergetico) FROM Alimento a JOIN a.usuarios u WHERE u.id = :id")
	public int sumaAlimentos(Long id);

	
	//Metodo que permite relaciónar los alimentos que usuario agrega su perfil.
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO `usuarios_alimentos` (usuarios_id, alimentos_id) VALUES (:id_usuario, :id_alimento)", nativeQuery = true)
	public void insertarAlimentoUser(Long id_usuario, Long id_alimento);

}
