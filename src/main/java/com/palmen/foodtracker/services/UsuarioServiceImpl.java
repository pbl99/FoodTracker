package com.palmen.foodtracker.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.palmen.foodtracker.models.Rol;
import com.palmen.foodtracker.models.Usuario;
import com.palmen.foodtracker.repositories.IRolRepository;
import com.palmen.foodtracker.repositories.IUsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private IRolRepository rolRepository;

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

}
