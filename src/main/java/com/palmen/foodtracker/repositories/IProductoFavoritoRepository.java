package com.palmen.foodtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.palmen.foodtracker.models.ProductoFavorito;

@Repository
public interface IProductoFavoritoRepository extends JpaRepository<ProductoFavorito, String> {

}
