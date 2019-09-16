/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.implementation;

import com.TP20192.SRVTFL.models.dao.ICitaDao;
import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.service.ICitaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hp
 */
@Service("CitaServiceImpl")
public class CitaServiceImpl implements ICitaService {
    
    @Autowired
    public ICitaDao citaService;

   @Transactional(readOnly = true)
    @Override
    public List<Cita> obtenerCitas() {       
        return (List<Cita>)citaService.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Cita obtenerCita(int cita_id) {
        return citaService.listarCita(cita_id);
    }
    
    @Transactional
    @Override
    public void registrarCita(Cita cita) {
        citaService.save(cita);
    }
}
