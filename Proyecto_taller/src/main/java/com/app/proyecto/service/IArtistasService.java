package com.app.proyecto.service;

import com.app.proyecto.entity.Artista;

import java.util.List;
import java.util.Map;
import com.app.proyecto.entity.Artista;
import org.springframework.http.ResponseEntity;

public interface IArtistasService {
    List<Artista> consultarTodoslosArtistas();

    ResponseEntity<Artista> consultarUno(int idArt);

    ResponseEntity<Artista>consultarbyNombre(String nombre);

    ResponseEntity<Map<String, String>> insertarArtista(Artista obj);

    ResponseEntity<Map<String, String>> actualizarArtista(Artista obj, int idArt);

    ResponseEntity<Map<String, String>> eliminarArtista(int idArt);
}
