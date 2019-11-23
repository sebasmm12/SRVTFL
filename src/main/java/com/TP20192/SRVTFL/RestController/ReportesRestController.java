/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.RestController;

import com.TP20192.SRVTFL.models.domain.PacientePsicologo;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.service.ICitaService;
import com.TP20192.SRVTFL.utils.paginator.PageRender;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author USUARIO
 */
@RestController
@RequestMapping("/api/reportes")
@SessionAttributes("usuario")
public class ReportesRestController {

    @Autowired
    private ICitaService citaService;

    @RequestMapping(value = "/buscar", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public ModelAndView buscarfiltroPaciente(String nombrePaciente, int page, ModelAndView model,Map<String, Object> modelo) {

        Pageable pageRequest = PageRequest.of(page, 10);
        Usuario usu = (Usuario) modelo.get("usuario");
        Page<PacientePsicologo> reportes = citaService.filtrarReportesPacienteEspecifico(usu.getUsu_id(), pageRequest, nombrePaciente);
        PageRender<PacientePsicologo> pageRender = new PageRender<>("/api/reportes/buscar", reportes);
        model.addObject("citas", reportes);
        model.addObject("page", pageRender);
        model.setViewName("Reportes/_ListarPacientes");
        return model;
    }

}
