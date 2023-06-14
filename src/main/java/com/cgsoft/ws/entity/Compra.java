package com.cgsoft.ws.entity;

import com.cgsoft.ws.security.entity.Proceso;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "compras")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String nombre;

    private String marca;

    private String peso;

    private int cantidad;

    private float valor;

    private float valor_venta;


    private String descripcion;

    private String fecha;

    @OneToOne
    @JoinColumn(name = "proveedor_id", updatable = true, nullable = false)
    private Proveedor proveedor;

    @OneToOne
    @JoinColumn(name = "unidad_id", updatable = true, nullable = false)
    private Unidad unidad;


    private float total;

    @OneToOne
    @JoinColumn(name = "proceso_id", updatable = true, nullable = false)
    private Proceso proceso;



}
