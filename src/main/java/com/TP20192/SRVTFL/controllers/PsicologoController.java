/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.controllers;

import com.TP20192.SRVTFL.models.dao.IPulsoSimulacionDao;
import com.TP20192.SRVTFL.models.entity.Actividad;
import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.entity.PulsoSimulacion;
import com.TP20192.SRVTFL.models.entity.TipoDocumento;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.service.ICitaService;
import com.TP20192.SRVTFL.models.service.IPacienteService;
import com.TP20192.SRVTFL.models.service.IPsicologoService;
import com.TP20192.SRVTFL.models.service.IPulsoSimulacionService;
import com.TP20192.SRVTFL.models.service.IUsuarioService;
import com.TP20192.SRVTFL.utils.paginator.PageRender;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.*;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 *
 * @author USUARIO
 */
@Controller
@RequestMapping(value = {"/", "/psicologo"})
@SessionAttributes("usuario")
public class PsicologoController {

    final static Logger logger = LoggerFactory.getLogger(PsicologoController.class);
    //public static boolean finalizar = false;
    public static CopyOnWriteArrayList<Integer> cowl = new CopyOnWriteArrayList<>();
    //StaticInteger si;

    @Autowired
    @Qualifier("UsuarioDatos")
    private IUsuarioService usuarioService;

    @Autowired
    private IPsicologoService psicologoService;

    @Autowired
    private ICitaService citaService;

    @Autowired
    private IPacienteService pacienteService;
    
    @Autowired
    private IPulsoSimulacionService pulsoSimulacionService;

    private volatile Thread th1;

    @GetMapping(value = {"/index", "/"})
    public String index(Model model, Authentication authentication) {
        Usuario usuario = usuarioService.encontrarUsuario(authentication.getName());
        model.addAttribute("usuario", usuario);
        return "Psicologo/index";
    }

    @GetMapping(value = "/GestionarAgenda")
    public String gestionarAgenda(Map<String, Object> model) {
        Usuario usu = (Usuario) model.get("usuario");
        Long usu_codigo = usu.getUsu_id();
        List<Actividad> actividad = new ArrayList<Actividad>();
        actividad = psicologoService.encontrarActividadPsicologo(usu_codigo);
        model.put("actividades", actividad);
        return "Agenda/index";
    }

    @GetMapping(value = "/RealizarSesionTratamiento")
    public String realizarSesionTratamiento(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageRequest = PageRequest.of(page, 5);
        Page<Cita> citas = citaService.encontrarCitasenEstadoenCita(2, pageRequest);
        PageRender<Cita> pageRender = new PageRender<>("/api/sesion/buscar", citas);
        model.addAttribute("citas", citas);
        model.addAttribute("page", pageRender);
        return "Psicologo/RealizarSesionTratamiento/ListarSesionesCitas";
    }

    @GetMapping(value = "/VisualizarInformacion")
    public String visualizarInformacionCita(Model model, @RequestParam(value = "citId") Long Id) {
        Cita cita = citaService.encontrarCitaconPacinenteconEstado(Id);
        TipoDocumento tipoDoc = pacienteService.findDocumentoById(Long.valueOf(cita.getPaciente().getTipDocId()));
        model.addAttribute("cita", cita);
        model.addAttribute("documento", tipoDoc);
        return "Psicologo/RealizarSesionTratamiento/VisualizarCita";
    }

    @GetMapping(value = "/RealizarSesion")
    public String realizarSesion(Model model) {
        model.addAttribute("titulo", "Realizacion de Sesion de Simulacion");
        System.out.println("Se llego aqui");
        return "Psicologo/RealizarSesionTratamiento/iniciarSimulacion";
    }

    /*@GetMapping(value="/chartSEEyWF")
    public String chartSEE(){
        return "Psicologo/RealizarSesionTratamiento/iniciarSimulacion";
    }*/
    @GetMapping("/simulacionPulso")
    public SseEmitter iniciarSimulacion() {
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                for (int i = 0; true; i++) {

                    if (StaticInteger.getInteger() != null /*&& cowl.get(i) < 300*/) {
                        //emitter.send(StaticInteger.getInteger());
                        PulsoSimulacion ps = new PulsoSimulacion();
                        ps.setPulSimHora(Calendar.getInstance().getTime());
                        ps.setPulSimNormal(true);
                        ps.setPulSimPulso(StaticInteger.getInteger().longValue());
                        pulsoSimulacionService.insertarPulsoSimulacion(ps);
                        System.out.println("Dato Recivido");
                    }
                    /*if(cowl.get(i) != null){
                        emitter.send(cowl.get(i));
                        logger.info("Dato Recivido");
                    }*/
                    Thread.sleep(1500);
                }
            } catch (Exception ex) {
            }
        });
        return emitter;
    }

    @RequestMapping(value = "/iniciarLectura", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @Async
    public String iniciarLecturaArduino() {

        th1 = new Thread(new Runnable() {
            int i = 1;

            @Override
            public void run() {
                SerialPort sp[] = SerialPort.getCommPorts();
                StaticInteger.setFinalizar(false);
                SerialPort s = sp[0];
                System.out.println(s.getDescriptivePortName());
                System.out.println(s.isOpen());

                s.openPort();
                s.clearRTS();
                s.clearDTR();
                s.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 0, 0);
                s.setComPortParameters(9600, 8, 1, 0);

                logger.info("Conectado a " + s.getSystemPortName());

                s.addDataListener(new SerialPortDataListener() {

                    @Override
                    public void serialEvent(SerialPortEvent event) {
                        //String data = new String (event.getReceivedData());
                        //chm.put(Integer.parseInt(data), Integer.parseInt(data));
                        //if(th1 == Thread.currentThread()){
                        String data = new String(event.getReceivedData());
                        //cowl.add(Integer.parseInt(data));
                        StaticInteger.setInteger(Integer.parseInt(data));
                        logger.info("Data: " + data + " incertado en el concurrentHashMap");
                        /*}else{
                            s.removeDataListener();
                            s.closePort();  
                            logger.info("Listener Eliminado y Puerto Cerrado");
                        }*/

                    }

                    @Override
                    public int getListeningEvents() {
                        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
                    }

                });
                while (!StaticInteger.isFinalizar()) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                s.removeDataListener();
                s.closePort();
            }
        }
        );
        th1.start();

        return "1";
    }

    @RequestMapping(value = "/pausarLectura", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @Async
    public String pausarLecturaArduino() {
        //th1 = null;
        System.out.println("Lectura de Listener pausada");
        return "1";
    }

    @RequestMapping(value = "/finalizarLectura", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @Async
    public String finalizarLecturaArduino() {
        //th1 = null;
        System.out.println("Lectura de Listener Finalizado");
        //Aqui se hara algo mas
        StaticInteger.setFinalizar(true);
        return "1";
    }
    /*Thread th2 = Thread.currentThread();
                            while(th1 == th2){
                                System.out.println("Data: "+i);
                                i++;
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    //java.util.logging.Logger.getLogger(PsicologoController.class.getName()).log(Level.SEVERE, null, ex);
                                    System.out.println("Ocurrio un error");
                                }
                            }*/
}
