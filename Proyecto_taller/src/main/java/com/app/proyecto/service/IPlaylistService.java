package com.app.proyecto.service;

import java.util.List;
import java.util.Map;
import com.app.proyecto.entity.Playlist;
import org.springframework.http.ResponseEntity;

public interface IPlaylistService {

    List<Playlist> consultarTodoslosArtistas();

    ResponseEntity<Playlist> consultarUno(int idPlay);

    ResponseEntity<Playlist>consultarbyNombredePlaylis(String nombre);

    ResponseEntity<Map<String, String>> insertarArtista(Playlist obj);

    ResponseEntity<Map<String, String>> actualizarPlaylistPlaylist(Playlist obj, int idPlay);

    ResponseEntity<Map<String, String>> eliminarPlaylist(int idPlay);
}
