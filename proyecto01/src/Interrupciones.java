/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sandro
 */
public class Interrupciones {
    private Integer codigo;
    private Integer tick;
    private String  estado;
    private Integer time;
    private Proceso proceso;
    private String  descripcion;
    private Integer prioridad;

    public Interrupciones(Integer codigo, Integer tick, String estado, Integer time, Proceso proceso, Integer prioridad) {
        this.codigo = codigo;
        this.tick = tick;
        this.estado = estado;
        this.time = time;
        this.proceso = proceso;
        this.prioridad = prioridad;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getTick() {
        return tick;
    }

    public void setTick(Integer tick) {
        this.tick = tick;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }
    
    public void PendienteACompletado(VerInterrupciones interrupciones, DefaultTableModel modeloint, Integer tick){
        if (this.time <= 0){
            this.estado = "Completado";
            Object[] miTabla = new Object[6];
            miTabla[0]=tick;
            miTabla[1]=this.getProceso().getPid();
            miTabla[2]=this.getTick();
            miTabla[3]=this.getTime();
            miTabla[4]=this.getDescripcion();
            miTabla[5]=this.getEstado();
            modeloint.addRow(miTabla);
            interrupciones.tbInterrupciones.setModel(modeloint);
        }
    }
    
    public void Descripcion(){
        switch (codigo){
            case 0:
                    this.descripcion = "División por cero";
                    break;
                    
            case 4:
                    this.descripcion = "Operación de desbordamiento";
                    break;
                    
            case 8:
                    this.descripcion = "Timer";
                    break;
                    
            case 9:
                    this.descripcion = "Lectura de Operación de Teclado";
                    break;
                    
            case 14:
                    this.descripcion = "Lectura de CD-ROM";
                    break;
                    
            case 15:
                    this.descripcion = "Operación de Salida de Impresora";
                    break;
                    
            case 116:
                    this.descripcion = "Lectura de Operación de Mouse";
                    break;
                    
            case 118:
                    this.descripcion = "Operación de Disco duro";
                    break;
        }
    }
    
}
