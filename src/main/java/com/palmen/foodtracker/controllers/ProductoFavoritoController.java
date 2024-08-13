package com.palmen.foodtracker.controllers;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.palmen.foodtracker.models.ProductoFavorito;
import com.palmen.foodtracker.models.Usuario;
import com.palmen.foodtracker.models.api.Product;
import com.palmen.foodtracker.models.api.ProductResponse;
import com.palmen.foodtracker.services.IProductoFavoritoService;
import com.palmen.foodtracker.services.IUsuarioService;

@Controller
public class ProductoFavoritoController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IProductoFavoritoService productoFavoritoService;

	@GetMapping("/productosFavoritos")
	public String productosFavoritos(Model model, Authentication authentication) {

		// Obtener el nombre de usuario desde la autenticación
		String nombreUsuario = authentication.getName();

		Optional<Usuario> usuarioOpt = usuarioService.findByNombreUsuario(nombreUsuario);

		if (usuarioOpt.isPresent()) {
			Usuario usuario = usuarioOpt.get();

			// Obtener la lista de productos favoritos del usuario
			Set<ProductoFavorito> productosFavoritos = usuario.getProductosFavoritos();

			// Si hay productos favoritos, obtener más detalles desde la API
			if (!productosFavoritos.isEmpty()) {
				try {
					// Recorre los productos favoritos y obtiene los detalles de la API
					for (ProductoFavorito favorito : productosFavoritos) {
						String url = "https://world.openfoodfacts.net/api/v2/product/" + favorito.getBarcode();
						ProductResponse response = restTemplate.getForObject(url, ProductResponse.class);

						if (response != null && response.getProduct() != null) {
							Product product = response.getProduct();
							favorito.setProduct(product); // Aquí se establece el Product en ProductoFavorito
						}
					}

					// Añadir la lista de productos favoritos al modelo
					model.addAttribute("productosFavoritos", productosFavoritos);

				} catch (HttpServerErrorException e) {
					model.addAttribute("error", "Error del servidor al obtener los datos: " + e.getMessage());
				} catch (Exception e) {
					model.addAttribute("error", "Error inesperado: " + e.getMessage());
				}
			} else {
				model.addAttribute("error", "No tienes productos favoritos.");
			}
		} else {
			model.addAttribute("error", "Usuario no encontrado.");
		}

		return "productos-favoritos";
	}

	@PostMapping("/guardarFavorito")
	public String guardarProductoFavorito(@RequestParam String codigoBarras, Authentication authentication) {

		String nombreUsuario = authentication.getName();

		Optional<Usuario> usuarioOpt = usuarioService.findByNombreUsuario(nombreUsuario);

		if (usuarioOpt.isPresent()) {
			Usuario usuario = usuarioOpt.get();

			// Guarda el producto favorito
			ProductoFavorito productoFavorito = new ProductoFavorito();
			productoFavorito.setBarcode(codigoBarras);
			productoFavoritoService.save(productoFavorito);

			// Asocia el producto favorito al usuario
			usuarioService.guardarProductoFavorito(usuario.getId(), codigoBarras);
		} else {
			throw new RuntimeException("Usuario no encontrado");
		}

		return "redirect:/productosFavoritos";
	}

	@PostMapping("/eliminarProductoFavorito")
	public String eliminarProductoFavorito(@RequestParam String codigoBarras, Authentication authentication) {
		String nombreUsuario = authentication.getName();

		Optional<Usuario> usuarioOpt = usuarioService.findByNombreUsuario(nombreUsuario);

		if (usuarioOpt.isPresent()) {
			Usuario usuario = usuarioOpt.get();
			productoFavoritoService.deleteFavoriteProduct(usuario.getId(), codigoBarras);

		}
		return "redirect:/productosFavoritos";
	}
}
