
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author sandro
 */
public class Planificador {
    private String Esquema;
    private String Politica;
    private Integer Quantum;

    public Planificador(String Politica) {
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
    
    
    
    public Proceso planificarProcesos(Integer tick, ColaDeListos Cola){
        Integer EnEjecucion = -1;
        switch(Politica){
            case "FCFS":
                if(Cola == null){
                    System.out.println("Cola vacía");
                }
                Integer menor_tick_de_llegada = 201;
                for (int j = 0; j < Cola.getCola_de_listos().size(); j++) {
                    if (Cola.getCola_de_listos().get(j).getBurst_time() != 0 && (Cola.getCola_de_listos().get(j).getPcb().getTick_de_llegada() < menor_tick_de_llegada) && (Cola.getCola_de_listos().get(j).getPcb().getTick_de_llegada() <= tick)&&(Cola.getCola_de_listos().get(j).getPcb().getEstado()=="Listo" || Cola.getCola_de_listos().get(j).getPcb().getEstado()=="En ejecución")) {
                        menor_tick_de_llegada = Cola.getCola_de_listos().get(j).getPcb().getTick_de_llegada();
                        EnEjecucion = j;
                    }
                }
                break;
                
            case "SJF":
                if(Cola == null){
                    System.out.println("Cola vacía");
                }
                menor_tick_de_llegada = 201;
                for (int j = 0; j < Cola.getCola_de_listos().size(); j++) {
                    if (Cola.getCola_de_listos().get(j).getBurst_time() != 0 && (Cola.getCola_de_listos().get(j).getBurst_time() < menor_tick_de_llegada) && (Cola.getCola_de_listos().get(j).getPcb().getTick_de_llegada() <= tick)&&(Cola.getCola_de_listos().get(j).getPcb().getEstado()=="Listo" || Cola.getCola_de_listos().get(j).getPcb().getEstado()=="En ejecución")) {
                        menor_tick_de_llegada = Cola.getCola_de_listos().get(j).getBurst_time();
                        EnEjecucion = j;
                    }
                }
                break;
            
            case "Round Robin":
                Integer QuantumRest = this.Quantum;
                if(Cola == null){
                    System.out.println("Cola vacía");
                }
                menor_tick_de_llegada = 201;
                for (int j = 0; j < Cola.getCola_de_listos().size(); j++) {
                    if(this.Quantum < 1){
                        if(Cola.getCola_de_listos().size()>0){
                            menor_tick_de_llegada = Cola.getCola_de_listos().get(j).getPcb().getTick_de_llegada();
                            QuantumRest = this.Quantum;
                        }
                    }else{
                            if(Cola.getCola_de_listos().get(j).getBurst_time() != 0 && (Cola.getCola_de_listos().get(j).getPcb().getTick_de_llegada() < menor_tick_de_llegada) && (Cola.getCola_de_listos().get(j).getPcb().getTick_de_llegada() <= tick)&&(Cola.getCola_de_listos().get(j).getPcb().getEstado()=="Listo" || Cola.getCola_de_listos().get(j).getPcb().getEstado()=="En ejecución")) {
                                if (Cola.getCola_de_listos().get(j).getPcb().getEstado()=="En Ejecución"){
                                    menor_tick_de_llegada = Cola.getCola_de_listos().get(j).getPcb().getTick_de_llegada();
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
                menor_tick_de_llegada = 201;
                Integer max_prioridad=0;
                for (int j = 0; j < Cola.getCola_de_listos().size(); j++) {
                    if (Cola.getCola_de_listos().get(j).getBurst_time() != 0 && (Cola.getCola_de_listos().get(j).getPcb().getPrioridad()>max_prioridad) && (Cola.getCola_de_listos().get(j).getPcb().getTick_de_llegada()<=tick) && (Cola.getCola_de_listos().get(j).getPcb().getEstado()=="Listo" || Cola.getCola_de_listos().get(j).getPcb().getEstado()=="En ejecución")){
                        max_prioridad=Cola.getCola_de_listos().get(j).getPcb().getPrioridad();
                    }
                }
                for (int j = 0; j < Cola.getCola_de_listos().size(); j++) {
                    if (Cola.getCola_de_listos().get(j).getBurst_time() != 0 && (Cola.getCola_de_listos().get(j).getPcb().getTick_de_llegada() < menor_tick_de_llegada) && (Cola.getCola_de_listos().get(j).getPcb().getTick_de_llegada() <= tick)&&(Cola.getCola_de_listos().get(j).getPcb().getEstado()=="Listo" || Cola.getCola_de_listos().get(j).getPcb().getEstado()=="En ejecución")&&(max_prioridad==Cola.getCola_de_listos().get(j).getPcb().getPrioridad())) {
                        menor_tick_de_llegada = Cola.getCola_de_listos().get(j).getPcb().getTick_de_llegada();
                        EnEjecucion = j;
                    }
                }
                break;
        }
        
        if(EnEjecucion==-1){
            return null;
        }else{
            return Cola.getCola_de_listos().get(EnEjecucion);
        }
    }
    
    public void ListoATerminado(VerProcesos verprocesos, DefaultTableModel modelo, Integer tick, Proceso proceso){
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
    }
}
