//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.repositorio;

import com.arg_prog.venturi.trabajo_practico.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {


}
