package com.palmen.foodtracker.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palmen.foodtracker.models.ProductoFavorito;
import com.palmen.foodtracker.models.Rol;
import com.palmen.foodtracker.models.Usuario;
import com.palmen.foodtracker.repositories.IProductoFavoritoRepository;
import com.palmen.foodtracker.repositories.IRolRepository;
import com.palmen.foodtracker.repositories.IUsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Autowired
	private IRolRepository rolRepository;

	@Autowired
	private IProductoFavoritoRepository productoFavoritoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void save(Usuario usuario) {
		Rol rolPorDefecto = rolRepository.findByNombreRol("USER");
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

		Set<Rol> roles = new HashSet<>();
		roles.add(rolPorDefecto);
		usuario.setRoles(roles);

		usuarioRepository.save(usuario);
	}

	@Transactional
	public void guardarProductoFavorito(Integer usuarioId, String codigoBarras) {
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		ProductoFavorito productoFavorito = productoFavoritoRepository.findById(codigoBarras)
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));
		usuario.getProductosFavoritos().add(productoFavorito);
		usuarioRepository.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findByNombreUsuario(String nombreUsuario) {
		return usuarioRepository.findByNombreUsuario(nombreUsuario);
	}

}
