/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.dao;

import com.TP20192.SRVTFL.models.entity.ResultadoSimulacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Gerhard
 */
public interface IResultadoSimulacionDao  extends CrudRepository<ResultadoSimulacion, Long>{
    
    @Query("select c from ResultadoSimulacion c where c.cita.estadoCita.estCitId = 1")
    public ResultadoSimulacion obtenerParametrosiniciales ();
    
    
}
