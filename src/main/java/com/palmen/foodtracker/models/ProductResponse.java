package com.palmen.foodtracker.models;

import java.util.List;

public class ProductResponse {

	private Product product;

	private List<Product> products;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
