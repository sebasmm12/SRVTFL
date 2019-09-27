/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.controllers;

import com.TP20192.SRVTFL.models.service.IUsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.entity.TipoDocumento;
import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.models.entity.Rol;
import com.TP20192.SRVTFL.models.entity.UsuarioRol;
import com.TP20192.SRVTFL.models.entity.UsuarioRolId;
import com.TP20192.SRVTFL.utils.paginator.PageRender;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author hp
 */
@Controller
@RequestMapping("/Administrador")
@SessionAttributes({"usuario","detUsu"})
public class AdministradorController {

    @Qualifier("UsuarioDatos")
    @Autowired
    public IUsuarioService usuarioService;
    
    @Autowired 
    public BCryptPasswordEncoder passwordEncoder;

    @RequestMapping("/GestionarUsuarios")
    public String gestionarUsuarios(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 5);
        Page<DetalleUsuario> detUsus = usuarioService.encontrarDetalleUsuario(pageRequest);
        
        PageRender<Usuario> pageRender = new PageRender("/Administrador/GestionarUsuarios", detUsus);
        model.addAttribute("detUsus", detUsus);
        model.addAttribute("titulo","Gestion de Usurios y Cuentas");       
        model.addAttribute("page",pageRender);
        return "Administrador/ListarUsuario";
    }
    
    @GetMapping("/RegistrarUsuario")
    public String registrarUsuario(Model model){
        model.addAttribute("titulo", "Registro de Usuarios");
        List<TipoDocumento> tipDocs = usuarioService.listarTipoDocuemto();
        Usuario usu = new Usuario();
        DetalleUsuario detUsu = new DetalleUsuario();
        model.addAttribute("usu", usu);
        model.addAttribute("detUsu", detUsu);
        model.addAttribute("tipDocs",tipDocs);
        return "Administrador/RegistrarUsuario";
    }
    
    @GetMapping(value="/cargar-roles/{term}", produces = {"application/json"})
    public @ResponseBody List<Rol> cargarRoles(@PathVariable(name="term") String term){
        return usuarioService.listarRol(term);
    }
   
    @RequestMapping(value="/GuardarUsuario",method = RequestMethod.POST)
    public String guardarUsuario(DetalleUsuario detUsu,Model model, 
                  @RequestParam(name="item_id[]", required = false) String[] rolId){
        Usuario usu =new Usuario();
        if(usuarioService.obtenerUsuarioPorNombre(detUsu.getDetUsuNombre().trim())==null){
            //Creacion de Usuario
            usu.setUsu_codigo(detUsu.getDetUsuNombre().trim());
            usu.setEstadoUsuario(usuarioService.obtenerEstadoUsuario(1));
            usu.setUsu_contraseña(passwordEncoder.encode(detUsu.getDetUsuNombre().trim()));
            usu = usuarioService.guardarUsuario(usu);//guardando usuario en la BD
        }
        List<UsuarioRol> lur = new ArrayList<UsuarioRol>();
        Long[] Idroles = conversion(rolId);
        for (int i = 0; i < Idroles.length; i++) {
            UsuarioRol ur = new UsuarioRol();
            
            UsuarioRolId uri = new UsuarioRolId();
            //Rol rol = 
            uri.setUsuId(usu.getUsu_id().intValue());
            uri.setRolId(Idroles[i].intValue());
            ur.setId(uri);
            lur.add(ur);
        }
        //Guardando los permisos en la Base de Datos
        usuarioService.guardarRolesUsuario(lur);        
       //actualizando al usuario en sus roles
       //usu.setRoles(lur);
       //usuarioService.guardarUsuario(usu); 
       usuarioService.guardarDetalleUsuario(detUsu);
       //Guardando Datos Personales
        return "Administrador/GestionarUsuarios";
    }
    
    private Long[] conversion(String[] array){
        Long[]val = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            val[0] = Long.parseLong(array[0]);
        }
        return val;
    }
    
    @GetMapping("/consultarUsuario/{usu_id}")
    public String consultarUsuario(@PathVariable(name="usu_id") Long usu_id, Model model){
        DetalleUsuario detUsu = new DetalleUsuario();
        detUsu = usuarioService.obtenerDetalleUsuario(usu_id);
        if(detUsu == null){
            return "redirect: Administrador/GestionarUsuarios";
        }
        model.addAttribute("detUsu",detUsu);
        model.addAttribute("titulo","Detalle de Cuenta de Usuario");
        return "Administrador/ConsultarUsuario";
    }
}
