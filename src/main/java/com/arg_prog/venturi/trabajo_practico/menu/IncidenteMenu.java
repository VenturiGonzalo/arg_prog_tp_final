//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.menu;


import com.arg_prog.venturi.trabajo_practico.TPI_App;
import com.arg_prog.venturi.trabajo_practico.enums.MedioEnum;
import com.arg_prog.venturi.trabajo_practico.model.*;
import com.arg_prog.venturi.trabajo_practico.util.MenuMaker;

import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IncidenteMenu {


    public static void menuPrincipal(){

        int opcionIngresada;
        List<String> listaMenu = new ArrayList<>(Arrays.asList(
                "alta Incidente",
                "ver un Incidente",
                "listar todos los Incidentes",
                "CONFIRMAR INCIDENTE",
                "CERRAR INCIDENTE",
                "ACTUALIZAR HORAS ESTIMADAS",
                "borrar Incidente",
                "volver")) ;

        opcionIngresada = MenuMaker.printMenu(listaMenu,"Seleccione una opción", "MENU MESA DE AYUDA");

        switch (opcionIngresada) {
            case 0: //Crear
                crearIncidente();
                break;
            case 1: //Ver uno
                verUnIncidente();
                break;
            case 2: //Ver todos
                listarIncidentes();
                break;
            case 3: //Confirmar
                confirmarIncidente();
                break;
            case 4: //Cerrar
                cerrarIncidente();
                break;
            case 5: //Colchon de horas
                actTiempoEstimadoIncidente();
                break;
            case 6: //Borrar
                borrarIncidente();
                break;
            case 7: //Salir
                MenuMaker.menuPrincipal();
                break;
        }

    }

    private static void crearIncidente(){
        //Seleccion de cliente
        List<Cliente> clientes = TPI_App.clienteService.findAll();
        List<String> clientesStr = clientes.stream().map(cliente -> cliente.toString()).toList();
        int indexCliente = MenuMaker.printMenu(clientesStr,"Seleccione al cliente","Alta Incidente");
        Cliente cliente = clientes.get(indexCliente);

        //Seleccion de Servicio Contratado;
        List<Servicio> servicios = cliente.getServiciosContratados();
        List<String> serviciosStr = servicios.stream().map(servicio -> servicio.toString()).toList();
        int indexServicio = MenuMaker.printMenu(serviciosStr,"Seleccione un servicio contratado","Alta Incidente");
        Servicio servicio = servicios.get(indexServicio);

        //Ingrese titulo
        String titulo = JOptionPane.showInputDialog(null,"Ingrese Título");

        //Ingrese descripcion
        String descripcion = JOptionPane.showInputDialog(null,"Ingrese Descripción");

        //Seleccion de tipoProblema
        List<TipoProblema> tipoProblemas = TPI_App.tipoProblemaService.findAll();
        List<Integer> seleccion = new ArrayList<>();
        List<TipoProblema> tipProbSeleccionados =  MenuMaker.JOPcheckBoxProblema(tipoProblemas,
                "Selecciones los problemas", "Alta Incidente",seleccion);

        //Obtengoespecialidades
        List<Especialidad> especialidades = new ArrayList<>();
        for(TipoProblema prob : tipProbSeleccionados){
            especialidades.addAll(prob.getEspecialidades());
        }

        //Seleccion de tecnico
        List<Tecnico> tecnicos = TPI_App.tecnicoService.findAll();
        List<Tecnico> tecnicosAptos = new ArrayList<>();
        boolean hayTecnico = false;
        for(Tecnico tec : tecnicos){
            if(especialidades.stream().anyMatch(especialidad -> tec.sosApto(especialidad))){
                tecnicosAptos.add(tec);
                hayTecnico = true;
            }
        }
        if(!hayTecnico){
            JOptionPane.showMessageDialog(null,"No hay técnico apto para atender el problema");
        } else{
            List<String> tecnicosStr = tecnicosAptos.stream().map(tecnico -> tecnico.toString()).toList();
            int indexTecnico = MenuMaker.printMenu(tecnicosStr,"Seleccione al tecnico","Alta Incidente");
            Tecnico tecnico = tecnicos.get(indexTecnico);

            //Creo el incidente
            Incidente incidente = new Incidente(titulo,descripcion,LocalDate.now(),cliente,tecnico,tipProbSeleccionados,servicio,especialidades);

            //guardo incidente
            incidente = TPI_App.incidenteService.save(incidente);
            JOptionPane.showMessageDialog(null,"Incidente guardado correctamente");

            //sumo lasnhoras estimadas por cada problema y lo muestro
            int horasEstimadas = tipProbSeleccionados.stream().mapToInt(prob -> prob.getTiempoEstimado()).sum();
            JOptionPane.showMessageDialog(null,"Tiempo estimado para resolver: " + horasEstimadas);

            //Informa al cliente
            JOptionPane.showMessageDialog(null,
                    "Enviando email a: " + incidente.getCliente().getEmail()
                            + "\n" + "Su problema podría estar resuelto el día: "
                            + incidente.getFechaEstimadaResolucion().toString());

            //Informa al tecnico
            String mensaje;
            if(incidente.getTecnico().getMedioComunicacion().getMedio().equals(MedioEnum.EMAIL)){
                mensaje = "Enviando email a: " + incidente.getTecnico().getMedioComunicacion().getContacto()
                        + "\n" + "Tiene un incidente para resolver con el cliente: "
                        + incidente.getCliente().getRazonSocial();
            }
                mensaje = "Enviando mensaje al: " + incidente.getTecnico().getMedioComunicacion().getContacto()
                    + "\n" + "Tiene un incidente para resolver con el cliente: "
                    + incidente.getCliente().getRazonSocial();

            JOptionPane.showMessageDialog(null,mensaje);
        }

        //Retorno al menu
        menuPrincipal();

    }


    private static void verUnIncidente(){
        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID del incidente"));
        try {
            Incidente incidente = TPI_App.incidenteService.findById(id);
            JOptionPane.showMessageDialog(null,incidente.toStringLarge());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"El incidente no existe");
        }

        //Retorno al menu
        menuPrincipal();
    }

    private static void listarIncidentes(){
        List<Incidente> incidentes = TPI_App.incidenteService.findAll();
        String listIncidentes = "";
        for(Incidente incidente : incidentes){
            listIncidentes = listIncidentes + incidente.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null,listIncidentes);

        //Retorno al menu
        menuPrincipal();
    }

    private static void borrarIncidente(){
        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID del incidente"));
        if(0 == JOptionPane.showConfirmDialog(null,"Está seguro de borrar al Incidente","Borra Incidente",JOptionPane.YES_NO_OPTION)){
            try {
                TPI_App.incidenteService.delete(TPI_App.incidenteService.findById(id));
                JOptionPane.showMessageDialog(null,"Incidente borrado correctamente");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"El incidente no existe");
            }

        }

        //Retorno al menu
        menuPrincipal();
    }

    //EL TECNICO CONFIRMA QUE ESTA TRABAJANDO EN EL INCIDENTE
    private static void confirmarIncidente(){
        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID del incidente"));
        try {
            Incidente incidente = TPI_App.incidenteService.findById(id);
            incidente.confirmarIncidente();
            TPI_App.incidenteService.update(incidente, incidente.getId());
            JOptionPane.showMessageDialog(null,"Incidente confirmado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"El incidente no existe");
        }

        //Retorno al menu
        menuPrincipal();
    }

    //INCIDENTE FINALIZADO
    private static void cerrarIncidente(){
        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID del incidente"));
         try {
             Incidente incidente = TPI_App.incidenteService.findById(id);
             String consideraciones = JOptionPane.showInputDialog("Ingrese sus Consideraciones");
             incidente.cerrarIncidente(consideraciones,LocalDate.now());
             TPI_App.incidenteService.update(incidente, incidente.getId());
             JOptionPane.showMessageDialog(null,"Incidente cerrado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"El incidente no existe");
        }

        //Retorno al menu
        menuPrincipal();
    }

    //Agrega horas estimada a un incidente complejo
    private static void actTiempoEstimadoIncidente(){
        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID del incidente"));
        try {
            Incidente incidente = TPI_App.incidenteService.findById(id);
            int horasAgregar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese las horas a agregar"));
            int diasAgregar = horasAgregar / 24;
            LocalDate nuevaFecha = incidente.getFechaEstimadaResolucion().plus(diasAgregar, ChronoUnit.DAYS);
            incidente.setFechaEstimadaResolucion(nuevaFecha);
            TPI_App.incidenteService.update(incidente, incidente.getId());
            JOptionPane.showMessageDialog(null,"Incidente actualizado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"El incidente no existe");
            System.out.println(e);
        }

        //Retorno al menu
        menuPrincipal();
    }


}
