package com.cgsoft.ws.serviceIpml;

import com.cgsoft.ws.dto.CompraDto;
import com.cgsoft.ws.dto.Mensaje;
import com.cgsoft.ws.entity.Compra;
import com.cgsoft.ws.exceptions.CustomException;
import com.cgsoft.ws.repository.ComprasRepository;
import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompraServiceImpl implements CompraService {

    @Autowired
    ComprasRepository comprasRepository;

    @Override
    public List<Compra> getListComprasByProceso(Proceso proceso) {
        return comprasRepository.findAllByProceso(proceso);
    }


    public Compra getById(Long id){
       return comprasRepository.findById(id).orElseThrow(()->new CustomException(HttpStatus.NOT_FOUND,"Esa compra no existe"));
    }
    @Override
    public Mensaje saveCompra(CompraDto compraDto) {
        compraDto.setTotal(compraDto.getCantidad()* compraDto.getValor());
        Compra compra= Compra.builder().nombre(compraDto.getNombre()).cantidad(compraDto.getCantidad()).fecha(compraDto.getFecha())
                .descripcion(compraDto.getDescripcion()).marca(compraDto.getMarca()).peso(compraDto.getPeso()).proveedor(compraDto.getProveedor())
                .proceso(compraDto.getProceso()).total(compraDto.getTotal()).valor_venta(compraDto.getValor_venta())
                .valor(compraDto.getValor()).unidad(compraDto.getUnidad()).build();
        comprasRepository.save(compra);
        return new Mensaje("Compra guardada exitosamente");
    }
    public Mensaje updateCompra(CompraDto compraDto) {
        Compra compra=getById(compraDto.getId());
        compraDto.setTotal(compraDto.getCantidad()* compraDto.getValor());
        compra.setNombre(compraDto.getNombre());
        compra.setCantidad(compraDto.getCantidad());
        compra.setFecha(compraDto.getFecha());
        compra.setDescripcion(compraDto.getDescripcion());
        compra.setMarca(compraDto.getMarca());
        compra.setPeso(compraDto.getPeso());
        compra.setProveedor(compraDto.getProveedor());
        compra.setProceso(compraDto.getProceso());
        compra.setTotal(compraDto.getTotal());
        compra.setValor_venta(compraDto.getValor_venta());
        compra.setValor(compraDto.getValor());
        compra.setUnidad(compraDto.getUnidad());
        comprasRepository.save(compra);
        return new Mensaje("Compra editada exitosamente");
    }
    public Mensaje deleteCompra(Long id){
        comprasRepository.deleteById(id);
        return new Mensaje("Compra Eliminada exitosamente");
    }
}
