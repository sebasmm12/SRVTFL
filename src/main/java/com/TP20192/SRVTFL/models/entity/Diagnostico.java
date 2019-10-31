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
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author hp
 */
@Entity
@Table(name="T_Diagnostico")
public class Diagnostico implements Serializable{
    
    public Diagnostico(){
        
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dia_id")
    private Long diaId;
    
    @NotEmpty
    @Column(name="dia_pruebas_aplicadas")
    private String diaPruebasAplicadas;
    
    @NotEmpty
    @Column(name="dia_observaciones")
    private String diaObservaciones;
    
    
    @NotEmpty
    @Column(name="dia_diagnostico")
    private String diaDiagnostico;

    public String getDiaDiagnostico() {
        return diaDiagnostico;
    }

    public void setDiaDiagnostico(String diaDiagnostico) {
        this.diaDiagnostico = diaDiagnostico;
    }
    
    @NotEmpty
    @Column(name="dia_recomendacion")
    private String diaRecomendacion;

    
    @JoinColumn(name="cit_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cita citId;

    public Cita getCitId() {
        return citId;
    }

    public void setCitId(Cita citId) {
        this.citId = citId;
    }
    
    public Long getDiaId() {
        return diaId;
    }

    public void setDiaId(Long diaId) {
        this.diaId = diaId;
    }

    public String getDiaPruebasAplicadas() {
        return diaPruebasAplicadas;
    }

    public void setDiaPruebasAplicadas(String diaPruebasAplicadas) {
        this.diaPruebasAplicadas = diaPruebasAplicadas;
    }

    public String getDiaObservaciones() {
        return diaObservaciones;
    }

    public void setDiaObservaciones(String diaObservaciones) {
        this.diaObservaciones = diaObservaciones;
    }

    public String getDiaRecomendacion() {
        return diaRecomendacion;
    }

    public void setDiaRecomendacion(String diaRecomendacion) {
        this.diaRecomendacion = diaRecomendacion;
    }
    
    
}
