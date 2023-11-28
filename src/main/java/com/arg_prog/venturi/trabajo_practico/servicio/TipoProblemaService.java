//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.servicio;

import com.arg_prog.venturi.trabajo_practico.model.TipoProblema;
import com.arg_prog.venturi.trabajo_practico.repositorio.TipoProblemaRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class TipoProblemaService {

    TipoProblemaRepository tipoProblemaRepository;

    @Autowired
    public TipoProblemaService(TipoProblemaRepository tipoProblemaRepository) {
        this.tipoProblemaRepository= tipoProblemaRepository;
    }

    public TipoProblema save(TipoProblema t){
        return this.tipoProblemaRepository.save(t);
    }

    public TipoProblema findById(Integer id){
        return this.tipoProblemaRepository.findById(id).get();
    }

    public void delete(TipoProblema t){
        tipoProblemaRepository.delete(t);
    }

    public TipoProblema update(TipoProblema tipoProblema, Integer id){
        tipoProblema.setId(id);
        return tipoProblemaRepository.save(tipoProblema);
    }

    public List<TipoProblema> findAll() {
        return this.tipoProblemaRepository.findAll();
    }


}
