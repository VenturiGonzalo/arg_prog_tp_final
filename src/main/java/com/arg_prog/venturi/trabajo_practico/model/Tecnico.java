//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tecnico")
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;

    @ManyToOne
    @JoinColumn(name="medio_comunicacion_id", referencedColumnName="id")
    private MedioComunicacion medioComunicacion;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "tecnico_especialidad",
            joinColumns = @JoinColumn(name = "id_tecnico"),
            inverseJoinColumns = @JoinColumn(name = "id_especialidad"))
    private List<Especialidad> especialidades;


    public Tecnico(String nombre, String apellido, MedioComunicacion medioComunicacion, List<Especialidad> especialidades) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.medioComunicacion = medioComunicacion;
        this.especialidades = especialidades;
    }

    public boolean sosApto(Especialidad especialidad){
        return this.especialidades.contains(especialidad);
    }

    @Override
    public String toString() {
        return "id: " + this.id + " | Nombre Completo: " + this.nombre + " " + this.apellido;
    }

    public String toStringLarge() {
        return "id: " + this.id +
                "\nNombre: " + this.nombre +
                "\nApellido: " + this.apellido +
                "\nMedio de Comunicacion: " + this.medioComunicacion.toString() +
                "\nEspecialidades: " + this.especialidades.stream().map(especialidad -> especialidad.toStringList()).collect(Collectors.joining());


    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.id, ((Tecnico) obj).getId());
    }
}
