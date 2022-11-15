package com.app.proyecto.repository;

import com.app.proyecto.entity.Artista;
import com.app.proyecto.entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CancionRepository extends JpaRepository<Cancion, Integer> {

    @Query("select o from Cancion o where o.nombreCancion like ?1")
    Cancion findByNombreCan (String nombreCancion);
}
