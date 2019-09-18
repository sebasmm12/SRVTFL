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
 * @author USUARIO
 */
@Entity
@Table(name="T_Tipo_Detalle_Usuario")
public class TipoDetalleUsuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name="tip_det_usu_id")
    private Long tipDetUsuId;
    
    @Column(name="tip_det_usu_nombre")
    private String tipDetUsuNombre;

    public Long getTipDetUsuId() {
        return tipDetUsuId;
    }

    public void setTipDetUsuId(Long tipDetUsuId) {
        this.tipDetUsuId = tipDetUsuId;
    }

    public String getTipDetUsuNombre() {
        return tipDetUsuNombre;
    }

    public void setTipDetUsuNombre(String tipDetUsuNombre) {
        this.tipDetUsuNombre = tipDetUsuNombre;
    }
    
}
