/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.RestController;

import com.TP20192.SRVTFL.models.JsonClass.CitaJson;
import com.TP20192.SRVTFL.models.entity.Actividad;
import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.models.entity.EstadoCita;
import com.TP20192.SRVTFL.models.entity.Fobia;
import com.TP20192.SRVTFL.models.entity.Paciente;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.service.ICitaService;
import com.TP20192.SRVTFL.models.service.IFobiaService;
import com.TP20192.SRVTFL.models.service.IPacienteService;
import com.TP20192.SRVTFL.models.service.IUsuarioService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author hp
 */
@RestController
@RequestMapping("/api/recepcionista")
@SessionAttributes("usuario")
public class RecepcionistaRestController {
    
    @Autowired
    private IPacienteService pacienteService;
    
    @Autowired
    private IFobiaService fobiaservice;
    
    @Autowired
    private ICitaService citaservice;
    
    @Autowired
    private IUsuarioService usuarioService;
    
    @RequestMapping(value="/verificarNombre",method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String verificarNombrePaciente(@RequestBody String term){
        Paciente pac = new Paciente();
        String valor= "";
        String [] paciente = term.split(" ");
        if( paciente.length == 1){
            return "0";
        }
        System.out.println("Realizando Consulta paciente");
        pac = pacienteService.findOnePaciente(paciente[0],paciente[1]);
        if(pac.getPacId() == null || pac.getPacId() == 0){
            valor = "0";
        }else{
            valor = pac.getPacId().toString();
        }
        return valor;
    }
    
    @RequestMapping(value="/verificarFobia",method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String verificarFobia(@RequestBody String term){
        Fobia fob = new Fobia();
        String valor="";
        fob = fobiaservice.findOneFobiaByNombre(term);
        if(fob.getFobId() == null || fob.getFobId() ==0){
            valor = "0";
        }else{
            valor = fob.getFobId().toString();
        }
        return valor;
    }
    
    @RequestMapping(value="/verificarPsicologo",method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String VerificarPsicologo(@RequestBody String term){
        DetalleUsuario detusu = new DetalleUsuario();
        String valor="";
        detusu = citaservice.encontrarDetalleUsuarioByNombre(term);
        if(detusu.getUsu_id() == null || detusu.getUsu_id() ==0){
            valor ="0";
        }else{
            valor = detusu.getUsu_id().toString(); 
        }
        return valor;
    }
    
    @RequestMapping(value="/ingresarCita", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String ingresarCita(@RequestBody CitaJson citaJson,Map<String, Object> model){
        
        Cita cita = citaJson.getCita();
        Long pacienteId = citaJson.getPacienteId();
        Long fobiaId  = citaJson.getFobiaId();
        Long psicologoId = citaJson.getPsicologoId();
        
        Fobia fob = fobiaservice.findFobiaById(fobiaId);
        Paciente pac = pacienteService.obtenerPaciente(pacienteId);
        Usuario psico = usuarioService.encontrarUsuarioPorId(psicologoId);
        Usuario usu = (Usuario) model.get("usuario");
        EstadoCita ec = citaservice.findEstadoCitaById(2);
        
        cita.setPaciente(pac);
        cita.setEstadoCita(ec);
        cita.setSimId(fobiaId);
        cita.setUsuId(psicologoId);
        cita.setUsuRegistro(usu.getUsu_id());
        cita.setEstadoCita(ec);
        cita = citaservice.registrarCita(cita);
        
        Actividad act = new Actividad();
        act.setAct_id(cita.getCitId());
        act.setAgenda_id(psicologoId);
        act.setAct_nombre("Cita para " + pac.getPacNombre());
        act.setAct_descripcion("Motivo :" + cita.getCitMotivo());
        act.setAct_fin(cita.getCitFechaHoraFin());
        act.setAct_inicio(cita.getCitFechaHoraInicio());
        //Guardando la Actividad
        citaservice.insertaActividad(act);
        return "1";
        
    }
    
    @RequestMapping(value="/actualizarCita", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String actualizarCita(@RequestBody CitaJson citaJson,Map<String, Object> model){
        
        Cita cita = citaJson.getCita();
        Long pacienteId = citaJson.getPacienteId();
        Long fobiaId  = citaJson.getFobiaId();
        Long psicologoId = citaJson.getPsicologoId();
        
        Fobia fob = fobiaservice.findFobiaById(fobiaId);
        Paciente pac = pacienteService.obtenerPaciente(pacienteId);
        Usuario psico = usuarioService.encontrarUsuarioPorId(psicologoId);
        Usuario usu = (Usuario) model.get("usuario");
        EstadoCita ec = citaservice.findEstadoCitaById(2);
        
        cita.setPaciente(pac);
        cita.setEstadoCita(ec);
        cita.setSimId(fobiaId);
        cita.setUsuId(psicologoId);
        cita.setUsuRegistro(usu.getUsu_id());
        cita.setEstadoCita(ec);
        cita = citaservice.registrarCita(cita);
        
        Actividad act = new Actividad();
        act.setAct_id(cita.getCitId());
        act.setAgenda_id(psicologoId);
        act.setAct_nombre("Cita para " + pac.getPacNombre());
        act.setAct_descripcion("Motivo :" + cita.getCitMotivo());
        act.setAct_fin(cita.getCitFechaHoraFin());
        act.setAct_inicio(cita.getCitFechaHoraInicio());
        act.setAct_id(1L);
        //Guardando la Actividad
        citaservice.insertaActividad(act);
        return "1";
        
    }
    
    
    
    
    @GetMapping("prueba")
      public String ver(){
        DetalleUsuario detusu = new DetalleUsuario();
        String valor="";
        detusu= usuarioService.encontrarDetalleUsuarioPorId(24L);
        
        return detusu.toString();
    }
    
    
    
}
