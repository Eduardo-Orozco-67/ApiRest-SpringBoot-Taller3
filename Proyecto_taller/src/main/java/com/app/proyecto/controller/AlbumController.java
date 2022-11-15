package com.app.proyecto.controller;

import com.app.proyecto.entity.Album;
import com.app.proyecto.entity.Artista;
import com.app.proyecto.service.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RequestMapping("/api")
public class AlbumController {

    @Autowired
    private IAlbumService lognegocioAlbum;

    @GetMapping("/album/ver")
    public List<Album> muestratodoslosalbumes(){
        return lognegocioAlbum.consultarTodoslosAlbums();
    }

    @GetMapping("/album/ver/nombrea/{nombre}")
    public ResponseEntity<?> muestraporNombre(@PathVariable("nombre") String nom){
        return lognegocioAlbum.consultarbyNombre(nom);
    }

    @GetMapping("/album/ver/ida/{id}")
    public ResponseEntity<Album> localizar(@PathVariable("id") int idalbum){
        return lognegocioAlbum.consultarUno(idalbum);
    }

    @DeleteMapping("/album/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") int idalbum){
        return lognegocioAlbum.eliminarAlbum(idalbum);
    }

    @PostMapping("/album/guardar")
    public String insertar(@RequestBody Album objalbum){
        lognegocioAlbum.insertarAlbum(objalbum);
        return "El o El Album fue registrado correctamente";
    }

    @PutMapping("/album/actualizar/{id}")
    public String actualizar(@RequestBody Album obj, @PathVariable("id") int idalbum){
        lognegocioAlbum.actualizarAlbum(obj, idalbum);
        return "El o El Album fue actualizado correctamente";
    }

}
