/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.service;

import com.TP20192.SRVTFL.models.entity.ResultadoSimulacion;

/**
 *
 * @author Gerhard
 */

public interface IResultadoSimulacionService {
    
     public ResultadoSimulacion obtenerParametrosiniciales();
      public void finalizar( int nivelfinal, Long id );
      
      public ResultadoSimulacion RegistrarResultadoSimulacion(ResultadoSimulacion rs);
}
