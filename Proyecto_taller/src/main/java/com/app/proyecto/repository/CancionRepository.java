package com.app.proyecto.repository;

import com.app.proyecto.entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CancionRepository extends JpaRepository<Cancion, Integer> {

    @Query("select o from Cancion o where o.nombreCancion like ?1")
    Cancion findByNombreCan (String nombreCancion);

   // List<Cancion> findCancionesByalbumsid(Integer albumId);
}
