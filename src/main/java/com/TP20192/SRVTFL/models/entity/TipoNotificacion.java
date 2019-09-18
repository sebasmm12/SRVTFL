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
@Table(name="T_Tipo_Notificacion")
public class TipoNotificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tip_not_id")
    private Long tipNotId;

    public Long getTipNotId() {
        return tipNotId;
    }

    public void setTipNotId(Long tipNotId) {
        this.tipNotId = tipNotId;
    }

    public String getTipNotNombre() {
        return tipNotNombre;
    }

    public void setTipNotNombre(String tipNotNombre) {
        this.tipNotNombre = tipNotNombre;
    }

    public String getTipNotDescripcion() {
        return tipNotDescripcion;
    }

    public void setTipNotDescripcion(String tipNotDescripcion) {
        this.tipNotDescripcion = tipNotDescripcion;
    }
    
    @Column(name="tip_not_nombre")
    private String tipNotNombre;
    
    @Column(name="tip_not_descripcion")
    private String tipNotDescripcion;
}
