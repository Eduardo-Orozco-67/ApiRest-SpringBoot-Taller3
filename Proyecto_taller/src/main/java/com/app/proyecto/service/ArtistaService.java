package com.app.proyecto.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.app.proyecto.entity.Artista;
import com.app.proyecto.repository.ArtistaRepository;
import com.app.proyecto.service.IArtistasService;
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
            throw new ResourceNotFoundException("No existe categorías registradas en la BD");

        return ArtistaRepo.findAll();
    }

}
