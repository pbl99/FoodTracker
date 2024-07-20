package com.palmen.foodtracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmen.foodtracker.models.Usuario;
import com.palmen.foodtracker.repositories.IUsuarioRepository;


@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	//private PasswordEncoder passwordEncoder;

	@Override
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
		
	}

}
