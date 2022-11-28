package com.app.proyecto.controller;

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

    @PostMapping("/playlist/{UserId}/guardar")
    public Playlist guardarPlaylist(@PathVariable(value = "UserId") Integer Userid,@Valid @RequestBody Playlist playlists) {
        return usuarioRepository.findById(Userid).map(usuario -> {
            playlists.setUsuario(usuario);
            return playlistRepository.save(playlists);
        }).orElseThrow(() -> new ResourceNotFoundException("Usuario con el ID : " + Userid + " no encontrado(a)"));
    }

    /*@PostMapping("/cancion/{CancionId}/guardar")
    public ResponseEntity<Cancion> insertar2(@PathVariable(value = "CancionId") Integer cancionid, @RequestBody Playlist playlist) {
        Playlist play = cancionRepository.findById(cancionid).map(cancion -> {
            int tagId = playlist.getId_playlist();

            // tag is existed
            if (tagId != 0L) {
                Cancion _tag = cancionRepository.findById(tagId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Song with id = " + tagId));
                playlist.addCancion(_tag);
                playlistRepository.save(playlist);
                return _tag;
            }

            return null;
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Album with id = " + cancionid));

        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }*/

}
