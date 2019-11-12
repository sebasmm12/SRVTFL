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
@Table(name = "T_Observaicion")
public class Observacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="obs_id")
    private Long obsId;
    
    @Column(name="pul_sim_id")
    private Long pulSimId;
    
    @Column(name="obs_comentario")
    private String obsComentario;

    public Long getObsId() {
        return obsId;
    }

    public void setObsId(Long obsId) {
        this.obsId = obsId;
    }

    public Long getPulSimId() {
        return pulSimId;
    }

    public void setPulSimId(Long pulSimId) {
        this.pulSimId = pulSimId;
    }

    public String getObsComentario() {
        return obsComentario;
    }

    public void setObsComentario(String obsComentario) {
        this.obsComentario = obsComentario;
    }
    
}
