

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andy
 */
public class Pantalla extends javax.swing.JFrame implements Runnable{
    Integer codigo = 0;
    Integer corrida = 0;
    TableRowSorter trs;
    Estadistica estadistica = new Estadistica();
    VerInterrupciones interrupciones = new VerInterrupciones();
    VerProcesos verprocesos = new VerProcesos();
    VerMemoria vermemoria = new VerMemoria();
    int valor=0;
    Integer prio;
    Integer burst;
    Integer memo;
    
    Integer tick = 0;
    Integer numero_procesos = 20;
    
    CreadorProcesos creador = new CreadorProcesos(numero_procesos);
     
    ColaDeProcesos cola_procesos = new ColaDeProcesos(creador.CrearProcesos());
    
    //Thread hilo1;
    //Thread hilo2;
    public void inicio() throws InterruptedException{
        corrida++;
        
        for(int i = 0; i < cola_procesos.getCola_de_procesos().size(); i++){
            cola_procesos.getCola_de_procesos().get(i).setCorrida(corrida);
        }
       
        /* this.tick = 0;
        this.numero_procesos = 20;
        this.creador = new CreadorProcesos(numero_procesos);
        this.cola_procesos = new ColaDeProcesos(creador.CrearProcesos());
           
        hilo1 = new Thread(this);
        hilo2 = new Thread(this);
        hilo1.start();
        hilo2.start();
        */ 
        DefaultTableModel modelo=(DefaultTableModel) verprocesos.tbColaProcesos.getModel();
        DefaultTableModel modeloint=(DefaultTableModel) interrupciones.tbInterrupciones.getModel();
        DefaultTableModel modelomem = (DefaultTableModel) vermemoria.tbMemoria.getModel();
        DefaultTableModel modelocap = (DefaultTableModel) vermemoria.tbCapacidad.getModel();
        
        //PROCESOS NUEVOS EN TICK 0
        for(int xe=0; xe < cola_procesos.getCola_de_procesos().size(); xe++){ 
            if(cola_procesos.getCola_de_procesos().get(xe).getPcb().getTick_de_llegada()<= tick && cola_procesos.getCola_de_procesos().get(xe).getPcb().getEstado() == "Nuevo"){
                Object[] miTabla = new Object[13];

                miTabla[0]=cola_procesos.getCola_de_procesos().get(xe).getCorrida();
                miTabla[1]=tick;
                miTabla[2]=cola_procesos.getCola_de_procesos().get(xe).getPid();
                miTabla[3]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getEstado();
                miTabla[4]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getPrioridad();
                miTabla[5]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getTick_de_llegada();
                miTabla[6]=cola_procesos.getCola_de_procesos().get(xe).getBurst_time();
                miTabla[7]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getTamaño();
                miTabla[8]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getNumero_interrupciones();
                miTabla[9]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getCondicion();
                miTabla[10]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getDireccion_inicial();
                //Integer fin =cola_procesos.getCola_de_procesos().get(xe).getPcb().getDireccion_inicial() + cola_procesos.getCola_de_procesos().get(xe).getPcb().getTamaño();
                Integer fin = null;
                if(cola_procesos.getCola_de_procesos().get(xe).getPcb().getDireccion_inicial()!=null){
                    fin = (Integer) cola_procesos.getCola_de_procesos().get(xe).getPcb().getDireccion_inicial() + (Integer) cola_procesos.getCola_de_procesos().get(xe).getPcb().getTamaño();
                    miTabla[11]= fin;
                }
                miTabla[11] = fin;
                miTabla[12]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getProgram_counter();

                modelo.addRow(miTabla);
                verprocesos.tbColaProcesos.setModel(modelo);  
            }   
        }
        
        //CAMBIO DE LISTO A NUEVO
        cola_procesos.NuevoAListo(verprocesos, modelo, tick);
        
        //PROCESOS EN LISTO EN TICK 0
        for(int xe=0; xe < cola_procesos.getCola_de_procesos().size(); xe++){ 
            if(cola_procesos.getCola_de_procesos().get(xe).getPcb().getTick_de_llegada()<= tick && cola_procesos.getCola_de_procesos().get(xe).getPcb().getEstado() == "Listo"){
                Object[] miTabla = new Object[13];
                
                miTabla[0]=cola_procesos.getCola_de_procesos().get(xe).getCorrida();
                miTabla[1]=tick;
                miTabla[2]=cola_procesos.getCola_de_procesos().get(xe).getPid();
                miTabla[3]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getEstado();
                miTabla[4]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getPrioridad();
                miTabla[5]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getTick_de_llegada();
                miTabla[6]=cola_procesos.getCola_de_procesos().get(xe).getBurst_time();
                miTabla[7]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getTamaño();
                miTabla[8]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getNumero_interrupciones();
                miTabla[9]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getCondicion();
                miTabla[10]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getDireccion_inicial();
                Integer fin = null;
                if(cola_procesos.getCola_de_procesos().get(xe).getPcb().getDireccion_inicial()!=null){
                    fin = (Integer) cola_procesos.getCola_de_procesos().get(xe).getPcb().getDireccion_inicial() + (Integer) cola_procesos.getCola_de_procesos().get(xe).getPcb().getTamaño();
                    miTabla[11]= fin;
                }
                miTabla[11] = fin;
                miTabla[12]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getProgram_counter();

                modelo.addRow(miTabla);
                verprocesos.tbColaProcesos.setModel(modelo);  
            }   
        }
        
        //CREACIÓN DE COLA DE LISTOS
        ColaDeListos cola_listos = new ColaDeListos(cola_procesos.getCola_de_procesos());

        Planificador Planificador1 = new Planificador(String.valueOf(comboPolitica.getSelectedItem()));
        Planificador1.setEsquema(String.valueOf(comboExpropiativo.getSelectedItem()));
        Planificador1.setQuantum(Integer.valueOf(vQuantum.getValue().toString()));
        Despachador Despachador1 = new Despachador("Usuario");
        
        Integer tamano_memoria = 4096;
        DireccionMemoria[] direcciones = new DireccionMemoria[tamano_memoria]; 
        MemoriaPrincipal MemoriaRAM = new MemoriaPrincipal(tamano_memoria, direcciones);
        
        Integer[][] mapa = new Integer[tamano_memoria/16][16];
        MapaDeBits mapa_bits = new MapaDeBits(mapa);
        mapa_bits.llenarSO(tamano_memoria);
        
        AdministradorDeMemoria adminmemoria = new AdministradorDeMemoria(String.valueOf(comboMemoria.getSelectedItem()));
        //Método para que el adminmemoria asigne memoria
        adminmemoria.asignarMemoriaCola(verprocesos, modelo, mapa_bits, MemoriaRAM, cola_procesos, tick);
        System.out.println("Memoria asignada en tick " + tick);
        
        AdministradorErrores adminerror = new AdministradorErrores();
        
        EstadísticasProcesos statsp = new EstadísticasProcesos();
        EstadísticasMemoria statsm = new EstadísticasMemoria(tick);
        statsm.setHuecos(0);
        statsm.setBusqueda(0);
        statsm.setNbusqueda(0);
        Integer[] busqueda = {0, 0};
        
        Proceso ProcesoEnEjecucion = new Proceso();
        ProcesoEnEjecucion.getPcb().setEstado("Terminado");
        
        CPU procesador = new CPU();
        ArrayList<Interrupciones> cola_interrupciones = new ArrayList<>();
        Interrupciones interrupcion1 = new Interrupciones(1, 0, "Completado", 0, ProcesoEnEjecucion, 0);
        cola_interrupciones.add(interrupcion1);
        AdministradorDeInterrupciones admininterrupciones = new AdministradorDeInterrupciones();
        admininterrupciones.setCola_interrupciones(cola_interrupciones);
        
        
        //CREACIÓN DE DISPOSITIVOS
        
        DispositivoES Teclado;
        Teclado = new DispositivoES(9, "Teclado");
        DispositivoES CD = new DispositivoES(14, "CD-ROM");
        DispositivoES Impresora = new DispositivoES(15, "Impresora");
        DispositivoES Mouse = new DispositivoES(116, "Mouse");
        DispositivoES Disco = new DispositivoES(118, "Disco Duro");
        
        //CREACIÓN DE CONTROLADORES
        
        Controlador ConTeclado = new Controlador(9, Teclado, cola_interrupciones);
        Controlador ConCD = new Controlador(14, CD, cola_interrupciones);
        Controlador ConImpresora = new Controlador(15, Impresora, cola_interrupciones);
        Controlador ConMouse = new Controlador(116, Mouse, cola_interrupciones);
        Controlador ConDisco = new Controlador(118, Disco, cola_interrupciones);

        //CREACIÓN DE PLANIFICADORES
        
        Planificador_Interrupciones PlanTeclado = new Planificador_Interrupciones(Planificador1.getEsquema(), Planificador1.getPolitica(), Planificador1.getQuantum(), ConTeclado);
        Planificador_Interrupciones PlanCD = new Planificador_Interrupciones(Planificador1.getEsquema(), Planificador1.getPolitica(), Planificador1.getQuantum(), ConCD);
        Planificador_Interrupciones PlanImpresora = new Planificador_Interrupciones(Planificador1.getEsquema(), Planificador1.getPolitica(), Planificador1.getQuantum(), ConImpresora);
        Planificador_Interrupciones PlanMouse = new Planificador_Interrupciones(Planificador1.getEsquema(), Planificador1.getPolitica(), Planificador1.getQuantum(), ConMouse);
        Planificador_Interrupciones PlanDisco = new Planificador_Interrupciones(Planificador1.getEsquema(), Planificador1.getPolitica(), Planificador1.getQuantum(), ConDisco);   
        
        //Enlazando Planificador a Controlador
        ConTeclado.setPlanificador(PlanTeclado);
        ConCD.setPlanificador(PlanCD);
        ConImpresora.setPlanificador(PlanImpresora);
        ConMouse.setPlanificador(PlanMouse);
        ConDisco.setPlanificador(PlanDisco);
        
        //Enlazando Controlador con Administrador de Interrupciones
        admininterrupciones.setConCD(ConCD);
        admininterrupciones.setConTeclado(ConTeclado);
        admininterrupciones.setConImpresora(ConImpresora);
        admininterrupciones.setConMouse(ConMouse);
        admininterrupciones.setConDisco(ConDisco);
        
        for(int i = 0; i < valor; i++){
            cola_procesos.getCola_de_procesos().add(creador.CrearProcesoManual(numero_procesos + 1,prio,burst,memo));
            numero_procesos++;
        }
        Integer menor_tick_de_llegada = 1301;
        Integer nuevo_burst_time = -1;
        boolean llave=true;
        
        
        
        while(!cola_procesos.FinProceso()){
            tick++;
            /*try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("error en delay");
            }*/
            cola_procesos.pausar();
            statsm.setHuecos(adminmemoria.contarHuecos(vermemoria, modelomem, modelocap, mapa_bits, MemoriaRAM, tick)+statsm.getHuecos());
            //DefaultTableModel modelo=(DefaultTableModel) verprocesos.tbColaProcesos.getModel();
            
            //ESTADO DE LOS PROCESOS NUEVOS AL INICIAR EL TICK VARIABLE
            for(int xe=0; xe < cola_procesos.getCola_de_procesos().size(); xe++){ 
                if(cola_procesos.getCola_de_procesos().get(xe).getPcb().getTick_de_llegada()<= tick && cola_procesos.getCola_de_procesos().get(xe).getPcb().getEstado() == "Nuevo"){
                    Object[] miTabla = new Object[13];

                    miTabla[0]=cola_procesos.getCola_de_procesos().get(xe).getCorrida();
                    miTabla[1]=tick;
                    miTabla[2]=cola_procesos.getCola_de_procesos().get(xe).getPid();
                    miTabla[3]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getEstado();
                    miTabla[4]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getPrioridad();
                    miTabla[5]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getTick_de_llegada();
                    miTabla[6]=cola_procesos.getCola_de_procesos().get(xe).getBurst_time();
                    miTabla[7]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getTamaño();
                    miTabla[8]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getNumero_interrupciones();
                    miTabla[9]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getCondicion();
                    miTabla[10]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getDireccion_inicial();
                    Integer fin = null;
                    if(cola_procesos.getCola_de_procesos().get(xe).getPcb().getDireccion_inicial()!=null){
                        fin = (Integer) cola_procesos.getCola_de_procesos().get(xe).getPcb().getDireccion_inicial() + (Integer) cola_procesos.getCola_de_procesos().get(xe).getPcb().getTamaño();
                        miTabla[11]= fin;
                    }
                    miTabla[11] = fin;
                    miTabla[12]=cola_procesos.getCola_de_procesos().get(xe).getPcb().getProgram_counter();

                    modelo.addRow(miTabla);
                    verprocesos.tbColaProcesos.setModel(modelo);  
                }   
            }
           
            //DefaultTableModel modelo1=(DefaultTableModel) interrupciones.tbInterrupciones.getModel();
            /*for (int i = 0; i < cola_interrupciones.size(); i++){
                //FALTA IMPRIMIR INTERRUPCIONES 0, 1, 8
               if (cola_interrupciones.get(i).getTick()!=0 && cola_interrupciones.get(i).getEstado()=="Pendiente"){
                   Object[] miTabla = new Object[5];

                   miTabla[0]=tick;
                   miTabla[1]=cola_interrupciones.get(i).getProceso().getPid();
                   miTabla[2]=cola_interrupciones.get(i).getTick();
                   miTabla[3]=cola_interrupciones.get(i).getTime();
                   miTabla[4]=cola_interrupciones.get(i).getDescripcion();
                   modeloint.addRow(miTabla);
                   interrupciones.tbInterrupciones.setModel(modeloint);
               }
            }
            System.out.println("Ejecutando tick " + tick);*/
            
            //PROCESOS NUEVOS A LISTOS
            cola_procesos.NuevoAListo(verprocesos, modelo, tick);

            busqueda = adminmemoria.asignarMemoriaCola(verprocesos, modelo, mapa_bits, MemoriaRAM, cola_procesos, tick);
            statsm.setBusqueda(statsm.getBusqueda()+busqueda[0]);
            statsm.setNbusqueda(statsm.getNbusqueda()+busqueda[1]);
            if((ProcesoEnEjecucion.getPcb().getEstado() == "En Ejecución" || ProcesoEnEjecucion.getPcb().getEstado() == "Listo") && Planificador1.getEsquema()=="No expropiativo"){
                //El proceso en ejecucion sigue ejecutándose
                if(ProcesoEnEjecucion.getPcb().getEstado() == "Bloqueado"){
                    statsp.setTiempo_desperdiciado(statsp.getTiempo_desperdiciado()+1);
                }
            }else{
                if (Planificador1.planificarProcesos(tick, cola_listos) == null && ProcesoEnEjecucion.getPcb().getEstado() != "En ejecución"){
                    System.out.println("Tiempo desperdiciado por planificación en " + tick);
                    statsp.setTiempo_desperdiciado(statsp.getTiempo_desperdiciado()+1);
                    statsp.setTick(tick);
                    statsm.setTick(tick);
                    if(ProcesoEnEjecucion.getPcb().getProcesohijo()!=null){
                        procesador.copiarProcesoHijoenPadre(ProcesoEnEjecucion.getPcb().getProcesohijo());
                    }
                    admininterrupciones.EjecutarInterrupciones(verprocesos, interrupciones, modelo, modeloint, tick);
                    continue;
                }else{
                    if(ProcesoEnEjecucion != null && ProcesoEnEjecucion != Planificador1.planificarProcesos(tick, cola_listos)){
                        Despachador1.EjecucionAListo(verprocesos, modelo, tick, ProcesoEnEjecucion);
                        ProcesoEnEjecucion = Planificador1.planificarProcesos(tick, cola_listos);
                    }                   
                    procesador.setProgram_counter(ProcesoEnEjecucion.getPcb().getProgram_counter());
                    //System.out.println("Proceso identificado");
                }
            }
            Despachador1.CambioDeContexto();
            Despachador1.ListoAEjecucion(verprocesos, modelo, tick, ProcesoEnEjecucion);
            System.out.println("Realizando proceso " + ProcesoEnEjecucion.getPid());
            //CPU
            if(Math.random()<ProcesoEnEjecucion.getPcb().getNumero_interrupciones()/ProcesoEnEjecucion.getBurst_time()){
                admininterrupciones.getCola_interrupciones().add(procesador.LanzarInterrupcion(verprocesos, interrupciones, modelo, modeloint, tick, ProcesoEnEjecucion));
                System.out.println ("Interrupciones = " + admininterrupciones.getCola_interrupciones().size());
                ProcesoEnEjecucion.getPcb().setNumero_interrupciones(ProcesoEnEjecucion.getPcb().getNumero_interrupciones() - 1);
            }
            procesador.ejecutarProceso(tick, ProcesoEnEjecucion);
            admininterrupciones.EjecutarInterrupciones(verprocesos, interrupciones, modelo, modeloint, tick);
            //CPU
            Despachador1.CambioDeContexto();
            Planificador1.ListoATerminado(verprocesos, modelo, tick, ProcesoEnEjecucion);
            System.out.println(ProcesoEnEjecucion.getPcb().getEstado());
            adminmemoria.desasignarMemoriaProceso(mapa_bits, MemoriaRAM, ProcesoEnEjecucion);
            adminerror.VerificarActividad(verprocesos, modelo, tick, ProcesoEnEjecucion);
            statsp.setTick(tick);
            statsm.setTick(tick);
            if(ProcesoEnEjecucion.getBurst_time() == -1){
                System.out.println("Tiempo desperdiciado en " + tick);
                continue;
            }
            //System.out.println("Tick de llegada: " + ProcesoEnEjecucion.getTick_de_llegada());
            //System.out.println("Burst time: " + (nuevo_burst_time+1) + " -> " + nuevo_burst_time);
            if(ProcesoEnEjecucion.getBurst_time() == 0){
                System.out.println("Burst time 0");
                menor_tick_de_llegada = 1301;
            }
            
            
        }
        PintarFilasProcesos pintarfilasprocesos=new PintarFilasProcesos();
        PintarFilasMemoria pintarfilasmemoria= new PintarFilasMemoria();
        verprocesos.tbColaProcesos.setDefaultRenderer(verprocesos.tbColaProcesos.getColumnClass(0),pintarfilasprocesos);
        vermemoria.tbMemoria.setDefaultRenderer(vermemoria.tbMemoria.getColumnClass(0),pintarfilasmemoria);
       
    
        statsp.imprimirUsodeCPU();
        statsp.imprimirTiempoDeEsperaPromedio(cola_procesos);
        statsp.imprimirTiempoDeRespuestaPromedio(cola_procesos);
        statsm.imprimirEficienciaEnPerformance();
        statsm.imprimirOptimizaciónDeFraccionamiento();
        
        if ("First Fit".equals(String.valueOf(comboMemoria.getSelectedItem()))){
           
            
            DefaultTableModel modelo2=(DefaultTableModel) estadistica.tbEstadisticaFirstFit.getModel();
        
    
                Object[] miTabla = new Object[6];

                miTabla[0]=comboPolitica.getSelectedItem();
                miTabla[1]=statsm.imprimirOptimizaciónDeFraccionamiento();
                miTabla[2]=statsp.imprimirUsodeCPU();
                miTabla[3]=statsp.imprimirTiempoDeEsperaPromedio(cola_procesos);
                miTabla[4]=statsp.imprimirTiempoDeRespuestaPromedio(cola_procesos);
                miTabla[5]=statsm.imprimirOptimizaciónDeFraccionamiento();
                modelo2.addRow(miTabla);
                estadistica.tbEstadisticaFirstFit.setModel(modelo2); 
                
        }
        
        if ("Best Fit".equals(String.valueOf(comboMemoria.getSelectedItem()))){
             
            DefaultTableModel modelo2=(DefaultTableModel) estadistica.tbEstadisticaBestFit.getModel();
        
    
                Object[] miTabla = new Object[6];

                miTabla[0]=comboPolitica.getSelectedItem();
                miTabla[1]=statsm.imprimirOptimizaciónDeFraccionamiento();
                miTabla[2]=statsp.imprimirUsodeCPU();
                miTabla[3]=statsp.imprimirTiempoDeEsperaPromedio(cola_procesos);
                miTabla[4]=statsp.imprimirTiempoDeRespuestaPromedio(cola_procesos);
                miTabla[5]=statsm.imprimirOptimizaciónDeFraccionamiento();
                modelo2.addRow(miTabla);
                estadistica.tbEstadisticaBestFit.setModel(modelo2);    
        }
        
        if ("Worst Fit".equals(String.valueOf(comboMemoria.getSelectedItem()))){
             
            DefaultTableModel modelo2=(DefaultTableModel) estadistica.tbEstadisticaWorstFit.getModel();
        
    
                Object[] miTabla = new Object[6];

                miTabla[0]=comboPolitica.getSelectedItem();
                miTabla[1]=statsm.imprimirOptimizaciónDeFraccionamiento();
                miTabla[2]=statsp.imprimirUsodeCPU();
                miTabla[3]=statsp.imprimirTiempoDeEsperaPromedio(cola_procesos);
                miTabla[4]=statsp.imprimirTiempoDeRespuestaPromedio(cola_procesos);
                miTabla[5]=statsm.imprimirOptimizaciónDeFraccionamiento();
                modelo2.addRow(miTabla);
                estadistica.tbEstadisticaWorstFit.setModel(modelo2);    
        }
        
        
         
    }

    
        
