/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.RestController;

import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.entity.Pregunta;
import com.TP20192.SRVTFL.models.service.ICitaService;
import com.TP20192.SRVTFL.utils.paginator.PageRender;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author USUARIO
 */
@RestController
@RequestMapping("/api/sesion")
public class SesionTratamientoRestController {

    @Autowired
    private ICitaService citaService;

    @RequestMapping(value = "/buscar", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public ModelAndView buscarFiltros(String nombrePaciente, String datetime, int page, ModelAndView model, String selectFiltroFecha, String selectFiltroPaciente) throws ParseException {

        Pageable pageRequest = PageRequest.of(page, 5);
        Page<Cita> citas = citaService.obtenerCitas(pageRequest);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date fechaD = new Date();
        if (!nombrePaciente.equals("") && !datetime.equals("")) {
            if (!selectFiltroPaciente.equals("Aproximado") && !selectFiltroFecha.equals("Anterior")) {
                fechaD = format.parse(datetime);
                citas = citaService.filtroCombinadoEspecificoUnoaUno(fechaD, nombrePaciente, 2, pageRequest);
            } else {

            }
        } else {
            if (!nombrePaciente.equals("")) {
                if (selectFiltroPaciente.equals("Aproximado")) {
                    citas = citaService.filtroCitaPacienteAproximadoCitado(nombrePaciente, 2, pageRequest);
                } else {
                    citas = citaService.filtroCitaPacienteEspecificoCitado(nombrePaciente, 2, pageRequest);
                }
            }
            if (!datetime.equals("")) {
                fechaD = format.parse(datetime);
                if (selectFiltroFecha.equals("Anterior")) {
                    citas = citaService.filtroCitaFechaAproximadoCitado(fechaD, 2, pageRequest);
                } else {
                    citas = citaService.filtroCitaFechaEspecificoCitado(fechaD, 2, pageRequest);
                }
            }
        }
        PageRender<Cita> pageRender = new PageRender<>("/api/sesion/buscar", citas);
        model.addObject("citas", citas);
        model.addObject("page", pageRender);
        model.setViewName("Psicologo/RealizarSesionTratamiento/_ListarCitas");
        return model;
    }

    @GetMapping(value = "/RealizarPreguntas")
    public String RealizarPreguntas(@RequestParam(value = "citId") Long Id, Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Cita cita = citaService.encontrarCitaconPacinenteconEstado(Id);
        Boolean tratId = false;
        if (cita.getTratId() == null) {
            tratId = true;
        }
        Pageable pageRequest = PageRequest.of(page, 5);
        Page<Pregunta> preguntas = citaService.EncontrarPreguntasCita(tratId, cita.getSimId(), pageRequest);
        PageRender<Pregunta> pageRender = new PageRender<>("/api/sesion/buscar", preguntas);
        model.addAttribute("preguntas", preguntas);
        model.addAttribute("cita", cita);
        model.addAttribute("page", pageRender);
        return "Psicologo/RealizarSesionTratamiento/RealizarPreguntas";
    }
}
