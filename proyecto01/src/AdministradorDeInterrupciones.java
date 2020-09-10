/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sandro
 */
public class AdministradorDeInterrupciones {
    private ArrayList<Interrupciones> cola_interrupciones;

    public AdministradorDeInterrupciones() {
        this.cola_interrupciones = cola_interrupciones;
    }

    public ArrayList<Interrupciones> getCola_interrupciones() {
        return cola_interrupciones;
    }

    public void setCola_interrupciones(ArrayList<Interrupciones> cola_interrupciones) {
        this.cola_interrupciones = cola_interrupciones;
    }
    
    public void BloqueadoAListo(VerProcesos verprocesos, DefaultTableModel modelo, Integer tick, Proceso proceso){
        if(proceso.getPcb().getEstado() == "Bloqueado"){
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
            Integer fin = null;
            if(proceso.getPcb().getDireccion_inicial()!=null){
                fin = (Integer) proceso.getPcb().getDireccion_inicial() + (Integer) proceso.getPcb().getTamaño();
            }
            miTabla[10] = fin;
            miTabla[11]=proceso.getPcb().getProgram_counter();
            modelo.addRow(miTabla);
            verprocesos.tbColaProcesos.setModel(modelo);
        }
    }
    
    public void BloqueadoATerminado(VerProcesos verprocesos, DefaultTableModel modelo, Integer tick, Proceso proceso){
        if(proceso.getPcb().getEstado() == "Bloqueado"){
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
            Integer fin = null;
            if(proceso.getPcb().getDireccion_inicial()!=null){
                fin = (Integer) proceso.getPcb().getDireccion_inicial() + (Integer) proceso.getPcb().getTamaño();
            }
            miTabla[10] = fin;
            miTabla[11]=proceso.getPcb().getProgram_counter();
            modelo.addRow(miTabla);
            verprocesos.tbColaProcesos.setModel(modelo);
        }
    }
    
