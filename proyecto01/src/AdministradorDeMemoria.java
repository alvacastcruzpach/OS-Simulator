
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
                Object[] miTabla = new Object[12];
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
                Integer fin1 = null;
                if(proceso.getPcb().getDireccion_inicial()!=null){
                    fin1 = (Integer) proceso.getPcb().getDireccion_inicial() + (Integer) proceso.getPcb().getTamaño();
                    miTabla[10]= fin1;
                }
                miTabla[10] = fin1;
                miTabla[11]=proceso.getPcb().getProgram_counter();
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
            Object[] miTabla = new Object[12];
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
            Integer fin1 = null;
            if(proceso.getPcb().getDireccion_inicial()!=null){
                fin1 = (Integer) proceso.getPcb().getDireccion_inicial() + (Integer) proceso.getPcb().getTamaño();
                miTabla[10]= fin1;
            }
            miTabla[10] = fin1;
            miTabla[11]=proceso.getPcb().getProgram_counter();
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
    
    public Integer contarHuecos(MapaDeBits mapa, MemoriaPrincipal memoria){
        Integer huecos = 0;
        Boolean control = true;
        for(int i = 0; i < memoria.getTamaño()/16; i++){
            for(int j = 0; j < 16; j++){
                if(mapa.getMapa_de_bits()[i][j]==0 && control){
                    huecos++;
                    control = false;
                }
                if(mapa.getMapa_de_bits()[i][j]==1 && !control){
                    control = true;
                }
            }
        }
        
        return huecos; 
    }
    
}
