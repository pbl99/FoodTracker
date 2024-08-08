package com.palmen.foodtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palmen.foodtracker.models.Usuario;
import com.palmen.foodtracker.models.UsuarioListaCompraItem;
import com.palmen.foodtracker.repositories.IUsuarioListaCompraItemRepository;

@Service
public class UsuarioListaCompraItemServiceImpl implements IUsuarioListaCompraItemService {

	@Autowired
	private IUsuarioListaCompraItemRepository usuarioListaCompraItemRepository;

	@Override
	@Transactional
	public UsuarioListaCompraItem save(UsuarioListaCompraItem item) {
		return usuarioListaCompraItemRepository.save(item);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<UsuarioListaCompraItem> findById(Long id) {
		return usuarioListaCompraItemRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioListaCompraItem> findByUsuarioAndDia(Usuario usuario, String dia) {
		return usuarioListaCompraItemRepository.findByUsuarioAndDia(usuario, dia);
	}

	@Override
	@Transactional
	public void delete(UsuarioListaCompraItem item) {
		usuarioListaCompraItemRepository.delete(item);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioListaCompraItem> findAllByUsuarioId(Long usuarioId) {
		return usuarioListaCompraItemRepository.findAllByUsuarioId(usuarioId);
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioListaCompraItem findByCodigoBarrasAndDia(String codigoBarras, String dia) {
		return usuarioListaCompraItemRepository.findByCodigoBarrasAndDia(codigoBarras, dia);
	}
}
