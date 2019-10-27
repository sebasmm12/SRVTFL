/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.controllers;

/**
 *
 * @author hp
 */
public class StaticInteger {

    private static Integer integer;
    private static boolean finalizar= false;

    
    public static Integer getInteger() {
        return integer;
    }

    public static void setInteger(Integer integer) {
        StaticInteger.integer = integer;
    }

    public static boolean isFinalizar() {
        return finalizar;
    }

    public static void setFinalizar(boolean finalizar) {
        StaticInteger.finalizar = finalizar;
    }

    
}
