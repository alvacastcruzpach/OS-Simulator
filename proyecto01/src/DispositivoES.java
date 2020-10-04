/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sandro
 */
public class DispositivoES {
    private Integer Codigo;
    private String Nombre;

    public DispositivoES(Integer Codigo, String Nombre) {
        this.Codigo = Codigo;
        this.Nombre = Nombre;
    }

    public Integer getCodigo() {
        return Codigo;
    }

    public void setCodigo(Integer Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
}
