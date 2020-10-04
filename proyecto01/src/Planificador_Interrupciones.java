
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author sandro
 */
public class Planificador_Interrupciones{
    private String Esquema;
    private String Politica;
    private Integer Quantum;
    private Controlador Controlador;

    public Planificador_Interrupciones(String Esquema, String Politica, Integer Quantum, Controlador Controlador) {
        this.Esquema = Esquema;
        this.Politica = Politica;
        this.Quantum = Quantum;
        this.Controlador = Controlador;
    }

    public Planificador_Interrupciones(String Politica) {
        this.Politica = Politica;
    }

    public String getPolitica() {
        return Politica;
    }

    public void setPolitica(String Politica) {
        this.Politica = Politica;
    }

    public String getEsquema() {
        return Esquema;
    }

    public void setEsquema(String Esquema) {
        this.Esquema = Esquema;
    }

    public Integer getQuantum() {
        return Quantum;
    }

    public void setQuantum(Integer Quantum) {
        this.Quantum = Quantum;
    }

    public Interrupciones planificarInterrupciones(Integer tick, ArrayList<Interrupciones> Cola){
        Integer EnEjecucion = -1;
        switch(Politica){
            case "FCFS":
                if(Cola == null){
                    System.out.println("Cola vacía");
                }
                Integer menor_tick_de_llegada = 100001;
                for (int j = 0; j < Cola.size(); j++) {
                    if (Cola.get(j).getTime() != 0 && (Cola.get(j).getTick() < menor_tick_de_llegada) && (Cola.get(j).getTick() <= tick)&&(Cola.get(j).getEstado()=="Pendiente") && (Controlador.getCodigo() == Cola.get(j).getCodigo())) {
                        menor_tick_de_llegada = Cola.get(j).getTick();
                        EnEjecucion = j;
                    }
                }
                break;
                
            case "SJF":
                if(Cola == null){
                    System.out.println("Cola vacía");
                }
                menor_tick_de_llegada = 100001;
                for (int j = 0; j < Cola.size(); j++) {
                    if (Cola.get(j).getTime() != 0 && (Cola.get(j).getTime() < menor_tick_de_llegada) && (Cola.get(j).getTick() <= tick)&&(Cola.get(j).getEstado()=="Pendiente") && (Controlador.getCodigo() == Cola.get(j).getCodigo())) {
                        menor_tick_de_llegada = Cola.get(j).getTime();
                        EnEjecucion = j;
                    }
                }
                break;
            
            case "Round Robin":
                Integer QuantumRest = this.Quantum;
                if(Cola == null){
                    System.out.println("Cola vacía");
                }
                menor_tick_de_llegada = 100001;
                for (int j = 0; j < Cola.size(); j++) {
                    if(this.Quantum < 1){
                        if(Cola.size()>0){
                            menor_tick_de_llegada = Cola.get(j).getTick();
                            QuantumRest = this.Quantum;
                        }
                    }else{
                            if((Cola.get(j).getTime() != 0 && (Cola.get(j).getTick() < menor_tick_de_llegada) && (Cola.get(j).getTick()) <= tick)&&(Cola.get(j).getEstado()=="Pendiente") && (Controlador.getCodigo() == Cola.get(j).getCodigo())){
                                if (Cola.get(j).getEstado()=="En Ejecución"){
                                    menor_tick_de_llegada = Cola.get(j).getTick();
                                    QuantumRest--;
                            }
                            EnEjecucion = j;
                            }
                        }
                }
                break;
                
            case "Por prioridades":
                if(Cola == null){
                    System.out.println("Cola vacía");
                }
                menor_tick_de_llegada = 100001;
                Integer max_prioridad=0;
                for (int j = 0; j < Cola.size(); j++) {
                    if (Cola.get(j).getTime() != 0 && (Cola.get(j).getPrioridad()>max_prioridad) && (Cola.get(j).getTick()<=tick) && (Cola.get(j).getEstado()=="Pendiente") && (Controlador.getCodigo() == Cola.get(j).getCodigo())){
                        max_prioridad=Cola.get(j).getPrioridad();
                    }
                }
                for (int j = 0; j < Cola.size(); j++) {
                    if (Cola.get(j).getTime() != 0 && (Cola.get(j).getTick() < menor_tick_de_llegada) && (Cola.get(j).getTick() <= tick)&&(Cola.get(j).getEstado()=="Pendiente")&&(max_prioridad==Cola.get(j).getPrioridad()) && (Controlador.getCodigo() == Cola.get(j).getCodigo())) {
                        menor_tick_de_llegada = Cola.get(j).getTick();
                        EnEjecucion = j;
                    }
                }
                break;
        }
        
        if(EnEjecucion==-1){
            return null;
        }else{
            System.out.println(Cola.get(EnEjecucion).getCodigo());
            System.out.println(Cola.get(EnEjecucion).getTick());
            return Cola.get(EnEjecucion);
        }
    }
    
    /*public void ListoATerminado(VerProcesos verprocesos, DefaultTableModel modelo, Integer tick, Proceso proceso){
        if (proceso.getBurst_time() == 0){
            proceso.getPcb().setEstado("Terminado");
            proceso.getPcb().setTick_de_fin(tick);
            Object[] miTabla = new Object[12];
            miTabla[0]=tick;
            miTabla[1]=proceso.getPid();
            miTabla[2]=proceso.getPcb().getEstado();
            miTabla[3]=proceso.getPcb().getPrioridad();
            miTabla[4]=proceso.getPcb().getTick_de_llegada();
            miTabla[5]=proceso.getBurst_time();
            miTabla[6]=proceso.getPcb().getTamaño();
            miTabla[7]=proceso.getPcb().getNumero_interrupciones();
            miTabla[8]=proceso.getPcb().getCondicion();
            miTabla[9]=proceso.getPcb().getDireccion_inicial();
            Integer fin1 = null;
            if(proceso.getPcb().getDireccion_inicial()!=null){
                fin1 = (Integer) proceso.getPcb().getDireccion_inicial() + (Integer) proceso.getPcb().getTamaño();
                miTabla[10]= fin1;
            }
            miTabla[10] = fin1;
            miTabla[11]=proceso.getPcb().getProgram_counter();
            modelo.addRow(miTabla);
            verprocesos.tbColaProcesos.setModel(modelo);
        }else{
            //No hacer nada
        }
    }*/
}
