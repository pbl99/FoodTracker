package com.palmen.foodtracker.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre_usuario")
	private String nombreUsuario;

	private String email;

	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_roles", // Nombre de la tabla intermedia
			joinColumns = @JoinColumn(name = "usuario_id"), // Columna de la entidad Usuario
			inverseJoinColumns = @JoinColumn(name = "rol_id") // Columna de la entidad Rol
	)
	private Set<Rol> roles = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "usuarios_productos_favoritos", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "codigo_barras"))
	private Set<ProductoFavorito> productosFavoritos = new HashSet<>();

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UsuarioListaCompra> listasCompra;

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public Set<ProductoFavorito> getProductosFavoritos() {
		return productosFavoritos;
	}

	public void setProductosFavoritos(Set<ProductoFavorito> productosFavoritos) {
		this.productosFavoritos = productosFavoritos;
	}

	public Set<UsuarioListaCompra> getListasCompra() {
		return listasCompra;
	}

	public void setListasCompra(Set<UsuarioListaCompra> listasCompra) {
		this.listasCompra = listasCompra;
	}

}
