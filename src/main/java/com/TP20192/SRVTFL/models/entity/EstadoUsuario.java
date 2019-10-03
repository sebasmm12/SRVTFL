/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import java.io.Serializable;
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
@Entity
@Table(name="T_Estado_Usuario")
public class EstadoUsuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="est_usu_id")
    private Integer estUsuId;
    
    @Column(name="est_usu_nombre")
    private String estUsuNombre;

    public Integer getEstUsuId() {
        return estUsuId;
    }

    public void setEstUsuId(Integer estUsuId) {
        this.estUsuId = estUsuId;
    }

    public String getEstUsuNombre() {
        return estUsuNombre;
    }

    public void setEstUsuNombre(String estUsuNombre) {
        this.estUsuNombre = estUsuNombre;
    }
}
