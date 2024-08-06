package com.palmen.foodtracker.models.api;

import com.palmen.foodtracker.models.api.images.SelectedImages;

public class Product {

	private String code;
	private String product_name;
	private String generic_name;
	private String ingredients_text;
	private String image_front_small_url;
	private String image_front_url;
	private String nutrition_grades;
	private String image_nutrition_url;
	private String origins;
	private String quantity;
	private String stores;
	private String brands;
	private String categories;
	private String countries;
	private ProductNutriment nutriments;
	private SelectedImages selected_images;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

	public String getImage_nutrition_url() {
		return image_nutrition_url;
	}

	public void setImage_nutrition_url(String image_nutrition_url) {
		this.image_nutrition_url = image_nutrition_url;
	}

	public String getOrigins() {
		return origins;
	}

	public void setOrigins(String origins) {
		this.origins = origins;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getStores() {
		return stores;
	}

	public void setStores(String stores) {
		this.stores = stores;
	}

	public String getBrands() {
		return brands;
	}

	public void setBrands(String brands) {
		this.brands = brands;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getGeneric_name() {
		return generic_name;
	}

	public void setGeneric_name(String generic_name) {
		this.generic_name = generic_name;
	}

	public String getCountries() {
		return countries;
	}

	public void setCountries(String countries) {
		this.countries = countries;
	}

	public ProductNutriment getNutriments() {
		return nutriments;
	}

	public void setNutriments(ProductNutriment nutriments) {
		this.nutriments = nutriments;
	}

	public SelectedImages getSelected_images() {
		return selected_images;
	}

	public void setSelected_images(SelectedImages selected_images) {
		this.selected_images = selected_images;
	}

	public String getImageUrl() {
		if (selected_images != null && selected_images.getFront() != null
				&& selected_images.getFront().getSmall() != null) {

			// Intentar cargar la imagen en el orden: es, en, de, fr
			String url = selected_images.getFront().getSmall().getEs();
			if (url != null) {
				return url;
			}
			url = selected_images.getFront().getSmall().getEn();
			if (url != null) {
				return url;
			}
			url = selected_images.getFront().getSmall().getDe();
			if (url != null) {
				return url;
			}
			url = selected_images.getFront().getSmall().getFr();
			if (url != null) {
				return url;
			}
		}
		return "/images/imgproductnotavailable.png";
	}

}
