package com.cgsoft.ws.security.repository;

import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario ,Long> {

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    List<Usuario> findAllByProcesos(Proceso procesos);
    Optional<Usuario> findByTokenPassword(String tokenPassword);
    Optional<Usuario> findByNombreUsuarioOrEmail(String nombreUsuario, String email);

    boolean existsByNombreUsuario(String nombreUsuario);

    boolean existsByNombreUsuarioAndIdNot(String nombreUsuario, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByEmail(String email);


}
