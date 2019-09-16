/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import java.io.Serializable;
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
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author hp
 */
@Entity
@Table(name= "T_Cita")
public class Cita implements Serializable {
    
    public Cita(){
        this.tratId = 0;
    }
    
    @Id
    @NotEmpty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "cit_id")
    private Long citId;
    
    @NotEmpty
    @Column(name= "usu_id")
    private int usuId;

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
    
    
    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pac_id")
    private Paciente paciente;
    
    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="est_cit_id")
    private EstadoCita estadoCita;
    
    
    
    @NotEmpty
    @Column(name= "dia_id")
    private int diaId;
    
    @NotEmpty
    @Column(name= "trat_id")
    private int tratId;
    
    @NotEmpty
    @Column(name= "sim_id")
    private int simId;
    
    @NotEmpty
    @Column(name= "cit_anotaciones")
    private String citAnotaciones;
    
    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name= "cit_fecha_hora_inicio")
    private Date citFechaHoraInicio;
    
    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name= "cit_fecha_hora_fin")
    private Date citFechaHoraFin;
    
    //@NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name= "cit_fecha_hora_inicio_real")
    private Date citFechaHoraInicioReal;
    
    //@NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name= "cit_fecha_hora_fin_real")
    private Date citFechaHoraFinReal;
    
    @NotEmpty
    @Column(name= "cit_motivo")
    private String citMotivo;
    
    @NotEmpty
    @Column(name= "cit_vr")
    private boolean citVr;

    public Long getCitId() {
        return citId;
    }

    public void setCitId(Long citId) {
        this.citId = citId;
    }
    
    public int getUsuId() {
        return usuId;
    }

    public void setUsuId(int usuId) {
        this.usuId = usuId;
    }

   

    public int getDiaId() {
        return diaId;
    }

    public void setDiaId(int diaId) {
        this.diaId = diaId;
    }

    public int getTratId() {
        return tratId;
    }

    public void setTratId(int tratId) {
        this.tratId = tratId;
    }

    public int getSimId() {
        return simId;
    }

    public void setSimId(int simId) {
        this.simId = simId;
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

    public Date getCitFechaHoraInicioReal() {
        return citFechaHoraInicioReal;
    }

    public void setCitFechaHoraInicioReal(Date citFechaHoraInicioReal) {
        this.citFechaHoraInicioReal = citFechaHoraInicioReal;
    }

    public Date getCitFechaHoraFinReal() {
        return citFechaHoraFinReal;
    }

    public void setCitFechaHoraFinReal(Date citFechaHoraFinReal) {
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
