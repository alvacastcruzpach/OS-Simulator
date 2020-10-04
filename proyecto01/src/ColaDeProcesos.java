/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sandro
 */
public class ColaDeProcesos extends Thread{
    private ArrayList<Proceso> cola_de_procesos;
    public ColaDeProcesos(ArrayList<Proceso> cola_de_procesos) {
        this.cola_de_procesos = cola_de_procesos;
    }

    public ArrayList<Proceso> getCola_de_procesos() {
        return cola_de_procesos;
    }
    
    public void pausar() throws InterruptedException{
        System.out.println("Iniciando pausa");
        Thread.sleep(0);
        System.out.println("Finalizando pausa");
    }
    public void setCola_de_procesos(ArrayList<Proceso> cola_de_procesos) {
        this.cola_de_procesos = cola_de_procesos;
    }

    public void NuevoAListo(VerProcesos verprocesos, DefaultTableModel modelo, Integer tick){
        //ESTADO DE LOS PROCESOS EN LISTO EN EL TICK VARIABLE
        for(int i = 0; i < cola_de_procesos.size(); i++){
            if((cola_de_procesos.get(i).getPcb().getTick_de_llegada()) == tick && (cola_de_procesos.get(i).getPcb().getEstado() == "Nuevo")){
                cola_de_procesos.get(i).getPcb().setEstado("Listo");
                Object[] miTabla = new Object[13];

                    miTabla[0]=cola_de_procesos.get(i).getCorrida();
                    miTabla[1]=tick;
                    miTabla[2]=cola_de_procesos.get(i).getPid();
                    miTabla[3]=cola_de_procesos.get(i).getPcb().getEstado();
                    miTabla[4]=cola_de_procesos.get(i).getPcb().getPrioridad();
                    miTabla[5]=cola_de_procesos.get(i).getPcb().getTick_de_llegada();
                    miTabla[6]=cola_de_procesos.get(i).getBurst_time();
                    miTabla[7]=cola_de_procesos.get(i).getPcb().getTamaño();
                    miTabla[8]=cola_de_procesos.get(i).getPcb().getNumero_interrupciones();
                    miTabla[9]=cola_de_procesos.get(i).getPcb().getCondicion();
                    miTabla[10]=cola_de_procesos.get(i).getPcb().getDireccion_inicial();
                    Integer fin = null;
                    if(cola_de_procesos.get(i).getPcb().getDireccion_inicial()!=null){
                        fin = (Integer) cola_de_procesos.get(i).getPcb().getDireccion_inicial() + (Integer) cola_de_procesos.get(i).getPcb().getTamaño();
                        miTabla[11]= fin;
                    }
                    miTabla[11] = fin;
                    miTabla[12]=cola_de_procesos.get(i).getPcb().getProgram_counter();

                    modelo.addRow(miTabla);
                    verprocesos.tbColaProcesos.setModel(modelo);
            }
        }
    }
    
    public void ReiniciarValores(){
        for (int i = 0; i < cola_de_procesos.size(); i++){
            cola_de_procesos.get(i).setBurst_time(cola_de_procesos.get(i).getPcb().getBurst_time_inicial());
            cola_de_procesos.get(i).getPcb().setCondicion("Correcto");
            cola_de_procesos.get(i).getPcb().setDireccion_inicial(null);
            cola_de_procesos.get(i).getPcb().setEstado("Nuevo");
            cola_de_procesos.get(i).getPcb().setNumero_interrupciones(cola_de_procesos.get(i).getPcb().getInterrupciones());
            cola_de_procesos.get(i).getPcb().setProcesohijo(null);
            cola_de_procesos.get(i).getPcb().setProcesopadre(null);
            cola_de_procesos.get(i).getPcb().setTick_de_fin(null);
        }
    }
    
    public Boolean FinProceso(){
        Boolean fin = true;
        for(int i = 0; i < cola_de_procesos.size(); i++){
            if(cola_de_procesos.get(i).getPcb().getEstado() != "Terminado"){
                fin = false;
                break;
            }
        }
        return fin;
    }
    
}
