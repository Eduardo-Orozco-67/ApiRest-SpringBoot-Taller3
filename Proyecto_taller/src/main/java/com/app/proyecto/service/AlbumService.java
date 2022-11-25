package com.app.proyecto.service;

import com.app.proyecto.entity.Album;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.proyecto.entity.Artista;
import com.app.proyecto.entity.Cancion;
import com.app.proyecto.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.app.proyecto.repository.AlbumRepository;
import org.springframework.stereotype.Service;
import com.app.proyecto.exception.ResourceNotFoundException;

@Service
public class AlbumService implements IAlbumService{

    @Autowired
    private AlbumRepository AlbumRepo;
    @Override
    public List<Album> consultarTodoslosAlbums() {
        List<Album> result = AlbumRepo.findAll();
        if(result.isEmpty())
            throw new ResourceNotFoundException("No existen albumes registrados en la BD");
        return result;
    }

    @Override
    public ResponseEntity<Album> consultarUno(int idalbum) {
        Album obj = AlbumRepo.findById(idalbum).orElseThrow(() -> new ResourceNotFoundException("No existe Album con el Id :" + idalbum));
        return ResponseEntity.ok(obj);
    }

    @Override
    public ResponseEntity<Album> consultarbyNombre(String nombre) {
        Album objalb = AlbumRepo.findByNombre(nombre);
        if (objalb==null)
            throw new ResourceNotFoundException("No existe un album con el nombre :" + nombre);

        return ResponseEntity.ok(objalb);
    }

    @Override
    public ResponseEntity<Map<String, String>> insertarAlbum(Album obj) {
        Map<String, String> okResponse = new HashMap<>();
        okResponse.put("message", "El album se ha registrado correctamente");
        okResponse.put("status", HttpStatus.CREATED.toString());
        return new ResponseEntity<>(okResponse,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Map<String, String>> actualizarAlbum(Album obj, int idalbum) {
        Map<String, String> okResponse = new HashMap<>();
        okResponse.put("message", "Los datos del album se actualizaron correctamente");
        okResponse.put("status", HttpStatus.OK.toString());

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "No existe album con el Id: " + idalbum);
        errorResponse.put("status", HttpStatus.NOT_FOUND.toString());

        return AlbumRepo.findById(idalbum).map( p -> {
                    obj.setId(idalbum);
                    AlbumRepo.save(obj);
                    return new ResponseEntity<>(okResponse, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Map<String, String>> eliminarAlbum(int idalbum) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "Ese album no fue encontrado");
        errorResponse.put("status", HttpStatus.NOT_FOUND.toString());

        Map<String, String> okResponse = new HashMap<>();
        okResponse.put("message", "El album fue eliminado correctamente");
        okResponse.put("status", HttpStatus.OK.toString());

        return AlbumRepo.findById(idalbum).map( p -> {
                    AlbumRepo.deleteById(idalbum);
                    return new ResponseEntity<>(okResponse, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND));

    }
}

