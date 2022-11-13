package com.app.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.proyecto.jpa.ArtistaRepository;

@SpringBootApplication
public class ProyectoTallerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoTallerApplication.class, args);
	}
	@Autowired
	private ArtistaRepository ArtisRepo;  
	
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Bienvenidos al Taller de Spring DATA JPA ");
		System.out.println("Paso 1. Insertar 3 materiales en el inventario ");
		//Guardar un registro		
		//inventRepo.save(new Inventario ("JDP78", "MOUSE", "HP", 2018));
		/*Inventario obj1 = new Inventario ("DRT6110", "Teclado", "Dell", 2019);
		inventRepo.save(obj1);*/
		
		
	}

}
