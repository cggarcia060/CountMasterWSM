package com.cgsoft.ws.repository;

import com.cgsoft.ws.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
/*
    Optional<Categoria> findByNombre(String nombre);
    boolean existsByNombre(String nombre);

    @Query(value = "SELECT * FROM categorias WHERE estado=:estado", nativeQuery=true)
    List<Categoria> findByEstado(boolean estado);*/

}
