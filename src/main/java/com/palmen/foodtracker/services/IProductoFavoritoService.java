package com.palmen.foodtracker.services;


import com.palmen.foodtracker.models.ProductoFavorito;

public interface IProductoFavoritoService {

	void save(ProductoFavorito productoFavorito);
	
	void deleteFavoriteProduct(Long usuarioId, String codigoBarras);
	
}
