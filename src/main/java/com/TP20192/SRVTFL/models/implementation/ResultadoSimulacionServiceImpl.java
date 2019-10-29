/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.implementation;

import com.TP20192.SRVTFL.models.dao.IResultadoSimulacionDao;
import com.TP20192.SRVTFL.models.entity.ResultadoSimulacion;
import com.TP20192.SRVTFL.models.service.IResultadoSimulacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gerhard
 */


@Service("IResultadoSimulacionService")
public class ResultadoSimulacionServiceImpl implements IResultadoSimulacionService{

      @Autowired
    public IResultadoSimulacionDao resultadoSimulacioneService;
    
    @Override
    public ResultadoSimulacion obtenerParametrosiniciales() {
       return resultadoSimulacioneService.obtenerParametrosiniciales();    
    }

    @Override
    public void finalizar(int nivelfinal, Long id) {
        ResultadoSimulacion resultadoSimulacion =   resultadoSimulacioneService.obtenerResultado(id);
        resultadoSimulacion.setResSimNivelFinal(nivelfinal);
        resultadoSimulacioneService.save(resultadoSimulacion);
    }

    @Override
    public ResultadoSimulacion RegistrarResultadoSimulacion(ResultadoSimulacion rs) {
        return resultadoSimulacioneService.save(rs);
    }
}
