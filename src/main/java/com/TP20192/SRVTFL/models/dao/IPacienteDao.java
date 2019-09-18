/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.TP20192.SRVTFL.models.entity.Paciente;
/**
 *
 * @author hp
 */
public interface IPacienteDao extends PagingAndSortingRepository<Paciente, Long> {
    
}
