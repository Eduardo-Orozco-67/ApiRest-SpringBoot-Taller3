package com.app.proyecto.service;

import com.app.proyecto.entity.Album;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

public interface IAlbumService {

    List<Album> consultarTodoslosAlbums();

    ResponseEntity<Album> consultarUno(int idalbum);

    ResponseEntity<Album>consultarbyNombre(String nombre);

    ResponseEntity<Map<String, String>> insertarAlbum(Album obj);

    ResponseEntity<Map<String, String>> actualizarAlbum(Album obj, int idalbum);

    ResponseEntity<Map<String, String>> eliminarAlbum(int idalbum);
}
