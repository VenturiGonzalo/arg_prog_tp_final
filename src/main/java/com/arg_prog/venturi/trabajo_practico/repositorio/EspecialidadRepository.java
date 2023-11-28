//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.repositorio;

import com.arg_prog.venturi.trabajo_practico.model.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {
    List<Especialidad> findBynombre(String nombre);

}
