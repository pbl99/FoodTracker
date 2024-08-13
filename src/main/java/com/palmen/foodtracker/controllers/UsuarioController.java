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
	public String miPerfil(Model model, Authentication authentication) {
		String username = authentication.getName();
		Optional<Usuario> optionalUsuario = usuarioService.findByNombreUsuario(username);

		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			model.addAttribute("usuario", usuario);
		}
		return "mi-perfil";
	}

	@GetMapping("/miCalendario")
	public String miCalendario(Model model, Authentication authentication) {
		String username = authentication.getName();
		Optional<Usuario> optionalUsuario = usuarioService.findByNombreUsuario(username);

		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			model.addAttribute("usuario", usuario);

			List<UsuarioListaCompra> listasCompra = usuarioListaCompraService
					.getListasCompraByUsuarioId(usuario.getId());

			Map<String, String> listasCompraMap = new HashMap<>();
			for (UsuarioListaCompra listaCompra : listasCompra) {
				listasCompraMap.put(listaCompra.getDia(), listaCompra.getLista());
			}

			model.addAttribute("listasCompra", listasCompraMap);

			List<UsuarioListaCompraItem> itemsCompra = usuarioListaCompraItemService
					.findAllByUsuarioId(usuario.getId());

			if (!itemsCompra.isEmpty()) {
				try {
					Map<UsuarioListaCompraItem, Product> itemsModel = new HashMap<>();

					for (UsuarioListaCompraItem usuarioCompraItem : itemsCompra) {
						String url = "https://world.openfoodfacts.net/api/v2/product/"
								+ usuarioCompraItem.getCodigoBarras();
						ProductResponse response = restTemplate.getForObject(url, ProductResponse.class);

						if (response != null && response.getProduct() != null) {
							Product product = response.getProduct();
							itemsModel.put(usuarioCompraItem, product);
						}
					}

					model.addAttribute("itemsModel", itemsModel);
				} catch (HttpServerErrorException e) {
					model.addAttribute("error", "Error del servidor al obtener los datos: " + e.getMessage());
				} catch (Exception e) {
					model.addAttribute("error", "Error inesperado: " + e.getMessage());

				}

			}

		}
		return "mi-calendario";
	}

}
