package com.palmen.foodtracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.palmen.foodtracker.models.Usuario;
import com.palmen.foodtracker.services.IUsuarioService;

@Controller
public class RegistroController {

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/registro")
	public String registro(Usuario usuario) {
		return "registro";
	}

	@PostMapping("/registrarUsuario")
	public String registrarUsuario(Usuario usuario) {
		usuarioService.save(usuario);
		return "registro";

	}
}
