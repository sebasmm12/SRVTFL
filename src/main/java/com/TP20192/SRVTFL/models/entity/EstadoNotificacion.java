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
@Table(name = "T_Estado_Notificacion")
public class EstadoNotificacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "est_not_id")
    private Long estNotId;

    public Long getEstNotId() {
        return estNotId;
    }

    public void setEstNotId(Long estNotId) {
        this.estNotId = estNotId;
    }

    public String getEstNotNombre() {
        return estNotNombre;
    }

    public void setEstNotNombre(String estNotNombre) {
        this.estNotNombre = estNotNombre;
    }

    @Column(name = "est_not_nombre")
    private String estNotNombre;
}
