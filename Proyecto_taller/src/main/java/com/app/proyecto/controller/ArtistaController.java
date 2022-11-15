package com.app.proyecto.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.app.proyecto.entity.Artista;
import com.app.proyecto.service.IArtistasService;


@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RequestMapping("/api")
public class ArtistaController {

    @Autowired
    private IArtistasService lognegocioArtista;

    @GetMapping("/artistas/ver")
    public List<Artista> muestratodosArtistas(){
        return lognegocioArtista.consultarTodoslosArtistas();
    }

    @GetMapping("/artistas/ver/nombrear/{nombre}")
    public ResponseEntity<?> muestraporNombre(@PathVariable("nombre") String nom){
        return lognegocioArtista.consultarbyNombre(nom);
    }

    @GetMapping("/artistas/ver/idar/{id}")
    public ResponseEntity<Artista> localizar(@PathVariable("id") int idArt){
        return lognegocioArtista.consultarUno(idArt);
    }

    @PutMapping("/artistas/actualizar/{id}")
    public String actualizar(@RequestBody Artista obj, @PathVariable("id") int idArt){
        lognegocioArtista.actualizarArtista(obj, idArt);
        return "El o La artista fue actualizado(a) correctamente";
    }

    @DeleteMapping("/artistas/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") int idArt){
        return lognegocioArtista.eliminarArtista(idArt);

    }

    @PostMapping("/artistas/guardar")
    public String insertar(@RequestBody Artista objart){
        lognegocioArtista.insertarArtista(objart);
        return "El o La artista fue registrado(a) correctamente";
    }
}