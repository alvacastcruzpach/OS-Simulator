
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
public class Despachador {
    private String contexto;

    public Despachador(String contexto) {
        this.contexto = contexto;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }
    
    public void ListoAEjecucion(VerProcesos verprocesos, DefaultTableModel modelo, Integer tick, Proceso proceso){
        if(proceso.getPcb().getEstado() == "Listo"){
            proceso.getPcb().setEstado("En ejecución");
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
        }
    }
    
    public void EjecucionAListo(VerProcesos verprocesos, DefaultTableModel modelo, Integer tick, Proceso proceso){
        if(proceso.getPcb().getEstado() == "En ejecución"){
            proceso.getPcb().setEstado("Listo");
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
        }
    }
    
    public void CambioDeContexto(){
        if(this.contexto == "Usuario"){
            this.contexto = "Kernel";
        }else{
            this.contexto = "Usuario";
        }
    }
    
}
