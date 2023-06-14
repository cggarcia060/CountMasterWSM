package com.cgsoft.ws.repository;

import com.cgsoft.ws.entity.Compra;
import com.cgsoft.ws.security.entity.Proceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComprasRepository extends JpaRepository<Compra,Long> {


    List<Compra> findAllByProceso(Proceso proceso);


}
