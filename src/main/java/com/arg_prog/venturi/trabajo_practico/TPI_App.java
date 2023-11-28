//Gonzalo Venturi - Argentina Programa 4.0 - Java Intermedio - Trabajo Práctico Final
package com.arg_prog.venturi.trabajo_practico;

import com.arg_prog.venturi.trabajo_practico.enums.MedioEnum;
import com.arg_prog.venturi.trabajo_practico.model.*;
import com.arg_prog.venturi.trabajo_practico.servicio.*;
import com.arg_prog.venturi.trabajo_practico.util.MenuMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class TPI_App {
	public static EspecialidadService especialidadService;
	public static TecnicoService tecnicoService;
	public static MedioComunicacionService medioComunicacionService;
	public static ServicioService servicioService;
	public static TipoProblemaService tipoProblemaService;
	public static ClienteService clienteService;
	public static IncidenteService incidenteService;


	@Autowired
	public TPI_App(
			EspecialidadService especialidadService,
			ServicioService servicioService,
			TipoProblemaService tipoProblemaService,
			MedioComunicacionService medioComunicacionService,
			TecnicoService tecnicoService,
			ClienteService clienteService,
			IncidenteService incidenteService){
		TPI_App.especialidadService = especialidadService;
		TPI_App.servicioService = servicioService;
		TPI_App.tipoProblemaService = tipoProblemaService;
		TPI_App.medioComunicacionService = medioComunicacionService;
		TPI_App.tecnicoService = tecnicoService;
		TPI_App.clienteService = clienteService;
		TPI_App.incidenteService = incidenteService;
	}


	public static void main(String[] args) {
		SpringApplication.run(TPI_App.class, args);
		System.setProperty("java.awt.headless", "false"); //Deshabilito headless para que funcionen los JOptionPane
		//cargoAlgunosDatos();
		MenuMaker.menuPrincipal();
	}



	private static void cargoAlgunosDatos(){
		//ESPECIALIDADES---------------------------------------------------------------
		Especialidad esp1= new Especialidad("Especialidad 1", "Esta es la descripción de especialidad 1");
		Especialidad esp2= new Especialidad("Especialidad 2", "Esta es la descripción de especialidad 2");
		Especialidad esp3= new Especialidad("Especialidad 3", "Esta es la descripción de especialidad 3");
		esp1 = especialidadService.save(esp1);
		esp2 = especialidadService.save(esp2);
		esp3 = especialidadService.save(esp3);

		//SERVICIOS---------------------------------------------------------------
		Servicio serv1= new Servicio("Servicio 1", "Esta es la descripción de servicio 1");
		Servicio serv2= new Servicio("Servicio 2", "Esta es la descripción de servicio 2");
		Servicio serv3= new Servicio("Servicio 3", "Esta es la descripción de servicio 3");
		serv1 = servicioService.save(serv1);
		serv2 = servicioService.save(serv2);
		serv3 = servicioService.save(serv3);

		//PROBLEMAS---------------------------------------------------------------
		TipoProblema tpro1= new TipoProblema("Tipo Problema 1", 6, 12, List.of(esp1,esp2));
		TipoProblema tpro2= new TipoProblema("Tipo Problema 2", 9, 18, List.of(esp2,esp3));
		TipoProblema tpro3= new TipoProblema("Tipo Problema 3", 12, 24, List.of(esp3,esp1));
		tpro1 = tipoProblemaService.save(tpro1);
		tpro2 = tipoProblemaService.save(tpro2);
		tpro3 = tipoProblemaService.save(tpro3);

		//MEDIOS---------------------------------------------------------------
		MedioComunicacion med1 = new MedioComunicacion(MedioEnum.EMAIL,"contacto@med1.com");
		MedioComunicacion med2 = new MedioComunicacion(MedioEnum.EMAIL,"contacto@med2.com");
		MedioComunicacion med3 = new MedioComunicacion(MedioEnum.EMAIL,"contacto@med3.com");
		MedioComunicacion med4 = new MedioComunicacion(MedioEnum.WHATSAPP,"+549 3465 000004");
		MedioComunicacion med5 = new MedioComunicacion(MedioEnum.WHATSAPP,"+549 3465 000005");
		MedioComunicacion med6 = new MedioComunicacion(MedioEnum.WHATSAPP,"+549 3465 000006");
		med1 = medioComunicacionService.save(med1);
		med2 = medioComunicacionService.save(med2);
		med3 = medioComunicacionService.save(med3);
		med4 = medioComunicacionService.save(med4);
		med5 = medioComunicacionService.save(med5);
		med6 = medioComunicacionService.save(med6);

		//TECNICOS---------------------------------------------------------------
		Tecnico tec1 = new Tecnico("NombreTecnico1","ApellidoTecnico1",med1,List.of(esp1));
		Tecnico tec2 = new Tecnico("NombreTecnico2","ApellidoTecnico2",med2,List.of(esp2));
		Tecnico tec3 = new Tecnico("NombreTecnico3","ApellidoTecnico3",med3,List.of(esp3));
		Tecnico tec4 = new Tecnico("NombreTecnico4","ApellidoTecnico4",med4,List.of(esp1,esp2));
		Tecnico tec5 = new Tecnico("NombreTecnico5","ApellidoTecnico5",med5,List.of(esp2,esp3));
		Tecnico tec6 = new Tecnico("NombreTecnico6","ApellidoTecnico6",med6,List.of(esp3,esp1));
		tec1 = tecnicoService.save(tec1);
		tec2 = tecnicoService.save(tec2);
		tec3 = tecnicoService.save(tec3);
		tec4 = tecnicoService.save(tec4);
		tec5 = tecnicoService.save(tec5);
		tec6 = tecnicoService.save(tec6);

		//CLIENTES---------------------------------------------------------------
		Cliente cli1 = new Cliente("Razón Social 1","30.12345678.1","cliente1@correo.com",
				"NombreCliente1","ApellidoCliente1",List.of(serv1));
		Cliente cli2 = new Cliente("Razón Social 2","30.12345678.2","cliente2@correo.com",
				"NombreCliente2","ApellidoCliente2",List.of(serv2));
		Cliente cli3 = new Cliente("Razón Social 3","30.12345678.3","cliente3@correo.com",
				"NombreCliente3","ApellidoCliente3",List.of(serv3));
		Cliente cli4 = new Cliente("Razón Social 4","30.12345678.4","cliente4@correo.com",
				"NombreCliente4","ApellidoCliente4",List.of(serv1,serv2));
		Cliente cli5 = new Cliente("Razón Social 5","30.12345678.5","cliente5@correo.com",
				"NombreCliente5","ApellidoCliente5",List.of(serv2,serv3));
		Cliente cli6 = new Cliente("Razón Social 6","30.12345678.6","cliente6@correo.com",
				"NombreCliente6","ApellidoCliente6",List.of(serv3,serv1));
		cli1 = clienteService.save(cli1);
		cli2 = clienteService.save(cli2);
		cli3 = clienteService.save(cli3);
		cli4 = clienteService.save(cli4);
		cli5 = clienteService.save(cli5);
		cli6 = clienteService.save(cli6);

		//INCIDENTES---------------------------------------------------------------
		Incidente inci1 = new Incidente("Titulo1","Descripción1", LocalDate.of(2023,11,21),
				cli1,tec5,List.of(tpro1,tpro2),serv1,List.of(esp1));
		Incidente inci2 = new Incidente("Titulo2","Descripción2", LocalDate.of(2023,11,21),
				cli2,tec2,List.of(tpro2,tpro3),serv2,List.of(esp2));
		Incidente inci3 = new Incidente("Titulo3","Descripción3", LocalDate.of(2023,11,21),
				cli3,tec3,List.of(tpro3,tpro1),serv3,List.of(esp3));
		Incidente inci4 = new Incidente("Titulo4","Descripción4", LocalDate.of(2023,11,22),
				cli4,tec3,List.of(tpro1),serv1,List.of(esp1,esp2));
		Incidente inci5 = new Incidente("Titulo5","Descripción5", LocalDate.of(2023,11,22),
				cli5,tec5,List.of(tpro2),serv2,List.of(esp2,esp3));
		Incidente inci6 = new Incidente("Titulo6","Descripción6", LocalDate.of(2023,11,22),
				cli6,tec6,List.of(tpro3),serv3,List.of(esp3,esp1));
		Incidente inci7 = new Incidente("Titulo7","Descripción7", LocalDate.of(2023,11,23),
				cli1,tec1,List.of(tpro1,tpro2),serv1,List.of(esp1));
		Incidente inci8 = new Incidente("Titulo8","Descripción8", LocalDate.of(2023,11,23),
				cli2,tec2,List.of(tpro2,tpro3),serv2,List.of(esp2));
		Incidente inci9 = new Incidente("Titulo9","Descripción9", LocalDate.of(2023,11,23),
				cli3,tec2,List.of(tpro3,tpro1),serv3,List.of(esp3));
		Incidente inci10 = new Incidente("Titulo10","Descripción10", LocalDate.of(2023,11,24),
				cli4,tec4,List.of(tpro1),serv1,List.of(esp1,esp2));
		Incidente inci11 = new Incidente("Titulo11","Descripción11", LocalDate.of(2023,11,24),
				cli5,tec4,List.of(tpro2),serv2,List.of(esp2,esp3));
		Incidente inci12 = new Incidente("Titulo12","Descripción12", LocalDate.of(2023,11,24),
				cli6,tec6,List.of(tpro3),serv3,List.of(esp3,esp1));
		Incidente inci13 = new Incidente("Titulo13","Descripción13", LocalDate.of(2023,11,25),
				cli1,tec1,List.of(tpro1,tpro2),serv1,List.of(esp1));
		Incidente inci14 = new Incidente("Titulo14","Descripción14", LocalDate.of(2023,11,25),
				cli2,tec2,List.of(tpro2,tpro3),serv2,List.of(esp2));
		Incidente inci15 = new Incidente("Titulo15","Descripción15", LocalDate.of(2023,11,25),
				cli3,tec3,List.of(tpro3,tpro1),serv3,List.of(esp3));
		Incidente inci16 = new Incidente("Titulo16","Descripción16", LocalDate.of(2023,11,26),
				cli4,tec4,List.of(tpro1),serv1,List.of(esp1,esp2));
		Incidente inci17 = new Incidente("Titulo17","Descripción17", LocalDate.of(2023,11,26),
				cli5,tec5,List.of(tpro2),serv2,List.of(esp2,esp3));
		Incidente inci18 = new Incidente("Titulo18","Descripción18", LocalDate.of(2023,11,26),
				cli6,tec2,List.of(tpro3),serv3,List.of(esp3,esp1));
		Incidente inci19 = new Incidente("Titulo19","Descripción19", LocalDate.of(2023,11,27),
				cli1,tec6,List.of(tpro1,tpro2),serv1,List.of(esp1));
		Incidente inci20 = new Incidente("Titulo20","Descripción20", LocalDate.of(2023,11,27),
				cli2,tec2,List.of(tpro2,tpro3),serv2,List.of(esp2));
		Incidente inci21 = new Incidente("Titulo21","Descripción21", LocalDate.of(2023,11,27),
				cli3,tec2,List.of(tpro3,tpro1),serv3,List.of(esp3));
		Incidente inci22 = new Incidente("Titulo22","Descripción22", LocalDate.of(2023,11,28),
				cli4,tec5,List.of(tpro1),serv1,List.of(esp1,esp2));
		Incidente inci23 = new Incidente("Titulo23","Descripción23", LocalDate.of(2023,11,28),
				cli5,tec5,List.of(tpro2),serv2,List.of(esp2,esp3));
		Incidente inci24 = new Incidente("Titulo24","Descripción24", LocalDate.of(2023,11,28),
				cli6,tec6,List.of(tpro3),serv3,List.of(esp3,esp1));
		inci1.confirmarIncidente(); inci2.confirmarIncidente(); inci3.confirmarIncidente();inci4.confirmarIncidente();
		inci5.confirmarIncidente(); inci6.confirmarIncidente(); inci7.confirmarIncidente();inci8.confirmarIncidente();
		inci9.confirmarIncidente(); inci10.confirmarIncidente(); inci11.confirmarIncidente();inci12.confirmarIncidente();
		inci13.confirmarIncidente(); inci14.confirmarIncidente(); inci15.confirmarIncidente();inci16.confirmarIncidente();
		inci17.confirmarIncidente(); inci18.confirmarIncidente(); inci19.confirmarIncidente();inci20.confirmarIncidente();
		inci21.confirmarIncidente(); inci22.confirmarIncidente(); inci23.confirmarIncidente();inci24.confirmarIncidente();
		inci1.cerrarIncidente("Consideración1",LocalDate.of(2023,11,22));
		inci2.cerrarIncidente("Consideración2",LocalDate.of(2023,11,24));
		inci3.cerrarIncidente("Consideración3",LocalDate.of(2023,11,23));
		inci4.cerrarIncidente("Consideración4",LocalDate.of(2023,11,27));
		inci5.cerrarIncidente("Consideración5",LocalDate.of(2023,11,25));
		inci6.cerrarIncidente("Consideración6",LocalDate.of(2023,11,23));
		inci7.cerrarIncidente("Consideración7",LocalDate.of(2023,11,27));
		inci8.cerrarIncidente("Consideración8",LocalDate.of(2023,11,24));
		inci9.cerrarIncidente("Consideración9",LocalDate.of(2023,11,26));
		inci10.cerrarIncidente("Consideración10",LocalDate.of(2023,11,27));
		inci11.cerrarIncidente("Consideración11",LocalDate.of(2023,11,25));
		inci12.cerrarIncidente("Consideración12",LocalDate.of(2023,11,25));
		inci13.cerrarIncidente("Consideración13",LocalDate.of(2023,11,26));
		inci14.cerrarIncidente("Consideración14",LocalDate.of(2023,11,27));
		inci15.cerrarIncidente("Consideración15",LocalDate.of(2023,11,26));
		inci16.cerrarIncidente("Consideración16",LocalDate.of(2023,11,26));
		inci17.cerrarIncidente("Consideración17",LocalDate.of(2023,11,27));
		inci18.cerrarIncidente("Consideración18",LocalDate.of(2023,11,27));
		inci1 = incidenteService.save(inci1);
		inci2 = incidenteService.save(inci2);
		inci3 = incidenteService.save(inci3);
		inci4 = incidenteService.save(inci4);
		inci5 = incidenteService.save(inci5);
		inci6 = incidenteService.save(inci6);
		inci7 = incidenteService.save(inci7);
		inci8 = incidenteService.save(inci8);
		inci9 = incidenteService.save(inci9);
		inci10 = incidenteService.save(inci10);
		inci11 = incidenteService.save(inci11);
		inci12 = incidenteService.save(inci12);
		inci13 = incidenteService.save(inci13);
		inci14 = incidenteService.save(inci14);
		inci15 = incidenteService.save(inci15);
		inci16 = incidenteService.save(inci16);
		inci17 = incidenteService.save(inci17);
		inci18 = incidenteService.save(inci18);
		inci19 = incidenteService.save(inci19);
		inci20 = incidenteService.save(inci20);
		inci21 = incidenteService.save(inci21);
		inci22 = incidenteService.save(inci22);
		inci23 = incidenteService.save(inci23);
		inci24 = incidenteService.save(inci24);

	}

}
