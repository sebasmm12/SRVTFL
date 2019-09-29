/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author hp
 */
@Entity
@Table(name= "T_Cita")

public class Cita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "cit_id")
    private Long citId;
    

    @Column(name= "usu_id")
    private Long usuId;

    
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public EstadoCita getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(EstadoCita estadoCita) {
        this.estadoCita = estadoCita;
    }
    
    @PrePersist
    public void prepersist(){
        this.citVr = true;
    }
    
    //@NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pac_id")
    private Paciente paciente;
    
    //@NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="est_cit_id")
    private EstadoCita estadoCita;

    //@NotEmpty
    @Column(name= "dia_id")
    private Integer diaId;
    
    //@Null
    @Column(name= "trat_id")
    private Integer tratId;
    
    //@NotEmpty
    @Column(name= "sim_id")
    private Integer simId;
    
    //@NotEmpty
    @Column(name= "cit_anotaciones")
    private String citAnotaciones;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name= "cit_fecha_hora_inicio")
    private Date citFechaHoraInicio;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name= "cit_fecha_hora_fin")
    private Date citFechaHoraFin;
    
    //@NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name= "cit_fecha_hora_inicio_real")
    private String citFechaHoraInicioReal;
    
    //@NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name= "cit_fecha_hora_fin_real")
    private String citFechaHoraFinReal;
    
    @NotEmpty
    @Size(min=2, max=255)
    @Column(name= "cit_motivo")
    private String citMotivo;
    
    //@Defa
    @Column(name= "cit_vr")
    private boolean citVr;

    public Integer getDiaId() {
        return diaId;
    }

    public void setDiaId(Integer diaId) {
        this.diaId = diaId;
    }

    public Integer getSimId() {
        return simId;
    }

    public void setSimId(Integer simId) {
        this.simId = simId;
    }
    
    public Integer getTratId() {
        return tratId;
    }

    public void setTratId(Integer tratId) {
        this.tratId = tratId;
    }
    
    public Long getCitId() {
        return citId;
    }

    public void setCitId(Long citId) {
        this.citId = citId;
    }

    public Long getUsuId() {
        return usuId;
    }

    public void setUsuId(Long usuId) {
        this.usuId = usuId;
    }
    public String getCitAnotaciones() {
        return citAnotaciones;
    }

    public void setCitAnotaciones(String citAnotaciones) {
        this.citAnotaciones = citAnotaciones;
    }

    public Date getCitFechaHoraInicio() {
        return citFechaHoraInicio;
    }

    public void setCitFechaHoraInicio(Date citFechaHoraInicio) {
        this.citFechaHoraInicio = citFechaHoraInicio;
    }

    public Date getCitFechaHoraFin() {
        return citFechaHoraFin;
    }

    public void setCitFechaHoraFin(Date citFechaHoraFin) {
        this.citFechaHoraFin = citFechaHoraFin;
    }

    
    public String getCitFechaHoraInicioReal() {
        return citFechaHoraInicioReal;
    }

    public void setCitFechaHoraInicioReal(String citFechaHoraInicioReal) {
        this.citFechaHoraInicioReal = citFechaHoraInicioReal;
    }

    public String getCitFechaHoraFinReal() {
        return citFechaHoraFinReal;
    }

    public void setCitFechaHoraFinReal(String citFechaHoraFinReal) {
        this.citFechaHoraFinReal = citFechaHoraFinReal;
    }
   

  

    public String getCitMotivo() {
        return citMotivo;
    }

    public void setCitMotivo(String citMotivo) {
        this.citMotivo = citMotivo;
    }

    public boolean isCitVr() {
        return citVr;
    }

    public void setCitVr(boolean citVr) {
        this.citVr = citVr;
    }

    
    
    

}