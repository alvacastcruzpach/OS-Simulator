/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sandro
 */
public class EstadísticasMemoria {
    private Integer tick;
    private Integer busqueda;
    private Integer huecos;
    private Integer nbusqueda;

    public EstadísticasMemoria(Integer tick) {
        this.tick = tick;
    }

    public Integer getTick() {
        return tick;
    }

    public void setTick(Integer tick) {
        this.tick = tick;
    }

    public Integer getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(Integer busqueda) {
        this.busqueda = busqueda;
    }

    public Integer getHuecos() {
        return huecos;
    }

    public void setHuecos(Integer huecos) {
        this.huecos = huecos;
    }

    public Integer getNbusqueda() {
        return nbusqueda;
    }

    public void setNbusqueda(Integer nbusqueda) {
        this.nbusqueda = nbusqueda;
    }
    
    public float imprimirEficienciaEnPerformance(){
        float tbusqueda = (float)(busqueda)/nbusqueda;
        System.out.println("Tiempo promedio de asignación de memoria = " + tbusqueda + " espacios de memoria recorridos");
        return tbusqueda;
    }
    
    public float imprimirOptimizaciónDeFraccionamiento(){
        float huecospromedio = (float)(huecos)/tick;
        System.out.println("Fraccionamiento = " + huecospromedio + " huecos en promedio");
        return huecospromedio;
    }
}
