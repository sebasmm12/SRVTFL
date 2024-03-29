/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.service;

import com.TP20192.SRVTFL.models.entity.Fobia;
import java.util.List;

/**
 *
 * @author hp
 */
public interface IFobiaService {
    
    public List<Fobia> findFobiaByNombre(String term);
    
    public Fobia findOneFobiaByNombre(String term);
    
    public Fobia findFobiaById(Long fobiaId);
    
    public List<Fobia> findAllFobia();
}
