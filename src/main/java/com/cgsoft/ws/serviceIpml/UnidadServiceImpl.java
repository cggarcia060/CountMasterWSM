package com.cgsoft.ws.serviceIpml;

import com.cgsoft.ws.entity.Unidad;
import com.cgsoft.ws.repository.UnidadRepository;
import com.cgsoft.ws.service.UnidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UnidadServiceImpl implements UnidadService {

    @Autowired
    UnidadRepository unidadRepository;
    @Override
    public List<Unidad> getListUnidad() {
        return unidadRepository.findAll();
    }


}
