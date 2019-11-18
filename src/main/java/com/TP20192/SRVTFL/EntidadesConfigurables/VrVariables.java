/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.EntidadesConfigurables;

/**
 *
 * @author
 */
public class VrVariables {

    // public static long  idcita;
    private static int nivelInicial;
    private static long simulacionId;
    private static long resSimId;
    private static String nombreSimulacio;
    
    private static String imagen;

    public static String getImagen() {
        return imagen;
    }

    public static void setImagen(String imagen) {
        VrVariables.imagen = imagen;
    }

    // public static long  ;
    public static int getNivelInicial() {
        return nivelInicial;
    }

    public static void setNivelInicial(int nivelInicial) {
        VrVariables.nivelInicial = nivelInicial;
    }

    public static long getSimulacionId() {
        return simulacionId;
    }

    public static void setSimulacionId(long simulacionId) {
        VrVariables.simulacionId = simulacionId;
    }

    public static long getResSimId() {
        return resSimId;
    }

    public static void setResSimId(long resSimId) {
        VrVariables.resSimId = resSimId;
    }

    public static String getNombreSimulacio() {
        return nombreSimulacio;
    }

    public static void setNombreSimulacio(String nombreSimulacio) {
        VrVariables.nombreSimulacio = nombreSimulacio;
    }

    
    
}
