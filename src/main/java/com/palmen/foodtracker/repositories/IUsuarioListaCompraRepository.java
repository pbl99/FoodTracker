package com.palmen.foodtracker.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.palmen.foodtracker.models.Usuario;
import com.palmen.foodtracker.models.UsuarioListaCompra;

@Repository
public interface IUsuarioListaCompraRepository extends JpaRepository<UsuarioListaCompra, Long> {
	List<UsuarioListaCompra> findByUsuarioId(Long usuarioId);

	Optional<UsuarioListaCompra> findByUsuarioAndDia(Usuario usuario, String dia);
}
