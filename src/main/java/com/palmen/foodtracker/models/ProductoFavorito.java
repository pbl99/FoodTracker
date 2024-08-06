package com.palmen.foodtracker.models;

import java.util.HashSet;
import java.util.Set;

import com.palmen.foodtracker.models.api.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "productos_favoritos")
public class ProductoFavorito {

	@Id
	@Column(name = "codigo_barras", nullable = false)
	private String barcode;

	@ManyToMany(mappedBy = "productosFavoritos")
	private Set<Usuario> usuarios = new HashSet<>();

	@Transient
	private Product product; // Este campo no se guarda en la base de datos

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
}
