package com.palmen.foodtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.palmen.foodtracker.models.Usuario;
import com.palmen.foodtracker.models.UsuarioListaCompraItem;

@Repository
public interface IUsuarioListaCompraItemRepository extends JpaRepository<UsuarioListaCompraItem, Long>{
	List<UsuarioListaCompraItem> findByUsuarioAndDia(Usuario usuario, String dia);
	List<UsuarioListaCompraItem> findAllByUsuarioId(Long usuarioId);
	UsuarioListaCompraItem findByCodigoBarrasAndDia(String codigoBarras, String dia);
}