    public void EjecutarInterrupciones(VerProcesos verprocesos, VerInterrupciones interrupciones, DefaultTableModel modelo, DefaultTableModel modeloint, Integer tick){
        Integer time = 0;
        System.out.println("INTERRUPCIONES");
        System.out.println("PID\t\tTIME\t\tDESCRIPCIÓN");
        for (int i = 0; i < this.cola_interrupciones.size(); i++){
            if(this.cola_interrupciones.get(i).getEstado()=="Pendiente"){
                System.out.print(cola_interrupciones.get(i).getProceso().getPid()+"\t\t");
                System.out.print(cola_interrupciones.get(i).getTime()+"\t\t");
                System.out.println(cola_interrupciones.get(i).getDescripcion());
            }
            switch(this.cola_interrupciones.get(i).getCodigo()){
                case 0:
                    if(this.cola_interrupciones.get(i).getEstado()=="Pendiente"){
                        time = this.cola_interrupciones.get(i).getTime();
                        this.cola_interrupciones.get(i).setTime(0);
                        System.out.print(cola_interrupciones.get(i).getProceso().getPid()+"\t\t");
                        System.out.print(time+"\t\t");
                        System.out.println(cola_interrupciones.get(i).getDescripcion());
                    }
                    if(this.cola_interrupciones.get(i).getTime()==0 && this.cola_interrupciones.get(i).getEstado()=="Pendiente"){
                        this.cola_interrupciones.get(i).PendienteACompletado(interrupciones, modeloint, tick);
                        this.BloqueadoATerminado(verprocesos, modelo, tick, this.cola_interrupciones.get(i).getProceso());
                    }
                    break;
                    
                case 4:
                    if(this.cola_interrupciones.get(i).getEstado()=="Pendiente"){
                        time = this.cola_interrupciones.get(i).getTime();
                        this.cola_interrupciones.get(i).setTime(0);
                        System.out.print(cola_interrupciones.get(i).getProceso().getPid()+"\t\t");
                        System.out.print(time+"\t\t");
                        System.out.println(cola_interrupciones.get(i).getDescripcion());
                    }
                    if(this.cola_interrupciones.get(i).getTime()==0 && this.cola_interrupciones.get(i).getEstado()=="Pendiente"){
                        this.cola_interrupciones.get(i).PendienteACompletado(interrupciones, modeloint, tick);
                        this.BloqueadoATerminado(verprocesos, modelo, tick, this.cola_interrupciones.get(i).getProceso());
                    }
                    break;
                    
                case 8:
                    if(this.cola_interrupciones.get(i).getEstado()=="Pendiente"){
                        time = this.cola_interrupciones.get(i).getTime();
                        this.cola_interrupciones.get(i).setTime(0);
                        System.out.print(cola_interrupciones.get(i).getProceso().getPid()+"\t\t");
                        System.out.print(time+"\t\t");
                        System.out.println(cola_interrupciones.get(i).getDescripcion());
                    }
                    if(this.cola_interrupciones.get(i).getTime()==0 && this.cola_interrupciones.get(i).getEstado()=="Pendiente"){
                        this.cola_interrupciones.get(i).PendienteACompletado(interrupciones, modeloint, tick);
                        this.BloqueadoAListo(verprocesos, modelo, tick, this.cola_interrupciones.get(i).getProceso());
                    }
                    break;
                    
                case 9:
                    if(this.cola_interrupciones.get(i).getEstado()=="Pendiente"){
                      time = this.cola_interrupciones.get(i).getTime();
                      this.cola_interrupciones.get(i).setTime(time - 1);
                      System.out.print(cola_interrupciones.get(i).getProceso().getPid()+"\t\t");
                      System.out.print(time+"\t\t");
                      System.out.println(cola_interrupciones.get(i).getDescripcion());
                    }
                    if(this.cola_interrupciones.get(i).getTime()==0 && this.cola_interrupciones.get(i).getEstado()=="Pendiente"){
                        this.cola_interrupciones.get(i).PendienteACompletado(interrupciones, modeloint, tick);
                        this.BloqueadoAListo(verprocesos, modelo, tick, this.cola_interrupciones.get(i).getProceso());
                    }
                    break;
                    
                case 14:
                    if(this.cola_interrupciones.get(i).getEstado()=="Pendiente"){
                      time = this.cola_interrupciones.get(i).getTime();
                      this.cola_interrupciones.get(i).setTime(time - 1);
                      System.out.print(cola_interrupciones.get(i).getProceso().getPid()+"\t\t");
                      System.out.print(time+"\t\t");
                      System.out.println(cola_interrupciones.get(i).getDescripcion());
                    }
                    if(this.cola_interrupciones.get(i).getTime()==0 && this.cola_interrupciones.get(i).getEstado()=="Pendiente"){
                        this.cola_interrupciones.get(i).PendienteACompletado(interrupciones, modeloint, tick);
                        this.BloqueadoAListo(verprocesos, modelo, tick, this.cola_interrupciones.get(i).getProceso());
                    }
                    break;
                    
                case 15:
                    if(this.cola_interrupciones.get(i).getEstado()=="Pendiente"){
                      time = this.cola_interrupciones.get(i).getTime();
                      this.cola_interrupciones.get(i).setTime(time - 1);
                      System.out.print(cola_interrupciones.get(i).getProceso().getPid()+"\t\t");
                      System.out.print(time+"\t\t");
                      System.out.println(cola_interrupciones.get(i).getDescripcion());
                    }
                    if(this.cola_interrupciones.get(i).getTime()==0 && this.cola_interrupciones.get(i).getEstado()=="Pendiente"){
                        this.cola_interrupciones.get(i).PendienteACompletado(interrupciones, modeloint, tick);
                        this.BloqueadoAListo(verprocesos, modelo, tick, this.cola_interrupciones.get(i).getProceso());
                    }
                    break;
                
                case 116:
                    if(this.cola_interrupciones.get(i).getEstado()=="Pendiente"){
                      time = this.cola_interrupciones.get(i).getTime();
                      this.cola_interrupciones.get(i).setTime(time - 1);
                      System.out.print(cola_interrupciones.get(i).getProceso().getPid()+"\t\t");
                      System.out.print(time+"\t\t");
                      System.out.println(cola_interrupciones.get(i).getDescripcion());
                    }
                    if(this.cola_interrupciones.get(i).getTime()==0 && this.cola_interrupciones.get(i).getEstado()=="Pendiente"){
                        this.cola_interrupciones.get(i).PendienteACompletado(interrupciones, modeloint, tick);
                        this.BloqueadoAListo(verprocesos, modelo, tick, this.cola_interrupciones.get(i).getProceso());
                    }
                    break;
                    
                case 118:
                    if(this.cola_interrupciones.get(i).getEstado()=="Pendiente"){
                      time = this.cola_interrupciones.get(i).getTime();
                      this.cola_interrupciones.get(i).setTime(time - 1);
                      System.out.print(cola_interrupciones.get(i).getProceso().getPid()+"\t\t");
                      System.out.print(time+"\t\t");
                      System.out.println(cola_interrupciones.get(i).getDescripcion());
                    }
                    if(this.cola_interrupciones.get(i).getTime()==0 && this.cola_interrupciones.get(i).getEstado()=="Pendiente"){
                        this.cola_interrupciones.get(i).PendienteACompletado(interrupciones, modeloint, tick);
                        this.BloqueadoAListo(verprocesos, modelo, tick, this.cola_interrupciones.get(i).getProceso());
                    }
                    break;
                    
            }
        }
    }
}
