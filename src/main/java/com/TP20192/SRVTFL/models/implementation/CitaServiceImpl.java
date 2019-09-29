/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.implementation;

import com.TP20192.SRVTFL.models.dao.ICitaDao;
import com.TP20192.SRVTFL.models.dao.IPacienteDao;
import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.entity.EstadoCita;
import com.TP20192.SRVTFL.models.entity.Paciente;
import com.TP20192.SRVTFL.models.service.ICitaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    
    @Autowired
    public IPacienteDao pacienteService;
    
    @Transactional(readOnly = true)
    @Override
    public List<Cita> obtenerCitas() {        
        return (List<Cita>) citaService.findAll();
    }
    
    @Transactional(readOnly = true)
    @Override
    public Cita obtenerCita(Long cita_id) {
        return citaService.listarCita(cita_id);
    }
    
    @Transactional
    @Override
    public void registrarCita(Cita cita) {
        citaService.save(cita);
    }
    
    @Transactional(readOnly = true)
    @Override
    public Page<Cita> obtenerCitas(Pageable page) {
        return citaService.findAll(page);
    }

    @Transactional(readOnly= true)
    @Override
    public List<Paciente> findPacienteByNombre(String term) {
       return citaService.findPacienteByNombre(term);
    }
    
    @Transactional(readOnly= true)
    @Override
    public Paciente findPacienteById(Long id) {
       return pacienteService.findById(id).orElse(null);
    }
    
    @Transactional(readOnly= true)
    @Override
    public EstadoCita findEstadoCitaById(int estCitId) {
        return citaService.findEstadoCitaById(estCitId);       
    }
    @Transactional
    @Override
    public void deleteCitaById(Long id) {
        citaService.deleteById(id);
    }

    @Override
    public Page<Cita> obtenerCitasporPaciente(long pac_id, Pageable page) {
       return citaService.listarCitasporPaciente(pac_id, page) ;
    
    }
}
