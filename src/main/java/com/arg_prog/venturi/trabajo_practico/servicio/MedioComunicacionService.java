//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.servicio;

import com.arg_prog.venturi.trabajo_practico.model.MedioComunicacion;
import com.arg_prog.venturi.trabajo_practico.repositorio.MedioComunicacionRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class MedioComunicacionService {

    MedioComunicacionRepository medioComunicacionRepository;

    @Autowired
    public MedioComunicacionService(MedioComunicacionRepository medioComunicacionRepository) {
        this.medioComunicacionRepository= medioComunicacionRepository;
    }


    public MedioComunicacion save(MedioComunicacion m){
        return this.medioComunicacionRepository.save(m);
    }

    public MedioComunicacion findById(Integer id){
        return this.medioComunicacionRepository.findById(id).get();
    }

    public void delete(MedioComunicacion m){
        medioComunicacionRepository.delete(m);
    }

    public MedioComunicacion update(MedioComunicacion medioComunicacion, Integer id){
        medioComunicacion.setId(id);
        return medioComunicacionRepository.save(medioComunicacion);

    }

    public List<MedioComunicacion> findAll() {
        return this.medioComunicacionRepository.findAll();
    }




}
