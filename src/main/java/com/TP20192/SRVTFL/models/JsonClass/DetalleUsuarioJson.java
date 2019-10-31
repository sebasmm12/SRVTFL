/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.JsonClass;

import com.TP20192.SRVTFL.models.entity.DetalleUsuario;
import com.TP20192.SRVTFL.models.entity.TipoDocumento;
import com.TP20192.SRVTFL.models.entity.Usuario;

/**
 *
 * @author USUARIO
 */
public class DetalleUsuarioJson {
    
    private DetalleUsuario detalleUsuario;
    private TipoDocumento tipDoc;
    

    public TipoDocumento getTipDoc() {
        return tipDoc;
    }

    public void setTipDoc(TipoDocumento tipDoc) {
        this.tipDoc = tipDoc;
    }
    
    public DetalleUsuario getDetalleUsuario() {
        return detalleUsuario;
    }

    public void setDetalleUsuario(DetalleUsuario detalleUsuario) {
        this.detalleUsuario = detalleUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    private Usuario usuario;
}
