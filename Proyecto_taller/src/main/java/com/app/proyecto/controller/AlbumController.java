package com.app.proyecto.controller;


import javax.validation.Valid;
import com.app.proyecto.entity.Album;
import com.app.proyecto.entity.Artista;
import com.app.proyecto.entity.Cancion;
import com.app.proyecto.exception.ResourceNotFoundException;
import com.app.proyecto.repository.AlbumRepository;
import com.app.proyecto.repository.ArtistaRepository;
import com.app.proyecto.repository.CancionRepository;
import com.app.proyecto.service.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RequestMapping("/api")
public class AlbumController {

    @Autowired
    private IAlbumService lognegocioAlbum;

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private CancionRepository cancionRepository;

    @GetMapping("/album/ver")
    public ResponseEntity<Collection<Album>>muestratodoslosalbumes(){
        return new ResponseEntity<>(albumRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/album/ver/nombrea/{nombre}")
    public ResponseEntity<?> muestraporNombre(@PathVariable("nombre") String nom){
        return lognegocioAlbum.consultarbyNombre(nom);
    }

    @GetMapping("/album/ver/ida/{id}")
    public ResponseEntity<Album> localizar(@PathVariable("id") int idalbum){
        return lognegocioAlbum.consultarUno(idalbum);
    }

    @PostMapping("/album/{ArtistaId}/guardar")
    public Album guardarAlbum(@PathVariable(value = "ArtistaId") Integer Artistaid,@Valid @RequestBody Album album) {
        return artistaRepository.findById(Artistaid).map(artista -> {
            album.setArtista(artista);
            return albumRepository.save(album);
        }).orElseThrow(() -> new ResourceNotFoundException("Artista con el ID : " + Artistaid + " no encontrada"));
    }

    @PutMapping("/album/actualizar/{id}")
    public ResponseEntity<Album> actualizar(@PathVariable("id") int id, @RequestBody Album album) {
        Album _album = albumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Album with id = " + id));

        _album.setNombre_album(album.getNombre_album());
        _album.setLanzamiento(album.getLanzamiento());
        _album.setImagen_url(album.getImagen_url());
        _album.setNo_canciones(album.getNo_canciones());
        _album.setTipo(album.getTipo());
        _album.setProductora(album.getProductora());

        return new ResponseEntity<>(albumRepository.save(_album), HttpStatus.OK);
    }


    @DeleteMapping("/album/eliminar/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") int id) {
        albumRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
