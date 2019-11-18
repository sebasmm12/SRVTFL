/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.dao;

import com.TP20192.SRVTFL.models.entity.Observacion;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author hp
 */
public interface IObservacionDao extends PagingAndSortingRepository<Observacion, Long>{
    
    @Query("select o from Observacion o where o.pulSimId = :citId")
    public List<Observacion> listarObservacionesSesion(@Param("citId") Long citId);
}
