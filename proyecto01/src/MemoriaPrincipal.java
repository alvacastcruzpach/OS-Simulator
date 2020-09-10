/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author sandro
 */
public class MemoriaPrincipal {
    private Integer Tamaño;
    private DireccionMemoria[] Memoria;

    public MemoriaPrincipal(Integer Tamaño, DireccionMemoria[] Memoria) {
        this.Tamaño = Tamaño;
        this.Memoria = Memoria;
    }

    public Integer getTamaño() {
        return Tamaño;
    }

    public void setTamaño(Integer Tamaño) {
        this.Tamaño = Tamaño;
    }

    public DireccionMemoria[] getMemoria() {
        return Memoria;
    }

    public void setMemoria(DireccionMemoria[] Memoria) {
        this.Memoria = Memoria;
    }
    
    
}
