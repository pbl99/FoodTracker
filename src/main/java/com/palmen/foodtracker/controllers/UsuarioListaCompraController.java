package com.palmen.foodtracker.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.palmen.foodtracker.models.Usuario;
import com.palmen.foodtracker.models.UsuarioListaCompra;
import com.palmen.foodtracker.models.UsuarioListaCompraItem;
import com.palmen.foodtracker.services.IUsuarioListaCompraItemService;
import com.palmen.foodtracker.services.IUsuarioListaCompraService;
import com.palmen.foodtracker.services.IUsuarioService;

@Controller
public class UsuarioListaCompraController {

	@Autowired
	private IUsuarioListaCompraService usuarioListaCompraService;

	@Autowired
	private IUsuarioListaCompraItemService usuarioListaCompraItemService;

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

	@PostMapping("/agregarItem")
	public String agregarItem(@RequestParam("usuarioId") Long usuarioId, @RequestParam("dia") String dia,
			@RequestParam("codigoBarras") String codigoBarras) {
		Usuario usuario = usuarioService.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		UsuarioListaCompraItem item = new UsuarioListaCompraItem();
		item.setUsuario(usuario);
		item.setDia(dia);
		item.setCodigoBarras(codigoBarras);

		usuarioListaCompraItemService.save(item);

		return "redirect:/miCalendario";
	}

	@PostMapping("/eliminarProducto")
	public String eliminarProducto(@RequestParam("itemId") Long itemId, RedirectAttributes redirectAttributes) {
		try {
			// Obtener el ítem de la lista de compra por ID
			UsuarioListaCompraItem item = usuarioListaCompraItemService.findById(itemId)
					.orElseThrow(() -> new RuntimeException("Ítem no encontrado"));

			usuarioListaCompraItemService.delete(item);

			redirectAttributes.addFlashAttribute("message", "Producto eliminado exitosamente.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Error al eliminar el producto: " + e.getMessage());
		}

		return "redirect:/miCalendario";
	}

}
