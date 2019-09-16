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
public class RolPermisoId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "rol_id")
    private int rolId;

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public int getPerId() {
        return perId;
    }

    public void setPerId(int perId) {
        this.perId = perId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.rolId;
        hash = 67 * hash + this.perId;
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
        if (getClass()!= obj.getClass()) {
            return false;
        }
        final RolPermisoId other = (RolPermisoId) obj;
        if (this.rolId != other.rolId) {
            return false;
        }
        if (this.perId != other.perId) {
            return false;
        }
        return true;
    }

    @Column(name = "per_id")
    private int perId;
}
