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
@Table(name="T_Tipo_Documento")
public class TipoDocumento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tip_doc_id")
    private Long tipDocId;

    public Long getTipDocId() {
        return tipDocId;
    }

    public void setTipDocId(Long tipDocId) {
        this.tipDocId = tipDocId;
    }

    public String getTipDocNombre() {
        return tipDocNombre;
    }

    public void setTipDocNombre(String tipDocNombre) {
        this.tipDocNombre = tipDocNombre;
    }
    
    @Column(name="tip_doc_nombre")
    private String tipDocNombre;
}
