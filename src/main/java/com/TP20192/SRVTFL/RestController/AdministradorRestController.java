/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.RestController;

import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.models.entity.Rol;
import com.TP20192.SRVTFL.models.entity.TipoDetalleUsuario;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.entity.UsuarioRol;
import com.TP20192.SRVTFL.models.entity.UsuarioRolId;
import com.TP20192.SRVTFL.models.service.IUsuarioService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

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
    
    @RequestMapping(value="/registrarUsuario", method = RequestMethod.POST,consumes = "application/json;charset=UTF-8")
    public String registrarUsuario(@RequestBody DetalleUsuario detUsu){
      
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
    @RequestMapping(value="/registrarRoles", method = RequestMethod.POST,consumes = "application/json;charset=UTF-8")
    public String registrarRolesUsuario(@RequestBody List<Integer> roles){
        Long id = roles.get(roles.size()-1).longValue();
        Long[] rolId = new Long[roles.size()-1];
        roles.remove(roles.size()-1);
        for (int i = 0; i < rolId.length; i++) {
            rolId[i] = roles.get(i).longValue();
        }
        usuarioService.guardarRolesUsuario(rolesDeUsuario(rolId,id));
        return "1";
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
