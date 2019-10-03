/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.dao;

import com.TP20192.SRVTFL.models.entity.Actividad;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author hp
 */
public interface IActividadDao extends PagingAndSortingRepository<Actividad, Long> {
    
}
