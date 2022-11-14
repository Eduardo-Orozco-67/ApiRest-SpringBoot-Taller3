package com.app.proyecto.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.app.proyecto.entity.Artista;
import com.app.proyecto.repository.ArtistaRepository;
import org.springframework.stereotype.Service;
import com.app.proyecto.exception.ResourceNotFoundException;

@Service
public class ArtistaService implements IArtistasService {

@Autowired
private ArtistaRepository ArtistaRepo;

    @Override
    public List<Artista> consultarTodoslosArtistas() {

        List<Artista> result = ArtistaRepo.findAll();
        if(result.isEmpty())
            throw new ResourceNotFoundException("No existe ese artista registrado(a) en la BD");

        return ArtistaRepo.findAll();
    }

    @Override
    public ResponseEntity<Artista> consultarUno(int idArt) {
        Artista obj = ArtistaRepo.findById(idArt).orElseThrow(() -> new ResourceNotFoundException("No existe Artista con el Id :" + idArt));
        return ResponseEntity.ok(obj);
    }

    @Override
    public ResponseEntity<Artista>consultarbyNombre(String nombre) {
        Artista objart = ArtistaRepo.findByNombre(nombre);
        if (objart==null)
            throw new ResourceNotFoundException("No existe un Artista con el nombre :" + nombre);

        return ResponseEntity.ok(objart);
    }

    @Override
    public ResponseEntity<Map<String, String>> insertarArtista(Artista obj) {
        Map<String, String> okResponse = new HashMap<>();
        okResponse.put("message", "El artista se ha registrado correctamente");
        okResponse.put("status", HttpStatus.CREATED.toString());
        ArtistaRepo.save(obj);
        return new ResponseEntity<>(okResponse,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Map<String, String>> actualizarArtista(Artista obj, int idArt) {
        Map<String, String> okResponse = new HashMap<>();
        okResponse.put("message", "El artista se actualizo correctamente");
        okResponse.put("status", HttpStatus.OK.toString());

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "No existe un artista con el Id: " + idArt);
        errorResponse.put("status", HttpStatus.NOT_FOUND.toString());

        return ArtistaRepo.findById(idArt).map( p -> {
                    obj.setId_artista(idArt);
                    ArtistaRepo.save(obj);
                    return new ResponseEntity<>(okResponse, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND));

    }

    @Override
    public ResponseEntity<Map<String, String>> eliminarArtista(int idArt) {

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "Ese artista no fue encontrado");
        errorResponse.put("status", HttpStatus.NOT_FOUND.toString());

        Map<String, String> okResponse = new HashMap<>();
        okResponse.put("message", "El artista fue eliminado correctamente");
        okResponse.put("status", HttpStatus.OK.toString());

        return ArtistaRepo.findById(idArt).map( p -> {
                    ArtistaRepo.deleteById(idArt);
                    return new ResponseEntity<>(okResponse, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND));

    }
}
