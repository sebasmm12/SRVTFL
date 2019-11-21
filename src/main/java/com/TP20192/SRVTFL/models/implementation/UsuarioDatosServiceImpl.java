/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.implementation;

import com.TP20192.SRVTFL.models.dao.IDetalleUsuarioDao;
import com.TP20192.SRVTFL.models.dao.IUsuarioDao;
import com.TP20192.SRVTFL.models.dao.IUsuarioRolDao;
import com.TP20192.SRVTFL.models.dao.IAgendaDao;
import com.TP20192.SRVTFL.models.dao.INotificacionDao;
import com.TP20192.SRVTFL.models.entity.Agenda;
import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.models.entity.EstadoUsuario;
import com.TP20192.SRVTFL.models.entity.Notificacion;
import com.TP20192.SRVTFL.models.entity.Rol;
import com.TP20192.SRVTFL.models.entity.TipoDetalleUsuario;
import com.TP20192.SRVTFL.models.entity.TipoDocumento;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.entity.UsuarioRol;
import com.TP20192.SRVTFL.models.service.IUsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author USUARIO
 */
@Service("UsuarioDatos")
public class UsuarioDatosServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;
    
    @Autowired
    private IDetalleUsuarioDao detUsuDao;
    
    @Autowired
    private IUsuarioRolDao usuRolDao;
    
    @Autowired
    private IAgendaDao agendaDao;
    
    @Autowired
    private INotificacionDao notificacionDao;
    
    @Override
    @Transactional(readOnly = true)
    public Usuario encontrarUsuario(String usuCodigo) {
        return usuarioDao.encontrarUsuario(usuCodigo);
    }

    @Override
    public Page<Usuario> encontrarUsuarios(Pageable page) {
     return  usuarioDao.findAll(page);  
    }

    @Override
    public Page<DetalleUsuario> encontrarDetalleUsuario(Pageable page) {
        return detUsuDao.findAll(page);
    }

    @Override
    public DetalleUsuario obtenerDetalleUsuario(Long usu_id) {
       return detUsuDao.findById(usu_id).orElse(null);
    }

    @Override
    public List<TipoDocumento> listarTipoDocuemto() {
        return detUsuDao.listarTipoDocumento();
    }

    @Override
    public List<Rol> listarRol(String term) {
        return detUsuDao.encontrarRol(term);
    }

    @Override
    public Usuario obtenerUsuarioPorNombre(String term) {
       return detUsuDao.encontrarUsuarioPorCodigo(term);
    }

    @Override
    @Transactional(readOnly=true)
    public EstadoUsuario obtenerEstadoUsuario(int estUsuId) {
        return detUsuDao.encontrarEstadoUsuario(estUsuId);
    }

    @Override
    public Usuario guardarUsuario(Usuario usu) {
       return usuarioDao.save(usu);
    }

    @Override
    @Transactional
    public void guardarRolesUsuario(List<UsuarioRol> rolesUsuario) {
        usuRolDao.saveAll(rolesUsuario);
    }

    @Override
    
    public Rol obtenerRolPorId(Long id) {
        return  detUsuDao.encontrarRolPorId(id);
    }

    @Override
    public void guardarDetalleUsuario(DetalleUsuario detUsu) {
        detUsuDao.save(detUsu);
    }

    @Override
    public TipoDetalleUsuario encontrarTipoDetalleUsuarioPorId(Long id) {
        return detUsuDao.encontrarTipoDetalleUsuario(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario encontrarUsuarioPorId(Long usu_id) {
        return usuarioDao.encontrarUsuarioPorId(usu_id);
        
    }

    @Override
    public Page<DetalleUsuario> filtroDetUsuEspecifico(String nombre, Pageable page) {
        return detUsuDao.filtroDetUsuEspecifico(nombre, page);
    }

    @Override
    public Page<DetalleUsuario> filtroDetUsuAproximado(String nombre, Pageable page) {
         return detUsuDao.filtroDetUsuAproximado(nombre, page);
    }

    @Override
    public void crearAgenda(Agenda ag) {
        agendaDao.save(ag);
    }

    @Override
    public DetalleUsuario encontrarDetalleUsuarioPorId(Long id) {
        return detUsuDao.encontrarUsuarioById(id);
    }

    @Override
    @Transactional
    public Notificacion saveNotificacion(Notificacion notificacion) {
       return  notificacionDao.save(notificacion);
    }

    @Override
    public Notificacion findNotificacion(Long Id) {
        return notificacionDao.findById(Id).orElse(null);
    }
    
}
