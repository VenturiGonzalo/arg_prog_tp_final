//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.servicio;

import com.arg_prog.venturi.trabajo_practico.model.Especialidad;
import com.arg_prog.venturi.trabajo_practico.repositorio.EspecialidadRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class EspecialidadService {
    
    EspecialidadRepository especialidadRepository;


    @Autowired
    public EspecialidadService( EspecialidadRepository especialidadRepository) {
        this.especialidadRepository= especialidadRepository;
    }


    public Especialidad save(Especialidad e){
        return this.especialidadRepository.save(e);
    }

    public List<Especialidad> saveAll(List<Especialidad> es){
        return this.especialidadRepository.saveAll(es);
    }


    public  Especialidad findById(Integer id){
        return this.especialidadRepository.findById(id).get();
    }

    public  List<Especialidad> findByNombre(String nombre){
        return this.especialidadRepository.findBynombre(nombre);
    }
    public void delete( Especialidad e){
        especialidadRepository.delete(e);
    }

    public  Especialidad update( Especialidad especialidad, Integer id){
       especialidad.setId(id);
        return especialidadRepository.save(especialidad);

    }

    public List< Especialidad> findAll() {
        return this.especialidadRepository.findAll();
    }



}
