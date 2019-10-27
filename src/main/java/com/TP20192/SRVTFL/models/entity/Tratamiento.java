/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author hp
 */

@Entity
@Table(name="T_Tratamiento")
public class Tratamiento implements Serializable{
    
    @Id
    @NotEmpty
    @Column(name="trat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tratId;

    public int getTratId() {
        return tratId;
    }

    public void setTratId(int tratId) {
        this.tratId = tratId;
    }
    
    @Column(name="trat_primera_vez")
    private boolean tratPrimeraVez;

    public boolean isTratPrimeraVez() {
        return tratPrimeraVez;
    }

    public void setTratPrimeraVez(boolean tratPrimeraVez) {
        this.tratPrimeraVez = tratPrimeraVez;
    }

    public Cita getCitId() {
        return citId;
    }

    public void setCitId(Cita citId) {
        this.citId = citId;
    }
    
    
    @JoinColumn(name="cit_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties
    private Cita citId;
}
