package com.app.proyecto.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.app.proyecto.entity.Cancion;
import com.app.proyecto.repository.CancionRepository;
import org.springframework.stereotype.Service;
import com.app.proyecto.exception.ResourceNotFoundException;

@Service
public class CancionService implements ICancionService {

    @Autowired
    private CancionRepository CancRepo;


    @Override
    public ResponseEntity<Cancion> consultarUna(int idCan)
    {
        Cancion obj = CancRepo.findById(idCan).orElseThrow(() -> new ResourceNotFoundException("No existe la cancion con el Id :" + idCan));
        return ResponseEntity.ok(obj);
    }


    @Override
    public ResponseEntity<Cancion>consultarbyNombreCancion(String nombreCan)
    {
        Cancion objcan = CancRepo.findByNombreCan(nombreCan);
        if (objcan==null)
            throw new ResourceNotFoundException("No existe la cancion con el nombre :" + nombreCan);

        return ResponseEntity.ok(objcan);
    }

    //-----CRUD de canciones-----

    @Override
    public ResponseEntity<Map<String, String>> insertarCancion(Cancion obj)
    {
        Map<String, String> okResponse = new HashMap<>();
        okResponse.put("message", "La cancion se registro correctamente");
        okResponse.put("status", HttpStatus.CREATED.toString());
        CancRepo.save(obj);
        return new ResponseEntity<>(okResponse,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Map<String, String>> actualizarCancion(Cancion obj, int idCan)
    {
        Map<String, String> okResponse = new HashMap<>();
        okResponse.put("message", "La cancion se actualizo correctamente");
        okResponse.put("status", HttpStatus.OK.toString());

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "No existe una cancion con el Id: " + idCan);
        errorResponse.put("status", HttpStatus.NOT_FOUND.toString());

        return CancRepo.findById(idCan).map( p -> {
                    obj.setIdCancion(idCan);
                    CancRepo.save(obj);
                    return new ResponseEntity<>(okResponse, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND));
    }


    @Override
    public ResponseEntity<Map<String, String>> eliminarCancion(int idCan)
    {

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "La cancion no fue encontrada");
        errorResponse.put("status", HttpStatus.NOT_FOUND.toString());

        Map<String, String> okResponse = new HashMap<>();
        okResponse.put("message", "La cancion fue eliminada correctamente");
        okResponse.put("status", HttpStatus.OK.toString());

        return CancRepo.findById(idCan).map( p -> {
                    CancRepo.deleteById(idCan);
                    return new ResponseEntity<>(okResponse, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND));
    }

}
