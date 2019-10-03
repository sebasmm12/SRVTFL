/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name ="T_Detalle_Usuario")
public class DetalleUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="usu_id")
    private Long usu_id;
    
    @JoinColumn(name="tip_det_usu_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private TipoDetalleUsuario tipDetUsuId;
    
    @JoinColumn(name="tip_doc_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private TipoDocumento tipDocId;
    
    @NotEmpty
    @Column(name="det_usu_nombre")
    private String detUsuNombre;
    
    @Email
    @NotEmpty
    @Column(name="det_usu_correo")
    private String detUsuCorreo;
    
    @NotEmpty
    @Column(name="det_usu_direccion")
    private String detUsuDireccion;
    
    @NotEmpty
    @Column(name="det_usu_telefono")
    @Pattern(regexp = "/d{9}")
    private String detUsuTelefono;
    
    @Column(name="det_usu_sexo")
    private String detUsuSexo;
    
    @NotEmpty
    @Column(name="det_usu_tip_doc_numero")
    private String detUsuTipoDocNumero;
    
    @Column(name="det_usu_imagen")
    private String detUsuImagen;
    
    @Column(name="det_usu_codigo_colegio")
    private String detUsuCodigoColegio;
    
    @Column(name="det_usu_especialidad")
    private String detUsuEspecialidad;
    
    @Column(name="det_usu_edad")
    private Integer detUsuEdad;
    
    @Column(name="det_usu_estado_civil")
    private String detUsuEstadoCivil;
    
    @Column(name="det_usu_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date detUsuFechaNacimiento;
    
    @NotEmpty
    @Column(name="det_usu_lugar_nacimiento")
    private String detUsuLugarNacimiento;

    @JoinColumn(name="usu_id",unique=true)
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Usuario usuario;

    public Long getUsu_id() {
        return usu_id;
    }

    public void setUsu_id(Long usu_id) {
        this.usu_id = usu_id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public TipoDetalleUsuario getTipDetUsuId() {
        return tipDetUsuId;
    }

    public void setTipDetUsuId(TipoDetalleUsuario tipDetUsuId) {
        this.tipDetUsuId = tipDetUsuId;
    }

    public TipoDocumento getTipDocId() {
        return tipDocId;
    }

    public void setTipDocId(TipoDocumento tipDocId) {
        this.tipDocId = tipDocId;
    }

    public String getDetUsuNombre() {
        return detUsuNombre;
    }

    public void setDetUsuNombre(String detUsuNombre) {
        this.detUsuNombre = detUsuNombre;
    }

    public String getDetUsuCorreo() {
        return detUsuCorreo;
    }

    public void setDetUsuCorreo(String detUsuCorreo) {
        this.detUsuCorreo = detUsuCorreo;
    }

    public String getDetUsuDireccion() {
        return detUsuDireccion;
    }

    public void setDetUsuDireccion(String detUsuDireccion) {
        this.detUsuDireccion = detUsuDireccion;
    }

    public String getDetUsuTelefono() {
        return detUsuTelefono;
    }

    public void setDetUsuTelefono(String detUsuTelefono) {
        this.detUsuTelefono = detUsuTelefono;
    }

    public String getDetUsuSexo() {
        return detUsuSexo;
    }

    public void setDetUsuSexo(String detUsuSexo) {
        this.detUsuSexo = detUsuSexo;
    }

    public String getDetUsuTipoDocNumero() {
        return detUsuTipoDocNumero;
    }

    public void setDetUsuTipoDocNumero(String detUsuTipoDocNumero) {
        this.detUsuTipoDocNumero = detUsuTipoDocNumero;
    }

    public String getDetUsuImagen() {
        return detUsuImagen;
    }

    public void setDetUsuImagen(String detUsuImagen) {
        this.detUsuImagen = detUsuImagen;
    }

    public String getDetUsuCodigoColegio() {
        return detUsuCodigoColegio;
    }

    public void setDetUsuCodigoColegio(String detUsuCodigoColegio) {
        this.detUsuCodigoColegio = detUsuCodigoColegio;
    }

    public String getDetUsuEspecialidad() {
        return detUsuEspecialidad;
    }

    public void setDetUsuEspecialidad(String detUsuEspecialidad) {
        this.detUsuEspecialidad = detUsuEspecialidad;
    }

    public Integer getDetUsuEdad() {
        return detUsuEdad;
    }

    public void setDetUsuEdad(Integer detUsuEdad) {
        this.detUsuEdad = detUsuEdad;
    }

    public String getDetUsuEstadoCivil() {
        return detUsuEstadoCivil;
    }

    public void setDetUsuEstadoCivil(String detUsuEstadoCivil) {
        this.detUsuEstadoCivil = detUsuEstadoCivil;
    }

    public Date getDetUsuFechaNacimiento() {
        return detUsuFechaNacimiento;
    }

    public void setDetUsuFechaNacimiento(Date detUsuFechaNacimiento) {
        this.detUsuFechaNacimiento = detUsuFechaNacimiento;
    }

    public String getDetUsuLugarNacimiento() {
        return detUsuLugarNacimiento;
    }

    public void setDetUsuLugarNacimiento(String detUsuLugarNacimiento) {
        this.detUsuLugarNacimiento = detUsuLugarNacimiento;
    }

    public String getDetUsuOcupacion() {
        return detUsuOcupacion;
    }

    public void setDetUsuOcupacion(String detUsuOcupacion) {
        this.detUsuOcupacion = detUsuOcupacion;
    }

    public String getDetUsuReligion() {
        return detUsuReligion;
    }

    public void setDetUsuReligion(String detUsuReligion) {
        this.detUsuReligion = detUsuReligion;
    }
    
    @Column(name="det_usu_ocupacion")
    private String detUsuOcupacion;
    
    @Column(name="det_usu_religion")
    private String detUsuReligion;
    
    
    
    
    
    
    
}
