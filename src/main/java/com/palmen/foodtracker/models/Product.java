package com.palmen.foodtracker.models;

public class Product {
	private String product_name;
	private String ingredients_text;
	private String image_front_small_url;
	private String image_front_url;
	private String nutrition_grades;

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getIngredients_text() {
		return ingredients_text;
	}

	public void setIngredients_text(String ingredients_text) {
		this.ingredients_text = ingredients_text;
	}

	public String getImage_front_small_url() {
		return image_front_small_url;
	}

	public void setImage_front_small_url(String image_front_small_url) {
		this.image_front_small_url = image_front_small_url;
	}

	public String getNutrition_grades() {
		return nutrition_grades;
	}

	public void setNutrition_grades(String nutrition_grades) {
		this.nutrition_grades = nutrition_grades;
	}

	public String getImage_front_url() {
		return image_front_url;
	}

	public void setImage_front_url(String image_front_url) {
		this.image_front_url = image_front_url;
	}

}
