/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.RestController;

import com.TP20192.SRVTFL.models.entity.Agenda;
import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.models.entity.Rol;
import com.TP20192.SRVTFL.models.entity.TipoDetalleUsuario;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.entity.UsuarioRol;
import com.TP20192.SRVTFL.models.entity.UsuarioRolId;
import com.TP20192.SRVTFL.models.service.IUsuarioService;
import com.TP20192.SRVTFL.utils.paginator.PageRender;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hp
 */
@RestController
@RequestMapping("/api/administrador")
//@SessionAttributes("usuario")
public class AdministradorRestController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/registrarUsuario", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String registrarUsuario(@RequestBody DetalleUsuario detUsu) {

        Usuario u = new Usuario();
        u = detUsu.getUsuario();
        u.setUsu_contraseña(passwordEncoder.encode(detUsu.getDetUsuNombre().trim()));
        u.setEstadoUsuario(usuarioService.obtenerEstadoUsuario(1));
        u = usuarioService.guardarUsuario(u);

        detUsu.setUsu_id(u.getUsu_id());
        detUsu.setUsuario(u);
        Integer index = 1;
        detUsu.setTipDetUsuId(usuarioService.encontrarTipoDetalleUsuarioPorId(index.longValue()));
        usuarioService.guardarDetalleUsuario(detUsu);

        return u.getUsu_id().toString();
    }

    @RequestMapping(value = "/registrarRoles", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String registrarRolesUsuario(@RequestBody List<Integer> roles) {
        Long id = roles.get(roles.size() - 1).longValue();
        Long[] rolId = new Long[roles.size() - 1];
        roles.remove(roles.size() - 1);
        for (int i = 0; i < rolId.length; i++) {
            rolId[i] = roles.get(i).longValue();
        }
        usuarioService.guardarRolesUsuario(rolesDeUsuario(rolId, id));
        for (int i = 0; i < rolId.length; i++) {
            if (rolId[i] == 1) {
                Agenda ag = new Agenda();
                ag.setAgendaId(id);
                usuarioService.crearAgenda(ag);
                break;
            }
        }
        return "1";
    }

    @RequestMapping(value = "/actualizarUsuario", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public String actualizarUsuario(@RequestBody DetalleUsuario detUsu) {
        Integer index = 1;
        detUsu.setTipDetUsuId(usuarioService.encontrarTipoDetalleUsuarioPorId(index.longValue()));
        usuarioService.guardarDetalleUsuario(detUsu);

        return "1";
    }
    
    @RequestMapping(value = "/GestionarUsuarios", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public ModelAndView gestionarUsuarios(int page,
              String nombreUsu, Integer filtro ,ModelAndView model) throws ParseException {

        Pageable pageRequest = PageRequest.of(page, 5);
        Page<DetalleUsuario> detUsus;
        if (nombreUsu == null || nombreUsu.trim().equals("")) {
            detUsus = usuarioService.encontrarDetalleUsuario(pageRequest);
        } else {
            if (filtro == 0) {
                detUsus = usuarioService.filtroDetUsuEspecifico(nombreUsu, pageRequest);
            } else {
                detUsus = usuarioService.filtroDetUsuAproximado(nombreUsu, pageRequest);
            }
        }
        if (detUsus.isEmpty()) {
            detUsus = usuarioService.encontrarDetalleUsuario(pageRequest);
            model.addObject("mensage", "No se pudo encontrar los Datos solicitados, intente cambiando el tipo de filtro o los parametros de entrada");
        }
        PageRender<Usuario> pageRender = new PageRender("/Administrador/_ListarUsuarios", detUsus);
        model.addObject("detUsus", detUsus);
        model.addObject("page", pageRender);
        model.setViewName("/Administrador/_ListarUsuarios");
        return model;
    }
    
    

    private List<UsuarioRol> rolesDeUsuario(Long[] rolId, Long id) {
        List<UsuarioRol> lur = new ArrayList<>();
        for (int i = 0; i < rolId.length; i++) {
            UsuarioRol ur = new UsuarioRol();

            UsuarioRolId uri = new UsuarioRolId();
            Rol rol = usuarioService.obtenerRolPorId(rolId[i]);
            uri.setUsuId(id);
            uri.setRolId(rolId[i]);
            ur.setId(uri);
            ur.setRol(rol);
            lur.add(ur);
        }
        return lur;
    }

}
