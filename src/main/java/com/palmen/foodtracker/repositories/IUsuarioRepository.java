package com.palmen.foodtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.palmen.foodtracker.models.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, String>{

}
