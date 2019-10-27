/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.JsonClass;

/**
 *
 * @author USUARIO
 */
public class RespuestaJson {
    
    private Long resId;

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public String getResRespuesta() {
        return resRespuesta;
    }

    public void setResRespuesta(String resRespuesta) {
        this.resRespuesta = resRespuesta;
    }

    public Long getCitId() {
        return citId;
    }

    public void setCitId(Long citId) {
        this.citId = citId;
    }

    public Long getPregId() {
        return pregId;
    }

    public void setPregId(Long pregId) {
        this.pregId = pregId;
    }
    
    private String resRespuesta;
    
    private Long citId;
    
    private Long pregId;
}
