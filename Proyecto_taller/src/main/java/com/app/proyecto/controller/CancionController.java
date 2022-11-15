package com.app.proyecto.controller;


import java.util.List;

import com.app.proyecto.entity.Artista;
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
import com.app.proyecto.entity.Cancion;
import com.app.proyecto.service.ICancionService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RequestMapping("/api")
public class CancionController {

    @Autowired
    private ICancionService lognegocioCancion;

    @GetMapping("/canciones/ver")
    public List<Cancion> muestratodasCanciones(){
        return lognegocioCancion.consultarTodaslasCanciones();
    }

    @GetMapping("/canciones/ver/{nombreCancion}")
    public ResponseEntity<?> muestraporNombreCan(@PathVariable("nombreCancion") String nombreCan){
        return lognegocioCancion.consultarbyNombreCancion(nombreCan);
    }

    @GetMapping("/canciones/ver/{id}")
    public ResponseEntity<Cancion> localizar(@PathVariable("id") int idCan){
        return lognegocioCancion.consultarUna(idCan);
    }

    @PostMapping("/canciones/guardar")
    public String insertar(@RequestBody Cancion objcan){
        lognegocioCancion.insertarCancion(objcan);
        return "La cancion fue registrada correctamente";
    }

    @PutMapping("/canciones/actualizar/{id}")
    public String actualizar(@RequestBody Cancion obj, @PathVariable("id") int idCan){
        lognegocioCancion.actualizarCancion(obj, idCan);
        return "La cancion fue actualizada correctamente";
    }

    @DeleteMapping("/canciones/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") int idCan){
        return lognegocioCancion.eliminarCancion(idCan);

    }

}
