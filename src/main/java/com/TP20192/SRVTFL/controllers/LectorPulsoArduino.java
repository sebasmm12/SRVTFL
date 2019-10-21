/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.controllers;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

/**
 *
 * @author hp
 */
public class LectorPulsoArduino {

    public static void main(String[] args) {
        SerialPort sp[] = SerialPort.getCommPorts();
        SerialPort s = sp[0];
        s.openPort();
        s.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 0, 0);
        s.setComPortParameters(9600, 8, 1, 0);
        if (s.openPort()) {
            System.out.println("Conected Correctly");
        }
        s.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_RECEIVED) {
                    return;
                }
                //byte[] readBuffer = new byte[event.getReceivedData()];
                String data = new String(event.getReceivedData());
                System.out.println("Data: " + data);
            }
        });
    }
}
