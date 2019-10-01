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
import com.TP20192.SRVTFL.models.entity.TipoDetalleUsuario;
import com.TP20192.SRVTFL.models.entity.UsuarioRol;
import com.TP20192.SRVTFL.models.entity.UsuarioRolId;
import com.TP20192.SRVTFL.utils.paginator.PageRender;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author hp
 */
@Controller
@RequestMapping("/Administrador")
@SessionAttributes({"usuario", "detUsu", "usuCred"})
public class AdministradorController {

    @Qualifier("UsuarioDatos")
    @Autowired
    public IUsuarioService usuarioService;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @RequestMapping("/GestionarUsuarios")
    public String gestionarUsuarios(@RequestParam(name = "page", defaultValue = "0") int page,
            Model model,@RequestParam(name = "nombreUsu", required = false, defaultValue="") String nombreUsu,
            @RequestParam(name = "tipoFiltro", required = false,defaultValue="1") Integer filtro) {

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
        PageRender<Usuario> pageRender = new PageRender("/Administrador/GestionarUsuarios?nombreUsu="+nombreUsu
                +"&tipoFiltro="+filtro, detUsus);
        model.addAttribute("detUsus", detUsus);
        model.addAttribute("titulo", "Gestion de Usuarios y Cuentas");
        model.addAttribute("page", pageRender);
        model.addAttribute("term",nombreUsu);
        return "Administrador/ListarUsuario";
    }

