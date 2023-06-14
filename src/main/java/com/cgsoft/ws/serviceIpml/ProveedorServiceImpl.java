package com.cgsoft.ws.serviceIpml;

import com.cgsoft.ws.entity.Proveedor;
import com.cgsoft.ws.repository.ProveedorRepository;
import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProveedorServiceImpl implements ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    public List<Proveedor> getListByProceso(Proceso proceso) {
        return proveedorRepository.findAllByProceso(proceso);
    }
}
