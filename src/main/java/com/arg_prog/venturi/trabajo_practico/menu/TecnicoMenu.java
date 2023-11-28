//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.menu;

import com.arg_prog.venturi.trabajo_practico.TPI_App;
import com.arg_prog.venturi.trabajo_practico.enums.EstadoEnum;
import com.arg_prog.venturi.trabajo_practico.enums.MedioEnum;
import com.arg_prog.venturi.trabajo_practico.model.Especialidad;
import com.arg_prog.venturi.trabajo_practico.model.Incidente;
import com.arg_prog.venturi.trabajo_practico.model.MedioComunicacion;
import com.arg_prog.venturi.trabajo_practico.model.Tecnico;
import com.arg_prog.venturi.trabajo_practico.util.MenuMaker;

import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TecnicoMenu {


    public static void menuPrincipal(){

        int opcionIngresada;
        List<String> listaMenu = new ArrayList<>(Arrays.asList(
                "alta Técnico",
                "ver un Técnico",
                "listar todos los Técnicos",
                "REPORTE DIARIO DE INCIDENTES",
                "TÉCNICO CON MÁS INCIDENTES RESUELTOS EN D DÍAS",
                "TÉCNICO CON MÁS INCIDENTES RESUELTOS EN D DÍAS, Y EN E ESPECIALIDAD",
                "TÉCNICO QUE MÁS RÁPIDO RESOLVIÓ LOS INCIDENTES",
                "modificar Técnico",
                "borrar Técnico",
                "volver")) ;

        opcionIngresada = MenuMaker.printMenu(listaMenu,"Seleccione una opción", "MENU RRHH");

        switch (opcionIngresada) {
            case 0 -> //Crear
                    crearTecnico();
            case 1 -> //Ver uno
                    verUnTecnico();
            case 2 -> //Ver todos
                    listarTecnicos();
            case 3 -> //Reporte
                    reporteDiarioincidentes();
            case 4 -> //Técnico mas resueltos
                    tecnicoMasResueltos();
            case 5 -> //Técnico mas res por esp
                    tecnicoMasResueltosPorEsp();
            case 6 -> //Técnico más rápido
                    tecnicoMasRapido();
            case 7 -> //Modificar
                    modificarTecnico();
            case 8 -> //Borrar
                    borrarTecnico();
            case 9 -> //Salir
                    MenuMaker.menuPrincipal();
        }

    }

    private static void crearTecnico(){

        //Ingrese nombre
        String nombre = JOptionPane.showInputDialog(null,"Ingrese Nombre");

        //Ingrese nombre
        String apellido = JOptionPane.showInputDialog(null,"Ingrese Apellido");

        //Seleccion del modo de Notificacion
        MedioEnum medio = (MedioEnum) MenuMaker.JOPbuttons(MedioEnum.values(),
                "Seleccione el método de Notificación", "Alta Técnico",0);
        String contacto = JOptionPane.showInputDialog(null,"Ingrese el " + medio.toString());
        MedioComunicacion medioComunicacion = new MedioComunicacion(medio,contacto);

        //Seleccion de Especialidad
        List<Especialidad> especialidades = TPI_App.especialidadService.findAll();
        List<Integer> seleccion = new ArrayList<>();
        List<Especialidad> especSeleccionadas =  MenuMaker.JOPcheckBoxEspecs(especialidades,
                "Selecciones las especialidades", "Alta Técnico",seleccion);


        //guardo medioComunicacion, y lo obtengo con el id generado por la db
        medioComunicacion = TPI_App.medioComunicacionService.save(medioComunicacion);


        //Creo el técnico
        Tecnico tecnico = new Tecnico(nombre,apellido,medioComunicacion,especSeleccionadas);

        //guardo tecnico
        TPI_App.tecnicoService.save(tecnico);
        JOptionPane.showMessageDialog(null,"Técnico guardado correctamente");



        //Retorno al menu
        menuPrincipal();

    }


    private static void verUnTecnico(){
        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID del técnico"));
        try {
            Tecnico tecnico = TPI_App.tecnicoService.findById(id);
            JOptionPane.showMessageDialog(null,tecnico.toStringLarge());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"El técnico no existe");
        }

        //Retorno al menu
        menuPrincipal();
    }

    private static void listarTecnicos(){
        List<Tecnico> tecnicos = TPI_App.tecnicoService.findAll();
        String listTecnicos = "";
        for(Tecnico tecnico : tecnicos){
            listTecnicos = listTecnicos + tecnico.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null,listTecnicos);

        //Retorno al menu
        menuPrincipal();
    }

    private static void modificarTecnico(){
        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID del técnico"));
        try {
            Tecnico tecnico = TPI_App.tecnicoService.findById(id);
            JOptionPane.showMessageDialog(null,tecnico.toStringLarge());

            //Nombre
            String nombre = JOptionPane.showInputDialog(null,"Ingrese Nombre",tecnico.getNombre());
            if(!Objects.equals(nombre, "")) {
                tecnico.setNombre(nombre);
            }

            //Apellido
            String apellido = JOptionPane.showInputDialog(null,"Ingrese Apellido",tecnico.getApellido());
            if(!Objects.equals(apellido, "")) {
                tecnico.setApellido(apellido);
            }

            //Seleccion del modo de Notificacion
            MedioEnum medio = (MedioEnum) MenuMaker.JOPbuttons(MedioEnum.values(),
                    "Seleccione el método de Notificación", "Modificar Técnico",tecnico.getMedioComunicacion().getMedio().ordinal());
            String contacto = JOptionPane.showInputDialog(null,"Ingrese el " + medio.toString(),tecnico.getMedioComunicacion().getContacto());
            MedioComunicacion medioComunicacion = new MedioComunicacion(medio,contacto);

            //Seleccion de Especialidad
            List<Especialidad> especialidades = TPI_App.especialidadService.findAll();
            List<Integer> seleccionVieja = tecnico.getEspecialidades().stream().map(especialidad -> especialidad.getId()).toList();            System.out.println(seleccionVieja);
            List<Especialidad> especSeleccionadas =  MenuMaker.JOPcheckBoxEspecs(especialidades,
                    "Selecciones las especialidades", "Modificar Técnico",seleccionVieja);

            //guardo medioComunicacion, y lo obtengo con el id generado por la db
            medioComunicacion = TPI_App.medioComunicacionService.update(medioComunicacion,tecnico.getMedioComunicacion().getId());

            //guardo tecnico
            Tecnico tecnicoNuevo = new Tecnico(nombre,apellido,medioComunicacion,especSeleccionadas);
            TPI_App.tecnicoService.update(tecnicoNuevo,tecnico.getId());
            JOptionPane.showMessageDialog(null,"Técnico actualizado correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"El técnico no existe");
        }



        //Retorno al menu
        menuPrincipal();

    }

    private static void borrarTecnico(){
        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID del técnico"));
        if(0 == JOptionPane.showConfirmDialog(null,"Está seguro de borrar al Técnico","Borra Técnico",JOptionPane.YES_NO_OPTION)){
            try {
                TPI_App.tecnicoService.delete(TPI_App.tecnicoService.findById(id));
                JOptionPane.showMessageDialog(null,"Técnico borrado correctamente");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"El técnico no existe");
            }

        }

        //Retorno al menu
        menuPrincipal();
    }

    private static void reporteDiarioincidentes(){
        List<Incidente> incidentes = TPI_App.incidenteService.findDiario();
        String incidentesStr = "";
        for(Incidente incidente : incidentes){
            incidentesStr = incidentesStr + incidente.toStringEstado() + "\n";
        }
        JOptionPane.showMessageDialog(null,incidentesStr);

        //Retorno al menu
        menuPrincipal();
    }

    private static void tecnicoMasResueltos(){
        //Obtengo la lista de incidentes resueltos
        List<Incidente> incidentesResueltos = TPI_App.incidenteService.findByEstado(EstadoEnum.FINALIZADO);
        //Pido los dias a consultar
        int dias = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese la cantidad de días a consultar"));
        List<Tecnico> tecnicos = new ArrayList<>();
        for(Incidente incidente : incidentesResueltos){
            //si el incidente está dentro de los días consultados
            if(incidente.getFechaResolucion().until(LocalDate.now(), ChronoUnit.DAYS) <= dias){
                //agrego tecnicos a la lista
                tecnicos.add(incidente.getTecnico());
            }
        }
        //me fijo que tecnico es el que más se repite
        Map<Tecnico, Long> mapTecnicos = tecnicos.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(mapTecnicos);
        Tecnico tecnico = new Tecnico();
        long max = 0;
        for(Tecnico tec : mapTecnicos.keySet()){
            if(mapTecnicos.get(tec) > max){
                max = mapTecnicos.get(tec);
                tecnico = tec;
            }
        }
        JOptionPane.showMessageDialog(null,"El Técnico qie más incidente resolvió en " +
                dias + " días fue:\n" + tecnico.toString());

        //Retorno al menu
        menuPrincipal();
    }

    private static void tecnicoMasResueltosPorEsp(){
        //Obtengo la lista de incidentes resueltos
        List<Incidente> incidentesResueltos = TPI_App.incidenteService.findByEstado(EstadoEnum.FINALIZADO);
        //Pido los dias a consultar
        int dias = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese la cantidad de días a consultar"));
        //Pido la especialidad
        List<Especialidad> especialidades = TPI_App.especialidadService.findAll();
        List<String> especialidadesStr = especialidades.stream().map(esp -> esp.getNombre()).toList();
        int index = MenuMaker.printMenu(especialidadesStr,"Selecciones especialidad", "Filtrado de técnicos po Incidente");
        Especialidad especialidad = especialidades.get(index);

        List<Tecnico> tecnicos = new ArrayList<>();
        for(Incidente incidente : incidentesResueltos){
            //si el incidente está dentro de los días consultados y es de la especialidad
            if(incidente.getFechaResolucion().until(LocalDate.now(), ChronoUnit.DAYS) <= dias &&
            incidente.getEspecialidades().contains(especialidad)){
                //agrego tecnicos a la lista
                tecnicos.add(incidente.getTecnico());
            }
        }
        //me fijo que tecnico es el que más se repite
        Map<Tecnico, Long> mapTecnicos = tecnicos.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(mapTecnicos);
        Tecnico tecnico = new Tecnico();
        long max = 0;
        for(Tecnico tec : mapTecnicos.keySet()){
            if(mapTecnicos.get(tec) > max){
                max = mapTecnicos.get(tec);
                tecnico = tec;
            }
        }
        JOptionPane.showMessageDialog(null,"El Técnico que más incidente resolvió en " +
                dias + " días fue:\n" + tecnico.toString());

        //Retorno al menu
        menuPrincipal();
    }

    private static void tecnicoMasRapido(){
        //Obtengo la lista de incidentes resueltos
        List<Incidente> incidentesResueltos = TPI_App.incidenteService.findByEstado(EstadoEnum.FINALIZADO);
        List<Tecnico> tecnicos = TPI_App.tecnicoService.findAll();
        double mejorPromedio = 100000.0;
        Tecnico tecnicoMasRapido = new Tecnico();
        for(Tecnico tecnico : tecnicos){
            //Filtro incidentes por tecnico
            List<Incidente> incidentesResueltosPorTecnico =
                    incidentesResueltos.stream().filter(inc -> inc.getTecnico().equals(tecnico)).toList();
            //Cuento las horas
            long tecnicoHorasTotales  = 24 * incidentesResueltosPorTecnico.stream().mapToLong
                    (inc -> inc.getFechaIngreso().until(inc.getFechaResolucion(),ChronoUnit.DAYS)).sum();
            //calculo el promedio y lo comparo
            if(incidentesResueltosPorTecnico.size() != 0){
                double promedioPorTecnico = (double) tecnicoHorasTotales / incidentesResueltosPorTecnico.size();

                System.out.println(tecnico.toString() + " Promedio: " + promedioPorTecnico);

                if(promedioPorTecnico < mejorPromedio){
                    mejorPromedio = promedioPorTecnico;
                    tecnicoMasRapido = tecnico;
                }
            }

        }
        JOptionPane.showMessageDialog(null,"El Técnico que más rápido resolvió los incidentes fue:\n" +
                tecnicoMasRapido.toString());

        //Retorno al menu
        menuPrincipal();
    }


}