    @RequestMapping(value="/FiltrarUsuario",method = RequestMethod.POST)
    public String filtrarUsuario(@RequestParam(name = "nombreUsu", required = false) String nombreUsu,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "tipoFiltro", required = false) Integer filtro ,Model model) {
        int test =0;
        
        Pageable pageRequest = PageRequest.of(page, 5);
        Page<DetalleUsuario> detUsus;
        //filtro=0 ==> especifico
        if (nombreUsu == null || nombreUsu.trim().equals("")) {
            detUsus = usuarioService.encontrarDetalleUsuario(pageRequest);
        } else {
            if (filtro == 0) {
                detUsus = usuarioService.filtroDetUsuEspecifico(nombreUsu, pageRequest);
            } else {
                detUsus = usuarioService.filtroDetUsuAproximado(nombreUsu, pageRequest);
            }
        }
        
        PageRender<Usuario> pageRender = new PageRender("/Administrador/GestionarUsuarios", detUsus);
        model.addAttribute("detUsus", detUsus);
        model.addAttribute("titulo", "Gestion de Usuarios y Cuentas");
        model.addAttribute("page", pageRender);
        return "Administrador/ListarUsuario";
    }

    @GetMapping("/RegistrarUsuario")
    public String registrarUsuario(Model model) {
        model.addAttribute("titulo", "Registro de Usuarios");
        List<TipoDocumento> tipDocs = usuarioService.listarTipoDocuemto();
        Usuario usu = new Usuario();
        DetalleUsuario detUsu = new DetalleUsuario();
        model.addAttribute("usu", usu);
        model.addAttribute("detUsu", detUsu);
        model.addAttribute("tipDocs", tipDocs);
        return "Administrador/RegistrarUsuario";
    }

    @GetMapping(value = "/cargar-roles/{term}", produces = {"application/json"})
    public @ResponseBody
    List<Rol> cargarRoles(@PathVariable(name = "term") String term) {
        return usuarioService.listarRol(term);
    }

    @RequestMapping(value = "/GuardarUsuario", method = RequestMethod.POST)
    public String guardarUsuario(@Valid @ModelAttribute("detUsu") DetalleUsuario detUsu, BindingResult result,
            Model model, @RequestParam(name = "item_id[]", required = false) Long[] rolId,
            @RequestParam(name = "usuCodigo") String usuCodigo
    /*@RequestParam(name="file") MultipartFile foto,*/) /*throws IOException*/ {
        Usuario nuevoUsuario = new Usuario();

        /*String rootPath = "D://SRVTFLrepo";
        if(foto.isEmpty()){          
            byte[] bytes = foto.getBytes();
            Path rutaCompleta = Paths.get(rootPath+"//"+foto.getOriginalFilename());
            Files.write(rutaCompleta,bytes);
            detUsu.setDetUsuImagen(foto.getOriginalFilename());
        }else{
            return "Administrador/RegistrarUsuario";
        }*/
        Usuario usuVal = new Usuario();
        usuVal = usuarioService.obtenerUsuarioPorNombre(usuCodigo.trim());
        if (result.hasErrors() || usuVal != null || usuCodigo.trim().equals("")) {
            if (usuCodigo.trim().equals("")) {
                result.addError(new ObjectError("noNulo", "Debe Ingresar un Nombre de Usuario Obligatoriamente"));
            } else if (usuVal != null) {
                result.addError(new ObjectError("nombreUsuario", "Debe ingresar un nombre de Usuario diferente"));
            }
            model.addAttribute("titulo", "Registro de Usuarios");
            model.addAttribute("tipDocs", usuarioService.listarTipoDocuemto());
            return "Administrador/RegistrarUsuario";
        }
        if (rolId == null || rolId.length == 0) {
            result.addError(new ObjectError("roles", "Debe Ingresar los roles del Usuario Obligatoriamente"));
            model.addAttribute("titulo", "Registro de Usuarios");
            model.addAttribute("tipDocs", usuarioService.listarTipoDocuemto());
            return "Administrador/RegistrarUsuario";
        }
        nuevoUsuario.setUsu_codigo(usuCodigo.trim());
        //Creacion de Usuario
        nuevoUsuario.setEstadoUsuario(usuarioService.obtenerEstadoUsuario(1));
        nuevoUsuario.setUsu_contraseña(passwordEncoder.encode(detUsu.getDetUsuNombre().trim()));
        nuevoUsuario = usuarioService.guardarUsuario(nuevoUsuario);//guardando usuario en la BD

        //Guardando los permisos en la Base de Datos
        usuarioService.guardarRolesUsuario(rolesDeUsuario(rolId, nuevoUsuario));
        detUsu.setUsu_id(nuevoUsuario.getUsu_id());
        Integer index = 1;
        TipoDetalleUsuario tipDetUsu = usuarioService.encontrarTipoDetalleUsuarioPorId(index.longValue());
        detUsu.setTipDetUsuId(tipDetUsu);
        detUsu.setDetUsuEdad(obtenerEdad(detUsu.getDetUsuFechaNacimiento()));
        usuarioService.guardarDetalleUsuario(detUsu);
        //Guardando Datos Personales
        return "redirect:/Administrador/GestionarUsuarios";
    }

    private Integer obtenerEdad(Date cumpleaños) {
        Calendar fechaNac = new GregorianCalendar();
        Calendar Hoy = Calendar.getInstance();
        fechaNac.setTime(cumpleaños);
        int años = Hoy.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
        return años;
    }

    @GetMapping("/consultarUsuario/{usu_id}")
    public String consultarUsuario(@PathVariable(name = "usu_id") Long usu_id, Model model) {
        DetalleUsuario detUsu = new DetalleUsuario();
        detUsu = usuarioService.obtenerDetalleUsuario(usu_id);
        if (detUsu == null) {
            return "redirect: Administrador/GestionarUsuarios";
        }
        model.addAttribute("detUsu", detUsu);
        model.addAttribute("titulo", "Detalle de Cuenta de Usuario");
        return "Administrador/ConsultarUsuario";
    }

    @GetMapping("/ModificarUsuario/{usu_id}")
    public String ModificarUsuario(@PathVariable(name = "usu_id") Long usu_id, Model model) {

        DetalleUsuario detUsu = new DetalleUsuario();
        List<TipoDocumento> tipDocs = usuarioService.listarTipoDocuemto();

        detUsu = usuarioService.obtenerDetalleUsuario(usu_id);
        if (detUsu == null) {
            return "redirect: Administrador/GestionarUsuarios";
        }
        model.addAttribute("tipDocs", tipDocs);
        model.addAttribute("detUsu", detUsu);
        model.addAttribute("titulo", "Modificacion de Cuenta de Usuario");
        return "Administrador/ActualizarUsuario";

    }

    @RequestMapping(value = "/ActualizarUsuario", method = RequestMethod.POST)
    public String ActualizarUsuario(@Valid @ModelAttribute("detUsu") DetalleUsuario detUsu,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tipDocs", usuarioService.listarTipoDocuemto());
            model.addAttribute("titulo", "Modificacion de Cuenta de Usuario");
            return "Administrador/ActualizarUsuario";
        }
        usuarioService.guardarDetalleUsuario(detUsu);
        return "redirect:/Administrador/GestionarUsuarios";
    }

    @GetMapping(value = "/obtener-roles-usuario/{term}", produces = {"application/json"})
    public @ResponseBody
    List<UsuarioRol> obtenerRolesUsuario(@PathVariable(name = "term") Long term) {
        return usuarioService.encontrarUsuarioPorId(term).getRoles();
    }

    @GetMapping("/ModificarCredencial/{usu_id}")
    public String ModificarCredencial(@PathVariable(name = "usu_id") Long usu_id, Model model) {
        Usuario usuCred = usuarioService.encontrarUsuarioPorId(usu_id);
        model.addAttribute("usuCred", usuCred);
        model.addAttribute("titulo", "Gestion de Credenciales");
        return "Administrador/ActualizarCredenciales";
    }

    @RequestMapping(value = "/ActualizarCredencial", method = RequestMethod.POST)
    public String ActualizarCredencial(@Valid @ModelAttribute("usuCred") Usuario usuCred,
             BindingResult result, Model model,
            @RequestParam(name = "item_id[]", required = false) Long[] rolId) {
        int valprueba1 = 0;
        int valprueba2 = 0;
        if (result.hasErrors()) {
            model.addAttribute("usuCred", usuCred);
            model.addAttribute("titulo", "Gestion de Credenciales");
            return "Administrador/ActualizarCredenciales";
        }
        if (rolId == null || rolId.length == 0) {
            result.addError(new ObjectError("sinRol", "Debe asignar un Rol al  Usuario Obligatoriamente"));
            model.addAttribute("usuCred", usuCred);
            model.addAttribute("titulo", "Gestion de Credenciales");
            return "Administrador/ActualizarCredenciales";
        }
        List<UsuarioRol> ru = new ArrayList<>();
        ru = rolesDeUsuario(rolId, usuCred);
        usuCred.setRoles(ru);
        usuarioService.guardarUsuario(usuCred);
        return "redirect:/Administrador/GestionarUsuarios";
    }

    private List<UsuarioRol> rolesDeUsuario(Long[] rolId, Usuario nuevoUsuario) {
        List<UsuarioRol> lur = new ArrayList<>();
        for (int i = 0; i < rolId.length; i++) {
            UsuarioRol ur = new UsuarioRol();

            UsuarioRolId uri = new UsuarioRolId();
            Rol rol = usuarioService.obtenerRolPorId(rolId[i]);
            uri.setUsuId(nuevoUsuario.getUsu_id());
            uri.setRolId(rolId[i]);
            ur.setId(uri);
            ur.setRol(rol);
            lur.add(ur);
        }
        return lur;
    }
}
