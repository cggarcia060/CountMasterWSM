package com.cgsoft.ws.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Permisos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @OneToOne
    @JoinColumn(name = "rol_id", updatable = true, nullable = false)
    private Rol rol;

    @OneToOne
    @JoinColumn(name = "proceso_id", updatable = true, nullable = false)
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

}
