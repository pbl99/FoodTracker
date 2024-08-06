package com.palmen.foodtracker.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.palmen.foodtracker.models.api.Product;
import com.palmen.foodtracker.models.api.ProductResponse;

@Controller
public class AlimentoController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/analizadorAlimentos")
	public String analizadorAlimentos(@RequestParam("codigoBarras") String codigoBarras, Model model) {
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
	public String listarAlimentos(@RequestParam(defaultValue = "1") int page, Model model) {
		String url = String.format(
				"https://world.openfoodfacts.net/api/v2/search?countries_tags_en=spain&stores_tags=mercadona&page=%d&page_size=48",
				page);
		try {
			ProductResponse response = restTemplate.getForObject(url, ProductResponse.class);
			if (response != null && response.getProducts() != null) {
				List<Product> productos = response.getProducts();
				model.addAttribute("productos", productos);
				model.addAttribute("currentPage", page);
				model.addAttribute("totalPages", response.getTotalPages() / 48); // Ajusta según tu lógica de total de
																			// páginas
			} else {
				model.addAttribute("error", "No se encontraron productos");
			}
		} catch (HttpServerErrorException e) {
			model.addAttribute("error", "Error del servidor al obtener los datos: " + e.getMessage());
		} catch (Exception e) {
			model.addAttribute("error", "Error inesperado: " + e.getMessage());
		}
		return "lista-alimentos";
	}

	@PostMapping("/filtrarPorTienda")
	public String filtrarAlimentosPorTienda(@RequestParam("tienda") String tiendaSeleccionada, Model model) {
		// String url =
		// "https://world.openfoodfacts.net/api/v2/search?categories_tags_en=Orange
		// Juice&nutrition_grades_tags=c";
		String url = "https://world.openfoodfacts.net/api/v2/search?countries_tags_en=spain&page_size=48&stores_tags="
				+ tiendaSeleccionada;
		try {
			ProductResponse response = restTemplate.getForObject(url, ProductResponse.class);

			if (response != null && response.getProducts() != null) {
				List<Product> productos = response.getProducts();
				model.addAttribute("productos", productos);
			} else {
				model.addAttribute("error", "No se encontraron productos");
			}
		} catch (HttpServerErrorException e) {
			model.addAttribute("error", "Error del servidor al obtener los datos: " + e.getMessage());
		} catch (Exception e) {
			model.addAttribute("error", "Error inesperado: " + e.getMessage());
		}
		return "lista-alimentos";
	}

	@PostMapping("/filtrarPorCategoria")
	public String filtrarAlimentosPorCategoria(@RequestParam("categoria") String categoriaSeleccionada, Model model) {
		// String url =
		// "https://world.openfoodfacts.net/api/v2/search?categories_tags_en=Orange
		// Juice&nutrition_grades_tags=c";
		String url = "https://world.openfoodfacts.net/api/v2/search?countries_tags_en=spain&page_size=48&categories_tags_en="
				+ categoriaSeleccionada;
		try {
			ProductResponse response = restTemplate.getForObject(url, ProductResponse.class);

			if (response != null && response.getProducts() != null) {
				List<Product> productos = response.getProducts();
				model.addAttribute("productos", productos);
			} else {
				model.addAttribute("error", "No se encontraron productos");
			}
		} catch (HttpServerErrorException e) {
			model.addAttribute("error", "Error del servidor al obtener los datos: " + e.getMessage());
		} catch (Exception e) {
			model.addAttribute("error", "Error inesperado: " + e.getMessage());
		}
		return "lista-alimentos";
	}

}
