//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String razonSocial;
    private String cuit;
    private String email;
    private String nombre;
    private String apellido;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "cliente_servicio",
            joinColumns = @JoinColumn(name = "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_servicio"))
    private List<Servicio> serviciosContratados;


    public Cliente(String razonSocial, String cuit, String email, String nombre, String apellido, List<Servicio> servicios) {
        super();
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.serviciosContratados = servicios;
    }


    @Override
    public String toString() {
        return "id: " + this.id + " | Razon Social: " + this.razonSocial + " | CUIT: " + this.cuit + "\n";
    }

    public String toStringLarge() {
        return "id: " + this.id +
                "\nRazon Social: " + this.razonSocial +
                "\nCUIT: " + this.cuit +
                "\neMail: " + this.email +
                "\nNombre: " + this.nombre +
                "\nApellido: " + this.apellido +
                "\nServicios: " + this.serviciosContratados.stream().map(servicio -> servicio.toStringList()).collect(Collectors.joining());


    }


}
