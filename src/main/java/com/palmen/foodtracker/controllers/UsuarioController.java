package com.palmen.foodtracker.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {

	@GetMapping("/miPerfil")
	public String miPerfil() {
		return "mi-perfil";
	}
	
	@GetMapping("/cerrarSesion")
	public String cerrarSesion() {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "login";
	}
}
