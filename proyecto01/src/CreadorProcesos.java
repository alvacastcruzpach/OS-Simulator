/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.lang.*;

/**
 *
 * @author sandro
 */
public class CreadorProcesos {
    Integer Numero_De_Procesos;

    public CreadorProcesos(Integer Numero_De_Procesos) {
        this.Numero_De_Procesos = Numero_De_Procesos;
    }

    public Integer getNumero_De_Procesos() {
        return Numero_De_Procesos;
    }

    public void setNumero_De_Procesos(Integer Numero_De_Procesos) {
        this.Numero_De_Procesos = Numero_De_Procesos;
    }
    
    public ArrayList<Proceso> CrearProcesos(){
        ArrayList<Proceso> ColaDeProcesos = new ArrayList<>();
        for (int i = 0; i < this.Numero_De_Procesos; i++){
            Integer pid = i + 1;
            String estado = "Nuevo";
            Integer prioridad = (int)((Math.random())*4+1); //Acá debe implementarse un random
            Integer tick_de_llegada = (int)(Math.random()*100); //Acá debe implementarse un random
            Integer burst_time = (int)((Math.random()*30)+1); //Acá debe implementarse un random
            Integer tamaño = (int)((Math.random()*256)+1);
            String condicion = "Correcto";
            Double porcentaje_datos = (Math.random()*0.5) + 0.3;
            Integer interrupciones = (int)(15*(burst_time/30 + tamaño/256*0.7 + Math.random())/3)+5;
            String tipo = "Sistema Operativo";
            ColaDeProcesos.add(new Proceso(pid, estado, prioridad, tick_de_llegada, burst_time, tamaño, condicion, porcentaje_datos, interrupciones, tipo));
        }
        return ColaDeProcesos;
    }
    
    public Proceso CrearProcesoManual(Integer pid, Integer prioridad, Integer burst_time, Integer tamaño){
        Scanner sc = new Scanner(System.in);
        Integer tick = (int)(Math.random()*100); //Acá debe implementarse un random
        String estado = "Nuevo";

        String condicion = "Correcto";
        Double porcentaje = 0.5;
        Integer interrupciones = (int)(15*(burst_time/30 + tamaño/256*0.7 + Math.random())/3)+5;
        String tipo = "Usuario";
        Proceso ProcesoManual = new Proceso(pid, estado, prioridad, tick, burst_time, tamaño, condicion, porcentaje, interrupciones, tipo);
        
        return ProcesoManual;
    }
}
