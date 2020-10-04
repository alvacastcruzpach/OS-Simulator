
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
public class AdministradorDeMemoria {
    String Metodo_De_Asignacion;

    public AdministradorDeMemoria(String Metodo_De_Asignacion) {
        this.Metodo_De_Asignacion = Metodo_De_Asignacion;
    }

    public String getMetodo_De_Asignacion() {
        return Metodo_De_Asignacion;
    }

    public void setMetodo_De_Asignacion(String Metodo_De_Asignacion) {
        this.Metodo_De_Asignacion = Metodo_De_Asignacion;
    }

    public Integer asignarMemoriaProceso(VerProcesos verprocesos, DefaultTableModel modelo, MapaDeBits mapa, MemoriaPrincipal memoria, Proceso proceso, Integer tick){
        Integer inicio = 0;
        Integer fin = 0;
        Integer tamaño = -1;
        Integer min_tamaño = 4000;
        Integer max_tamaño = 0;
        Boolean control = true;
        Integer tiempo_busqueda = 0;
        switch(this.Metodo_De_Asignacion){
            case("First Fit"):
                for(int i = 0; i < memoria.getTamaño()/16; i++){
                    if(proceso.getPcb().getTamaño()<=(fin-inicio) || proceso.getPcb().getTamaño() <= tamaño){
                        break;
                    }
                    for(int j = 0; j < 16; j++){
                        tiempo_busqueda++;
                        if(mapa.getMapa_de_bits()[i][j]==0 && control){
                            inicio = 16*i + j;
                            tamaño++;
                            control = false;
                        }
                        if(!control){
                            tamaño++;
                        }
                        if(mapa.getMapa_de_bits()[i][j]==1 && !control){
                            fin = 16*i + j;
                            tamaño = -1;
                            control = true;
                        }
                        if(proceso.getPcb().getTamaño()<=(fin-inicio) || proceso.getPcb().getTamaño() <= tamaño){
                            fin = 16*i + j + 1;
                            break;
                        }
                    }
                }
                break;
                
            case("Best Fit"):
                Integer iniciox = 0;
                Integer finx = 0;
                for(int i = 0; i < memoria.getTamaño()/16; i++){
                    for(int j = 0; j < 16; j++){
                        tiempo_busqueda++;
                        if(mapa.getMapa_de_bits()[i][j]==0 && control){
                            iniciox = 16*i + j;
                            tamaño++;
                            control = false;
                        }
                        if(!control){
                            tamaño++;
                        }
                        if(mapa.getMapa_de_bits()[i][j]==1 && !control){
                            finx = 16*i + j;
                            tamaño = -1;
                            control = true;
                        }
                    }
                    if((proceso.getPcb().getTamaño()<=(finx-iniciox)) && ((finx-iniciox) <= min_tamaño)){
                        min_tamaño = tamaño;
                        inicio = iniciox;
                        fin = iniciox + proceso.getPcb().getTamaño();
                    }
                }
                break;
                
            case("Worst Fit"):
                Integer inicioy = 0;
                Integer finy = 0;
                for(int i = 0; i < memoria.getTamaño()/16; i++){
                    for(int j = 0; j < 16; j++){
                        tiempo_busqueda++;
                        if(mapa.getMapa_de_bits()[i][j]==0 && control){
                            inicioy = 16*i + j;
                            tamaño++;
                            control = false;
                        }
                        if(!control){
                            tamaño++;
                        }
                        if(mapa.getMapa_de_bits()[i][j]==1 && !control){
                            finy = 16*i + j;
                            tamaño = -1;
                            control = true;
                        }
                    }
                    if((proceso.getPcb().getTamaño()<=(finy-inicioy)) && ((finy-inicioy) >= max_tamaño)){
                        max_tamaño = tamaño;
                        inicio = inicioy;
                        fin = inicioy + proceso.getPcb().getTamaño();
                    }
                }
                break;
        }
        
        System.out.println(fin);
        System.out.println(inicio);
        if((fin-inicio)>=proceso.getPcb().getTamaño()){
            proceso.getPcb().setDireccion_inicial(inicio);
            proceso.getPcb().setProgram_counter(proceso.getPcb().getDireccion_inicial() + (int)(proceso.getPcb().getTamaño()*proceso.getPcb().getPorcentaje_segmento_datos()));
            if(proceso.getPcb().getEstado() == "Bloqueado" && tick != proceso.getPcb().getTick_de_llegada()){
                proceso.getPcb().setEstado("Listo");
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
            }
            //Asignar en mapa de bits
            for(int i = inicio; i < fin; i++){
               Integer i1 = i/16;
               Integer j1 = i % 16;
               mapa.getMapa_de_bits()[i1][j1] = 1;
            }
            //Asignar en memoria
            for(int i = inicio; i < fin; i++){
               DireccionMemoria direccion = new DireccionMemoria(i, proceso);
               memoria.getMemoria()[i] = direccion;
            }
        }else{
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
            System.out.println("Proceso " + proceso.getPid() + " en espera a que haya espacio en memoria");
        }
        return tiempo_busqueda;
    }
    
