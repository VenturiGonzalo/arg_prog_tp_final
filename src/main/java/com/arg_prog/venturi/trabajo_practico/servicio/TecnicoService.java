//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.servicio;
import java.util.List;

import com.arg_prog.venturi.trabajo_practico.model.Tecnico;
import com.arg_prog.venturi.trabajo_practico.repositorio.TecnicoRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class TecnicoService {

    TecnicoRepository tecnicoRepository;

    @Autowired
    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository= tecnicoRepository;
    }


    public Tecnico save(Tecnico t){
        return this.tecnicoRepository.save(t);
    }

    public Tecnico findById(Integer id){
        return this.tecnicoRepository.findById(id).get();
    }

    public void delete(Tecnico t){
        tecnicoRepository.delete(t);
    }

    public Tecnico update(Tecnico tecnico, Integer id){
        tecnico.setId(id);
        return tecnicoRepository.save(tecnico);
    }

    public List<Tecnico> findAll() {
        return this.tecnicoRepository.findAll();
    }


}
