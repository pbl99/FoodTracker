package com.palmen.foodtracker.services;

import java.util.List;
import java.util.Optional;

import com.palmen.foodtracker.models.Usuario;
import com.palmen.foodtracker.models.UsuarioListaCompraItem;

public interface IUsuarioListaCompraItemService {

	UsuarioListaCompraItem save(UsuarioListaCompraItem item);

	Optional<UsuarioListaCompraItem> findById(Long id);

	List<UsuarioListaCompraItem> findByUsuarioAndDia(Usuario usuario, String dia);

	void delete(UsuarioListaCompraItem item);

	List<UsuarioListaCompraItem> findAllByUsuarioId(Long usuarioId);
	
	UsuarioListaCompraItem findByCodigoBarrasAndDia(String codigoBarras, String dia);
}
