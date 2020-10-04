/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author sandro
 */

public class Proceso {
    private Integer corrida;
    private Integer pid;
    private Integer burst_time;
    private PCB pcb;

    public Proceso(Integer pid, String estado, Integer prioridad, Integer tick_de_llegada, Integer burst_time, Integer tamaño, String condicion, Double porcentaje, Integer interrupciones, String tipo) {
        this.pid = pid;
        this.burst_time = burst_time;
        this.pcb = new PCB(estado, prioridad, tick_de_llegada, tamaño, condicion, porcentaje, interrupciones, tipo, burst_time);
    }

    public Proceso() {
        this.pcb = new PCB();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getBurst_time() {
        return burst_time;
    }

    public void setBurst_time(Integer burst_time) {
        this.burst_time = burst_time;
    }

    public PCB getPcb() {
        return pcb;
    }

    public void setPcb(PCB pcb) {
        this.pcb = pcb;
    }

    public Integer getCorrida() {
        return corrida;
    }

    public void setCorrida(Integer corrida) {
        this.corrida = corrida;
    }
    
}