    public void desasignarMemoriaProceso(MapaDeBits mapa, MemoriaPrincipal memoria, Proceso proceso){
        if (proceso.getPcb().getEstado()=="Terminado"){
            Integer i1 = 0;
            Integer j1 = 0;
            System.out.println("Desasignando memoria");
            System.out.println(proceso.getPcb().getDireccion_inicial());
            for(int i = proceso.getPcb().getDireccion_inicial(); i < (proceso.getPcb().getDireccion_inicial() + proceso.getPcb().getTamaño()); i++){
                i1 = (Integer)(i/16);
                j1 = i % 16;
                mapa.getMapa_de_bits()[i1][j1] = 0;
            }
        }
    }
    
    public Integer[] asignarMemoriaCola(VerProcesos verprocesos, DefaultTableModel modelo, MapaDeBits mapa, MemoriaPrincipal memoria, ColaDeProcesos cola, Integer tick){
        Integer[] busqueda = {0, 0};
        for(int i = 0; i < cola.getCola_de_procesos().size(); i++){
            if(cola.getCola_de_procesos().get(i).getPcb().getTick_de_llegada()<=tick && cola.getCola_de_procesos().get(i).getPcb().getDireccion_inicial()==null){
                busqueda[0] = busqueda[0] + this.asignarMemoriaProceso(verprocesos, modelo, mapa, memoria, cola.getCola_de_procesos().get(i), tick);
                busqueda[1]++;
                System.out.println("Memoria asignada en tick " + tick);
            }
        }
        return busqueda;
    }
    
    public Integer contarHuecos(VerMemoria vermemoria, DefaultTableModel modelomem, DefaultTableModel modelocap, MapaDeBits mapa, MemoriaPrincipal memoria, Integer tick){
        Integer huecos = 0;
        Integer iniciox = 0;
        Integer finx = 0;
        Integer inicioy = 0;
        Integer finy = 0;
        Integer lleno = 2048;
        Integer vacio = 0;
        Boolean control = true;
        for(int i = 0; i < memoria.getTamaño()/16; i++){
            for(int j = 0; j < 16; j++){
                if(mapa.getMapa_de_bits()[i][j]==0 && control){
                    huecos++;
                    control = false;
                    iniciox=16*i + j;
                    finy=16*i + j;
                    if(inicioy!=0 || finy!=0){
                        Object[] miTabla = new Object[5];
                        miTabla[0] = tick;
                        miTabla[1]=memoria.getMemoria()[iniciox-1].getProceso().getPid();
                        miTabla[2]=inicioy;
                        miTabla[3]=finy;
                        miTabla[4]="Lleno";
                        modelomem.addRow(miTabla);
                        vermemoria.tbMemoria.setModel(modelomem);
                        lleno = lleno + finy - inicioy;
                    }
                }
                if(mapa.getMapa_de_bits()[i][j]==1 && !control){
                    control = true;
                    Object[] miTabla = new Object[5];
                    finx=16*i + j;
                    inicioy=16*i + j;
                    miTabla[0]=tick;
                    miTabla[1]="-";
                    miTabla[2]=iniciox;
                    miTabla[3]=finx;
                    miTabla[4]="Vacío";
                    modelomem.addRow(miTabla);
                    vermemoria.tbMemoria.setModel(modelomem);
                    vacio = vacio + finx - iniciox;
                }
            }
        }
        Object[] miTabla = new Object[5];
        miTabla[0]=tick;
        miTabla[1]="Sistema Operativo";
        miTabla[2]=2048;
        miTabla[3]=4096;
        miTabla[4]="Lleno";
        modelomem.addRow(miTabla);
        vermemoria.tbMemoria.setModel(modelomem);
        
        Object[] miTabla2 = new Object[3];
        miTabla2[0]=tick;
        miTabla2[1]=vacio;
        miTabla2[2]=lleno;
        modelocap.addRow(miTabla2);
        vermemoria.tbCapacidad.setModel(modelocap);
        
        return huecos; 
    }
    
}
