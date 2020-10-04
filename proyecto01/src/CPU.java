
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author sandro
 */
public class CPU {
    private Integer program_counter; 

    public CPU() {
    }

    public Integer getProgram_counter() {
        return program_counter;
    }

    public void setProgram_counter(Integer program_counter) {
        this.program_counter = program_counter;
    }
    
    public void ejecutarProceso(Integer tick, Proceso proceso){
        this.ProcesoErrado(proceso);
        System.out.println("Ejecutando...");
        System.out.println(proceso.getPcb().getCondicion());
        System.out.println(proceso.getPcb().getEstado());
        if(proceso.getPcb().getCondicion()!="Errado" && proceso.getPcb().getEstado() != "Bloqueado"){
            if(proceso.getPcb().getProcesohijo()==null){
                this.crearProcesoHijo(proceso);
            }
            program_counter++;
            proceso.getPcb().getProcesohijo().setBurst_time(proceso.getBurst_time() - 1);
            proceso.getPcb().getProcesohijo().getPcb().setProgram_counter(program_counter);
        }
    }
    
    public void ProcesoErrado(Proceso proceso){
        if (Math.random()<0.005){
            System.out.println("Proceso errado");
            proceso.getPcb().setCondicion("Errado");
        }else{
            //
        }
    }
    
    public Interrupciones LanzarInterrupcion(VerProcesos verprocesos, VerInterrupciones interrupciones, DefaultTableModel modelo, DefaultTableModel modeloint, Integer tick, Proceso proceso){
        Integer[] codigos = {0, 4, 8, 9, 14, 15, 116, 118, 9, 14, 15, 116, 118, 9, 14, 15, 116, 118, 9, 14, 15, 116, 118};
        Integer codigo = (int)(Math.random()*23);
        System.out.println(codigo);
        String estado = "Pendiente";
        Integer time = (int)(15*(proceso.getBurst_time()/30 + Math.random())/2)+5;
        if(codigos[codigo]==0 || codigos[codigo] == 4 || codigos[codigo] ==8){
            time = 1;
        }
        Integer prioridad = (int)((Math.random()*4) + 1);
        Interrupciones interrupcion = new Interrupciones(codigos[codigo], tick, estado, time, proceso, prioridad);
        //IF interrupcion.codigo = 9, entonces LANZAR VENTANA EMERGENTE
        interrupcion.Descripcion();
        Object[] miTablaInt = new Object[6];
            miTablaInt[0]=tick;
            miTablaInt[1]=interrupcion.getProceso().getPid();
            miTablaInt[2]=interrupcion.getTick();
            miTablaInt[3]=interrupcion.getTime();
            miTablaInt[4]=interrupcion.getDescripcion();
            miTablaInt[5]=interrupcion.getEstado();
            modeloint.addRow(miTablaInt);
            interrupciones.tbInterrupciones.setModel(modeloint);
        if(proceso.getPcb().getProcesohijo()==null){
            this.crearProcesoHijo(proceso);
        }
        this.copiarProcesoHijoenPadre(proceso.getPcb().getProcesohijo());
        proceso.getPcb().setProgram_counter(program_counter + 1);
        proceso.getPcb().setEstado("Bloqueado");
        Object[] miTabla = new Object[13];
        miTabla[0]=proceso.getCorrida();
        miTabla[1]=tick;
        miTabla[2]=proceso.getPid();
        miTabla[3]=proceso.getPcb().getEstado();
        miTabla[4]=proceso.getPcb().getPrioridad();
        miTabla[5]=proceso.getPcb().getTick_de_llegada();
        miTabla[6]=proceso.getBurst_time();
        miTabla[7]=proceso.getPcb().getTamaño();
        miTabla[8]=proceso.getPcb().getNumero_interrupciones();
        miTabla[9]=proceso.getPcb().getCondicion();
        miTabla[10]=proceso.getPcb().getDireccion_inicial();
        Integer fin1 = null;
        if(proceso.getPcb().getDireccion_inicial()!=null){
            fin1 = (Integer) proceso.getPcb().getDireccion_inicial() + (Integer) proceso.getPcb().getTamaño();
            miTabla[11]= fin1;
        }
        miTabla[11] = fin1;
        miTabla[12]=proceso.getPcb().getProgram_counter();
        modelo.addRow(miTabla);
        verprocesos.tbColaProcesos.setModel(modelo);
        return interrupcion;
    }
    
    public Proceso crearProcesoHijo(Proceso proceso){
        System.out.println("Creando proceso hijo");
        Proceso procesohijo = new Proceso();
        procesohijo = proceso;
        procesohijo.getPcb().setProcesopadre(proceso);
        proceso.getPcb().setProcesohijo(procesohijo);
        System.out.println("Proceso hijo: " + procesohijo.getBurst_time() +" "+ procesohijo.getPid());
        return procesohijo;
    }
    
    public void copiarProcesoHijoenPadre(Proceso procesohijo){
        procesohijo.getPcb().setProcesopadre(procesohijo);
    }
}
