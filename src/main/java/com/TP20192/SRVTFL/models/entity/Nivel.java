/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name="T_Nivel")
public class Nivel implements Serializable {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "niv_sim_id")
    private Long nivSimId;
    
    @Column(name="niv_id")
    private Long nivId;
    
    @JoinColumn(name="sim_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Simulacion simId;
    
    @Column(name="det_niv_descripcion_nivel")
    private String detNivDescripcionNivel;

    public Long getNivSimId() {
        return nivSimId;
    }

    public void setNivSimId(Long nivSimId) {
        this.nivSimId = nivSimId;
    }

    public Long getNivId() {
        return nivId;
    }

    public void setNivId(Long nivId) {
        this.nivId = nivId;
    }

    public Simulacion getSimId() {
        return simId;
    }

    public void setSimId(Simulacion simId) {
        this.simId = simId;
    }

    public String getDetNivDescripcionNivel() {
        return detNivDescripcionNivel;
    }

    public void setDetNivDescripcionNivel(String detNivDescripcionNivel) {
        this.detNivDescripcionNivel = detNivDescripcionNivel;
    }

    public String getDetNivUrlImagen() {
        return detNivUrlImagen;
    }

    public void setDetNivUrlImagen(String detNivUrlImagen) {
        this.detNivUrlImagen = detNivUrlImagen;
    }
    
    @Column(name="det_niv_url_imagen")
    private String detNivUrlImagen;
}
