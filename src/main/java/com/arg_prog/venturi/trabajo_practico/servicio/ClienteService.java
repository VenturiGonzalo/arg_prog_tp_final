//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.servicio;

import com.arg_prog.venturi.trabajo_practico.model.Cliente;
import com.arg_prog.venturi.trabajo_practico.repositorio.ClienteRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class ClienteService {

    ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository= clienteRepository;
    }


    public Cliente save(Cliente t){
        return this.clienteRepository.save(t);
    }

    public Cliente findById(Integer id){
        return this.clienteRepository.findById(id).get();
    }


    public void delete(Cliente t){
        clienteRepository.delete(t);
    }

    public Cliente update(Cliente cliente, Integer id){
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    public List<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }



}
