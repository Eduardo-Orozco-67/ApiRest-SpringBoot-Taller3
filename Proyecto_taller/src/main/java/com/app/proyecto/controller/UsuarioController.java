package com.app.proyecto.controller;

import java.util.List;

import com.app.proyecto.entity.Usuario;
import com.app.proyecto.service.ICancionService;
import com.app.proyecto.service.IUsuarioService;
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

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private IUsuarioService lognegocioUser;

    @GetMapping("/usuarios/ver")
    public List<Usuario> muestratodosUsuarios() {
        return lognegocioUser.consultarTodoslosUsuarios();
    }

    @GetMapping("/usuarios/ver/nombreu/{nombre}")
    public ResponseEntity<?> muestraporNombreUsuario(@PathVariable("nombre") String nom) {
        return lognegocioUser.consultarbyNombre(nom);
    }

    @GetMapping("/usuarios/ver/idu/{id}")
    public ResponseEntity<Usuario> localizar(@PathVariable("id") int idUser) {
        return lognegocioUser.consultarUnUsuario(idUser);
    }

    @PutMapping("/usuarios/actualizar/{id}")
    public String actualizar(@RequestBody Usuario obj, @PathVariable("id") int idUser) {
        lognegocioUser.actualizarUsuario(obj, idUser);
        return "El Usuario fue actualizado(a) correctamente";
    }

    @DeleteMapping("/usuarios/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") int idUser) {
        return lognegocioUser.eliminarUsuario(idUser);

    }

    @PostMapping("/usuarios/guardar")
    public String insertar(@RequestBody Usuario objart) {
        lognegocioUser.insertarUsuario(objart);
        return "El o La artista fue registrado(a) correctamente";
    }
}
