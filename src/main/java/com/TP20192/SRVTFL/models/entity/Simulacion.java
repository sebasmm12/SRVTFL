/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "T_Simulacion")
public class Simulacion implements Serializable{
    
    public Simulacion(){
        
    }
    @Id
    @NotEmpty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sim_id")
    private int simId;
    
    @NotEmpty
    @Column(name="sim_nombre")
    private String sinNombre;
    
    @NotEmpty
    @Column(name="sim_apk_url")
    private String simApkUrl;

    public int getSimId() {
        return simId;
    }

    public void setSimId(int simId) {
        this.simId = simId;
    }

    public String getSinNombre() {
        return sinNombre;
    }

    public void setSinNombre(String sinNombre) {
        this.sinNombre = sinNombre;
    }

    public String getSimApkUrl() {
        return simApkUrl;
    }

    public void setSimApkUrl(String simApkUrl) {
        this.simApkUrl = simApkUrl;
    }
    
    
    
  }
