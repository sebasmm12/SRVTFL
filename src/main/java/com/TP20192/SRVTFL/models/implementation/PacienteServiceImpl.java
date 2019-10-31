/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.implementation;

import com.TP20192.SRVTFL.models.dao.IPacienteDao;
import com.TP20192.SRVTFL.models.dao.ITipoDocumento;
import com.TP20192.SRVTFL.models.entity.Paciente;
import com.TP20192.SRVTFL.models.entity.TipoDocumento;
import com.TP20192.SRVTFL.models.service.IPacienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alumno
 */
@Service("IPacienteService")
public class PacienteServiceImpl  implements  IPacienteService{

    
    @Autowired
    public IPacienteDao pacienteService;
    
    @Autowired
    public ITipoDocumento tipoDocumentoService;
    
     @Transactional(readOnly = true)
    @Override
    public List<Paciente> obtenerCitas() {     
        return  (List<Paciente>) pacienteService.findAll();
        
    }
    @Transactional(readOnly = true)
    @Override
    public Paciente obtenerPaciente(Long paciente_id) {
      return  (Paciente) pacienteService.buscarPaciente(paciente_id);
    }
    
    @Transactional
    @Override
    public int registrarPaciente(Paciente paciente) {
     
        try{
        pacienteService.save(paciente);  
        return 1;
        }
        catch(Exception e){
            return 0;
        }
    }

    @Override
    public Page<Paciente> obtenerPacientes(Pageable page) {
        return pacienteService.findAll(page);
        
    }

    @Override
    public List<Paciente> findPacienteByNombre(String term) {    
        return  pacienteService.findPacienteByNombre(term);      
    }

    @Transactional(readOnly = true)
    @Override
    public TipoDocumento findDocumentoById(Long id) {
        return tipoDocumentoService.findById(id).orElse(null);
    }

    @Override
    public Paciente findOnePaciente(String nombre, String apellido) {
        return pacienteService.buscarPacienteNombre(nombre,apellido);
    }
    
       
    
}
