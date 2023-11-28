//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.model;

import com.arg_prog.venturi.trabajo_practico.enums.EstadoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "incidente")
public class Incidente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaIngreso;
    private LocalDate fechaEstimadaResolucion;
    private LocalDate fechaResolucion;
    private EstadoEnum estado;
    private String consideraciones;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "incidente_tipo_problema",
            joinColumns = @JoinColumn(name = "id_incidente"),
            inverseJoinColumns = @JoinColumn(name = "id_tipo_problema"))
    private List<TipoProblema> tiposProblemas;

    @ManyToOne
    @JoinColumn(name="servicio_id", referencedColumnName="id")
    private Servicio servicioRelacionado;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "incidente_especialidad",
            joinColumns = @JoinColumn(name = "id_incidente"),
            inverseJoinColumns = @JoinColumn(name = "id_especialidad"))
    private List<Especialidad> especialidades;

    @ManyToOne
    @JoinColumn(name="cliente_id", referencedColumnName="id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="tecnico_id", referencedColumnName="id")
    private Tecnico tecnico;


    public Incidente(String titulo, String descripcion, LocalDate fechaIngreso,
                     Cliente cliente, Tecnico tecnico, List<TipoProblema> tiposProblemas,
                     Servicio servicio, List<Especialidad> especialidades) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaIngreso = fechaIngreso;
        this.estado = EstadoEnum.INCOMPLETO;
        this.cliente = cliente;
        this.tecnico = tecnico;
        this.tiposProblemas = tiposProblemas;
        this.servicioRelacionado = servicio;
        this.especialidades = especialidades;
        this.fechaEstimadaResolucion = fechaEstimadaR();
    }


    private LocalDate fechaEstimadaR(){
        //sumo lasnhoras estimadas por cada problema
        int horasEstimadas = this.tiposProblemas.stream().mapToInt(prob -> prob.getTiempoEstimado()).sum();
        // se las sumo a la hora actual y obtengo la fecha
        return LocalDateTime.now().plus(horasEstimadas, ChronoUnit.HOURS).toLocalDate();
    }


    public String toStringLarge() {
        return "id: " + this.id +
                "\nTitulo: " + this.titulo  +
                "\nDescripción: " + this.descripcion +
                "\nCliente: " + this.cliente.getRazonSocial()  +
                "\nEstado: " + this.estado.toString()  +
                "\nRegistrado: " + this.fechaIngreso +
                "\nServicio relacionado: " + this.servicioRelacionado +
                "\nResolución estimada para: " + this.fechaEstimadaResolucion +
                "\nTécnico asignado: " + this.tecnico.getNombre() + " " + this.tecnico.getApellido();
    }



    @Override
    public String toString() {
        return "id: " + this.id + " | Titulo: " + this.titulo
                + " | Cliente: " + this.cliente.getRazonSocial()
                + " | Técnico: " + this.tecnico.getNombre() + " " + this.tecnico.getApellido();
    }

    public String toStringEstado() {
        return "id: " + this.id + " | Titulo: " + this.titulo
                + " | Tecnico: " + this.tecnico.getNombre() + " " + this.tecnico.getApellido()
                + " | Estado: " + this.estado.toString();
    }


    public boolean confirmarIncidente(){
        if(this.estado.equals(EstadoEnum.INCOMPLETO)){
            this.estado = EstadoEnum.EN_PROCESO;
            return true;
        }else{
            return  false;
        }
    }

    public boolean cerrarIncidente(String consideraciones, LocalDate fechaCierre){
        if(this.estado.equals(EstadoEnum.EN_PROCESO)){
            this.estado = EstadoEnum.FINALIZADO;
            this.fechaResolucion = fechaCierre;
            this.consideraciones = consideraciones;
            return true;
        }else{
            return  false;
        }
    }

    public boolean estaFinalizado(){
        if(this.estado.equals(EstadoEnum.FINALIZADO)){
            return true;
        }else{
            return  false;
        }
    }

    public int diasConsumidos(){
        if(this.estado.equals(EstadoEnum.FINALIZADO)){
            return (int)this.fechaIngreso.until(this.fechaResolucion,ChronoUnit.DAYS);
        }else{
            return -1;
        }

    }

}
