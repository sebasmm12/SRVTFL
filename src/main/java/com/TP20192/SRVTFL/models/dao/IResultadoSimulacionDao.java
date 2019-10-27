/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.dao;

import com.TP20192.SRVTFL.models.entity.ResultadoSimulacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Gerhard
 */
public interface IResultadoSimulacionDao  extends CrudRepository<ResultadoSimulacion, Long>{
    
    @Query("select c from ResultadoSimulacion c where c.cita.estadoCita.estCitId = 1")
    public ResultadoSimulacion obtenerParametrosiniciales ();
    
   @Query("select c from ResultadoSimulacion c where c.resSimId = id")
    public ResultadoSimulacion obtenerResultado ( @Param("id")  Long id);
}
