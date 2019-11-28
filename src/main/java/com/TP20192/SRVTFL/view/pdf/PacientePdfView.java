/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.view.pdf;

import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.entity.Paciente;
import com.TP20192.SRVTFL.models.entity.ResultadoSimulacion;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.service.ICitaService;
import com.TP20192.SRVTFL.models.service.IResultadoSimulacionService;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

/**
 *
 * @author USUARIO
 */
@Component("Reportes/reportes")
public class PacientePdfView extends AbstractPdfView {

    @Autowired
    private ICitaService citaService;

    @Autowired
    private IResultadoSimulacionService resultadoSimulacionService;

    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document dcmnt, PdfWriter writer, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        Paciente paciente = (Paciente) map.get("paciente");

        String Imagefile;
        if (paciente.getPacImagen() != null) {
            Imagefile = "D:/SRVTFLrepo/" + paciente.getPacImagen();
        } else {
            Imagefile = "D:/SRVTFLrepo/" + "usuario_normal.png";
        }

        Image image = Image.getInstance(Imagefile);

        //image.setAbsolutePosition(100, 100);
        image.scaleAbsolute(80, 80);
        PdfPTable tabla = new PdfPTable(2);
        tabla.setSpacingAfter(20);
        PdfPCell cell = null;
        cell = new PdfPCell(new Phrase("Datos del Paciente"));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(15f);
        cell.setBorder(0);
        cell.setColspan(2);
        // cell.setFixedHeight(80);
        tabla.addCell(cell);
        cell = new PdfPCell(new Phrase("Apellidos y Nombres: " + paciente.getPacApellido().concat(" ").concat(paciente.getPacNombre())));
        cell.setPadding(15f);
        cell.setBorder(0);
        tabla.addCell(cell);
        cell = new PdfPCell();
        cell.setFixedHeight(80);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setImage(image);
        cell.setBorder(0);
        cell.setRowspan(2);
        System.out.println(image.getScaledHeight());
        tabla.addCell(cell);
        cell = new PdfPCell(new Phrase("Edad: " + paciente.getPac_edad()));
        cell.setPadding(15f);
        cell.setBorder(0);
        tabla.addCell(cell);
        cell = new PdfPCell(new Phrase("Teléfono: " + paciente.getPacTelefono()));
        cell.setPadding(15f);
        cell.setBorder(0);
        tabla.addCell(cell);
        if (paciente.getTipDocId() == 1) {
            cell = new PdfPCell(new Phrase("Tipo de Documento: ".concat("DNI")));
            cell.setPadding(15f);
            cell.setBorder(0);
            tabla.addCell(cell);
        } else {
            cell = new PdfPCell(new Phrase("Tipo de Documento: ".concat("PASAPORTE")));
            cell.setPadding(15f);
            cell.setBorder(0);
            tabla.addCell(cell);
        }
        cell = new PdfPCell(new Phrase("Número de Documento: " + paciente.getPacNumeroDocumento()));
        cell.setPadding(15f);
        cell.setBorder(0);
        tabla.addCell(cell);
        cell = new PdfPCell(new Phrase("Email: " + paciente.getPacEmail()));
        cell.setPadding(15f);
        cell.setBorder(0);
        tabla.addCell(cell);
        cell = new PdfPCell(new Phrase("Dirección: " + paciente.getPacDireccion()));
        cell.setPadding(15f);
        cell.setBorder(0);
        tabla.addCell(cell);
        if (paciente.isPacSexoBiologico()) {
            cell = new PdfPCell(new Phrase("Sexo Biológico: ".concat("Hombre")));
            cell.setPadding(15f);
            cell.setBorder(0);
            tabla.addCell(cell);
        } else {
            cell = new PdfPCell(new Phrase("Sexo Biológico: ".concat("Mujer")));
            cell.setPadding(15f);
            cell.setBorder(0);
            tabla.addCell(cell);
        }

        dcmnt.add(tabla);

        List<String> atributos = new ArrayList();

        atributos.add("Número de Cita");
        atributos.add("Motivo de la Cita");
        atributos.add("Necesito realidad virtual");
        atributos.add("Fecha");
        atributos.add("Pulso Promedio de la Cita");

        Usuario usuario = (Usuario) map.get("usuario");

        List<Cita> citas = new ArrayList();
        citas = citaService.encontrarCitasPacientePsicologo(usuario.getUsu_id(), paciente.getPacId());

        for (int i = 0; i < citas.size(); i++) {

            PdfPTable tabla2 = new PdfPTable(2);

            cell = new PdfPCell(new Phrase(atributos.get(0)));
            cell.setBackgroundColor(Color.GREEN);
            cell.setPadding(10f);
            tabla2.addCell(cell);
            cell = new PdfPCell(new Phrase("" + (i + 1)));
            cell.setPadding(10f);
            tabla2.addCell(cell);
            cell = new PdfPCell(new Phrase(atributos.get(1)));
            cell.setPadding(10f);
            tabla2.addCell(cell);
            cell = new PdfPCell(new Phrase(citas.get(i).getCitMotivo()));
            cell.setPadding(10f);
            tabla2.addCell(cell);
            cell = new PdfPCell(new Phrase(atributos.get(2)));
            cell.setPadding(10f);
            tabla2.addCell(cell);
            if (citas.get(i).isCitVr()) {
                cell = new PdfPCell(new Phrase("Sí necesito"));
                cell.setPadding(10f);
                tabla2.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase("No necesito"));
                cell.setPadding(10f);
                tabla2.addCell(cell);
            }
            cell = new PdfPCell(new Phrase(atributos.get(3)));
            cell.setPadding(10f);
            tabla2.addCell(cell);
            cell = new PdfPCell(new Phrase(citas.get(i).getCitFechaHoraFinReal().toString()));
            cell.setPadding(10f);
            tabla2.addCell(cell);
            cell = new PdfPCell(new Phrase(atributos.get(4)));
            cell.setPadding(10f);
            tabla2.addCell(cell);
            ResultadoSimulacion resultado = resultadoSimulacionService.obtenerResultadoByCita(citas.get(i).getCitId());
            if (resultado == null) {
                cell = new PdfPCell(new Phrase("No se necesito simulación"));
                cell.setPadding(10f);
                tabla2.addCell(cell);
            } else {
                cell = new PdfPCell(new Phrase(resultado.getRestSimPulsoPromedio().toString()));
                cell.setPadding(10f);
                tabla2.addCell(cell);
                cell = new PdfPCell(new Phrase("Nivel Inicial de la Simulación"));
                cell.setPadding(10f);
                tabla2.addCell(cell);
                cell = new PdfPCell(new Phrase("Nivel : " + resultado.getResSimNivelInicial()));
                cell.setPadding(10f);
                tabla2.addCell(cell);
                cell = new PdfPCell(new Phrase("Nivel Final de la Simulación"));
                cell.setPadding(10f);
                tabla2.addCell(cell);
                cell = new PdfPCell(new Phrase("Nivel : " + resultado.getResSimNivelFinal()));
                cell.setPadding(10f);
                tabla2.addCell(cell);
            }
            tabla2.setSpacingBefore(20f);
            dcmnt.add(tabla2);
        }

    }

}
