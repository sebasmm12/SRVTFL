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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author hp
 */
@Controller
@RequestMapping("/Recepcionista")
@SessionAttributes({"usuario", "cita"})

public class RecepcionistaController {

    @Autowired
    public ICitaService citaService;

    @Qualifier("UsuarioDatos")
    private IUsuarioService usuarioService;

    @GetMapping("/index")
    public String index(Model model, Authentication authentication) {
        model.addAttribute("titulo", "INDEX DE RECEPCIONISTA");
        return "Recepcionista/index";
    }

    @GetMapping("/GestionarCitas")
    public String listarCita(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
            @RequestParam(name = "buscar_Paciente", required = false, defaultValue = "") String nombrePaciente,
            @RequestParam(name = "tipoFiltro", required = false, defaultValue = "1") Integer tipoFiltro,
            @RequestParam(name = "fecha", required = false, defaultValue = "00/00/0000") /*@DateTimeFormat(pattern = "dd.MM.yyyy")*/ String fecha,
            @RequestParam(name = "evalFecha", required = false, defaultValue = "1") Integer evalFecha) {

        Pageable pageRequest = PageRequest.of(page, 5);
        Page<Cita> citas;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaD = new Date();
        try {
            fechaD = format.parse(fecha);
        } catch (Exception ex) {
            citas = citaService.obtenerCitas(pageRequest);
            PageRender<Cita> pageRender = new PageRender("/Recepcionista/GestionarCitas", citas);
            model.addAttribute("titulo", "Gestion de Citas");
            model.addAttribute("citas", citas);
            model.addAttribute("mensaje", "No se encontro el registro solicitado");
            model.addAttribute("page", pageRender);
        }

        if (nombrePaciente == null || nombrePaciente.trim().equals("")) {
            if (fecha == null) {
                citas = citaService.obtenerCitas(pageRequest);
            } else {
                if (evalFecha == 1) {
                    citas = citaService.filtroCitaFechaAproximado(fechaD, pageRequest);
                } else {
                    citas = citaService.filtroCitaFechaEspecifico(fechaD, pageRequest);
                }
            }
        } else {
            if (tipoFiltro == 1) {
                citas = citaService.filtroCitaPacienteAproximado(nombrePaciente, pageRequest);
            } else {
                citas = citaService.filtroCitaPacienteEspecifico(nombrePaciente, pageRequest);
            }
        }
        if (citas.isEmpty()) {
            citas = citaService.obtenerCitas(pageRequest);
            model.addAttribute("mensaje", "No se encontro el registro solicitado o no se ingreso ningun parametro de busqueda");
        }

        PageRender<Cita> pageRender = new PageRender("/Recepcionista/GestionarCitas?buscar_Paciente=" + nombrePaciente
                + "&tipoFiltro=" + tipoFiltro + "&fecha=" + fecha + "&evalFecha=" + evalFecha, citas);
        model.addAttribute("titulo", "Gestion de Citas");
        model.addAttribute("citas", citas);
        model.addAttribute("page", pageRender);
        model.addAttribute("fecha", fecha);
        model.addAttribute("nombre", nombrePaciente);
        model.addAttribute("fech", fecha);

        return "Recepcionista/ListarCita";
    }

    @GetMapping("/RegistrarCita")
    public String registrarCita(Model model) {
        model.addAttribute("titulo", "Registro de Cita");
        Cita cita = new Cita();
        model.addAttribute("cita", cita);
        return "Recepcionista/RegistrarCita";
    }

