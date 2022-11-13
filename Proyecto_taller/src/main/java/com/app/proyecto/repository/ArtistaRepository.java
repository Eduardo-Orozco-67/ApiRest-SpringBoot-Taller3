package com.app.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.proyecto.entity.Artista;
import org.springframework.data.jpa.repository.Query;

public interface ArtistaRepository extends JpaRepository<Artista, Integer>{
	
	@Query("select o from Artista o where o.nombreArtista like ?1")
	Artista findByNombre (String nombre);


}
