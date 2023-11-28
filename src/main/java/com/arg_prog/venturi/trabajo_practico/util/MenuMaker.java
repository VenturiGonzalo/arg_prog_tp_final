//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.util;

import com.arg_prog.venturi.trabajo_practico.menu.ClienteMenu;
import com.arg_prog.venturi.trabajo_practico.menu.IncidenteMenu;
import com.arg_prog.venturi.trabajo_practico.menu.TecnicoMenu;
import com.arg_prog.venturi.trabajo_practico.model.Especialidad;
import com.arg_prog.venturi.trabajo_practico.model.Servicio;
import com.arg_prog.venturi.trabajo_practico.model.TipoProblema;


import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class MenuMaker {
    public static void menuPrincipal(){
        int opcionIngresada;
        List<String> listaMenu = Arrays.asList(
                "MESA DE AYUDA",
                "AREA COMERCIAL",
                "AREA DE RRHH",
                "SALIR") ;
        opcionIngresada = printMenu(listaMenu, "Menú Principal","SISTEMA DE REPORTE DE INCIDENTES");

        switch (opcionIngresada) {
            case 0: //Incidente (Mesa de Ayuda)
                IncidenteMenu.menuPrincipal();
                break;
            case 1: //Cliente (Area comercial)
                ClienteMenu.menuPrincipal();
                break;
            case 2: //Técnicos (Recursos Humanos)
                TecnicoMenu.menuPrincipal();
                break;
            case 3: //Salir
                break;
        }

    }


    public static int printMenu(List<String> lista,String texto, String titulo)  {
        int opcionIngresada = 0;
        String respuesta;
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?"); //regex que matchea solo con numeros (int y float, pos y neg)

        //armo el atring de la lista
        String listaString = texto + "\n";
        for (int i = 0; i < lista.size(); i++) {
            listaString = listaString + (i + 1) + ") - " + lista.get(i) + "\n";
        }

        //Muestro la opciones y Espero hasta que ingrese una opcion válida
        Object[] options = {"OK"};
        do {
            respuesta = JOptionPane.showInputDialog(null,listaString,titulo, JOptionPane.INFORMATION_MESSAGE);
            if(Objects.isNull(respuesta)){ //si es null (boton cancelar)
                //retorno el ultimo valor posible (salir)
                opcionIngresada = lista.size();
            } else if(pattern.matcher(respuesta).matches()){ //si matche es poruq es numero
                opcionIngresada = Integer.parseInt(respuesta);
            }

        }while(opcionIngresada < 1 || opcionIngresada > lista.size()); //reviso que el numero esté en la lista

        //devuelvo la opcion ingresada (-1 porque devuelvo el indice)
        return opcionIngresada - 1;
    }



    public static List<Especialidad> JOPcheckBoxEspecs(List<Especialidad> lista, String mensaje, String titulo, List<Integer> seleccionados){
        List<Especialidad> especialidadList = new ArrayList<>(); //Lista a retornar
        List<JCheckBox> listCheckBox = new ArrayList<>(); //Lista de checks

        int listaSize = lista.size(); //Tamaño de la lista (y dela Lista de Checkboxs)
        Object[] msgContent = new Object[listaSize + 1]; //Array de objetos para mostrar en el JOptiOnpane
        msgContent[0] = mensaje; //Primer elemento del array, el mensaje a mostrar

        //Creo un Checkbox por cada espec
        for (int i = 0; i < listaSize; i++) {
            JCheckBox checkBox = new JCheckBox(lista.get(i).getNombre());
            if(seleccionados.contains(lista.get(i).getId())){ //Si Ya fue seleccionado(en caso de una edicion)
                checkBox.setSelected(true);
            }
            listCheckBox.add(checkBox); //lo agrego a la lista de Checkbox
            msgContent[i + 1] = checkBox; //lo agrego al array a mostrar
        }

        boolean huboSeleccionados = false;
        do {
            //Muestro el JOP con los Chechboxs creados
            JOptionPane.showConfirmDialog(null, msgContent, titulo, JOptionPane.YES_NO_OPTION);

            //Recorro los Checkbox, por cada tildado agrego el object correspondiente
            for (int i = 0; i < listaSize; i++) {
                if (listCheckBox.get(i).isSelected()) {
                    especialidadList.add(lista.get(i));
                    huboSeleccionados = true;
                }
            }
        }while(!huboSeleccionados);

        return especialidadList;
    }




    public static List<TipoProblema> JOPcheckBoxProblema(List<TipoProblema> lista, String mensaje, String titulo, List<Integer> seleccionados){
        List<TipoProblema> tipoProblemaList = new ArrayList<>(); //Lista a retornar
        List<JCheckBox> listCheckBox = new ArrayList<>(); //Lista de checks

        int listaSize = lista.size(); //Tamaño de la lista (y dela Lista de Checkboxs)
        Object[] msgContent = new Object[listaSize + 1]; //Array de objetos para mostrar en el JOptiOnpane
        msgContent[0] = mensaje; //Primer elemento del array, el mensaje a mostrar

        //Creo un Checkbox por cada espec
        for (int i = 0; i < listaSize; i++) {
            JCheckBox checkBox = new JCheckBox(lista.get(i).getTipo());
            if(seleccionados.contains(lista.get(i).getId())){ //Si Ya fue seleccionado(en caso de una edicion)
                checkBox.setSelected(true);
            }
            listCheckBox.add(checkBox); //lo agrego a la lista de Checkbox
            msgContent[i + 1] = checkBox; //lo agrego al array a mostrar
        }

        boolean huboSeleccionados = false;
        do {
            //Muestro el JOP con los Chechboxs creados
            JOptionPane.showConfirmDialog(null, msgContent, titulo, JOptionPane.YES_NO_OPTION);

            //Recorro los Checkbox, por cada tildado agrego el object correspondiente
            for (int i = 0; i < listaSize; i++) {
                if (listCheckBox.get(i).isSelected()) {
                    tipoProblemaList.add(lista.get(i));
                    huboSeleccionados = true;
                }
            }
        }while(!huboSeleccionados);

        return tipoProblemaList;
    }



    public static List<Servicio> JOPcheckBoxServ(List<Servicio> lista, String mensaje, String titulo, List<Integer> seleccionados) {
        List<Servicio> servicioList = new ArrayList<>(); //Lista a retornar
        List<JCheckBox> listCheckBox = new ArrayList<>(); //Lista de checks

        int listaSize = lista.size(); //Tamaño de la lista (y dela Lista de Checkboxs)
        Object[] msgContent = new Object[listaSize + 1]; //Array de objetos para mostrar en el JOptiOnpane
        msgContent[0] = mensaje; //Primer elemento del array, el mensaje a mostrar

        //Creo un Checkbox por cada serv
        for (int i = 0; i < listaSize; i++) {
            JCheckBox checkBox = new JCheckBox(lista.get(i).getNombre());
            if(seleccionados.contains(lista.get(i).getId())){ //Si Ya fue seleccionado(en caso de una edicion)
                checkBox.setSelected(true);
            }
            listCheckBox.add(checkBox); //lo agrego a la lista de Checkbox
            msgContent[i + 1] = checkBox; //lo agrego al array a mostrar
        }

        boolean huboSeleccionados = false;
        do {
            //Muestro el JOP con los Chechboxs creados
            JOptionPane.showConfirmDialog(null, msgContent, titulo, JOptionPane.YES_NO_OPTION);

            //Recorro los Checkbox, por cada tildado agrego el object correspondiente
            for (int i = 0; i < listaSize; i++) {
                if (listCheckBox.get(i).isSelected()) {
                    servicioList.add(lista.get(i));
                    huboSeleccionados = true;
                }
            }
        }while(!huboSeleccionados);

        return servicioList;
    }




    //Recibe un ENUM, muestra una lista de botones, y devuelve el EMUN seleccionado
    public static Enum JOPbuttons(Enum[] enumes, String mensaje, String titulo, int seleccionado){
        Enum enumSeleccionado; //Valor a retornar
        List<String> listButtons = new ArrayList<>(); //Lista de botones

        //Creo una Lista, por cada boton y enum
        for (Enum enumi : enumes) {
            listButtons.add(enumi.toString()); //lo agrego a la lista de Botones
        }

        //Muestro el JOP con los Botones creados
        int index = JOptionPane.showOptionDialog (null,
                mensaje, titulo, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
                null, listButtons.toArray(), listButtons.toArray()[seleccionado]);

        //Retorno el seleccionado
        return enumes[index];
    }


}

