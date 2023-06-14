package com.cgsoft.ws.repository;

import com.cgsoft.ws.entity.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadRepository extends JpaRepository<Unidad,Long> {

}
