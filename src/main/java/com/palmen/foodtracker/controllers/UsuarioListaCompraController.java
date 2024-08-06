package com.palmen.foodtracker.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.palmen.foodtracker.models.Usuario;
import com.palmen.foodtracker.models.UsuarioListaCompra;
import com.palmen.foodtracker.services.IUsuarioListaCompraService;
import com.palmen.foodtracker.services.IUsuarioService;

@Controller
public class UsuarioListaCompraController {

	@Autowired
	private IUsuarioListaCompraService usuarioListaCompraService;

	@Autowired
	private IUsuarioService usuarioService;

	@PostMapping("/guardarLista")
	public String crearListaCompra(@RequestParam("usuarioId") Long usuarioId, @RequestParam("dia") String dia,
			@RequestParam("lista") String lista) {

		// Buscar el usuario por ID
		Usuario usuario = usuarioService.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		// Crear una nueva lista de compra y asignar los valores
		UsuarioListaCompra usuarioListaCompra = new UsuarioListaCompra();
		usuarioListaCompra.setUsuario(usuario);
		usuarioListaCompra.setDia(dia);
		usuarioListaCompra.setLista(lista);

		// Guardar la lista de compra
		usuarioListaCompraService.save(usuarioListaCompra);

		return "redirect:/miCalendario";
	}

	@PostMapping("/restablecerLista")
	public String restablecerLista(@RequestParam("usuarioId") Long usuarioId, @RequestParam("dia") String dia) {
		Optional<Usuario> usuario = usuarioService.findById(usuarioId);

		if (usuario.isPresent()) {
			Usuario usuarioPresente = usuario.get();
			Optional<UsuarioListaCompra> usuarioListaCompra = usuarioListaCompraService
					.findByUsuarioAndDia(usuarioPresente, dia);

			if (usuarioListaCompra.isPresent()) {
				usuarioListaCompraService.delete(usuarioListaCompra.get()); // Elimina la entrada existente
			}
		}

		return "redirect:/miCalendario";
	}
}
