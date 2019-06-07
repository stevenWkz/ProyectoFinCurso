package com.atos.springboot.backend.fitness.models.services;


import java.util.List;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.atos.springboot.backend.fitness.models.dao.InterfaceUsuarioDAO;
import com.atos.springboot.backend.fitness.models.entity.Usuario;

@Service
public class UsuarioService implements UserDetailsService, InterfaceUsuarioService  {
	
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class); 
	
	@Autowired
	private InterfaceUsuarioDAO usuarioDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return(List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioDao.findByUsername(username);
		
		if( usuario == null) {
			
			logger.error("Error en el login: no existe el usuario"+ username + "en el sistema");
			
			throw new UsernameNotFoundException("Error en el login: no existe el usuario"+ username + "en el sistema");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new  SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		
	
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnable(), true, true, true, authorities);
	}
	
	
	
	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {

		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		usuarioDao.deleteById(id);
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}




}
