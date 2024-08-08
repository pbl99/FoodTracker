package com.palmen.foodtracker.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.palmen.foodtracker.models.Usuario;
import com.palmen.foodtracker.models.UsuarioListaCompra;
import com.palmen.foodtracker.models.UsuarioListaCompraItem;
import com.palmen.foodtracker.models.api.Product;
import com.palmen.foodtracker.models.api.ProductResponse;
import com.palmen.foodtracker.services.IUsuarioListaCompraItemService;
import com.palmen.foodtracker.services.IUsuarioListaCompraService;
import com.palmen.foodtracker.services.IUsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IUsuarioListaCompraService usuarioListaCompraService;

	@Autowired
	private IUsuarioListaCompraItemService usuarioListaCompraItemService;

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

			// Obtener los items de lista de compra del usuario
			List<UsuarioListaCompraItem> itemsCompra = usuarioListaCompraItemService
					.findAllByUsuarioId(usuario.getId());
		

			Set<Product> itemsModelLunes = new HashSet<>();
			Set<Product> itemsModelMartes = new HashSet<>();
			Set<Product> itemsModelMiercoles = new HashSet<>();
			Set<Product> itemsModelJueves = new HashSet<>();
			Set<Product> itemsModelViernes = new HashSet<>();
			Set<Product> itemsModelSabado = new HashSet<>();
			Set<Product> itemsModelDomingo = new HashSet<>();

			if (!itemsCompra.isEmpty()) {
				try {
					for (UsuarioListaCompraItem usuarioCompraItem : itemsCompra) {
						String url = "https://world.openfoodfacts.net/api/v2/product/"
								+ usuarioCompraItem.getCodigoBarras();
						ProductResponse response = restTemplate.getForObject(url, ProductResponse.class);

						if (response != null && response.getProduct() != null) {
							Product product = response.getProduct();
							if (usuarioCompraItem.getDia().equals("Lunes")) {
								itemsModelLunes.add(product);
							} else if (usuarioCompraItem.getDia().equals("Martes")) {
								itemsModelMartes.add(product);
							} else if (usuarioCompraItem.getDia().equals("Miércoles")) {
								itemsModelMiercoles.add(product);
							} else if (usuarioCompraItem.getDia().equals("Jueves")) {
								itemsModelJueves.add(product);
							} else if (usuarioCompraItem.getDia().equals("Viernes")) {
								itemsModelViernes.add(product);
							} else if (usuarioCompraItem.getDia().equals("Sábado")) {
								itemsModelSabado.add(product);
							} else if (usuarioCompraItem.getDia().equals("Domingo")) {
								itemsModelDomingo.add(product);
							}
						}
					}
					model.addAttribute("itemsModelLunes", itemsModelLunes);
					model.addAttribute("itemsModelMartes", itemsModelMartes);
					model.addAttribute("itemsModelMiercoles", itemsModelMiercoles);
					model.addAttribute("itemsModelJueves", itemsModelJueves);
					model.addAttribute("itemsModelViernes", itemsModelViernes);
					model.addAttribute("itemsModelSabado", itemsModelSabado);
					model.addAttribute("itemsModelDomingo", itemsModelDomingo);
				} catch (HttpServerErrorException e) {
					model.addAttribute("error", "Error del servidor al obtener los datos: " + e.getMessage());
				} catch (Exception e) {
					model.addAttribute("error", "Error inesperado: " + e.getMessage());
				}
			}

		} else {
			return "redirect:/error";
		}

		return "mi-calendario";
	}
}
