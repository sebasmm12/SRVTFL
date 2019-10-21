    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.controllers;

import com.TP20192.SRVTFL.models.entity.Actividad;
import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.entity.TipoDocumento;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.service.ICitaService;
import com.TP20192.SRVTFL.models.service.IPacienteService;
import com.TP20192.SRVTFL.models.service.IPsicologoService;
import com.TP20192.SRVTFL.models.service.IUsuarioService;
import com.TP20192.SRVTFL.utils.paginator.PageRender;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


/**
 *
 * @author USUARIO
 */
@Controller
@RequestMapping(value = {"/","/psicologo"})
@SessionAttributes("usuario")
public class PsicologoController {
   
    @Autowired
    @Qualifier("UsuarioDatos")
    private IUsuarioService usuarioService;
    
    @Autowired
    private IPsicologoService psicologoService;
    
    @Autowired
    private ICitaService citaService;
    
    @Autowired
    private IPacienteService pacienteService;
    
    @GetMapping(value = {"/index","/"})
    public String index(Model model,Authentication authentication) {
        Usuario usuario = usuarioService.encontrarUsuario(authentication.getName());
        model.addAttribute("usuario", usuario);
        return "Psicologo/index";
    }
    
    @GetMapping(value = "/GestionarAgenda")
    public String gestionarAgenda(Map<String, Object> model){
        Usuario usu=(Usuario)model.get("usuario");
        Long usu_codigo = usu.getUsu_id();
        List<Actividad> actividad = new ArrayList<Actividad>();
        actividad = psicologoService.encontrarActividadPsicologo(usu_codigo);
        model.put("actividades", actividad);
        return "Agenda/index";
    }
    @GetMapping(value="/RealizarSesionTratamiento")
    public String realizarSesionTratamiento(Model model, @RequestParam(name="page", defaultValue = "0") int page)  {
        Pageable pageRequest = PageRequest.of(page,5);
        Page<Cita> citas= citaService.encontrarCitasenEstadoenCita(2, pageRequest);
        PageRender<Cita> pageRender = new PageRender<>("/psicologo/RealizarSesionTratamiento", citas);
        model.addAttribute("citas",citas);
        model.addAttribute("page", pageRender);
        return "Psicologo/RealizarSesionTratamiento/ListarSesionesCitas";
    }
    @GetMapping(value="/VisualizarInformacion")
    public String visualizarInformacionCita(Model model,@RequestParam(value = "citId")Long Id) {
        Cita cita = citaService.encontrarCitaconPacinenteconEstado(Id);
        TipoDocumento tipoDoc =  pacienteService.findDocumentoById(Long.valueOf(cita.getPaciente().getTipDocId()));
        model.addAttribute("cita", cita);
        model.addAttribute("documento",tipoDoc);
        return "Psicologo/RealizarSesionTratamiento/VisualizarCita";
    }
    
} 

