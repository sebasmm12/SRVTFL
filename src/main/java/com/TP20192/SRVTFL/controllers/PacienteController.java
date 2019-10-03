/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.controllers;


import com.TP20192.SRVTFL.models.entity.Paciente;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.service.IPacienteService;
import com.TP20192.SRVTFL.utils.paginator.PageRender;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;


/**
 *
 * @author alumno
 */
@Controller
    @RequestMapping("/Paciente")
@SessionAttributes({"usuario","paciente"})

public class PacienteController {       
      
    @Autowired
    private IPacienteService pacienteService; 
    
    
    @GetMapping("/index")
    public String index(Model model,Authentication authentication) {
      
        model.addAttribute("titulo", "INDEX DE PACIENTE");        
        return "Paciente/index";
    }
    
    
    @GetMapping("/GestionarPacientes")
    public String listarPaciente(@RequestParam(name="page", defaultValue="0") int page, Model model){
        
        Pageable pageRequest = PageRequest.of(page, 5);    
        Page<Paciente> pacientes = pacienteService.obtenerPacientes(pageRequest);
        PageRender<Paciente> pageRender= new PageRender("/Paciente/GestionarPacientes",pacientes);
        model.addAttribute("titulo", "Gestion de Pacientes");
        model.addAttribute("pacientes",pacientes);
        model.addAttribute("page",pageRender);
        return "Paciente/ListarPacientes";     
    }
    
    @GetMapping("/RegistrarPaciente")
    public String registrarPaciente(Model model){
        model.addAttribute("titulo", "Registro de Paciente");
        Paciente paciente = new Paciente();
        model.addAttribute("paciente", paciente);
        return "Paciente/RegistrarPaciente";
    }
    
    
     @GetMapping("/detallePaciente/{pacId}")
    public String detallePaciente(
            @PathVariable(name="pacId") Long pacienteId,
            Model model){
        Paciente paciente = pacienteService.obtenerPaciente(pacienteId);
        if(paciente == null){
            return "redirect:/Paciente/GestionarPacientes";
        }        
        model.addAttribute("paciente", paciente);
        model.addAttribute("titulo", "Paciente");
        return "Paciente/DetallePaciente";
    }
    
     @PostMapping(value="/ModificarPaciente")
    public String modificarPaciente(@Valid Paciente paciente, BindingResult result,Map<String, Object>  model, SessionStatus se ){             
        Usuario usu = (Usuario)model.get("usuario");
        Paciente pac = new Paciente();
        if(result.hasErrors()){
            model.put("titulo", "Modificacion del Paciente");
            return "Paciente/ActualizarPaciente";
        }
          pacienteService.registrarPaciente(paciente);
        return "redirect:/Paciente/GestionarPacientes";
    }
    
    @GetMapping(value="/ActualizarPaciente/{pacienteId}")
    public String actualizarPaciente(@PathVariable (name="pacienteId") Long id, Model model){
        Paciente paciente = pacienteService.obtenerPaciente(id);
        System.out.println(paciente.toString());
        model.addAttribute("paciente", paciente);
        model.addAttribute("titulo","Modificacion del Paciente");
        return "Paciente/ActualizarPaciente";
    }
    
    @PostMapping(value="/GuardarPaciente")
    public String guardarPaciente(@Valid Paciente paciente,BindingResult result, Map<String, Object>  model, SessionStatus se ){   
        Usuario usu = (Usuario)model.get("usuario");
        if(result.hasErrors()){
            model.put("titulo", "Registro de Paciente");
            return "Paciente/RegistrarPaciente";
        }
        paciente.setUsu_id(usu.getUsu_id());
        paciente.setPacId(null);
        System.out.println(paciente.toString());
                  pacienteService.registrarPaciente(paciente);
        return "redirect:/Paciente/GestionarPacientes";
    }
        
}
