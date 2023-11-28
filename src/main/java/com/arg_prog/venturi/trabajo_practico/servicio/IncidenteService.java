//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.servicio;

import com.arg_prog.venturi.trabajo_practico.enums.EstadoEnum;
import com.arg_prog.venturi.trabajo_practico.model.Cliente;
import com.arg_prog.venturi.trabajo_practico.model.Incidente;
import com.arg_prog.venturi.trabajo_practico.repositorio.ClienteRepository;
import com.arg_prog.venturi.trabajo_practico.repositorio.IncidenteRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@NoArgsConstructor
public class IncidenteService {

    IncidenteRepository incidenteRepository;

    @Autowired
    public IncidenteService(IncidenteRepository incidenteRepository) {
        this.incidenteRepository = incidenteRepository;
    }


    public Incidente save(Incidente i){
        return this.incidenteRepository.save(i);
    }

    public Incidente findById(Integer id){
        return this.incidenteRepository.findById(id).get();
    }


    public void delete(Incidente i){
        incidenteRepository.delete(i);
    }

    public Incidente update(Incidente incidente, Integer id){
        incidente.setId(id);
        return incidenteRepository.save(incidente);
    }

    public List<Incidente> findAll() {
        return this.incidenteRepository.findAll();
    }


    public List<Incidente> findDiario() {
        return incidenteRepository.findByfechaIngreso(LocalDate.now());
    }

    public List<Incidente> findByEstado(EstadoEnum estadoEnum){
        return incidenteRepository.findByestado(estadoEnum);
    }

}
