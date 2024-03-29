/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.dao;

import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.models.entity.Rol;
import com.TP20192.SRVTFL.models.entity.TipoDetalleUsuario;
import com.TP20192.SRVTFL.models.entity.TipoDocumento;
import com.TP20192.SRVTFL.models.entity.Usuario;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author USUARIO
 */
public interface IUsuarioDao extends PagingAndSortingRepository<Usuario,Usuario> {
    
    @Query("select u from Usuario u"
            + " join fetch u.roles r join fetch r.rol rt"
            + " join fetch u.estadoUsuario" 
            + " where u.usu_codigo = :usu_codigo")
    public Usuario encontrarUsuario(@Param("usu_codigo")String usu_codigo);
    
    @Query("select u from Usuario u where u.usu_id = :usu_id")
    public Usuario encontrarUsuarioPorId(@Param("usu_id")Long usu_id);
    
    
    
}
