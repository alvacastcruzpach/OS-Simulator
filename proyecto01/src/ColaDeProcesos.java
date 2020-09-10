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
public class ColaDeProcesos {
    private ArrayList<Proceso> cola_de_procesos;

    public ColaDeProcesos(ArrayList<Proceso> cola_de_procesos) {
        this.cola_de_procesos = cola_de_procesos;
    }

    public ArrayList<Proceso> getCola_de_procesos() {
        return cola_de_procesos;
    }

    public void setCola_de_procesos(ArrayList<Proceso> cola_de_procesos) {
        this.cola_de_procesos = cola_de_procesos;
    }

    public void NuevoAListo(VerProcesos verprocesos, DefaultTableModel modelo, Integer tick){
        //ESTADO DE LOS PROCESOS EN LISTO EN EL TICK VARIABLE
        for(int i = 0; i < cola_de_procesos.size(); i++){
            if((cola_de_procesos.get(i).getPcb().getTick_de_llegada()) == tick && (cola_de_procesos.get(i).getPcb().getEstado() == "Nuevo")){
                cola_de_procesos.get(i).getPcb().setEstado("Listo");
                Object[] miTabla = new Object[12];


                    miTabla[0]=tick;
                    miTabla[1]=cola_de_procesos.get(i).getPid();
                    miTabla[2]=cola_de_procesos.get(i).getPcb().getEstado();
                    miTabla[3]=cola_de_procesos.get(i).getPcb().getPrioridad();
                    miTabla[4]=cola_de_procesos.get(i).getPcb().getTick_de_llegada();
                    miTabla[5]=cola_de_procesos.get(i).getBurst_time();
                    miTabla[6]=cola_de_procesos.get(i).getPcb().getTamaño();
                    miTabla[7]=cola_de_procesos.get(i).getPcb().getNumero_interrupciones();
                    miTabla[8]=cola_de_procesos.get(i).getPcb().getCondicion();
                    miTabla[9]=cola_de_procesos.get(i).getPcb().getDireccion_inicial();
                    Integer fin = null;
                    if(cola_de_procesos.get(i).getPcb().getDireccion_inicial()!=null){
                        fin = (Integer) cola_de_procesos.get(i).getPcb().getDireccion_inicial() + (Integer) cola_de_procesos.get(i).getPcb().getTamaño();
                        miTabla[10]= fin;
                    }
                    miTabla[10] = fin;
                    miTabla[11]=cola_de_procesos.get(i).getPcb().getProgram_counter();

                    modelo.addRow(miTabla);
                    verprocesos.tbColaProcesos.setModel(modelo);
            }
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
