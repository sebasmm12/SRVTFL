/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author hp
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.springframework.data.repository.PagingAndSortingRepository;
import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.models.entity.EstadoUsuario;
import com.TP20192.SRVTFL.models.entity.Rol;
import com.TP20192.SRVTFL.models.entity.TipoDetalleUsuario;
import com.TP20192.SRVTFL.models.entity.TipoDocumento;
import com.TP20192.SRVTFL.models.entity.Usuario;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 *
 * @author hp
 */
public interface IDetalleUsuarioDao extends PagingAndSortingRepository<DetalleUsuario,Long>{
    
    @Query("select td from TipoDocumento td")
    public List<TipoDocumento> listarTipoDocumento();
    
    @Query("select r from Rol r where r.nombreRol like %?1%")
    public List<Rol> encontrarRol(String term);
    
    @Query("select u from Usuario u where u.usu_codigo = :codigo")
    public Usuario encontrarUsuarioPorCodigo(@Param("codigo") String codigo);
    
    @Query("select eu from EstadoUsuario eu where eu.estUsuId = :estUsuId")
    public EstadoUsuario encontrarEstadoUsuario(@Param("estUsuId") int estUsuId);
    
    @Query("select r from Rol r where r.Id = :id")
    public Rol encontrarRolPorId(@Param("id") Long id);
    
    @Query("select tdu from TipoDetalleUsuario tdu where tdu.tipDetUsuId = :tipDetUsuId")
    public TipoDetalleUsuario encontrarTipoDetalleUsuario(@Param("tipDetUsuId") Long tipDetUsuId);
    
    //filtros
    //Especifico
    @Query("select du from DetalleUsuario du where du.detUsuNombre = :detUsuNombre")
        public Page<DetalleUsuario> filtroDetUsuEspecifico(@Param("detUsuNombre") String detUsuNombre,Pageable pageable);
    //Aproximado
    @Query("select du from DetalleUsuario du where du.detUsuNombre like %?1%")
    public Page<DetalleUsuario> filtroDetUsuAproximado(String detUsuNombre,Pageable pageable);
    
    @Query("select du from DetalleUsuario du where du.detUsuNombre like %?1%")
    public List<DetalleUsuario> findDetalleUsuarioByNombre(String term);
    
    @Query("select du from DetalleUsuario du where du.detUsuNombre = :nombre_usuario")
    public DetalleUsuario encontrarUsuarioByNombre(@Param("nombre_usuario") String nombreUsuario);
    
    @Query("select du from DetalleUsuario du where du.usu_id = :id_usuario")
    public DetalleUsuario encontrarUsuarioById(@Param("id_usuario") Long id_usuario);
    
    @Query("select du from DetalleUsuario du where du.detUsuTipoDocNumero = :numDoc")
    public DetalleUsuario encontrarUsuarioPorNumeroDoc(@Param("numDoc") String numDoc);
}
