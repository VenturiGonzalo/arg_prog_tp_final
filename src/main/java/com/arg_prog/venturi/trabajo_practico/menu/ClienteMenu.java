//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico.menu;


import com.arg_prog.venturi.trabajo_practico.TPI_App;
import com.arg_prog.venturi.trabajo_practico.model.*;
import com.arg_prog.venturi.trabajo_practico.util.MenuMaker;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ClienteMenu {


    public static void menuPrincipal(){

        int opcionIngresada;
        List<String> listaMenu = new ArrayList<>(Arrays.asList(
                "alta Cliente",
                "ver un Cliente",
                "listar todos los Clientes",
                "modificar Cliente",
                "borrar Cliente",
                "volver")) ;

        opcionIngresada = MenuMaker.printMenu(listaMenu,"Seleccione una opción", "MENU AREA COMERCIAL");

        switch (opcionIngresada) {
            case 0: //Crear
                crearCliente();
                break;
            case 1: //Ver uno
                verUnCliente();
                break;
            case 2: //Ver todos
                listarClientes();
                break;
            case 3: //Modificar
                modificarCliente();
                break;
            case 4: //Borrar
                borrarCliente();
                break;
            case 5: //Salir
                MenuMaker.menuPrincipal();
                break;
        }

    }

    private static void crearCliente(){

        //Ingrese razonSocial
        String razonSocial = JOptionPane.showInputDialog(null,"Ingrese Razón Social");

        //Ingrese cuit
        String cuit = JOptionPane.showInputDialog(null,"Ingrese CUIT");

        //Ingrese email
        String email = JOptionPane.showInputDialog(null,"Ingrese eMail");

        //Ingrese nombre
        String nombre = JOptionPane.showInputDialog(null,"Ingrese Nombre");

        //Ingrese apellido
        String apellido = JOptionPane.showInputDialog(null,"Ingrese Apellido");

        //Seleccion de Servicios
        List<Servicio> servicios = TPI_App.servicioService.findAll();
        List<Integer> seleccion = new ArrayList<>();
        List<Servicio> servSeleccionados =  MenuMaker.JOPcheckBoxServ(servicios,
                "Selecciones los servicios", "Alta Cliente",seleccion);


        //Creo el cliente
        Cliente cliente = new Cliente(razonSocial,cuit,email,nombre,apellido,servSeleccionados);

        //guardo cliente
        TPI_App.clienteService.save(cliente);
        JOptionPane.showMessageDialog(null,"Cliente guardado correctamente");



        //Retorno al menu
        menuPrincipal();

    }


    private static void verUnCliente(){
        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID del cliente"));
        try {
            Cliente cliente = TPI_App.clienteService.findById(id);
            JOptionPane.showMessageDialog(null,cliente.toStringLarge());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"El cliente no existe");
        }

        //Retorno al menu
        menuPrincipal();
    }

    private static void listarClientes(){
        List<Cliente> clientes = TPI_App.clienteService.findAll();
        String listClientes = "";
        for(Cliente cliente : clientes){
            listClientes = listClientes + cliente.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null,listClientes);

        //Retorno al menu
        menuPrincipal();
    }

    private static void modificarCliente(){
        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID del cliente"));
        try {
            Cliente cliente = TPI_App.clienteService.findById(id);
            JOptionPane.showMessageDialog(null,cliente.toStringLarge());


            //Ingrese razonSocial
            String razonSocial = JOptionPane.showInputDialog(null,"Ingrese Razón Social",cliente.getRazonSocial());
            if(!Objects.equals(razonSocial, "")) {
                cliente.setRazonSocial(razonSocial);
            }
            //Ingrese cuit
            String cuit = JOptionPane.showInputDialog(null,"Ingrese CUIT",cliente.getCuit());
            if(!Objects.equals(cuit, "")) {
                cliente.setCuit(cuit);
            }

            //Ingrese email
            String email = JOptionPane.showInputDialog(null,"Ingrese eMail",cliente.getEmail());
            if(!Objects.equals(email, "")) {
                cliente.setEmail(email);
            }

            //Ingrese nombre
            String nombre = JOptionPane.showInputDialog(null,"Ingrese Nombre",cliente.getNombre());
            if(!Objects.equals(nombre, "")) {
                cliente.setNombre(nombre);
            }

            //Ingrese apellido
            String apellido = JOptionPane.showInputDialog(null,"Ingrese Apellido",cliente.getApellido());
            if(!Objects.equals(apellido, "")) {
                cliente.setApellido(apellido);
            }

            //Seleccion de Servicios
            List<Servicio> servicios = TPI_App.servicioService.findAll();
            List<Integer> seleccionVieja = cliente.getServiciosContratados().stream().map(servicio -> servicio.getId()).toList();
            List<Servicio> servSeleccionados =  MenuMaker.JOPcheckBoxServ(servicios,
                    "Selecciones los servicios", "Alta Cliente",seleccionVieja);


            //Creo el cliente
            Cliente clienteNuevo = new Cliente(razonSocial,cuit,email,nombre,apellido,servSeleccionados);

            //guardo cliente
            TPI_App.clienteService.update(clienteNuevo, cliente.getId());
            JOptionPane.showMessageDialog(null,"Cliente guardado correctamente");



        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"El cliente no existe");
        }



        //Retorno al menu
        menuPrincipal();

    }

    private static void borrarCliente(){
        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID del cliente"));
        if(0 == JOptionPane.showConfirmDialog(null,"Está seguro de borrar al Cliente","Borra Cliente",JOptionPane.YES_NO_OPTION)){
            try {
                TPI_App.clienteService.delete(TPI_App.clienteService.findById(id));
                JOptionPane.showMessageDialog(null,"Cliente borrado correctamente");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"El cliente no existe");
            }

        }

        //Retorno al menu
        menuPrincipal();
    }

}
