/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "T_Resultado_Simulacion")
public class ResultadoSimulacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_sim_id")
    private Long resSimId;

    @Column(name = "res_sim_nivel_inicial")
    private Integer resSimNivelInicial;

    @Column(name = "res_sim_nivel_final")
    private Integer resSimNivelFinal;

    @Column(name = "rest_sim_inicio")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date restSimInicio;

    @Column(name = "rest_sim_fin")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date restSimFinal;

    @Column(name = "rest_sim_salidaemergencia")
    private Boolean restSimSalidaEmergencia;

    @JsonIgnore
    @JoinColumn(name = "cit_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Cita cita;

    @Column(name = "rest_sim_pulso_promedio")
    private Integer restSimPulsoPromedio;

    public Long getResSimId() {
        return resSimId;
    }

    public void setResSimId(Long resSimId) {
        this.resSimId = resSimId;
    }

    public Integer getResSimNivelInicial() {
        return resSimNivelInicial;
    }

    public void setResSimNivelInicial(Integer resSimNivelInicial) {
        this.resSimNivelInicial = resSimNivelInicial;
    }

    public Integer getResSimNivelFinal() {
        return resSimNivelFinal;
    }

    public void setResSimNivelFinal(Integer resSimNivelFinal) {
        this.resSimNivelFinal = resSimNivelFinal;
    }

    public Date getRestSimInicio() {
        return restSimInicio;
    }

    public void setRestSimInicio(Date restSimInicio) {
        this.restSimInicio = restSimInicio;
    }

    public Date getRestSimFinal() {
        return restSimFinal;
    }

    public void setRestSimFinal(Date restSimFinal) {
        this.restSimFinal = restSimFinal;
    }

    public Boolean getRestSimSalidaEmergencia() {
        return restSimSalidaEmergencia;
    }

    public void setRestSimSalidaEmergencia(Boolean restSimSalidaEmergencia) {
        this.restSimSalidaEmergencia = restSimSalidaEmergencia;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Integer getRestSimPulsoPromedio() {
        return restSimPulsoPromedio;
    }

    public void setRestSimPulsoPromedio(Integer restSimPulsoPromedio) {
        this.restSimPulsoPromedio = restSimPulsoPromedio;
    }

}
