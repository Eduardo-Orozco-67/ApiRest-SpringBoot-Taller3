package com.app.proyecto.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.proyecto.entity.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Integer>{
	
	/*@Query (value = "select o from tb_artista o where o.apellidos = ?1", nativeQuery = true)
	Empleado findByApellidos (String apellidos); 
	
	
	Empleado findByEmail (String email);*/
}
