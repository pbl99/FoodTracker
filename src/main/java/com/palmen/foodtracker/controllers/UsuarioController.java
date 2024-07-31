package com.palmen.foodtracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {

	@GetMapping("/miPerfil")
	public String miPerfil() {
		return "mi-perfil";
	}
}
