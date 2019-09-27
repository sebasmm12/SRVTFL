/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.implementation;

import com.TP20192.SRVTFL.models.dao.IDetalleUsuarioDao;
import com.TP20192.SRVTFL.models.dao.IUsuarioDao;
import com.TP20192.SRVTFL.models.dao.IUsuarioRolDao;
import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.models.entity.EstadoUsuario;
import com.TP20192.SRVTFL.models.entity.Rol;
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
    public void guardarRolesUsuario(List<UsuarioRol> rolesUsuario) {
            usuRolDao.saveAll(rolesUsuario);
    }

    @Override
    public Rol obtenerRolPorId(Long id) {
        return new Rol();
    
    }

    @Override
    public void guardarDetalleUsuario(DetalleUsuario detUsu) {
        detUsuDao.save(detUsu);
    }
}
