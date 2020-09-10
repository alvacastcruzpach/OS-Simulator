/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sandro
 */
public class EstadísticasProcesos {
    private Integer tiempo_desperdiciado;
    private Integer tick;

    public EstadísticasProcesos() {
        tiempo_desperdiciado = 0;
    }

    public Integer getTiempo_desperdiciado() {
        return tiempo_desperdiciado;
    }

    public void setTiempo_desperdiciado(Integer tiempo_desperdiciado) {
        this.tiempo_desperdiciado = tiempo_desperdiciado;
    }

    public Integer getTick() {
        return tick;
    }

    public void setTick(Integer tick) {
        this.tick = tick;
    }
    
    public float imprimirUsodeCPU(){
        float uso_cpu = 100 - (float)(tiempo_desperdiciado)*100/tick;
        System.out.println("% de Uso de CPU = " + uso_cpu + "%");
        return uso_cpu;
    }
    
    public float imprimirTiempoDeEsperaPromedio(ColaDeProcesos procesos){
        float tiempo_de_espera = 0;
        for (int i = 0; i < procesos.getCola_de_procesos().size(); i++){
            tiempo_de_espera = tiempo_de_espera + procesos.getCola_de_procesos().get(i).getPcb().getTick_de_fin() - procesos.getCola_de_procesos().get(i).getPcb().getBurst_time_inicial()- procesos.getCola_de_procesos().get(i).getPcb().getTick_de_llegada();
        }
        tiempo_de_espera = tiempo_de_espera/procesos.getCola_de_procesos().size();
        System.out.println("Tiempo de espera promedio = " + tiempo_de_espera/procesos.getCola_de_procesos().size() + " ticks");
        return tiempo_de_espera;
    }
    
    public float imprimirTiempoDeRespuestaPromedio(ColaDeProcesos procesos){
        float tiempo_de_respuesta = 0;
        for (int i = 0; i < procesos.getCola_de_procesos().size(); i++){
            tiempo_de_respuesta = tiempo_de_respuesta + procesos.getCola_de_procesos().get(i).getPcb().getTick_de_fin() - procesos.getCola_de_procesos().get(i).getPcb().getTick_de_llegada();
        }
        tiempo_de_respuesta = tiempo_de_respuesta/procesos.getCola_de_procesos().size();
        System.out.println("Tiempo de respuesta promedio = " + tiempo_de_respuesta/procesos.getCola_de_procesos().size() + " ticks");
        return tiempo_de_respuesta;
    }
}
