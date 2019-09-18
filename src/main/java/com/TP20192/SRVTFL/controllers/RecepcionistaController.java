/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.TP20192.SRVTFL.models.implementation.*;
import com.TP20192.SRVTFL.models.entity.*;
import com.TP20192.SRVTFL.models.service.ICitaService;
import com.TP20192.SRVTFL.models.service.IUsuarioService;
import com.TP20192.SRVTFL.utils.paginator.PageRender;
import java.lang.reflect.Method;
import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
/**
 *
 * @author hp
 */
@Controller
@RequestMapping("/Recepcionista")
@SessionAttributes("usuario")
public class RecepcionistaController {
    
    @Autowired
    public ICitaService citaService;
    
    @Autowired
    @Qualifier("UsuarioDatos")
    private IUsuarioService usuarioService;
    
    @GetMapping("/index")
    public String index(Model model,Authentication authentication) {
      
        model.addAttribute("titulo", "INDEX DE RECEPCIONISTA");        
        return "Recepcionista/index";
    }
    
    @GetMapping("/GestionarCitas")
    public String listarCita(@RequestParam(name="page", defaultValue="0") int page, Model model){
        
        Pageable pageRequest = PageRequest.of(page, 5);    
        Page<Cita> citas = citaService.obtenerCitas(pageRequest);
        PageRender<Cita> pageRender= new PageRender("/Recepcionista/GestionarCitas",citas);
        model.addAttribute("titulo", "Gestion de Citas");
        model.addAttribute("citas",citas);
        model.addAttribute("page",pageRender);
        return "Recepcionista/ListarCita";       
    }
    
    @GetMapping("/RegistrarCita")
    public String registrarCita(Model model){
        model.addAttribute("titulo", "Registro de Cita");
        Cita cita = new Cita();
        model.addAttribute("cita", cita);
        return "Recepcionista/RegistrarCita";
    }
    
    @RequestMapping(value="/RegistrarCita",method = RequestMethod.POST)
    public String guardarCita(@Valid Cita cita,BindingResult result,
                             @RequestParam(name="paciente_id") Long paciente_id,
                             Map<String, Object>  model ){
        
        if(result.hasErrors()){
            model.put("titulo", "Registro de Cita");
            return "Recepcionista/RegistrarCita";
        }
        
        Usuario usu = (Usuario)model.get("usuario");
        Paciente pac = new Paciente();
        pac = citaService.findPacienteById(paciente_id);
        EstadoCita ec = citaService.findEstadoCitaById(2);
        //Est Cit 2 = 'CITADO'
        cita.setUsuId(usu.getUsu_id());
        cita.setEstadoCita(ec);
        cita.setPaciente(pac);
        citaService.registrarCita(cita);
        return "redirect:/Recepcionista/GestionarCitas";
    }
    
    @GetMapping(value="/cargar-pacientes/{term}", produces = {"application/json"})
    public @ResponseBody List<Paciente> cargarPacientes(@PathVariable(name="term") String term){
        return citaService.findPacienteByNombre(term);
    }
    
    @GetMapping("/detalleCita/{id}")
    public String detalleCita(
            @PathVariable(name="id") Long id,
            Model model){
        Cita cita = citaService.obtenerCita(id);
        if(cita == null){
            return "redirect:redirect:/Recepcionista/ListarCita";
        }
        model.addAttribute("cita", cita);
        model.addAttribute("titulo", "Detalle de Cita");
        return "Recepcionista/DetalleCita";
    }
    
    @GetMapping(value="/ModificarCita/{id}")
    public String modificarCita(@PathVariable (name="id") Long id, Model model){
        Cita cita = citaService.obtenerCita(id);
        model.addAttribute("cita", cita);
        return "Recepcionista/ActualizarCita";
    }
    
    public String eliminarCita(@PathVariable (name="id") Long id, Model model){
        Cita cita = citaService.obtenerCita(id);
        if(cita !=null){
           //citaService.deleteCitaById(id);
        }
            return "Recepcionista/GestionarCitas";   
    }
}
