/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.implementation;

import com.TP20192.SRVTFL.Entidades.VrSimulacion;
import com.TP20192.SRVTFL.EntidadesConfigurables.VrVariables;
import com.TP20192.SRVTFL.models.dao.IResultadoSimulacionDao;
import com.TP20192.SRVTFL.models.entity.ResultadoSimulacion;
import com.TP20192.SRVTFL.models.service.IVrAuxService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PIERO
 */
@Service("VrAuxService")
public class VrAuxServiceImpl implements IVrAuxService {

    @Autowired
    private IResultadoSimulacionDao resultadoDAO;

    @Override
    public void guardarNivel(Long resSimId, int nivel) {
        ResultadoSimulacion resultado = resultadoDAO.obtenerResultado(resSimId);
        resultado.setResSimNivelFinal(nivel);
        resultado.setRestSimFinal(new Date());
        resultadoDAO.save(resultado);
    }

    @Override
    public void iniciarTratamiento(Long idtratamiento, int nivel, Long resSimId, String nombreSimulacion) {
        VrVariables.setNivelInicial(nivel);
        VrVariables.setSimulacionId(idtratamiento);
        VrVariables.setResSimId(resSimId);
        VrVariables.setNombreSimulacio(nombreSimulacion);
    }

    @Override
    public VrSimulacion iniciarSimulacion() {
        ResultadoSimulacion resultado = resultadoDAO.obtenerResultado(VrVariables.getResSimId());
        resultado.setRestSimInicio(new Date());
        resultadoDAO.save(resultado);
        return new VrSimulacion(VrVariables.getSimulacionId(), VrVariables.getNombreSimulacio(), VrVariables.getNivelInicial(), VrVariables.getResSimId());
    }

}
