package com.app.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.proyecto.repository.ArtistaRepository;

@SpringBootApplication
public class ProyectoTallerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoTallerApplication.class, args);
	}
	@Autowired
	private ArtistaRepository ArtisRepo;  
	
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Bienvenidos al API REST DE U´BEAT");
		System.out.println("URL: http://localhost:5009/swagger-ui.html#");
		
	}

}
