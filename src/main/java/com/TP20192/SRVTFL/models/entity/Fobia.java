/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

/**
 *
 * @author hp
 */
public class Fobia implements Serializable{
    
    public Fobia(){
        
    }
    
    @Id
    @NotEmpty
    @Column(name="fob_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fobId;
    
    @NotEmpty
    @Column(name="fob_nombre")
    private String fobNombre;
    
    @NotEmpty
    @Column(name="fob_descripcion")
    private String fobDescripcion;
    
    @NotEmpty
    @Column(name="fob_url_video")
    private String fobUrlVideo;

    public int getFobId() {
        return fobId;
    }

    public void setFobId(int fobId) {
        this.fobId = fobId;
    }

    public String getFobNombre() {
        return fobNombre;
    }

    public void setFobNombre(String fobNombre) {
        this.fobNombre = fobNombre;
    }

    public String getFobDescripcion() {
        return fobDescripcion;
    }

    public void setFobDescripcion(String fobDescripcion) {
        this.fobDescripcion = fobDescripcion;
    }

    public String getFobUrlVideo() {
        return fobUrlVideo;
    }

    public void setFobUrlVideo(String fobUrlVideo) {
        this.fobUrlVideo = fobUrlVideo;
    }
    
    
    
    
}