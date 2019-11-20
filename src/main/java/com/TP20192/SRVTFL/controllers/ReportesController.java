/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.controllers;

import com.TP20192.SRVTFL.models.domain.PacientePsicologo;
import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.entity.Paciente;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.service.ICitaService;
import com.TP20192.SRVTFL.utils.paginator.PageRender;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author USUARIO
 */
@Controller
@RequestMapping("/Reportes")
@SessionAttributes("usuario")
public class ReportesController {

    @Autowired
    private ICitaService pacienteservice;
    
    @RequestMapping(value = {"/pacientes"},method = RequestMethod.GET)
    public String GenerarReportes(Map<String,Object> model,@RequestParam(name= "page", defaultValue = "0") int page) {
        Long usuId = ((Usuario)model.get("usuario")).getUsu_id();
        Pageable pageRequest = PageRequest.of(page, 10);
        Page<PacientePsicologo> citas = pacienteservice.encontrarPacientesPsicologo(usuId, pageRequest);
        PageRender<PacientePsicologo> pageRender = new PageRender<>("/api/reportes/usuario", citas);
        model.put("citas", citas);
        model.put("page", pageRender);
        return "Reportes/GenerarReportes";
    }
    @RequestMapping(value = {"/reportes/{id}"})
    public String CrearReportes(Map<String,Object> model, @PathVariable("id") Long pacId) {
        Paciente paciente = pacienteservice.findPacienteById(pacId);
        model.put("paciente", paciente);
        return "Reportes/reportes";
    }
}
