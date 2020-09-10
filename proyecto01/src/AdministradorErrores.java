
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
public class AdministradorErrores {

    public AdministradorErrores() {
    }
    
    public void VerificarActividad(VerProcesos verprocesos, DefaultTableModel modelo, Integer tick, Proceso proceso){
        if(proceso.getPcb().getCondicion()=="Errado"){
            proceso.getPcb().setEstado("Terminado");
            proceso.getPcb().setTick_de_fin(tick);
            Object[] miTabla = new Object[11];
            miTabla[0]=tick;
            miTabla[1]=proceso.getPid();
            miTabla[2]=proceso.getPcb().getEstado();
            miTabla[3]=proceso.getPcb().getPrioridad();
            miTabla[4]=proceso.getPcb().getTick_de_llegada();
            miTabla[5]=proceso.getBurst_time();
            miTabla[6]=proceso.getPcb().getTamaño();
            miTabla[7]=proceso.getPcb().getNumero_interrupciones();
            miTabla[8]=proceso.getPcb().getCondicion();
            miTabla[9]=proceso.getPcb().getDireccion_inicial();
            miTabla[10]=proceso.getPcb().getProgram_counter();
            modelo.addRow(miTabla);
            verprocesos.tbColaProcesos.setModel(modelo);
        }
    }
}