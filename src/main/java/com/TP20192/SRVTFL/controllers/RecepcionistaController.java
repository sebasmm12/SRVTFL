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
import java.util.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
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
    public CitaServiceImpl citaService;
    
    @Autowired
    @Qualifier("UsuarioDatos")
    private IUsuarioService usuarioService;
    
    @GetMapping("/index")
    public String index(Model model,Authentication authentication) {
      
        model.addAttribute("titulo", "INDEX DE RECEPCIONISTA");        
        return "Recepcionista/index";
    }
    
    @GetMapping("/GestionarCitas")
    public String listarCita(Model model){
        List <Cita> citas = new ArrayList();
        citas = citaService.obtenerCitas();
        model.addAttribute("titulo", "Gestion de Citas");
        model.addAttribute("citas",citas);
        return "Recepcionista/ListarCita";       
    }
    
    
    
}
