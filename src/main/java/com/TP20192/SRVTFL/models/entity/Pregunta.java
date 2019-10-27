/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name ="T_Pregunta")
public class Pregunta implements Serializable {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="preg_id")
    private Long pregId;
    
    @Column(name="preg_pregunta")
    private String pregPregunta;
    
    @Column(name ="preg_primera_vez")
    private Boolean pregPrimeraVez;

    public Long getPregId() {
        return pregId;
    }

    public void setPregId(Long pregId) {
        this.pregId = pregId;
    }

    public String getPregPregunta() {
        return pregPregunta;
    }

    public void setPregPregunta(String pregPregunta) {
        this.pregPregunta = pregPregunta;
    }

    public Boolean getPregPrimeraVez() {
        return pregPrimeraVez;
    }

    public void setPregPrimeraVez(Boolean pregPrimeraVez) {
        this.pregPrimeraVez = pregPrimeraVez;
    }

    public Fobia getFobId() {
        return fobId;
    }

    public void setFobId(Fobia fobId) {
        this.fobId = fobId;
    }
    
    @JoinColumn(name ="fob_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties
    private Fobia fobId;
}
