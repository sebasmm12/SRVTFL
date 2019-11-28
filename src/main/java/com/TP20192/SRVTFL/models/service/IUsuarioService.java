/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.service;

import com.TP20192.SRVTFL.models.entity.Actividad;
import com.TP20192.SRVTFL.models.entity.Agenda;
import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.models.entity.EstadoUsuario;
import com.TP20192.SRVTFL.models.entity.Notificacion;
import com.TP20192.SRVTFL.models.entity.Rol;
import com.TP20192.SRVTFL.models.entity.TipoDetalleUsuario;
import com.TP20192.SRVTFL.models.entity.TipoDocumento;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.entity.UsuarioRol;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

/**
 *
 * @author USUARIO
 */
public interface IUsuarioService {
    public Usuario encontrarUsuario(String usuCodigo);
    
    public Usuario encontrarUsuarioPorId(Long usuId);
    
    public Page<Usuario> encontrarUsuarios(Pageable page);
    
    public Page<DetalleUsuario> encontrarDetalleUsuario(Pageable page);
    
    public DetalleUsuario obtenerDetalleUsuario(Long usu_id);
    
    public List<TipoDocumento> listarTipoDocuemto();
    
    public List<Rol> listarRol(String term);
    
    public Usuario obtenerUsuarioPorNombre(String term);
    
    public EstadoUsuario obtenerEstadoUsuario(int estUsuId);
    
    public Usuario guardarUsuario(Usuario usu);
    
    public void guardarRolesUsuario(List<UsuarioRol> rolesUsuario);
    
    public Rol obtenerRolPorId(Long id);
    
    public void guardarDetalleUsuario(DetalleUsuario detUsu);
    
    public TipoDetalleUsuario encontrarTipoDetalleUsuarioPorId(Long id);
    
    public Page<DetalleUsuario> filtroDetUsuEspecifico(String nombre,Pageable page);
    
    public Page<DetalleUsuario> filtroDetUsuAproximado(String nombre,Pageable page);
    
    public void crearAgenda(Agenda ag);
    
    public DetalleUsuario encontrarDetalleUsuarioPorId(Long id);
    
    public Notificacion saveNotificacion(Notificacion notificacion);
    
    public Notificacion findNotificacion(Long Id);
    
    public DetalleUsuario encontrarUsuarioPorNumeroDoc(String numDoc);
}

