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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "T_Tratamiento")
public class Tratamiento implements Serializable {

    @Id
    @Column(name = "trat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tratId;

    @Column(name = "cit_id")
    private Long citId;

    @Column(name = "trat_cant_citas")
    public Integer tratCantidadCitas;

    @Column(name = "trat_tipo")
    public String tratTipo;

    @Column(name = "trat_primera_vez")
    private boolean tratPrimeraVez;

    @Column(name = "fob_id")
    private Long fobId;
    
    @Column(name = "pac_id")
    private Long pacienteId;
    
    @Column(name = "trat_est_trat")
    private Integer estadoTratamiento;

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Integer getEstadoTratamiento() {
        return estadoTratamiento;
    }

    public void setEstadoTratamiento(Integer estadoTratamiento) {
        this.estadoTratamiento = estadoTratamiento;
    }
    
    

    public Long getFobId() {
        return fobId;
    }

    public void setFobId(Long fobId) {
        this.fobId = fobId;
    }

    public Integer getTratCantidadCitas() {
        return tratCantidadCitas;
    }

    public Long getTratId() {
        return tratId;
    }

    public void setTratId(Long tratId) {
        this.tratId = tratId;
    }

    public void setTratCantidadCitas(Integer tratCantidadCitas) {
        this.tratCantidadCitas = tratCantidadCitas;
    }

    public String getTratTipo() {
        return tratTipo;
    }

    public void setTratTipo(String tratTipo) {
        this.tratTipo = tratTipo;
    }

    public boolean isTratPrimeraVez() {
        return tratPrimeraVez;
    }

    public void setTratPrimeraVez(boolean tratPrimeraVez) {
        this.tratPrimeraVez = tratPrimeraVez;
    }

    public Long getCitId() {
        return citId;
    }

    public void setCitId(Long citId) {
        this.citId = citId;
    }
}