    @RequestMapping(value = "/GuardarCita", method = RequestMethod.POST)
    public String guardarCita(@Valid Cita cita, BindingResult result,
            @RequestParam(name = "paciente_id", required = false, defaultValue = "0") Long paciente_id,
            @RequestParam(name = "buscar_paciente", required = false, defaultValue = "") String paciente_nombre,
            @RequestParam(name = "psicologo_id", required = false, defaultValue = "0") Long psicologo_id,
            @RequestParam(name = "buscar_psicologo", required = false, defaultValue = "") String psicologo_nombre,
            Map<String, Object> model, SessionStatus se) {

        if (result.hasErrors() || paciente_nombre.equals("") || paciente_nombre == null
                || paciente_id == 0 || paciente_id == null) {
            model.put("titulo", "Registro de Cita");

            if (paciente_nombre.equals("")) {

                result.addError(new ObjectError("paciente", "Debe ingresar un paciente  obligatoriamente"));
            } else if (paciente_id != null && paciente_id != 0
                    && !citaService.findPacienteById(paciente_id).nombreCompleto().equals(paciente_nombre)) {
                result.addError(new ObjectError("pacientenombre", "Debe ingresar un paciente existente"));
            } else {
                result.addError(new ObjectError("pacienteidnulo", "Debe ingresar un paciente existente"));
            }
            return "Recepcionista/RegistrarCita";
        }
        else if (psicologo_nombre.equals("") || psicologo_nombre == null
                || psicologo_id == 0 || psicologo_id == null) {
            model.put("titulo", "Registro de Cita");

            if (psicologo_nombre.equals("")) {
                result.addError(new ObjectError("psicologo", "Debe ingresar un psicologo  obligatoriamente"));
            } else if (psicologo_id != null && psicologo_id != 0
                    && !usuarioService.obtenerDetalleUsuario(psicologo_id).getDetUsuNombre().equals(psicologo_nombre)) {
                result.addError(new ObjectError("psicologonombre", "Debe ingresar un psicologo existente"));
            } else {
                result.addError(new ObjectError("psicologoidnulo", "Debe ingresar un psicologo existente"));
            }
            return "Recepcionista/RegistrarCita";
        }
        else if (cita.getCitFechaHoraFin().before(Calendar.getInstance().getTime())
                || cita.getCitFechaHoraInicio().before(Calendar.getInstance().getTime())) {
            result.addError(new ObjectError("fechaNoActual", "La fecha/Hora de inicio/fin no puede ser antes que la actual"));
            return "Recepcionista/RegistrarCita";
        } else if (cita.getCitFechaHoraInicio().after(cita.getCitFechaHoraFin())) {
            result.addError(new ObjectError("fechamenor", "La fecha/Hora de finalizacion no puede ser "
                    + "menor a la del inicio"));
            return "Recepcionista/RegistrarCita";
        }
        Usuario usu = (Usuario) model.get("usuario");
        Paciente pac = new Paciente();
        pac = citaService.findPacienteById(paciente_id);
        EstadoCita ec = citaService.findEstadoCitaById(2);
        //Est Cit 2 = 'CITADO'
        //se.
        cita.setUsuId(usu.getUsu_id());
        cita.setEstadoCita(ec);
        cita.setPaciente(pac);
        cita = citaService.registrarCita(cita);

        Actividad act = new Actividad();
        act.setAct_id(cita.getCitId());
        act.setAgenda_id(psicologo_id);
        act.setAct_nombre("Cita para " + paciente_nombre);
        act.setAct_descripcion("Motivo :" + cita.getCitMotivo());
        act.setAct_fin(cita.getCitFechaHoraFin());
        act.setAct_inicio(cita.getCitFechaHoraInicio());
        //Guardando la Actividad
        citaService.insertaActividad(act);
        return "redirect:/Recepcionista/GestionarCitas";
    }

