package com.palmen.foodtracker.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.palmen.foodtracker.models.Usuario;
import com.palmen.foodtracker.models.UsuarioListaCompra;
import com.palmen.foodtracker.services.IUsuarioListaCompraService;
import com.palmen.foodtracker.services.IUsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IUsuarioListaCompraService usuarioListaCompraService;

	@GetMapping("/miPerfil")
	public String miPerfil() {
		return "mi-perfil";
	}

	@GetMapping("/miCalendario")
	public String miCalendario(Model model, Authentication authentication) {
		String username = authentication.getName();
		Optional<Usuario> optionalUsuario = usuarioService.findByNombreUsuario(username);

		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			model.addAttribute("usuario", usuario);

			// Obtener las listas de compra del usuario
			List<UsuarioListaCompra> listasCompra = usuarioListaCompraService
					.getListasCompraByUsuarioId(usuario.getId());

			// Convertir listas de compra a un Map
			Map<String, String> listasCompraMap = new HashMap<>();
			for (UsuarioListaCompra listaCompra : listasCompra) {
				listasCompraMap.put(listaCompra.getDia(), listaCompra.getLista());
			}

			model.addAttribute("listasCompra", listasCompraMap);
		} else {
			// Manejar el caso en el que el usuario no está presente, por ejemplo, redirigir
			// a una página de error
			return "redirect:/error";
		}

		return "mi-calendario";
	}
}
