/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.RestController;

import com.TP20192.SRVTFL.models.JsonClass.CitaJson;
import com.TP20192.SRVTFL.models.JsonClass.CitaJsonTratamiento;
import com.TP20192.SRVTFL.models.entity.Actividad;
import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.models.entity.EstadoActividad;
import com.TP20192.SRVTFL.models.entity.EstadoCita;
import com.TP20192.SRVTFL.models.entity.Fobia;
import com.TP20192.SRVTFL.models.entity.Paciente;
import com.TP20192.SRVTFL.models.entity.Tratamiento;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.service.ICitaService;
import com.TP20192.SRVTFL.models.service.IFobiaService;
import com.TP20192.SRVTFL.models.service.IPacienteService;
import com.TP20192.SRVTFL.models.service.IUsuarioService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    public ICitaService citaService;

    @RequestMapping(value = "/verificarNombre", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String verificarNombrePaciente(@RequestBody String term) {
        Paciente pac = new Paciente();
        String valor = "";
        String[] paciente = term.split(" ");
        if (paciente.length == 1) {
            return "0";
        }
        System.out.println("Realizando Consulta paciente");
        pac = pacienteService.findOnePaciente2(paciente[0], paciente[1]);
        if (pac.getPacId() == null || pac.getPacId() == 0) {
            valor = "0";
        } else {
            valor = pac.getPacId().toString();
        }
        return valor;
    }

    @RequestMapping(value = "/verificarCitaPacienteTratamiento", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String verificarCitaPacienteTratamiento(@RequestBody String term) {
        Paciente pac = new Paciente();
        pac = pacienteService.verificarDniPaciente(term);
        Tratamiento t = new Tratamiento();
        t = citaService.obtenerTratamientoPorPaciente(pac.getPacId());
        List<Cita> lc = new ArrayList<>();
        lc = citaService.verificarCitaPacienteTratamiento(pac.getPacId(), t.getTratId());
        if (lc.size() == 0) {
            return "1";
        } else {
            return "0";
        }
    }

    @RequestMapping(value = "/verificarFactibilidadFechaCita", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String verificarFactibilidadFechaCita(@RequestBody CitaJsonTratamiento citaJsonTratamiento) throws ParseException {
        Date fechaIni = citaJsonTratamiento.getCita().getCitFechaHoraInicio();
        Date fechaFin = citaJsonTratamiento.getCita().getCitFechaHoraFin();
        DetalleUsuario psicologo = new DetalleUsuario();
        psicologo = usuarioService.encontrarUsuarioPorNumeroDoc(citaJsonTratamiento.getDocPsicologo());
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        String date = DATE_FORMAT.format(fechaIni);
        Date fmin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date + " 00:00:00");
        Date fmax = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date + " 23:59:59");
        List<Cita> lc = new ArrayList<>();
        lc = citaService.verificarFechaCitaPacienteCorrecto(psicologo.getUsu_id(), fechaIni, fechaFin);
        /*for(Cita c : lc){
            System.out.println(c.getCitFechaHoraInicio()+"-"+c.getCitFechaHoraFin());
        }
        System.out.println(verificarFCita(lc,fmin,fmax));*/
        boolean v = verificarFCita(lc, fmin, fmax);
        if (v == true) {
            return "1";
        } else {
            return "0";
        }
    }

    @RequestMapping(value = "/verificarFctibilidadPrimeraCita", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String verificarFactibilidadPrimeraCita(@RequestBody String[] array) throws ParseException {
        Paciente pac = new Paciente();
        pac = pacienteService.verificarDniPaciente(array[0]);
        Fobia fob = new Fobia();
        fob = fobiaservice.findOneFobiaByNombre(array[1]);
        List<Tratamiento> lt = new ArrayList<>();
        lt = citaService.obtenerTratamientosPorPaciente(pac.getPacId(),1);
        List<Cita> lc = new ArrayList<>();
        lc = citaService.verificarPrimCitaPacientePendiente(pac.getPacId(), fob.getFobId());
        for (Cita c : lc) {
            System.out.println("CITA ID: " + c.getCitId());
        }
        if (lt.size() == 0) {
            //el paciente no posee tratamientos
            if (lc.size() == 0) {
                //no tiene citas Iniciales pendiente para la fobia
                return "1";
            } else {
                return "3";//tiene citas pendientes iniciales para esa fobia
            }

        } else {
            for (Tratamiento t : lt) {
                if (t.getFobId() == fob.getFobId()) {
                    //paciente ya posee tratamiento para esta fobia , no se puede registrar la primeraCita
                    return "2";
                } else if (lc.size() != 0) {
                    return "3";
                }

            }
            //paciente ya tiene trataiento pero no para esta fobia en especifico
            return "1";
        }
    }

    public boolean verificarFCita(List<Cita> citas, Date ini, Date fin) {
        for (Cita c : citas) {
            if (c.getCitFechaHoraInicio().compareTo(fin) <= 0 || c.getCitFechaHoraFin().compareTo(ini) >= 0) {
                return false;
            }
        }
        return true;
    }

    @RequestMapping(value = "/verificarDNIpaciente", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String verificarDniPaciente(@RequestBody String term) {
        Paciente pac = new Paciente();
        pac = pacienteService.verificarDniPaciente(term);
        if (pac == null) {
            return "0";
        } else {
            return "1";
        }
    }

    @RequestMapping(value = "/verificarFobia", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String verificarFobia(@RequestBody String term) {
        Fobia fob = new Fobia();
        String valor = "";
        fob = fobiaservice.findOneFobiaByNombre(term);
        if (fob == null) {
            valor = "0";
        } else {
            valor = fob.getFobId().toString();
        }
        return valor;
    }
    
    
    @RequestMapping(value = "/bloquearHabilitarFobia", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String bloquearHabilitarFobia(@RequestBody Cita cita) {
        Cita eliminacionCita = new Cita();
        eliminacionCita = citaService.obtenerCita(cita.getCitId());
        EstadoCita ec = new EstadoCita();
        if(eliminacionCita.getEstadoCita().getEstCitId() == 2){
            //bloquear la cita
            ec = citaService.findEstadoCitaById(4);
        }else{
            ec = citaService.findEstadoCitaById(2);
        }
        eliminacionCita.setEstadoCita(ec);
        citaService.registrarCita(eliminacionCita);
        return "1";
    }
    

    @RequestMapping(value = "/verificarPsicologo", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String VerificarPsicologo(@RequestBody String term) {
        DetalleUsuario detusu = new DetalleUsuario();
        String valor = "";
        detusu = citaservice.encontrarDetalleUsuarioByNombre(term);
        if (detusu == null) {
            valor = "0";
        } else {
            valor = detusu.getUsu_id().toString();
        }
        return valor;
    }

    @RequestMapping(value = "/ingresarCita", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String ingresarCita(@RequestBody CitaJson citaJson, Map<String, Object> model) {

        Cita cita = citaJson.getCita();
        Paciente pac = new Paciente();
        pac = pacienteService.verificarDniPaciente(citaJson.getDocumentoPaciente());
        DetalleUsuario psicologo = new DetalleUsuario();
        psicologo = usuarioService.encontrarUsuarioPorNumeroDoc(citaJson.getDocumentoPsicologo());
        Fobia fob = new Fobia();
        fob = fobiaservice.findOneFobiaByNombre(citaJson.getFobia());

        Usuario usu = (Usuario) model.get("usuario");
        EstadoCita ec = citaservice.findEstadoCitaById(2);

        cita.setCitId(null);
        cita.setPaciente(pac);
        cita.setEstadoCita(ec);
        cita.setSimId(fob.getFobId());
        cita.setUsuId(psicologo.getUsu_id());
        cita.setUsuRegistro(usu.getUsu_id());
        cita = citaservice.registrarCita(cita);

        Actividad act = new Actividad();
        act.setAct_id(cita.getCitId());
        act.setAgenda_id(psicologo.getUsu_id());
        act.setAct_nombre("Cita para " + pac.getPacNombre());
        act.setAct_descripcion("Motivo :" + cita.getCitMotivo());
        act.setAct_fin(cita.getCitFechaHoraFin());
        act.setAct_inicio(cita.getCitFechaHoraInicio());
        //Guardando la Actividad

        act.setEstadoActividad(new EstadoActividad(1, "Urgente", "red"));
        citaservice.insertaActividad(act);
        return "1";

    }

    @RequestMapping(value = "/verificarTratamientoPaciente", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String verificarTratamiento(@RequestBody String term) {
        Paciente pac = new Paciente();
        pac = pacienteService.verificarDniPaciente(term);
        
        List<Tratamiento> lt = new ArrayList<>();
        lt = citaService.obtenerTratamientosPorPaciente(pac.getPacId(),1);
        if (lt.size() == 1) {
            Tratamiento t = new Tratamiento();
            t = citaService.obtenerTratamientoPorPaciente(pac.getPacId());
            List<Cita> lc = new ArrayList<>();
            lc = citaService.verificarSessionTratamientoPendiente(pac.getPacId(), t.getTratId(), t.getFobId());
            if (lc.size() > 0) {
                return "2";//tiene un unico tratamiento pero ya posee citas pendientes//CITA INFACTIBLE
            } else {
                return "1";//tiene un unico tratamiento y no posee citas pendiente//CITA FACTIBLE
            }
            //solo tiene un tratamiento
        } else {
            int countTratConCitaPendiente =0;
            List<Cita> lc = new ArrayList<>();
            lc = citaService.verificarSessionTratamientoPendiente(pac.getPacId(), lt.get(0).getTratId(), lt.get(0).getFobId());
            for(Tratamiento t : lt){                          
                if(!citaService.verificarSessionTratamientoPendiente(pac.getPacId(), t.getTratId(), t.getFobId()).isEmpty()){
                    countTratConCitaPendiente ++;
                }
            }
            System.out.println("Cantidad: "+countTratConCitaPendiente);
            if(countTratConCitaPendiente == 0){
                return "0";//tiene doble tratamiento y ninguno tiene session pendiente
            }else if(countTratConCitaPendiente == 1){
                return "3";//tiene doble tratamiento pero solo uno posee sesion pendiente
            }else{
                return "4";//tiene doble cita con tratamiento pendiente
            }
            
        }
    }

    @RequestMapping(value = "/verificarExisteSessionPendienteTratamiento", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String verificarExisteSessionPendienteTratamiento(@RequestBody String term) {
        Paciente pac = new Paciente();
        pac = pacienteService.verificarDniPaciente(term);
        Tratamiento t = new Tratamiento();
        t = citaService.obtenerTratamientoPorPaciente(pac.getPacId());
        List<Cita> lc = new ArrayList<>();
        lc = citaService.verificarSessionTratamientoPendiente(pac.getPacId(), t.getTratId(), t.getFobId());
        if (lc.size() > 0) {
            return "1";
        } else {
            return "0";
        }
        //pac = pacienteService.verificarDniPaciente(term);

    }
    @RequestMapping(value = "/ingresarCitaTratamientoSelector", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String ingresarCitaRegistrarSelector(@RequestBody CitaJsonTratamiento citaJsonTratamiento, Map<String, Object> model) {
        
        Usuario usu = (Usuario) model.get("usuario");
        EstadoCita ec = citaservice.findEstadoCitaById(2);
        Paciente pac = new Paciente();
        pac = pacienteService.verificarDniPaciente(citaJsonTratamiento.getNumeroDocumento());
        DetalleUsuario psicologo = new DetalleUsuario();
        psicologo = usuarioService.encontrarUsuarioPorNumeroDoc(citaJsonTratamiento.getDocPsicologo());
        Tratamiento t = new Tratamiento();
        t = citaService.obtenerTratamientosPorPacienteFobia(pac.getPacId(),citaJsonTratamiento.getCita().getSimId(),1);
        Cita NuevaCitaTrat = new Cita();
        NuevaCitaTrat.setPaciente(pac);
        NuevaCitaTrat.setCitFechaHoraInicio(citaJsonTratamiento.getCita().getCitFechaHoraInicio());
        NuevaCitaTrat.setCitFechaHoraFin(citaJsonTratamiento.getCita().getCitFechaHoraFin());
        NuevaCitaTrat.setCitMotivo(citaJsonTratamiento.getCita().getCitMotivo());
        NuevaCitaTrat.setUsuRegistro(usu.getUsu_id());
        NuevaCitaTrat.setUsuId(psicologo.getUsu_id());
        NuevaCitaTrat.setTratId(t);
        NuevaCitaTrat.setEstadoCita(ec);
        NuevaCitaTrat.setSimId(citaJsonTratamiento.getCita().getSimId());
        NuevaCitaTrat = citaService.registrarCita(NuevaCitaTrat);
        Actividad act = new Actividad();
        act.setAct_id(NuevaCitaTrat.getCitId());
        act.setAgenda_id(psicologo.getUsu_id());
        act.setAct_nombre("Cita para " + pac.getPacNombre());
        act.setAct_descripcion("Motivo :" + NuevaCitaTrat.getCitMotivo());
        act.setAct_fin(NuevaCitaTrat.getCitFechaHoraFin());
        act.setAct_inicio(NuevaCitaTrat.getCitFechaHoraInicio());
        //Guardando la Actividad

        act.setEstadoActividad(new EstadoActividad(1, "Urgente", "red"));
        citaservice.insertaActividad(act);
        return "1";
        
    }
    
    

    @RequestMapping(value = "/ingresarCitaTratamiento", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String ingresarCitaRegistrar(@RequestBody CitaJsonTratamiento citaJsonTratamiento, Map<String, Object> model) {

        Usuario usu = (Usuario) model.get("usuario");
        //DetalleUsuario psicologo = new DetalleUsuario();
        //psicologo = usuarioService.encontrarDetalleUsuarioPorId(18L);
        EstadoCita ec = citaservice.findEstadoCitaById(2);
        Paciente pac = new Paciente();
        pac = pacienteService.verificarDniPaciente(citaJsonTratamiento.getNumeroDocumento());
        DetalleUsuario psicologo = new DetalleUsuario();
        psicologo = usuarioService.encontrarUsuarioPorNumeroDoc(citaJsonTratamiento.getDocPsicologo());
        
        /*Tratamiento t = new Tratamiento();
        t = citaService.obtenerTratamientoPorPaciente(pac.getPacId());*/
        Tratamiento t = new Tratamiento();
        List<Tratamiento> lt = new ArrayList<>();
        lt = citaService.obtenerTratamientosPorPaciente(pac.getPacId(),1);
        if(lt.size() == 1){
            t = lt.get(0);
        }else{
            List<Cita> lc = new ArrayList<Cita>();
            if(citaService.verificarCitaPacienteTratamiento(pac.getPacId(), lt.get(0).getTratId()).isEmpty()){
                t = lt.get(0);
            }else{
                t = lt.get(1);
            }
        }
        
        Cita NuevaCitaTrat = new Cita();
        NuevaCitaTrat.setPaciente(pac);
        NuevaCitaTrat.setCitFechaHoraInicio(citaJsonTratamiento.getCita().getCitFechaHoraInicio());
        NuevaCitaTrat.setCitFechaHoraFin(citaJsonTratamiento.getCita().getCitFechaHoraFin());
        NuevaCitaTrat.setCitMotivo(citaJsonTratamiento.getCita().getCitMotivo());
        NuevaCitaTrat.setUsuRegistro(usu.getUsu_id());
        NuevaCitaTrat.setUsuId(psicologo.getUsu_id());
        NuevaCitaTrat.setTratId(t);
        NuevaCitaTrat.setSimId(t.getFobId());
        NuevaCitaTrat.setEstadoCita(ec);
        NuevaCitaTrat = citaService.registrarCita(NuevaCitaTrat);

        Actividad act = new Actividad();
        act.setAct_id(NuevaCitaTrat.getCitId());
        act.setAgenda_id(psicologo.getUsu_id());
        act.setAct_nombre("Cita para " + pac.getPacNombre());
        act.setAct_descripcion("Motivo :" + NuevaCitaTrat.getCitMotivo());
        act.setAct_fin(NuevaCitaTrat.getCitFechaHoraFin());
        act.setAct_inicio(NuevaCitaTrat.getCitFechaHoraInicio());
        //Guardando la Actividad

        act.setEstadoActividad(new EstadoActividad(1, "Urgente", "red"));
        citaservice.insertaActividad(act);
        return "1";
    }

    @RequestMapping(value = "/actualizarCita", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String actualizarCita(@RequestBody CitaJson citaJson, Map<String, Object> model) {

        Cita cita = citaJson.getCita();
        Paciente pac = new Paciente();
        pac = pacienteService.verificarDniPaciente(citaJson.getDocumentoPaciente());
        Fobia fob = new Fobia();
        fob = fobiaservice.findOneFobiaByNombre(citaJson.getFobia());
        DetalleUsuario psicologo = new DetalleUsuario();
        psicologo = usuarioService.encontrarUsuarioPorNumeroDoc(citaJson.getDocumentoPsicologo());
        Usuario usu = (Usuario) model.get("usuario");
        EstadoCita ec = citaservice.findEstadoCitaById(2);
        
        cita.setPaciente(pac);
        cita.setEstadoCita(ec);
        cita.setSimId(fob.getFobId());
        cita.setUsuId(psicologo.getUsu_id());
        cita.setUsuRegistro(usu.getUsu_id());
        cita.setEstadoCita(ec);
        cita = citaservice.registrarCita(cita);
        
        Actividad act = new Actividad();
        act = citaService.obtenerActividadPorId(cita.getCitId());
        act.setAgenda_id(psicologo.getUsu_id());
        act.setAct_nombre("Cita para " + pac.getPacNombre());
        act.setAct_descripcion("Motivo :" + cita.getCitMotivo());
        act.setAct_fin(cita.getCitFechaHoraFin());
        act.setAct_inicio(cita.getCitFechaHoraInicio());
        //Guardando la Actividad
        citaservice.insertaActividad(act);
        return "1";

    }

    @GetMapping("prueba")
    public String ver() {
        DetalleUsuario detusu = new DetalleUsuario();
        String valor = "";
        detusu = usuarioService.encontrarDetalleUsuarioPorId(24L);

        return detusu.toString();
    }

}
