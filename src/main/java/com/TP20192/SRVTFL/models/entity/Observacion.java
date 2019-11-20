/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "T_Observacion")
public class Observacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="obs_id")
    private Long obsId;
    
    @Column(name="res_sim_id")
    private Long resSimId;
    
    @Column(name="obs_comentario")
    private String obsComentario;
    
    @Column(name="obs_tiempo")
    private Integer obsTiempo;

    public Long getObsId() {
        return obsId;
    }

    public void setObsId(Long obsId) {
        this.obsId = obsId;
    }

    public Long getResSimId() {
        return resSimId;
    }

    public void setResSimId(Long resSimId) {
        this.resSimId = resSimId;
    }

    public String getObsComentario() {
        return obsComentario;
    }

    public void setObsComentario(String obsComentario) {
        this.obsComentario = obsComentario;
    }

    public Integer getObsTiempo() {
        return obsTiempo;
    }

    public void setObsTiempo(Integer obsTiempo) {
        this.obsTiempo = obsTiempo;
    }
}
