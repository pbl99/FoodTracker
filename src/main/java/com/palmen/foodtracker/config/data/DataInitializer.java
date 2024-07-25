package com.palmen.foodtracker.config.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.palmen.foodtracker.models.Rol;
import com.palmen.foodtracker.repositories.IRolRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private IRolRepository rolRepository;

	@Override
	public void run(String... args) throws Exception {
		if (rolRepository.count() == 0) {
			// Crear y guardar roles por defecto
			Rol userRole = new Rol();
			userRole.setNombreRol("USER");
			rolRepository.save(userRole);

			Rol adminRole = new Rol();
			adminRole.setNombreRol("ADMIN");
			rolRepository.save(adminRole);
		}
	}
}