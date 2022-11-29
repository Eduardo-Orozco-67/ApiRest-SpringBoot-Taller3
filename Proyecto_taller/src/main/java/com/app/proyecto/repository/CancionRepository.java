package com.app.proyecto.repository;

import com.app.proyecto.entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CancionRepository extends JpaRepository<Cancion, Integer> {

    @Query("select o from Cancion o where o.nombreCancion like ?1")
    Cancion findByNombreCan (String nombreCancion);

   @Query(value = "select c.id_cancion, c.anio, c.duracion, c.genero, c.nombrecancion  from tb_cancion c inner join relacion_album_cancion r on c.id_cancion=r.id_cancion\n" +
           "  inner join tb_album a on a.id_album=r.id_album where a.id_album=?1 and r.id_album=?1", nativeQuery = true)
    List<Cancion> findCancionesByalbumId(Integer albumId);


   @Query(value = "call guardar_cancion_album (?2, ?1, NULL)", nativeQuery = true)
    Cancion insertarenAlbum(Integer idalbum, Integer idcancion);

}
