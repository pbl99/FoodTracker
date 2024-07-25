package com.palmen.foodtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.palmen.foodtracker.models.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer>{

	Rol findByNombreRol(String nombreRol);
}
