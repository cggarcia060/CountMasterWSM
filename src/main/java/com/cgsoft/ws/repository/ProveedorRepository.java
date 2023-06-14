package com.cgsoft.ws.repository;

import com.cgsoft.ws.entity.Proveedor;
import com.cgsoft.ws.security.entity.Proceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Long> {


    List<Proveedor> findAllByProceso(Proceso proceso);
}
