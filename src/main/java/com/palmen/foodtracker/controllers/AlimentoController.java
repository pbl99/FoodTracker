package com.palmen.foodtracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.palmen.foodtracker.models.ProductResponse;

@Controller
public class AlimentoController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/analizadorAlimentos")
	public String buscarAlimentos(@RequestParam("codigoBarras") String codigoBarras, Model model) {
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
}
