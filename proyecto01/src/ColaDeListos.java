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
public class ColaDeListos {
    private ArrayList<Proceso> cola_de_listos;

    public ColaDeListos(ArrayList<Proceso> cola_de_listos) {
        this.cola_de_listos = cola_de_listos;
    }

    public ArrayList<Proceso> getCola_de_listos() {
        return cola_de_listos;
    }

    public void setCola_de_listos(ArrayList<Proceso> cola_de_listos) {
        this.cola_de_listos = cola_de_listos;
    }

    public void validarCola(){
        for(int i=0; i < cola_de_listos.size(); i++){
            if (cola_de_listos.get(i).getPcb().getEstado() != "Listo"){
                this.cola_de_listos.remove(cola_de_listos.get(i));
            }else{
                System.out.println("Error");
            }
        }
    }
}
