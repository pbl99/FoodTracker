package com.palmen.foodtracker.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.palmen.foodtracker.models.Usuario;
import com.palmen.foodtracker.models.api.Product;
import com.palmen.foodtracker.models.api.ProductResponse;
import com.palmen.foodtracker.services.IUsuarioService;

@Controller
public class AlimentoController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/analizadorAlimentos")
	public String analizadorAlimentos(@RequestParam("codigoBarras") String codigoBarras, Model model,
			Authentication authentication) {

		if (authentication != null && authentication.isAuthenticated()) {
			String nombreUsuario = authentication.getName();

			Optional<Usuario> usuarioOpt = usuarioService.findByNombreUsuario(nombreUsuario);

			if (usuarioOpt.isPresent()) {
				Usuario usuario = usuarioOpt.get();
				model.addAttribute("usuarioId", usuario.getId()); // Asegúrate de pasar solo el ID
			}
		}
		// Llama a la API para obtener la información del producto
		String url = "https://world.openfoodfacts.org/api/v2/product/" + codigoBarras + ".json";
		ProductResponse response = restTemplate.getForObject(url, ProductResponse.class);

		if (response != null && response.getProduct() != null) {
			model.addAttribute("product", response.getProduct());
		} else {
			model.addAttribute("error", "Producto no encontrado");
		}

		return "ver-alimentos";
	}

	@PostMapping("/enviarCodigo")
	public String enviarCodigo(@RequestParam("codigoBarras") String codigoBarras) {
		return "redirect:/analizadorAlimentos?codigoBarras=" + codigoBarras;
	}

	@GetMapping("/listarAlimentos")
	public String listarAlimentos(@RequestParam(defaultValue = "1") int page,
			@RequestParam(required = false) String categoria, @RequestParam(required = false) String tienda,
			Model model) {
		StringBuilder url = new StringBuilder("https://world.openfoodfacts.net/api/v2/search?countries_tags_en=spain");

		if (categoria != null && !categoria.isEmpty()) {
			url.append("&categories_tags=").append(categoria);
		}
		if (tienda != null && !tienda.isEmpty()) {
			url.append("&stores_tags=").append(tienda);
		}

		url.append("&page=").append(page).append("&page_size=48");

		try {
			ProductResponse response = restTemplate.getForObject(url.toString(), ProductResponse.class);

			if (response != null && response.getProducts() != null) {
				List<Product> productos = response.getProducts();
				model.addAttribute("productos", productos);
				model.addAttribute("currentPage", page);
				model.addAttribute("totalPages", (response.getTotalPages()) / 48);
			} else {
				model.addAttribute("error", "No se encontraron productos");
			}
		} catch (HttpServerErrorException e) {
			model.addAttribute("error", "Error del servidor al obtener los datos: " + e.getMessage());
		} catch (Exception e) {
			model.addAttribute("error", "Error inesperado: " + e.getMessage());
		}

		model.addAttribute("selectedCategoria", categoria);
		model.addAttribute("selectedTienda", tienda);

		return "lista-alimentos";
	}

	@PostMapping("/filtrarAlimentos")
	public String filtrarAlimentos(@RequestParam(required = false) String categoria,
			@RequestParam(required = false) String tienda, @RequestParam(defaultValue = "1") int page, Model model) {
		StringBuilder url = new StringBuilder("https://world.openfoodfacts.net/api/v2/search?countries_tags_en=spain");

		if (categoria != null && !categoria.isEmpty()) {
			url.append("&categories_tags=").append(categoria);
		}
		if (tienda != null && !tienda.isEmpty()) {
			url.append("&stores_tags=").append(tienda);
		}

		url.append("&page=").append(page).append("&page_size=48");

		try {
			ProductResponse response = restTemplate.getForObject(url.toString(), ProductResponse.class);

			if (response != null && response.getProducts() != null) {
				List<Product> productos = response.getProducts();
				model.addAttribute("productos", productos);
				model.addAttribute("currentPage", page);
				model.addAttribute("totalPages", response.getTotalPages() / 48);
			} else {
				model.addAttribute("error", "No se encontraron productos");
			}
		} catch (HttpServerErrorException e) {
			model.addAttribute("error", "Error del servidor al obtener los datos: " + e.getMessage());
		} catch (Exception e) {
			model.addAttribute("error", "Error inesperado: " + e.getMessage());
		}

		model.addAttribute("selectedCategoria", categoria);
		model.addAttribute("selectedTienda", tienda);

		return "lista-alimentos";
	}

}
