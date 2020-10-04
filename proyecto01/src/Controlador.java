/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sandro
 */
public class Controlador {
    private Integer Codigo;
    private DispositivoES Dispositivo;
    private ArrayList<Interrupciones> cola_interrupciones;
    private Planificador_Interrupciones Planificador;

    public Controlador(Integer Codigo, DispositivoES Dispositivo, ArrayList<Interrupciones> cola_interrupciones) {
        this.Codigo = Codigo;
        this.Dispositivo = Dispositivo;
        this.cola_interrupciones = cola_interrupciones;
    }

    public Integer getCodigo() {
        return Codigo;
    }

    public void setCodigo(Integer Codigo) {
        this.Codigo = Codigo;
    }

    public DispositivoES getDispositivo() {
        return Dispositivo;
    }

    public void setDispositivo(DispositivoES Dispositivo) {
        this.Dispositivo = Dispositivo;
    }

    public ArrayList<Interrupciones> getCola_interrupciones() {
        return cola_interrupciones;
    }

    public void setCola_interrupciones(ArrayList<Interrupciones> cola_interrupciones) {
        this.cola_interrupciones = cola_interrupciones;
    }

    public Planificador_Interrupciones getPlanificador() {
        return Planificador;
    }

    public void setPlanificador(Planificador_Interrupciones Planificador) {
        this.Planificador = Planificador;
    }
    
     Object[] options1 = {};
    JTextField textField = new JTextField(10);
    Boolean continuar = true;
    
     KeyListener eventoTecla = new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent e) {
               
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == 'c') {
                    continuar = true;
                    JOptionPane.showMessageDialog(null, "Continua");
                    textField.setText("");
                    JOptionPane.getRootFrame().dispose();
                    e.setKeyChar(' ');

                }
                if (e.getKeyChar() == 'x') {
                    continuar = false;
                    JOptionPane.showMessageDialog(null, "Interrupcion cancelada");
                    textField.setText("");
                    JOptionPane.getRootFrame().dispose();
                    e.setKeyChar(' ');
                }
                
            }
        };
    public Interrupciones EjecutarInterrupciones(VerInterrupciones interrupciones, DefaultTableModel modeloint, Integer tick){
        Integer time = 0;
        textField.addKeyListener(eventoTecla);  
        Interrupciones interrupcion = Planificador.planificarInterrupciones(tick, this.cola_interrupciones);

        if (this.Codigo == 9) {
            if (interrupcion != null) {

                int pid = interrupcion.getProceso().getPid();

                JPanel panel = new JPanel();
                
                panel.add(new JLabel("Presione C para continuar, X para cancelar   Proceso: " + String.valueOf(pid)));
                panel.add(textField);
                
                
                int result = JOptionPane.showOptionDialog(null, panel, "Interrupcion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null); 
               
                textField.addKeyListener(eventoTecla);
                
                textField.setText("");
                JOptionPane.getRootFrame().dispose();

                if (interrupcion.getEstado() == "Pendiente") {
                    if (continuar) {
                        interrupcion.setTime(0);
                    } else {
                        interrupcion.setTime(-1);
                    }
                }
                if ((interrupcion.getTime() == -1 || interrupcion.getTime() == 0) && interrupcion.getEstado() == "Pendiente") {
                    interrupcion.PendienteACompletado(interrupciones, modeloint, tick);
                    //this.BloqueadoAListo(verprocesos, modelo, tick, this.cola_interrupciones.get(i).getProceso());
                }
                return interrupcion;
            } else {
                return null;
            }
        } else {
            if (interrupcion != null) {
                if (interrupcion.getEstado() == "Pendiente") {
                    time = interrupcion.getTime();
                    interrupcion.setTime(time - 1);
                }
                if (interrupcion.getTime() == 0 && interrupcion.getEstado() == "Pendiente") {
                    interrupcion.PendienteACompletado(interrupciones, modeloint, tick);
                    //this.BloqueadoAListo(verprocesos, modelo, tick, this.cola_interrupciones.get(i).getProceso());
                }
                return interrupcion;
            } else {
                return null;
            }
        }
        
    }
}
