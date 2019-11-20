/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author hp
 */
@Table(name="T_Rango_Pulso")
@Entity
public class RangoPulso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ran_pul_id")
    private Long ranPulId;
    
    @Column(name = "ran_pul_edad_minima")
    private Integer ranPulEdadMinima;
    
    @Column(name = "ran_pul_edad_maxima")
    private Integer ranPulEdadMaxima;
    
    @Column(name ="ran_pul_sexo")
    private boolean ranPulSexo;
    
    @Column(name ="ran_pul_pulso_minima")
    private Integer ranPulPulsoMinimo;
    
    @Column(name ="ran_pul_pulso_maximo")
    private Integer ranPulPulsoMaximo;

    public Long getRanPulId() {
        return ranPulId;
    }

    public void setRanPulId(Long ranPulId) {
        this.ranPulId = ranPulId;
    }

    public Integer getRanPulEdadMinima() {
        return ranPulEdadMinima;
    }

    public void setRanPulEdadMinima(Integer ranPulEdadMinima) {
        this.ranPulEdadMinima = ranPulEdadMinima;
    }

    public Integer getRanPulEdadMaxima() {
        return ranPulEdadMaxima;
    }

    public void setRanPulEdadMaxima(Integer ranPulEdadMaxima) {
        this.ranPulEdadMaxima = ranPulEdadMaxima;
    }

    public boolean isRanPulSexo() {
        return ranPulSexo;
    }

    public void setRanPulSexo(boolean ranPulSexo) {
        this.ranPulSexo = ranPulSexo;
    }

    public Integer getRanPulPulsoMinimo() {
        return ranPulPulsoMinimo;
    }

    public void setRanPulPulsoMinimo(Integer ranPulPulsoMinimo) {
        this.ranPulPulsoMinimo = ranPulPulsoMinimo;
    }

    public Integer getRanPulPulsoMaximo() {
        return ranPulPulsoMaximo;
    }

    public void setRanPulPulsoMaximo(Integer ranPulPulsoMaximo) {
        this.ranPulPulsoMaximo = ranPulPulsoMaximo;
    }

   
    
    
}
