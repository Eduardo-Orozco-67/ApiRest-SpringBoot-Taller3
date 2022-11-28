package com.app.proyecto.repository;

import com.app.proyecto.entity.Artista;
import com.app.proyecto.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
    @Query("select o from Playlist o where o.nombre like ?1")
    Playlist findByNombre (String nombre);

}
