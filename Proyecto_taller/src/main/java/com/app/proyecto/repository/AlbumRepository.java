package com.app.proyecto.repository;

import com.app.proyecto.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    @Query("select o from Album o where o.nombre_album like ?1")
    Album findByNombre (String nombre);
}
