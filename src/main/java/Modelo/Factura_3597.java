
package Modelo;

import java.io.Serializable;

public class Factura_3597 implements Serializable{
    private int codigo;
    private String fecha;
    private int IDempleado;
    private int NITcliente;

    public Factura_3597() {
    }

    public Factura_3597(int codigo, String fecha, int IDempleado, int NITcliente) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.IDempleado = IDempleado;
        this.NITcliente = NITcliente;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIDempleado() {
        return IDempleado;
    }

    public void setIDempleado(int IDempleado) {
        this.IDempleado = IDempleado;
    }

    public int getNITcliente() {
        return NITcliente;
    }

    public void setNITcliente(int NITcliente) {
        this.NITcliente = NITcliente;
    }
    
    
}
