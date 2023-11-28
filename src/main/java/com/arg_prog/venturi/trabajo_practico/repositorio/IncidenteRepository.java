//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.repositorio;

import com.arg_prog.venturi.trabajo_practico.enums.EstadoEnum;
import com.arg_prog.venturi.trabajo_practico.model.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IncidenteRepository extends JpaRepository<Incidente, Integer> {

    List<Incidente> findByfechaIngreso(LocalDate fechaIngreso);
    List<Incidente> findByestado(EstadoEnum estadoEnum);
}
