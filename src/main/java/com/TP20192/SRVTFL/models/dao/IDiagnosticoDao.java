/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.dao;

import com.TP20192.SRVTFL.models.entity.Diagnostico;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author USUARIO
 */
public interface IDiagnosticoDao extends CrudRepository<Diagnostico, Long> {
    
}
