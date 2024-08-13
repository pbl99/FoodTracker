package com.palmen.foodtracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palmen.foodtracker.models.ProductoFavorito;
import com.palmen.foodtracker.models.Usuario;
import com.palmen.foodtracker.repositories.IProductoFavoritoRepository;
import com.palmen.foodtracker.repositories.IUsuarioRepository;

@Service
public class ProductoFavoritoServiceImpl implements IProductoFavoritoService {

	@Autowired
	private IProductoFavoritoRepository productoFavoritoRepository;
	
	@Autowired 
	private IUsuarioRepository usuarioRepository;

	@Override
	@Transactional
	public void save(ProductoFavorito productoFavorito) {
		productoFavoritoRepository.save(productoFavorito);
	}

	// Método para eliminar un producto favorito
	@Override
	@Transactional
	public void deleteFavoriteProduct(Long usuarioId, String codigoBarras) {
		// Buscar el usuario por su ID
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		// Buscar el producto por su código de barras
		ProductoFavorito producto = productoFavoritoRepository.findBybarcode(codigoBarras)
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));

		// Eliminar el producto de la lista de favoritos del usuario
		usuario.getProductosFavoritos().remove(producto);

		// Guardar los cambios en el usuario
		usuarioRepository.save(usuario);
	}
}
