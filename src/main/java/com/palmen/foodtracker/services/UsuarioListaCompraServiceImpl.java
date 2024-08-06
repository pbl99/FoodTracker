package com.palmen.foodtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmen.foodtracker.models.Usuario;
import com.palmen.foodtracker.models.UsuarioListaCompra;
import com.palmen.foodtracker.repositories.IUsuarioListaCompraRepository;

@Service
public class UsuarioListaCompraServiceImpl implements IUsuarioListaCompraService {

	@Autowired
	private IUsuarioListaCompraRepository usuarioListaCompraRepository;

	@Override
	public void save(UsuarioListaCompra usuarioListaCompra) {
		usuarioListaCompraRepository.save(usuarioListaCompra);
	}

	@Override
	public List<UsuarioListaCompra> getListasCompraByUsuarioId(Long usuarioId) {
		return usuarioListaCompraRepository.findByUsuarioId(usuarioId);
	}

	@Override
	public void delete(UsuarioListaCompra usuarioListaCompra) {
		usuarioListaCompraRepository.delete(usuarioListaCompra);
	}

	@Override
	public Optional<UsuarioListaCompra> findByUsuarioAndDia(Usuario usuario, String dia) {
		return usuarioListaCompraRepository.findByUsuarioAndDia(usuario, dia);
	}
}
