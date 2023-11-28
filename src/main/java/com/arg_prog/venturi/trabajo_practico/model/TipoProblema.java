//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipo_problema")
public class TipoProblema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tipo;
    private int tiempoEstimado;
    private int tiempoMaximo;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "tipo_problema_especialidad",
            joinColumns = @JoinColumn(name = "id_tipo_problema"),
            inverseJoinColumns = @JoinColumn(name = "id_especialidad"))
    private List<Especialidad> especialidades;


    public TipoProblema(String tipo, int tiempoEstimado, int tiempoMaximo, List<Especialidad> especialidades) {
        super();
        this.tipo = tipo;
        this.tiempoEstimado = tiempoEstimado;
        this.tiempoMaximo = tiempoMaximo;
        this.especialidades = especialidades;
    }


}
