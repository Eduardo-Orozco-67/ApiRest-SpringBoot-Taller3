package com.app.proyecto.service;

import com.app.proyecto.entity.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IUsuarioService {

    List<Usuario> consultarTodoslosUsuarios();

    ResponseEntity<Usuario> consultarUnUsuario(int idUser);

    ResponseEntity<Usuario>consultarbyNombre(String nombre);

    ResponseEntity<Map<String, String>> insertarUsuario(Usuario obj);

    ResponseEntity<Map<String, String>> actualizarUsuario(Usuario obj, int idUser);

    ResponseEntity<Map<String, String>> eliminarUsuario(int idUser);
}
