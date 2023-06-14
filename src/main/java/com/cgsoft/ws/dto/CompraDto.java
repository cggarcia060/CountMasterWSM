package com.cgsoft.ws.dto;

import com.cgsoft.ws.entity.Proveedor;
import com.cgsoft.ws.entity.Unidad;
import com.cgsoft.ws.security.entity.Proceso;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CompraDto {

    private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;


    private String marca;

    private String peso;
    @Min(1)
    private int cantidad;
    @Min(0)
    private float valor;

    private float valor_venta;

    private Unidad unidad;
    private String descripcion;

    @NotBlank(message = "La fecha es obligatorio")
    private String fecha;

    private Proveedor proveedor;
    private float total;
    private Proceso proceso;

    public CompraDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public float getValor_venta() {
        return valor_venta;
    }

    public void setValor_venta(float valor_venta) {
        this.valor_venta = valor_venta;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }
}
