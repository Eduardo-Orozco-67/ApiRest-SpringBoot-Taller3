package com.app.proyecto.service;

import com.app.proyecto.entity.Artista;

import java.util.List;
import java.util.Map;
import com.app.proyecto.entity.Artista;
import org.springframework.http.ResponseEntity;

public interface IArtistasService {
    List<Artista> consultarTodoslosArtistas();
}
