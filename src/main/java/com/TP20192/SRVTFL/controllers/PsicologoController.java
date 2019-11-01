/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TP20192.SRVTFL.controllers;

import com.TP20192.SRVTFL.models.dao.IPulsoSimulacionDao;
import com.TP20192.SRVTFL.models.entity.Actividad;
import com.TP20192.SRVTFL.models.entity.Cita;
import com.TP20192.SRVTFL.models.entity.Nivel;
import com.TP20192.SRVTFL.models.entity.Fobia;
import com.TP20192.SRVTFL.models.entity.Pregunta;
import com.TP20192.SRVTFL.models.entity.PulsoSimulacion;
import com.TP20192.SRVTFL.models.entity.ResultadoSimulacion;
import com.TP20192.SRVTFL.models.entity.TipoDocumento;
import com.TP20192.SRVTFL.models.entity.Usuario;
import com.TP20192.SRVTFL.models.service.ICitaService;
import com.TP20192.SRVTFL.models.service.IFobiaService;
import com.TP20192.SRVTFL.models.service.IPacienteService;
import com.TP20192.SRVTFL.models.service.IPsicologoService;
import com.TP20192.SRVTFL.models.service.IPulsoSimulacionService;
import com.TP20192.SRVTFL.models.service.IResultadoSimulacionService;
import com.TP20192.SRVTFL.models.service.IUsuarioService;
import com.TP20192.SRVTFL.models.service.IVrAuxService;
import com.TP20192.SRVTFL.utils.paginator.PageRender;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @Autowired
    private IFobiaService fobiaService;

    @Autowired
    private IResultadoSimulacionService resultadoSimulacionService;
    
    @Autowired
    private IVrAuxService vrAuxService;
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
    public String realizarSesion(@RequestParam(name ="citId",required = false, defaultValue = "") Long citId, Model model) {
        model.addAttribute("titulo", "Realizacion de Sesion de Simulacion");
        
        model.addAttribute("citId", citId);
        System.out.println("Resultado Simulacion Creada");
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

                    if (StaticInteger.getInteger() != null ) {
                        if(StaticInteger.getInteger() <= 150){
                            emitter.send(StaticInteger.getInteger());
                            /*PulsoSimulacion ps = new PulsoSimulacion();
                            ps.setPulSimHora(Calendar.getInstance().getTime());
                            ps.setPulSimNormal(true);
                            ps.setPulSimPulso(StaticInteger.getInteger().longValue());
                            pulsoSimulacionService.insertarPulsoSimulacion(ps);*/
                            System.out.println("Dato Recivido");
                        }
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
                        logger.info("Data: " + data + " incertado en la variable estatica");
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
                logger.info("Listener eliminado y puerto Cerrado");
                s.closePort();
            }
        }
        );
        th1.start();

        return "1";
    }

    
    @RequestMapping(value = "/registrarResultadoSimu", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    //@Async
    public Long registrarResultadoSimu(@RequestParam(name="citId") Long citId) {
        //th1 = null;
        ResultadoSimulacion rs = new ResultadoSimulacion();
        Cita c = citaService.obtenerCita(citId);
        rs.setCita(c);
        rs.setRestSimSalidaEmergencia(false);
        rs.setResSimId(c.getSimId());
        rs.setResSimNivelInicial(1);
        rs.setRestSimPulsoPromedio(0);
        rs = resultadoSimulacionService.RegistrarResultadoSimulacion(rs);
        Fobia fob = fobiaService.findFobiaById(c.getSimId());
        vrAuxService.iniciarTratamiento(c.getSimId(),1, rs.getResSimId(), "SIMULACION-"+fob.getFobNombre());
        return rs.getResSimId();
    }
    
    @RequestMapping(value = "/registrarPulso", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    //@Async
    public String registrarPulso(@RequestBody PulsoSimulacion pulSim) {
        //th1 = null;
        pulsoSimulacionService.insertarPulsoSimulacion(pulSim);
        //StaticInteger.setFinalizar(true);
        System.out.println("Pulso de Simulacion Creado");
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

    @RequestMapping(value = "/finalizarLectura", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    //@Async
    public String finalizarLecturaArduino(@RequestBody ResultadoSimulacion resSim,
            @RequestParam(name="observacion") String observacion) {
        //th1 = null;
        if(observacion =="" || observacion == null){
            observacion = "NO hay Observaciones";
        }
        System.out.println("Lectura de Listener Finalizado");
        ResultadoSimulacion rs = resultadoSimulacionService.findbyId(resSim.getResSimId());
        Cita cita = citaService.obtenerCita(rs.getCita().getCitId());
        rs.setCita(cita);
        resultadoSimulacionService.RegistrarResultadoSimulacion(rs);
        cita.setCitObservacion(observacion);
        citaService.registrarCita(cita);
        StaticInteger.setFinalizar(true);
        System.out.println("Resultado Simulacion Modificada");
        return cita.getCitId()+"";
    }
    @GetMapping(value = "/RealizarPreguntas")
    public String RealizarPreguntas(@RequestParam(value = "citId") Long Id, Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Cita cita = citaService.encontrarCitaconPacinenteconEstado(Id);
        Boolean tratId = false;
        if (cita.getTratId() == null) {
            tratId = true;
        }
        Pageable pageRequest = PageRequest.of(page, 10);

        Page<Pregunta> preguntas = citaService.EncontrarPreguntasCita(tratId, cita.getSimId().longValue(), pageRequest);
        PageRender<Pregunta> pageRender = new PageRender<>("/api/sesion/buscar", preguntas);
        model.addAttribute("preguntas", preguntas);
        model.addAttribute("cita", cita);
        model.addAttribute("page", pageRender);
        model.addAttribute("titulo","Preguntas para el paciente".concat(" " +cita.getPaciente().nombreCompleto()));
        citaService.registrarCita(cita);
        return "Psicologo/RealizarSesionTratamiento/RealizarPreguntas";
    }
    @GetMapping(value ="/RegistarDiagnostico")
    public String RegistrarDiagnostico(Model model, @RequestParam(value ="citId") Long Id,@RequestParam(value= "simId") Long simId) {
        ResultadoSimulacion resultadoSim = resultadoSimulacionService.findbyId(simId);
        Cita cita = citaService.obtenerCita(Id);
        Nivel nivelInicial = resultadoSimulacionService.encontrarNivel(resultadoSim.getResSimNivelInicial().longValue(),cita.getSimId());
        Nivel nivelFinal = resultadoSimulacionService.encontrarNivel(resultadoSim.getResSimNivelFinal().longValue(),cita.getSimId());
        model.addAttribute("citId", Id);
        model.addAttribute("resultadoSim", resultadoSim);
        model.addAttribute("nivelInicial", nivelInicial);
        model.addAttribute("nivelFinal", nivelFinal);
        return "Psicologo/RealizarSesionTratamiento/RegistrarDiagnostico";
    }
}
