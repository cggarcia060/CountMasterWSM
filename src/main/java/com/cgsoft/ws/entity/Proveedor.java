package com.cgsoft.ws.entity;

import com.cgsoft.ws.security.entity.Proceso;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedores")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private String nombre_empresa;

    @NotNull
    private int nit_empresa;

    private String direccion;

    private String descripcion;

    private String telefono;

    @OneToOne
    @JoinColumn(name = "proceso_id", updatable = true, nullable = false)
    private Proceso proceso;


}
