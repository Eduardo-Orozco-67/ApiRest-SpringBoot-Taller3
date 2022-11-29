package com.app.proyecto.controller;


import com.app.proyecto.entity.Album;
import com.app.proyecto.exception.ResourceNotFoundException;
import com.app.proyecto.repository.AlbumRepository;
import com.app.proyecto.repository.CancionRepository;
import com.app.proyecto.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.proyecto.entity.Cancion;
import com.app.proyecto.service.ICancionService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RequestMapping("/api")
public class CancionController {

    @Autowired
    private ICancionService lognegocioCancion;

    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PlaylistRepository playlistRepository;


    @GetMapping("/canciones/ver")
    public ResponseEntity<List<Cancion>> ver_todas_las_canciones() {
        List<Cancion> tags = new ArrayList<Cancion>();

        cancionRepository.findAll().forEach(tags::add);

        if (tags.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/canciones/ver/album/{AlbumId}")
    public ResponseEntity<List<Cancion>> muestra_canciones_por_album(@PathVariable(value = "AlbumId") Integer AlbumId) {
        if (!albumRepository.existsById(AlbumId)) {
            throw new ResourceNotFoundException("Not found Album with id = " + AlbumId);
        }

        List<Cancion> tags = cancionRepository.findCancionesByalbumId(AlbumId);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/canciones/ver/nombrec/{nombreCancion}")
    public ResponseEntity<?> muestraporNombreCan(@PathVariable("nombreCancion") String nombreCan){
        return lognegocioCancion.consultarbyNombreCancion(nombreCan);
    }

    @GetMapping("/canciones/ver/idc/{id}")
    public ResponseEntity<Cancion>  VerPorId(@PathVariable(value = "id") Integer id) {
        Cancion tag = cancionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Song with id = " + id));

        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @PostMapping("/canciones/{AlbumId}/guardar")
    public ResponseEntity<Cancion> insertar(@PathVariable(value = "AlbumId") Integer albumid, @RequestBody Cancion cancion) {
        Cancion tag = albumRepository.findById(albumid).map(album -> {
            int tagId = cancion.getIdCancion();

            // tag is existed
            if (tagId != 0L) {
                Cancion _tag = cancionRepository.findById(tagId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Song with id = " + tagId));
                album.addCancion(_tag);
                albumRepository.save(album);
                return _tag;
            }

            // add and create new Tag
            album.addCancion(cancion);
            return cancionRepository.save(cancion);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Album with id = " + albumid));

        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }


    @PostMapping("/cancion/{PlaylisId}/guardar2")
    public ResponseEntity<Cancion> insertar2(@PathVariable(value = "PlaylisId") Integer playid, @RequestBody Cancion cancion) {
        Cancion tag = playlistRepository.findById(playid).map(playlist -> {
            int tagId = cancion.getIdCancion();

            // tag is existed
            if (tagId != 0L) {
                Cancion _tag = cancionRepository.findById(tagId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Song with id = " + tagId));
                playlist.addCancion(_tag);
                playlistRepository.save(playlist);
                return _tag;
            }

            playlist.addCancion(cancion);
            return cancionRepository.save(cancion);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Album with id = " + playid));

        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }

    @PostMapping("/cancion/{cancionId}/{albumId}/guardar4")
    public ResponseEntity<Cancion> insertar_cancion_album(@PathVariable(value = "cancionId") Integer canid, @PathVariable(value = "albumId") Integer albid)  {

        Cancion tag = albumRepository.findById(albid).map(album -> {
            int tagId = canid;

            // tag is existed
            if (tagId != 0L) {
                Cancion _tag = cancionRepository.findById(tagId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Song with id = " + tagId));
                album.addCancion2(_tag);
                albumRepository.save(album);
                return _tag;
            }

            return null;
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Album with id = " + albid));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cancion/{cancionId}/{playId}/guardar3")
    public ResponseEntity<Cancion> insertar_cancion_playlist(@PathVariable(value = "cancionId") Integer canid, @PathVariable(value = "playId") Integer playid)  {

        Cancion tag = playlistRepository.findById(playid).map(playlist -> {
            int tagId = canid;

            // tag is existed
            if (tagId != 0L) {
                Cancion _tag = cancionRepository.findById(tagId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Song with id = " + tagId));
                playlist.addCancion2(_tag);
               playlistRepository.save(playlist);
                return _tag;
            }

            return null;
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Album with id = " + playid));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/canciones/actualizar/{id}")
    public String actualizar(@RequestBody Cancion obj, @PathVariable("id") int idCan){
        lognegocioCancion.actualizarCancion(obj, idCan);
        return "La cancion fue actualizada correctamente";
    }

    @DeleteMapping("/cancion/{AlbumId}/canciones/{CancionId}/eliminar")
    public ResponseEntity<HttpStatus> eliminar_de_un_album(@PathVariable(value = "AlbumId") Integer albumid, @PathVariable(value = "CancionId") Integer cancionid) {
        Album album = albumRepository.findById(albumid)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Album with id = " + albumid));

        album.removeCancion(cancionid);
        albumRepository.save(album);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/canciones/eliminar/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Integer id) {
        cancionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.app.proyecto.controller;


import com.app.proyecto.entity.Album;
import com.app.proyecto.exception.ResourceNotFoundException;
import com.app.proyecto.repository.AlbumRepository;
import com.app.proyecto.repository.CancionRepository;
import com.app.proyecto.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.proyecto.entity.Cancion;
import com.app.proyecto.service.ICancionService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RequestMapping("/api")
public class CancionController {

    @Autowired
    private ICancionService lognegocioCancion;

    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PlaylistRepository playlistRepository;


    @GetMapping("/canciones/ver")
    public ResponseEntity<List<Cancion>> ver_todas_las_canciones() {
        List<Cancion> tags = new ArrayList<Cancion>();

        cancionRepository.findAll().forEach(tags::add);

        if (tags.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/canciones/ver/album/{AlbumId}")
    public ResponseEntity<List<Cancion>> muestra_canciones_por_album(@PathVariable(value = "AlbumId") Integer AlbumId) {
        if (!albumRepository.existsById(AlbumId)) {
            throw new ResourceNotFoundException("Not found Album with id = " + AlbumId);
        }

        List<Cancion> tags = cancionRepository.findCancionesByalbumId(AlbumId);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/canciones/ver/nombrec/{nombreCancion}")
    public ResponseEntity<?> muestraporNombreCan(@PathVariable("nombreCancion") String nombreCan){
        return lognegocioCancion.consultarbyNombreCancion(nombreCan);
    }

    @GetMapping("/canciones/ver/idc/{id}")
    public ResponseEntity<Cancion>  VerPorId(@PathVariable(value = "id") Integer id) {
        Cancion tag = cancionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Song with id = " + id));

        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @PostMapping("/canciones/{AlbumId}/guardar")
    public ResponseEntity<Cancion> insertar(@PathVariable(value = "AlbumId") Integer albumid, @RequestBody Cancion cancion) {
        Cancion tag = albumRepository.findById(albumid).map(album -> {
            int tagId = cancion.getIdCancion();

            // tag is existed
            if (tagId != 0L) {
                Cancion _tag = cancionRepository.findById(tagId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Song with id = " + tagId));
                album.addCancion(_tag);
                albumRepository.save(album);
                return _tag;
            }

            // add and create new Tag
            album.addCancion(cancion);
            return cancionRepository.save(cancion);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Album with id = " + albumid));

        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }


    @PostMapping("/cancion/{PlaylisId}/guardar2")
    public ResponseEntity<Cancion> insertar2(@PathVariable(value = "PlaylisId") Integer playid, @RequestBody Cancion cancion) {
        Cancion tag = playlistRepository.findById(playid).map(playlist -> {
            int tagId = cancion.getIdCancion();

            // tag is existed
            if (tagId != 0L) {
                Cancion _tag = cancionRepository.findById(tagId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Song with id = " + tagId));
                playlist.addCancion(_tag);
                playlistRepository.save(playlist);
                return _tag;
            }

            playlist.addCancion(cancion);
            return cancionRepository.save(cancion);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Album with id = " + playid));

        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }

    @PostMapping("/cancion/{cancionId}/{albumId}/guardar4")
    public ResponseEntity<Cancion> insertar_cancion_album(@PathVariable(value = "cancionId") Integer canid, @PathVariable(value = "albumId") Integer albid, @RequestBody Cancion cancion)  {

        Cancion tag = albumRepository.findById(albid).map(album -> {
            int tagId = canid;

            // tag is existed
            if (tagId != 0L) {
                Cancion _tag = cancionRepository.findById(tagId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Song with id = " + tagId));
                album.addCancion2(_tag);
                albumRepository.save(album);
                return _tag;
            }

            return cancionRepository.save(cancion);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Album with id = " + albid));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/cancion/{cancionId}/{playId}/guardar3")
    public ResponseEntity<Cancion> insertar_cancion_playlist(@PathVariable(value = "cancionId") Integer canid, @PathVariable(value = "playId") Integer playid, @RequestBody Cancion cancion)  {

        Cancion tag = playlistRepository.findById(playid).map(playlist -> {
            int tagId = canid;

            // tag is existed
            if (tagId != 0L) {
                Cancion _tag = cancionRepository.findById(tagId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Song with id = " + tagId));
                playlist.addCancion2(_tag);
               playlistRepository.save(playlist);
                return _tag;
            }

            return cancionRepository.save(cancion);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Album with id = " + playid));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/canciones/actualizar/{id}")
    public String actualizar(@RequestBody Cancion obj, @PathVariable("id") int idCan){
        lognegocioCancion.actualizarCancion(obj, idCan);
        return "La cancion fue actualizada correctamente";
    }

    @DeleteMapping("/cancion/{AlbumId}/canciones/{CancionId}/eliminar")
    public ResponseEntity<HttpStatus> eliminar_de_un_album(@PathVariable(value = "AlbumId") Integer albumid, @PathVariable(value = "CancionId") Integer cancionid) {
        Album album = albumRepository.findById(albumid)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Album with id = " + albumid));

        album.removeCancion(cancionid);
        albumRepository.save(album);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/canciones/eliminar/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Integer id) {
        cancionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