    public Pantalla() {
        initComponents();
       
    }

    public void limpiar(){
        this.tick = 0;
        this.interrupciones = new VerInterrupciones();
        this.verprocesos=new VerProcesos();
        this.vermemoria = new VerMemoria();
        this.numero_procesos = 20;
        cola_procesos.ReiniciarValores();
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSimular = new javax.swing.JButton();
        btnEstadisticas = new javax.swing.JButton();
        labelProcesos = new javax.swing.JLabel();
        btnInterrupciones = new javax.swing.JToggleButton();
        btnVerProcesos = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMemoria = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtBurstTime = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPrioridad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNroProcesosNuevos = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnAgregarProceso = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comboPolitica = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        comboExpropiativo = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        comboMemoria = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        vQuantum = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSimular.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSimular.setForeground(new java.awt.Color(0, 0, 255));
        btnSimular.setText("Simular");
        btnSimular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimularActionPerformed(evt);
            }
        });

        btnEstadisticas.setText("Ver Estadisticas");
        btnEstadisticas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEstadisticasMouseClicked(evt);
            }
        });
        btnEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadisticasActionPerformed(evt);
            }
        });

        btnInterrupciones.setText("Ver Interrupciones");
        btnInterrupciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInterrupcionesActionPerformed(evt);
            }
        });

        btnVerProcesos.setText("Ver Procesos");
        btnVerProcesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerProcesosActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Procesos Manuales");

        txtMemoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMemoriaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("MB");

        jLabel4.setText("Cap. Memoria");

        txtBurstTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBurstTimeActionPerformed(evt);
            }
        });

        jLabel8.setText("Burst Time");

        txtPrioridad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrioridadActionPerformed(evt);
            }
        });

        jLabel5.setText("Prioridad");

        txtNroProcesosNuevos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNroProcesosNuevosActionPerformed(evt);
            }
        });

        jLabel6.setText("Numero de procesos");

        btnAgregarProceso.setText("Agregar");
        btnAgregarProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProcesoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(jLabel8)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNroProcesosNuevos, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBurstTime, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)))
                .addGap(3, 3, 3))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(btnAgregarProceso)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNroProcesosNuevos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtBurstTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(btnAgregarProceso)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Planificacion");

        jLabel9.setText("Politica");

        comboPolitica.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FCFS", "SJF", "Round Robin", "Por prioridades" }));
        comboPolitica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPoliticaActionPerformed(evt);
            }
        });

        jLabel10.setText("Contexto");

        comboExpropiativo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Expropiativo", "No expropiativo" }));
        comboExpropiativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboExpropiativoActionPerformed(evt);
            }
        });

        jLabel12.setText("Estrategia");

        comboMemoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "First Fit", "Best Fit", "Worst Fit" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Asignacion de memoria");

        jLabel1.setText("Tamaño Quantum");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(vQuantum, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboExpropiativo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboPolitica, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboPolitica, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vQuantum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboExpropiativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(25, 25, 25)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(comboMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jButton1.setText("Ver Memoria");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(labelProcesos)
                .addGap(77, 752, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnVerProcesos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnInterrupciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSimular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(55, 55, 55))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnSimular, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnVerProcesos)
                        .addGap(18, 18, 18)
                        .addComponent(btnEstadisticas)
                        .addGap(18, 18, 18)
                        .addComponent(btnInterrupciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelProcesos))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboPoliticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPoliticaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboPoliticaActionPerformed

    private void btnEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadisticasActionPerformed

        estadistica.setVisible(true);
    }//GEN-LAST:event_btnEstadisticasActionPerformed

    private void txtNroProcesosNuevosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNroProcesosNuevosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNroProcesosNuevosActionPerformed

    private void btnEstadisticasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstadisticasMouseClicked

    }//GEN-LAST:event_btnEstadisticasMouseClicked

    private void comboExpropiativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboExpropiativoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboExpropiativoActionPerformed

    private void btnSimularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimularActionPerformed
       if(this.codigo != 0){
            limpiar();
        }
        try {
            inicio();
        } catch (InterruptedException ex) {
            Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Simulacion exitosa");
        this.codigo++;
    }//GEN-LAST:event_btnSimularActionPerformed

  
    private void btnAgregarProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProcesoActionPerformed
       
        valor=Integer.parseInt(txtNroProcesosNuevos.getText());
        prio=Integer.parseInt(txtPrioridad.getText());
        burst=Integer.parseInt(txtBurstTime.getText());
        memo=Integer.parseInt(txtMemoria.getText());
        
        txtNroProcesosNuevos.setText("");
        txtPrioridad.setText("");
        txtBurstTime.setText("");
        txtMemoria.setText("");
        JOptionPane.showMessageDialog(null, "Proceso agregado");
    }//GEN-LAST:event_btnAgregarProcesoActionPerformed

    private void txtBurstTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBurstTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBurstTimeActionPerformed

    private void txtPrioridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrioridadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrioridadActionPerformed

    private void txtMemoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMemoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMemoriaActionPerformed

    private void btnInterrupcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInterrupcionesActionPerformed
       interrupciones.setVisible(true);
    }//GEN-LAST:event_btnInterrupcionesActionPerformed

    private void btnVerProcesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerProcesosActionPerformed
        
        verprocesos.setVisible(true);
    }//GEN-LAST:event_btnVerProcesosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        vermemoria.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


          
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProceso;
    private javax.swing.JButton btnEstadisticas;
    private javax.swing.JToggleButton btnInterrupciones;
    private javax.swing.JButton btnSimular;
    private javax.swing.JToggleButton btnVerProcesos;
    private javax.swing.JComboBox comboExpropiativo;
    public javax.swing.JComboBox comboMemoria;
    public javax.swing.JComboBox comboPolitica;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelProcesos;
    public javax.swing.JTextField txtBurstTime;
    public javax.swing.JTextField txtMemoria;
    public javax.swing.JTextField txtNroProcesosNuevos;
    public javax.swing.JTextField txtPrioridad;
    private javax.swing.JSpinner vQuantum;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
