package com.app.proyecto.repository;

import com.app.proyecto.entity.Artista;
import com.app.proyecto.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select o from Usuario o where o.nombre_u like ?1")
    Usuario findByNombre (String nombre);
}
