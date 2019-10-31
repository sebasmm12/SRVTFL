/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.implementation;

import com.TP20192.SRVTFL.models.dao.IFobiaDao;
import com.TP20192.SRVTFL.models.entity.Fobia;
import com.TP20192.SRVTFL.models.service.IFobiaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */

@Service("IFobiaService")
public class FobiaServiceImpl implements IFobiaService {

    @Autowired
    public IFobiaDao fobiaService;
    
    @Override
    public List<Fobia> findFobiaByNombre(String term) {
        return fobiaService.findFobiaByNombre(term);
    }

    @Override
    public Fobia findOneFobiaByNombre(String term) {
        return fobiaService.findOneFobiaByNombre(term);
    }

    @Override
    public Fobia findFobiaById(Long fobiaId) {
        return fobiaService.findById(fobiaId).orElse(null);
    }
}
