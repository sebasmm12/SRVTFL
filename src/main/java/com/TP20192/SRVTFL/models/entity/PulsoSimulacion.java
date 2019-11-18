/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import org.hibernate.annotations.Generated;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "T_Pulso_Simulacion")
public class PulsoSimulacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pul_sim_id")
    private Long pulsSimId;
    @Column(name = "pul_sim_hora")
    private Time pulSimHora;
    @Column(name = "pul_sim_pulso")
    private Long pulSimPulso;
    @Column(name = "res_sim_id")
    private Long resSimId;
    @Column(name = "pul_sim_normal")
    private boolean pulSimNormal;

    public Long getPulsSimId() {
        return pulsSimId;
    }

    public void setPulsSimId(Long pulsSimId) {
        this.pulsSimId = pulsSimId;
    }

    public Long getResSimId() {
        return resSimId;
    }

    public void setResSimId(Long resSimId) {
        this.resSimId = resSimId;
    }

    public Time getPulSimHora() {
        return pulSimHora;
    }

    public void setPulSimHora(Time pulSimHora) {
        this.pulSimHora = pulSimHora;
    }

    public Long getPulSimPulso() {
        return pulSimPulso;
    }

    public void setPulSimPulso(Long pulSimPulso) {
        this.pulSimPulso = pulSimPulso;
    }

    public boolean isPulSimNormal() {
        return pulSimNormal;
    }

    public void setPulSimNormal(boolean pulSimNormal) {
        this.pulSimNormal = pulSimNormal;
    }

    @Override
    public String toString() {
        return "PulsoSimulacion{" + "pulsSimId=" + pulsSimId + ", pulSimHora=" + pulSimHora + ", pulSimPulso=" + pulSimPulso + ", resSimId=" + resSimId + ", pulSimNormal=" + pulSimNormal + '}';
    }
    

}
