/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.dao;

import com.TP20192.SRVTFL.models.entity.Fobia;
import com.TP20192.SRVTFL.models.entity.Paciente;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author hp
 */
public interface IFobiaDao extends PagingAndSortingRepository<Fobia, Long>{
    
    @Query("select f from Fobia f where f.fobNombre like %?1%")
    public List<Fobia> findFobiaByNombre(String term);
    
}
