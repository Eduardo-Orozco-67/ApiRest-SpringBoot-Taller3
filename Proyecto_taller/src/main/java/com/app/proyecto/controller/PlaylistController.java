package com.app.proyecto.controller;

import java.util.List;

import com.app.proyecto.entity.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.proyecto.service.IPlaylistService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RequestMapping("/api")
public class PlaylistController {
    @Autowired
    private IPlaylistService lognegocioPlaylist;

    @GetMapping("/playlist/ver")
    public List<Playlist> muestratodosArtistas(){
        return lognegocioPlaylist.consultarTodoslosArtistas();
    }

    @GetMapping("/playlist/ver/nombrepl/{nombre}")
    public ResponseEntity<?> muestraporNombre(@PathVariable("nombre") String nom){
        return lognegocioPlaylist.consultarbyNombredePlaylis(nom);
    }

    @GetMapping("/playlist/ver/idpl/{id}")
    public ResponseEntity<Playlist> localizar(@PathVariable("id") int idCat){
        return lognegocioPlaylist.consultarUno(idCat);
    }

    @PutMapping("/playlist/actualizar/{id}")
    public String actualizar(@RequestBody Playlist obj, @PathVariable("id") int idPl){
        lognegocioPlaylist.actualizarPlaylist(obj, idPl);
        return "El o La artista fue actualizado(a) correctamente";
    }

    @DeleteMapping("/playlist/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") int idPl){
        return lognegocioPlaylist.eliminarPlaylist(idPl);

    }

    @PostMapping("/playlist/guardar")
    public String insertar(@RequestBody Playlist objplay){
        lognegocioPlaylist.insertarArtista(objplay);
        return "El o La artista fue registrado(a) correctamente";
    }
}
