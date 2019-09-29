/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.controllers;

import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.service.ICitaService;
import com.TP20192.SRVTFL.utils.paginator.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
/**
 *
 * @author Fernanda
 */
@Controller
@RequestMapping("/Historial")
@SessionAttributes({"usuario","paciente"})
public class VisualizarHistorialController {
    @Autowired  
    public ICitaService citaService;
    
    @GetMapping("/Ver/{id}")
    public String listarCita(@RequestParam(name="page", defaultValue="0") int page, Model model,
            @PathVariable(name="id") long id){
        
        Pageable pageRequest = PageRequest.of(page, 5);    
        Page<Cita> citas = citaService.obtenerCitasporPaciente(id,pageRequest);        
        PageRender<Cita> pageRender= new PageRender("/Historial/Ver/"+id,citas);
        model.addAttribute("titulo", "Visualizar Historial");
        model.addAttribute("citas",citas);
        model.addAttribute("page",pageRender);
        return "Historial/ListarHistorial";       
    }
    
     @GetMapping("/detalleCita/{citId}")
    public String detalleCita(
            @PathVariable(name="citId") Long citId,
            Model model){
        Cita cita = citaService.obtenerCita(citId);
        if(cita == null){
            return "redirect:/Historial/GestionarCitas";
        }
        model.addAttribute("cita", cita);
        model.addAttribute("titulo", "Acerca de la Cita");
        return "Historial/DetalleCita";
    }
    
    
    
}
