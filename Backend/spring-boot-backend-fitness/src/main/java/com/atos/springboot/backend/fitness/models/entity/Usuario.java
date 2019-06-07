package com.atos.springboot.backend.fitness.models.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "No puede estar vacio")
	@Size(min = 4, max = 12, message = "El tamaño debeestar entre 4 y 12")
	@Column(nullable = false)
	private String nombre;

	@NotEmpty(message = "No puede estar vacio")
	private String apellido;

	@NotEmpty(message = "No puede estar vacio")
	@Email(message = "No es una dirección de correo bien formada")
	@Column(nullable = false, unique = false)
	private String email;

	@NotEmpty(message = "No puede estar vacio")
	@Column(nullable = true)
	private String direccion;

	@Column(unique = true, length = 20)
	private String username;

	@Column(length = 60)
	private String password;

	private boolean enable;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Column(nullable = true)
	private List<Role> roles;

	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "usuarios_alimentos",
            joinColumns = { @JoinColumn(name = "usuarios_id") },
            inverseJoinColumns = { @JoinColumn(name = "alimentos_id") })
    private Set<Alimento> alimentos = new HashSet<>();

	@OneToOne(mappedBy = "usuario")
	private Objetivo objetivo;
	
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEnable() {
		return enable;
	}
	
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	
	
	



}
