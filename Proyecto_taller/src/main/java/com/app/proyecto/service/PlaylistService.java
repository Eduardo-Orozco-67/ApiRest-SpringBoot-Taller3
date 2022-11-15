package com.app.proyecto.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.app.proyecto.entity.Playlist;
import com.app.proyecto.repository.PlaylistRepository;
import org.springframework.stereotype.Service;
import com.app.proyecto.exception.ResourceNotFoundException;

@Service
public class PlaylistService implements IPlaylistService{

    @Autowired
    private PlaylistRepository PlayRepo;

    @Override
    public List<Playlist> consultarTodoslosArtistas() {
        List<Playlist> result = PlayRepo.findAll();
        if(result.isEmpty())
            throw new ResourceNotFoundException("No existe ese artista registrado(a) en la BD");
        return PlayRepo.findAll();
    }

    @Override
    public ResponseEntity<Playlist> consultarUno(int idPlay) {
        Playlist obj = PlayRepo.findById(idPlay).orElseThrow(() -> new ResourceNotFoundException("No existe Artista con el Id :" + idPlay));
        return ResponseEntity.ok(obj);
    }

    @Override
    public ResponseEntity<Playlist>consultarbyNombredePlaylis(String nombre) {
        Playlist objart = PlayRepo.findByNombre(nombre);
        if (objart==null)
            throw new ResourceNotFoundException("No existe un Artista con el nombre :" + nombre);

        return ResponseEntity.ok(objart);
    }

    @Override
    public ResponseEntity<Map<String, String>> insertarArtista(Playlist obj) {
        Map<String, String> okResponse = new HashMap<>();
        okResponse.put("message", "El artista se ha registrado correctamente");
        okResponse.put("status", HttpStatus.CREATED.toString());
        PlayRepo.save(obj);
        return new ResponseEntity<>(okResponse,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Map<String, String>> actualizarPlaylistPlaylist(Playlist obj, int idPlay) {
        Map<String, String> okResponse = new HashMap<>();
        okResponse.put("message", "El artista se actualizo correctamente");
        okResponse.put("status", HttpStatus.OK.toString());

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "No existe un artista con el Id: " + idPlay);
        errorResponse.put("status", HttpStatus.NOT_FOUND.toString());

        return PlayRepo.findById(idPlay).map( p -> {
                    obj.setId_playlist(idPlay);
                    PlayRepo.save(obj);
                    return new ResponseEntity<>(okResponse, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Map<String, String>> eliminarPlaylist(int idPlay) {

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "Ese artista no fue encontrado");
        errorResponse.put("status", HttpStatus.NOT_FOUND.toString());

        Map<String, String> okResponse = new HashMap<>();
        okResponse.put("message", "El artista fue eliminado correctamente");
        okResponse.put("status", HttpStatus.OK.toString());

        return PlayRepo.findById(idPlay).map( p -> {
                    PlayRepo.deleteById(idPlay);
                    return new ResponseEntity<>(okResponse, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND));

    }
}
