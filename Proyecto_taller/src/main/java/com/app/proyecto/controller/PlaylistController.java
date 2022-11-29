package com.app.proyecto.controller;

import com.app.proyecto.entity.Album;
import com.app.proyecto.entity.Cancion;
import com.app.proyecto.entity.Playlist;
import com.app.proyecto.exception.ResourceNotFoundException;
import com.app.proyecto.repository.CancionRepository;
import com.app.proyecto.repository.PlaylistRepository;
import com.app.proyecto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.proyecto.service.IPlaylistService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RequestMapping("/api")
public class PlaylistController {
    @Autowired
    private IPlaylistService lognegocioPlaylist;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CancionRepository cancionRepository;

    @GetMapping("/playlist/ver")
    public ResponseEntity<Collection<Playlist>>muestratodoslasPlaylist(){
        return new ResponseEntity<>(playlistRepository.findAll(), HttpStatus.OK);
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
    public ResponseEntity<Playlist> actualizar(@PathVariable("id") int id, @RequestBody Playlist playlist) {
        Playlist playlist1 = playlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Album with id = " + id));

        playlist1.setNombre(playlist.getNombre());
        playlist1.setUsuario(playlist.getUsuario());
        playlist1.setFecha_creacion(playlist.getFecha_creacion());
        playlist1.setCanciones(playlist.getCanciones());
        return new ResponseEntity<>(playlistRepository.save(playlist1), HttpStatus.OK);
    }

    @DeleteMapping("/playlist/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") int idPl){
        return lognegocioPlaylist.eliminarPlaylist(idPl);

    }

    @PostMapping("/playlist/{UserId}/guardar")
    public Playlist guardarPlaylist(@PathVariable(value = "UserId") Integer Userid,@Valid @RequestBody Playlist playlists) {
        return usuarioRepository.findById(Userid).map(usuario -> {
            playlists.setUsuario(usuario);
            return playlistRepository.save(playlists);
        }).orElseThrow(() -> new ResourceNotFoundException("Usuario con el ID : " + Userid + " no encontrado(a)"));
    }
}
