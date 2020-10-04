/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sandro
 */
public class PCB {
    private String estado;
    private Integer prioridad;
    private Integer tick_de_llegada;
    private Integer tamaño;
    private Integer direccion_inicial;
    private Integer program_counter;
    private String condicion;
    private Double porcentaje_segmento_datos;
    private Double porcentaje_variable;
    private Integer numero_interrupciones;
    private Integer interrupciones;
    private String tipo;
    private Integer tick_de_fin;
    private Integer burst_time_inicial;
    private Proceso procesohijo;
    private Proceso procesopadre;

    public PCB(String estado, Integer prioridad, Integer tick_de_llegada, Integer tamaño, String condicion, Double porcentaje_segmento_datos, Integer interrupciones, String tipo, Integer burst_time_inicial) {
        this.estado = estado;
        this.prioridad = prioridad;
        this.tick_de_llegada = tick_de_llegada;
        this.tamaño = tamaño;
        this.condicion = condicion;
        this.porcentaje_segmento_datos = porcentaje_segmento_datos;
        this.porcentaje_variable = 1 - porcentaje_segmento_datos;
        this.numero_interrupciones = interrupciones;
        this.tipo = tipo;
        this.burst_time_inicial = burst_time_inicial;
        this.interrupciones = interrupciones;
    }
    
    public PCB() {
        
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public Integer getTick_de_llegada() {
        return tick_de_llegada;
    }

    public void setTick_de_llegada(Integer tick_de_llegada) {
        this.tick_de_llegada = tick_de_llegada;
    }

    public Integer getTamaño() {
        return tamaño;
    }

    public void setTamaño(Integer tamaño) {
        this.tamaño = tamaño;
    }

    public Integer getDireccion_inicial() {
        return direccion_inicial;
    }

    public void setDireccion_inicial(Integer direccion_inicial) {
        this.direccion_inicial = direccion_inicial;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Double getPorcentaje_segmento_datos() {
        return porcentaje_segmento_datos;
    }

    public void setPorcentaje_segmento_datos(Double porcentaje_segmento_datos) {
        this.porcentaje_segmento_datos = porcentaje_segmento_datos;
    }

    public Integer getNumero_interrupciones() {
        return numero_interrupciones;
    }

    public void setNumero_interrupciones(Integer numero_interrupciones) {
        this.numero_interrupciones = numero_interrupciones;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getTick_de_fin() {
        return tick_de_fin;
    }

    public void setTick_de_fin(Integer tick_de_fin) {
        this.tick_de_fin = tick_de_fin;
    }

    public Integer getBurst_time_inicial() {
        return burst_time_inicial;
    }

    public void setBurst_time_inicial(Integer burst_time_inicial) {
        this.burst_time_inicial = burst_time_inicial;
    }

    public Integer getProgram_counter() {
        return program_counter;
    }

    public void setProgram_counter(Integer program_counter) {
        this.program_counter = program_counter;
    }

    public Proceso getProcesohijo() {
        return procesohijo;
    }

    public void setProcesohijo(Proceso procesohijo) {
        this.procesohijo = procesohijo;
    }

    public Proceso getProcesopadre() {
        return procesopadre;
    }

    public void setProcesopadre(Proceso procesopadre) {
        this.procesopadre = procesopadre;
    }

    public Double getPorcentaje_variable() {
        return porcentaje_variable;
    }

    public void setPorcentaje_variable(Double porcentaje_variable) {
        this.porcentaje_variable = porcentaje_variable;
    }

    public Integer getInterrupciones() {
        return interrupciones;
    }

    public void setInterrupciones(Integer interrupciones) {
        this.interrupciones = interrupciones;
    }
    
    
    
}
