package com.palmen.foodtracker.services;

import java.util.Optional;

import com.palmen.foodtracker.models.Usuario;

public interface IUsuarioService {
	
	Optional<Usuario> findByNombreUsuario(String nombreUsuario);

	void save(Usuario usuario);
	
	void guardarProductoFavorito(Long usuarioId, String codigoBarras);
	
	Optional<Usuario> findById(Long id);
	
}
