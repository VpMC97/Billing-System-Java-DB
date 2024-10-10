
package Modelo;

import java.io.Serializable;

public class Proveedor_3597 implements Serializable{
    private int codigo;
    private String nombre;
    private String NIT;
    private String dirección;
    private String telefono;

    public Proveedor_3597() {
    }

    public Proveedor_3597(int codigo, String nombre, String NIT, String dirección, String telefono) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.NIT = NIT;
        this.dirección = dirección;
        this.telefono = telefono;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "codigo=" + codigo + ", nombre=" + nombre + 
                ", NIT=" + NIT + ", direcci\u00f3n=" + dirección +
                ", telefono=" + telefono;
    }
    
    
}
