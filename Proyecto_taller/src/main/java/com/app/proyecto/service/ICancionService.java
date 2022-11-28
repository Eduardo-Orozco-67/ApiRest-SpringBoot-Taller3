package com.app.proyecto.service;

import java.util.List;
import java.util.Map;

import com.app.proyecto.entity.Artista;
import com.app.proyecto.entity.Cancion;
import org.springframework.http.ResponseEntity;

public interface ICancionService {



    ResponseEntity<Cancion> consultarUna(int idCan);

    ResponseEntity<Cancion>consultarbyNombreCancion(String nombreCan);

    ResponseEntity<Map<String, String>> insertarCancion(Cancion obj);

    ResponseEntity<Map<String, String>> actualizarCancion(Cancion obj, int idCan);

    ResponseEntity<Map<String, String>> eliminarCancion(int idCan);
}
