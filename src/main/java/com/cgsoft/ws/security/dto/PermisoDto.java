package com.cgsoft.ws.security.dto;

import com.cgsoft.ws.security.entity.Proceso;
import com.cgsoft.ws.security.entity.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;



public class PermisoDto {

    private  int id;
    private Rol rol;
    private Proceso proceso;
    private boolean dashboard;
    private boolean compras;
    private boolean ventas;
    private boolean categorias;
    private boolean productos;
    private boolean inventario;
    private boolean contabilidad;
    private boolean usuarios;
    private boolean configuracion;
    private boolean proveedores;
    private boolean administracion;

    private boolean administracion_proceso;
    private boolean administracion_roles;
    private boolean administracion_usuarios;
    private boolean administracion_permisos;

    public boolean isAdministracion_proceso() {
        return administracion_proceso;
    }

    public void setAdministracion_proceso(boolean administracion_proceso) {
        this.administracion_proceso = administracion_proceso;
    }

    public boolean isAdministracion_roles() {
        return administracion_roles;
    }

    public void setAdministracion_roles(boolean administracion_roles) {
        this.administracion_roles = administracion_roles;
    }

    public boolean isAdministracion_usuarios() {
        return administracion_usuarios;
    }

    public void setAdministracion_usuarios(boolean administracion_usuarios) {
        this.administracion_usuarios = administracion_usuarios;
    }

    public boolean isAdministracion_permisos() {
        return administracion_permisos;
    }

    public void setAdministracion_permisos(boolean administracion_permisos) {
        this.administracion_permisos = administracion_permisos;
    }

    public PermisoDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    public boolean isDashboard() {
        return dashboard;
    }

    public void setDashboard(boolean dashboard) {
        this.dashboard = dashboard;
    }

    public boolean isCompras() {
        return compras;
    }

    public void setCompras(boolean compras) {
        this.compras = compras;
    }

    public boolean isVentas() {
        return ventas;
    }

    public void setVentas(boolean ventas) {
        this.ventas = ventas;
    }

    public boolean isCategorias() {
        return categorias;
    }

    public void setCategorias(boolean categorias) {
        this.categorias = categorias;
    }

    public boolean isProductos() {
        return productos;
    }

    public void setProductos(boolean productos) {
        this.productos = productos;
    }

    public boolean isInventario() {
        return inventario;
    }

    public void setInventario(boolean inventario) {
        this.inventario = inventario;
    }

    public boolean isContabilidad() {
        return contabilidad;
    }

    public void setContabilidad(boolean contabilidad) {
        this.contabilidad = contabilidad;
    }

    public boolean isUsuarios() {
        return usuarios;
    }

    public void setUsuarios(boolean usuarios) {
        this.usuarios = usuarios;
    }

    public boolean isConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(boolean configuracion) {
        this.configuracion = configuracion;
    }

    public boolean isProveedores() {
        return proveedores;
    }

    public void setProveedores(boolean proveedores) {
        this.proveedores = proveedores;
    }

    public boolean isAdministracion() {
        return administracion;
    }

    public void setAdministracion(boolean administracion) {
        this.administracion = administracion;
    }
}
