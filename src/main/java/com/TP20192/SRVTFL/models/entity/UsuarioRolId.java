/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author USUARIO
 */
@Embeddable
public class UsuarioRolId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "rol_id")
    private Long rolId;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.rolId.intValue();
        hash = 29 * hash + this.usuId.intValue();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioRolId other = (UsuarioRolId) obj;
        if (this.rolId != other.rolId) {
            return false;
        }
        if (this.usuId != other.usuId) {
            return false;
        }
        return true;
    }
    @Column(name = "usu_id")
    private Long usuId;

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public Long getUsuId() {
        return usuId;
    }

    public void setUsuId(Long usuId) {
        this.usuId = usuId;
    }

   
}
