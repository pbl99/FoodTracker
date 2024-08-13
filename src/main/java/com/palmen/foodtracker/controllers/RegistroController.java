package com.palmen.foodtracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String registrarUsuario(Model model, Usuario usuario) {
		try {
			usuarioService.save(usuario);
			model.addAttribute("usuarioCreado", "Te has registrado correctamente");
			return "registro";
		}catch(Exception e) {
			model.addAttribute("usuarioError", "No se ha podido completar el registro");
			return "registro";
		}

	}
}