    @RequestMapping(value = "/ModificarCita", method = RequestMethod.POST)
    public String modificarCita(@Valid Cita cita, BindingResult result,
            @RequestParam(name = "paciente_id", defaultValue = "0") Long paciente_id,
            @RequestParam(name = "buscar_paciente", defaultValue = "") String paciente_nombre,
            Map<String, Object> model, SessionStatus se) {

        if (result.hasErrors() || paciente_nombre.trim().equals("") || paciente_nombre == null
                || !citaService.findPacienteById(paciente_id).nombreCompleto().equals(paciente_nombre)) {
            model.put("titulo", "Modificacion de Cita");

            if (paciente_nombre.equals("") || paciente_nombre == null
                    || !citaService.findPacienteById(paciente_id).nombreCompleto().equals(paciente_nombre)) {
                result.addError(new ObjectError("paciente", "Paciente inexistente o no ingresado, Intenete con un paciente sugerido"));
            }
            return "Recepcionista/ActualizarCita";
        } else if (!paciente_nombre.trim().equals("")) {
            
            if(!citaService.findPacienteById(paciente_id).nombreCompleto().equals(paciente_nombre)){
            result.addError(new ObjectError("paciente", "Paciente inexistente o no ingresado, Intenete con un paciente sugerido"));
            model.put("titulo", "Modificacion de Cita");
            return "Recepcionista/ActualizarCita";
            }
            
        } else if (cita.getCitFechaHoraFin().after(cita.getCitFechaHoraInicio())) {
            result.addError(new ObjectError("fechamenor", "La fecha/Hora de finalizacion no puede ser "
                    + "menor a la del inicio"));
            model.put("titulo", "Modificacion de Cita");
            return "Recepcionista/ActualizarCita";
        }

        Usuario usu = (Usuario) model.get("usuario");
        Paciente pac = new Paciente();
        pac = citaService.findPacienteById(paciente_id);
        EstadoCita ec = citaService.findEstadoCitaById(2);
        //Est Cit 2 = 'CITADO'
        cita.setUsuId(usu.getUsu_id());
        cita.setEstadoCita(ec);
        cita.setPaciente(pac);
        citaService.registrarCita(cita);

        Actividad act = new Actividad();
        act = citaService.ObtenerActividadPorId(cita.getCitId());
        act.setAct_nombre("Cita para " + paciente_nombre);
        act.setAct_descripcion("Motivo :" + cita.getCitMotivo());
        act.setAct_fin(cita.getCitFechaHoraFin());
        act.setAct_inicio(cita.getCitFechaHoraInicio());
        //Guardando la Actividad
        citaService.insertaActividad(act);

        return "redirect:/Recepcionista/GestionarCitas";
    }

    @GetMapping(value = "/cargar-pacientes/{term}", produces = {"application/json"})
    public @ResponseBody
    List<Paciente> cargarPacientes(@PathVariable(name = "term") String term) {
        return citaService.findPacienteByNombre(term);
    }

    @GetMapping(value = "/cargar-psicologos/{term}", produces = {"application/json"})
    public @ResponseBody
    List<DetalleUsuario> cargarPsicologos(@PathVariable(name = "term") String term) {
        return citaService.findDetalleUsuarioByNombre(term);
    }

    @GetMapping("/detalleCita/{citId}")
    public String detalleCita(
            @PathVariable(name = "citId") Long citId,
            Model model) {
        Cita cita = citaService.obtenerCita(citId);
        if (cita == null) {
            return "redirect:/Recepcionista/GestionarCitas";
        }
        model.addAttribute("cita", cita);
        model.addAttribute("titulo", "Acerca de la Cita");
        return "Recepcionista/DetalleCita";
    }

    @GetMapping(value = "/ActualizarCita/{citId}")
    public String actualizarCita(@PathVariable(name = "citId") Long id, Model model) {
        Cita cita = citaService.obtenerCita(id);
        model.addAttribute("cita", cita);
        model.addAttribute("titulo", "Modificacion de Cita");
        return "Recepcionista/ActualizarCita";
    }

    @GetMapping(value = "/CancelarCita/{citId}")
    public String cancelarCita(@PathVariable(name = "citId") Long id, Model model) {
        Cita cita = citaService.obtenerCita(id);
        if(cita ==null){
           return "redirect:/Recepcionista/GestionarCitas";
        }
        EstadoCita ec = citaService.findEstadoCitaById(4);
        cita.setEstadoCita(ec);
        citaService.registrarCita(cita);
        citaService.eliminarActividadPorId(id);
        return "redirect:/Recepcionista/GestionarCitas";
    }
}
