//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.model;

import com.arg_prog.venturi.trabajo_practico.enums.MedioEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medio_comunicacion")
public class MedioComunicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private MedioEnum medio;
    private String contacto;

    public MedioComunicacion(MedioEnum medio, String contacto) {
        super();
        this.medio = medio;
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return this.getMedio().toString() + ": " + this.contacto;
    }

}
