//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.servicio;

import com.arg_prog.venturi.trabajo_practico.model.Servicio;
import com.arg_prog.venturi.trabajo_practico.model.Tecnico;
import com.arg_prog.venturi.trabajo_practico.repositorio.ServicioRepository;
import com.arg_prog.venturi.trabajo_practico.repositorio.TecnicoRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class ServicioService {

    ServicioRepository serviceRepository;

    @Autowired
    public ServicioService(ServicioRepository serviceRepository) {
        this.serviceRepository= serviceRepository;
    }


    public Servicio save(Servicio s){
        return this.serviceRepository.save(s);
    }

    public Servicio findById(Integer id){
        return this.serviceRepository.findById(id).get();
    }

    public void delete(Servicio s){
        serviceRepository.delete(s);
    }

    public Servicio update(Servicio servicio, Integer id){
       servicio.setId(id);
        return serviceRepository.save(servicio);

    }

    public List<Servicio> findAll() {
        return this.serviceRepository.findAll();
    }



}
