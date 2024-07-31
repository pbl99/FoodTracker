package com.palmen.foodtracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palmen.foodtracker.models.ProductoFavorito;
import com.palmen.foodtracker.repositories.IProductoFavoritoRepository;

@Service
public class ProductoFavoritoServiceImpl implements IProductoFavoritoService {

	@Autowired
	private IProductoFavoritoRepository productoFavoritoRepository;

	@Override
	@Transactional
	public void save(ProductoFavorito productoFavorito) {
		productoFavoritoRepository.save(productoFavorito);
	}


}
