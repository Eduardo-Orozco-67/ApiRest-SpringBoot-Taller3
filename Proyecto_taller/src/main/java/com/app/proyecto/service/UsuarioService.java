package com.app.proyecto.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.proyecto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.app.proyecto.entity.Usuario;
import org.springframework.stereotype.Service;
import com.app.proyecto.exception.ResourceNotFoundException;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private UsuarioRepository UsuarioRepo;

    @Override
    public List<Usuario> consultarTodoslosUsuarios() {
        List<Usuario> result = UsuarioRepo.findAll();
        if(result.isEmpty())
            throw new ResourceNotFoundException("No existe ese usuario registrado(a) en la BD");
        return UsuarioRepo.findAll();
    }

    @Override
    public ResponseEntity<Usuario> consultarUnUsuario(int idUser) {
        Usuario obj = UsuarioRepo.findById(idUser).orElseThrow(() -> new ResourceNotFoundException("No existe el Usuario con el Id :" + idUser));
        return ResponseEntity.ok(obj);
    }

    @Override
    public ResponseEntity<Usuario>consultarbyNombre(String nombre) {
        Usuario objart = UsuarioRepo.findByNombre(nombre);
        if (objart==null)
            throw new ResourceNotFoundException("No existe un Usuario con el nombre :" + nombre);
        return ResponseEntity.ok(objart);
    }

    @Override
    public ResponseEntity<Map<String, String>> insertarUsuario(Usuario obj) {
        Map<String, String> okResponse = new HashMap<>();
        okResponse.put("message", "El Usuario se ha registrado correctamente");
        okResponse.put("status", HttpStatus.CREATED.toString());
        UsuarioRepo.save(obj);
        return new ResponseEntity<>(okResponse,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Map<String, String>> actualizarUsuario(Usuario obj, int idUser) {
        Map<String, String> okResponse = new HashMap<>();
        okResponse.put("message", "El Usuario se actualizo correctamente");
        okResponse.put("status", HttpStatus.OK.toString());

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "No existe un Usuario con el Id: " + idUser);
        errorResponse.put("status", HttpStatus.NOT_FOUND.toString());

        return UsuarioRepo.findById(idUser).map(p -> {
                    obj.setId_usuario(idUser);
                    UsuarioRepo.save(obj);
                    return new ResponseEntity<>(okResponse, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Map<String, String>> eliminarUsuario(int idUser) {

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "Ese Usuario no fue encontrado");
        errorResponse.put("status", HttpStatus.NOT_FOUND.toString());

        Map<String, String> okResponse = new HashMap<>();
        okResponse.put("message", "El Usuario fue eliminado correctamente");
        okResponse.put("status", HttpStatus.OK.toString());

        return UsuarioRepo.findById(idUser).map(p -> {
                    UsuarioRepo.deleteById(idUser);
                    return new ResponseEntity<>(okResponse, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND));

    }
}


