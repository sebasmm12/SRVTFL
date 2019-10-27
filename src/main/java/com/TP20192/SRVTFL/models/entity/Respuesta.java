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
@Table(name ="T_Respuesta")
public class Respuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name ="res_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resId;
    
    @Column(name="res_respuesta")
    private String resRespuesta;
    
    @JoinColumn(name ="cita_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cita citaId;

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public String getResRespuesta() {
        return resRespuesta;
    }

    public void setResRespuesta(String resRespuesta) {
        this.resRespuesta = resRespuesta;
    }

    public Cita getCitaId() {
        return citaId;
    }

    public void setCitaId(Cita citaId) {
        this.citaId = citaId;
    }

    public Pregunta getPregId() {
        return pregId;
    }

    public void setPregId(Pregunta pregId) {
        this.pregId = pregId;
    }
    
    @JoinColumn(name ="preg_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pregunta pregId;
}
