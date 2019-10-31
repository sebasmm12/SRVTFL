/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.dao;

import com.TP20192.SRVTFL.models.entity.Nivel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author USUARIO
 */
public interface INivelDao extends CrudRepository<Nivel, Long> {
    
    @Query("select n from Nivel n join fetch n.simId s where n.nivId = :nivId and s.simId = :simId")
    public Nivel encontrarNivel(@Param("nivId") Long nivId, @Param("simId") Long simId);
}
