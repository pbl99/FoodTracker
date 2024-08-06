package com.palmen.foodtracker.services;

import java.util.List;
import java.util.Optional;

import com.palmen.foodtracker.models.Usuario;
import com.palmen.foodtracker.models.UsuarioListaCompra;

public interface IUsuarioListaCompraService {

	void save(UsuarioListaCompra usuarioListaCompra);
	
	List<UsuarioListaCompra> getListasCompraByUsuarioId(Long usuarioId);
	
	void delete(UsuarioListaCompra usuarioListaCompra);
	
	Optional<UsuarioListaCompra> findByUsuarioAndDia(Usuario usuario, String dia);

}
